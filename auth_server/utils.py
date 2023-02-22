from passlib.context import CryptContext
import os
from datetime import datetime, timedelta
from sqlalchemy.orm import Session
import os.path
from typing import Union, List
import crud, schemas
from jose import jwt, JWTError
from fastapi.security import OAuth2PasswordBearer
from fastapi import Depends, HTTPException, status
from fastapi.responses import JSONResponse
from urllib.parse import quote
import redis
from secrets import token_bytes
from base64 import b64encode
from config import rd
import yagmail

# REFRESH_TOKEN_EXPIRE_MINUTES = 60 * 24 * 7 # 7 days
ALGORITHM = "HS256"
# JWT_SECRET_KEY = os.environ['JWT_SECRET_KEY']     # should be kept secret
# JWT_REFRESH_SECRET_KEY = os.environ['JWT_REFRESH_SECRET_KEY']      # should be kept secret

# to get a string like this run:
# openssl rand -hex 32
SECRET_KEY = "07f7576d46d2871ba587ceabd235f9234967ac12033c47d8047662c21c59732b"
SECRET_REFRESH_KEY = "7c49dfce5312a8a12d4e159f464b8d1ac7f4af35c634fd45168859c4d0bd0cf9"
ACCESS_TOKEN_EXPIRE_MINUTES = 10 # 10분
REFRESH_TOKEN_EXPIRE_MINUTES = 1440*14 # 2주

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token")
password_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

def get_hashed_password(password: str) -> str:
    return password_context.hash(password)


def verify_password(password: str, hashed_pass: str) -> bool:
    return password_context.verify(password, hashed_pass)


def authenticate_user(db: Session, email : str, password : str):
    user = crud.get_user_by_email(db, email)
    if not user:
        return False
    if not verify_password(password, user.hashed_password):
        return False   
    return user

def create_access_token(data: dict, expires_delta: Union[timedelta, None] = None):
    to_encode = data.copy() # dict
    if expires_delta:
        expire = datetime.utcnow() + expires_delta # 아니면 지정
    else:
        expire = datetime.utcnow() + timedelta(minutes=5) # 기본이 15분
    to_encode.update({"exp": expire})
    encoded_jwt = jwt.encode(to_encode, SECRET_KEY, algorithm=ALGORITHM)
    return encoded_jwt  # encoded_jwt == access_token??

def create_refresh_token(data: dict, expires_delta:  Union[timedelta, None] = None):
    to_encode = data.copy() # dict
    if expires_delta:
        expire = datetime.utcnow() + expires_delta # 아니면 지정
    else:
        expire = datetime.utcnow() + timedelta(minutes=1440) # 기본이 1일
    to_encode.update({"exp": expire})
    encoded_jwt = jwt.encode(to_encode, SECRET_KEY, algorithm=ALGORITHM)
    return encoded_jwt  # encoded_jwt == refresh_token??

def decode_token(token: str = Depends(oauth2_scheme)):
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM]) # jwt를 decode
        email : str = payload.get("sub") 
        return email
    except jwt.ExpiredSignatureError:
        raise HTTPException(status_code=401, detail="Token expired")
    except jwt.InvalidTokenError:
        raise HTTPException(status_code=401, detail="Invalid token")

def get_current_user(token: str = Depends(oauth2_scheme),
                     db = Session):
    """
        jwt를 decode를 하기 위해서는
        token, SECRET_KEY, algorihms가 필요한가?
    
        decoded된 payload에서 sub를 가져온다 = dict로된 payload에서 sub라는 key를 사용하여 value를 가져온다.
    
        get method가 None를 리턴한다면
        Error raise 그렇지 않다면? token_data에 TokenData(username)을 넣는다.. ok
    
        DB와 만들어둔 TokenData Username을 parameter로 user객체를 get_user()
    """
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    ) # 401 errors
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM]) # jwt를 decode
        email : str = payload.get("sub") 
        
        if email is None:
            raise credentials_exception
        token_data = schemas.TokenData(email=email)
        
    except JWTError:
        raise credentials_exception
    user = crud.get_user_by_email(db, email=token_data.email)
    
    if user is None:
        raise credentials_exception

    return user

def check_my_token(email: str, token: str, db: Session):
    user = crud.get_user_by_email(db, email=email)
    if user is None:
        return False
    print(user)
    mytoken = user.email + "sgd"
    if token == mytoken:
        return True
    else:
        return False

def user_from_token(token: str = Depends(oauth2_scheme),
                     db = Session):
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    ) # 401 errors
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM]) # jwt를 decode
        email : str = payload.get("sub") 
    except JWTError:
        raise credentials_exception
    if email is None:
        raise credentials_exception
    token_data = schemas.TokenData(email=email)
        
    user = crud.get_user_by_email(db, email=token_data.email)
    
    if user is None:
        raise credentials_exception
    return user

def is_valid_accessToken(token: str = Depends(oauth2_scheme)):
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM]) # jwt를 decode
        return True
    except JWTError:
        return False

def email_auth(email):
    token = b64encode(token_bytes(12)).decode() + b64encode(email.encode('ascii')).decode('ascii')
    token = token.replace("/", "")
    encoded_token = quote(token)
    try:
        yag = yagmail.SMTP("wlsdn2749@gmail.com", oauth2_file="./settings/oauth2_creds.json")
        yag.send(to=email, subject="당근마켓 Clone Projects 이메일 인증을 해주세요!", 
            contents = ["<h1>이 링크는 10분간 유효합니다.</h1>", f'<a href="http://localhost:8000/auth/email_verify_token/{encoded_token}/">인증을 위해서 이것을 클릭 해주세요</a>']
        )
        rd.set(token, email, timedelta(minutes=10))
        return JSONResponse(
            content = {'status': 'SUCCESS', 'detail': 'a mail is sent'},
            status_code = status.HTTP_202_ACCEPTED
        )
    except:
        return JSONResponse(
            content = {'status': 'FAILED', 'detail': 'mail is not sent'},
            status_code = status.HTTP_400_BAD_REQUEST
        )
        