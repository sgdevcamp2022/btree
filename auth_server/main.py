from fastapi import Depends, FastAPI, HTTPException, status
from fastapi.security import OAuth2PasswordBearer,OAuth2PasswordRequestForm
from datetime import datetime, timedelta
from sqlalchemy.orm import Session
# from jose import jwt, JWTError
from pydantic import BaseModel
from fastapi.encoders import jsonable_encoder
from fastapi.middleware.cors import CORSMiddleware
# from passlib.context import CryptContext
import crud, models, schemas, utils
from database import SessionLocal, engine
from config import rd
from redis_om import Migrator
import router

models.Base.metadata.create_all(bind=engine)

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token")
    
app = FastAPI()

origins = {
    "http://localhost",
    "http://localhost:3000",
}

app.add_middleware(
    CORSMiddleware,
    allow_origins = origins,
    allow_credentials = True,
    allow_methods = ["*"],
    allow_headers = ["*"],
)

# pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

# Dependency

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.post("/token", response_model=schemas.RefreshIncludedToken)
async def login_for_access_token(form_data: OAuth2PasswordRequestForm = Depends(),
                                 db: Session = Depends(get_db)):                                 
    user = utils.authenticate_user(db, form_data.username, form_data.password)
    if not user or not user.is_active:
        raise HTTPException(
            status_code = status.HTTP_401_UNAUTHORIZED,
            detail="Incorrect username or password",
            headers={"WWW-Authenticate" : "Bearer"},
        )
    # if Users?
    
    refresh_token = utils.create_refresh_token(
        data = {"sub": user.email},
        expires_delta=timedelta(minutes=utils.REFRESH_TOKEN_EXPIRE_MINUTES)
    )
    rd.set(refresh_token, user.userId, timedelta(minutes=3))
    access_token = utils.create_access_token(
        data={"sub": user.email},
        expires_delta=timedelta(minutes=utils.ACCESS_TOKEN_EXPIRE_MINUTES)
    )
    return {"refresh_token": refresh_token, "access_token" : access_token, "token_type" : "bearer"}

@app.post("/generate_access_token/", response_model=schemas.Token)
async def re_issue_access_token(token: str = Depends(oauth2_scheme), # access_token
                                refresh_token: str = None,
                                db: Session = Depends(get_db)):
    if not utils.is_valid_accessToken(token):
        if rd.exists(refresh_token):
            userId = rd.get(refresh_token)
            user = crud.get_user(db, user_id=userId)
            access_token = utils.create_access_token(
                data={"sub": user.email},
                expires_delta=timedelta(minutes=utils.ACCESS_TOKEN_EXPIRE_MINUTES)
            )
            return {"access_token": access_token, "token_type": "bearer"}
        else:
            raise HTTPException(
                status_code=status.HTTP_401_UNAUTHORIZED,
                detail="Generate token miss : RefreshToken not found",
                headers={"WWW-Authenticate": "Bearer"},
            ) # 401 errors
    else:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="accessToken is valid, this is bad request",
            headers={"WWW-Authenticate": "Bearer"},
        )
    

@app.post('/register', summary="Create new user", response_model=schemas.User)
async def create_user(data: schemas.UserCreate, db : Session = Depends(get_db)):
    print(data)
    # querying database to check if user already exist
    user = crud.get_user_by_email(db, data.email)
    if user is not None:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail="User with this email already exist"
            )
    user = {
        'email': data.email,
        'hashed_password': utils.get_hashed_password(data.password),
    }
    created_user = crud.create_user(db=db, user=user)
    # register_verify(created_user, db)
    return created_user # id, is_active, UserBase....

@app.post('/register_verify', summary="Token Authentication", response_model=schemas.User)
async def register_verify(tokendata: schemas.TokenDataModel, db : Session = Depends(get_db)):
    mytoken = utils.check_my_token(tokendata.email, tokendata.access_token, db)
    if(mytoken):
        updated_user = crud.update_user_active_by_email(db, tokendata.email)
        return updated_user # id, is_active, UserBase....
    else:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Token is different"
        )

@app.get("/profile/", response_model = schemas.UserProfile)
async def read_profile(token: str = Depends(oauth2_scheme),
                        db: Session = Depends(get_db)):
    if not utils.is_valid_accessToken(token):
        return HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="User with this email already exist"
        )

    user = utils.get_current_user(token, db)
    print(user.nickname, user.email, user.manner_temporature, user.create_at)
    return user

@app.post("/update_nickname", response_model = schemas.UserProfile)
async def update_nickname(token: str = Depends(oauth2_scheme),
                          db: Session = Depends(get_db),
                          new_nickname: str = None):
    if not utils.is_valid_accessToken(token):
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="User with this email already exist"
        )
        
    user = utils.get_current_user(token, db)
    user = crud.update_user_nickname(db,user, new_nickname)
    return user

@app.get("/items/")
async def read_items(token: str = Depends(oauth2_scheme)):
    return {"token": token}

app.include_router(router.router)