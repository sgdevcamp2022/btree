from passlib.context import CryptContext
import os
from datetime import datetime, timedelta
from jose import jwt
from sqlalchemy.orm import Session
import os.path
from typing import Union, List
# from googleapiclient.discovery import build
# from google_auth_oauthlib.flow import InstalledAppFlow
# from google.auth.transport.requests import Request
# from google.oauth2.credentials import Credentials
# from googleapiclient import errors
# from email.message import EmailMessage
# import base64
import crud, schemas
from jose import jwt, JWTError
from fastapi.security import OAuth2PasswordBearer
from fastapi import Depends, HTTPException, status

# REFRESH_TOKEN_EXPIRE_MINUTES = 60 * 24 * 7 # 7 days
ALGORITHM = "HS256"
# JWT_SECRET_KEY = os.environ['JWT_SECRET_KEY']     # should be kept secret
# JWT_REFRESH_SECRET_KEY = os.environ['JWT_REFRESH_SECRET_KEY']      # should be kept secret

# to get a string like this run:
# openssl rand -hex 32
SECRET_KEY = "07f7576d46d2871ba587ceabd235f9234967ac12033c47d8047662c21c59732b"
SECRET_REFRESH_KEY = "7c49dfce5312a8a12d4e159f464b8d1ac7f4af35c634fd45168859c4d0bd0cf9"
ACCESS_TOKEN_EXPIRE_MINUTES = 5
REFRESH_TOKEN_EXPIRE_MINUTES = 1440

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
    mytoken = str(user.userId) + user.email + "sgd"
    if token == mytoken:
        return True
    else:
        return False
    

# def gmail_authenticate():
#     SCOPES = ['https://mail.google.com/']
#     creds = None
#     if os.path.exists('token.json'):
#         creds = Credentials.from_authorized_user_file('token.json', SCOPES)
#     if not creds or not creds.valid:
#         if creds and creds.expired and creds.refresh_token:
#             creds.refresh(Request())
#         else:
#             flow = InstalledAppFlow.from_client_secrets_file('credentials.json', SCOPES)
#             creds = flow.run_local_server(port=0)
#         with open('token.json', 'w') as token:
#             token.write(creds.to_json())
#     return build('gmail', 'v1', credentials=creds)

# def create_message(sender, to, subject, message_text):
#     message = EmailMessage()
#     message["From"] = sender
#     message["To"] = to.split(",")
#     message["Subject"] = subject
#     message.set_content(message_text)
#     return {'raw': base64.urlsafe_b64encode(message.as_bytes()).decode('utf8')}

# def send_message(service, user_id, message):
#     try:
#         message = service.users().messages().send(userId=user_id, body=message).execute()
#         print('Message Id: %s' % message['id'])
#         return message
#     except errors.HttpError as error:
#         print('An error occurred: %s' % error)

# def mail(to, subject, content):
#     service = gmail_authenticate()
#     message = create_message("보내는사람", "받는사람", "제목", "내용")
#     send_message(service, "me", message)