from pydantic import BaseModel
from typing import List, Union, Optional
from datetime import datetime
from redis_om import HashModel
from config import redis_db
class Task(HashModel):
    name: str
    description: str

    class Meta:
        database: redis_db
class ItemBase(BaseModel):
    title: str
    description: Union[str, None] = None
class ItemCreate(ItemBase):
    pass

class Item(ItemBase):
    id: int
    owner_id: int

    class Config:
        orm_mode = True

class UserBase(BaseModel):
    email: str

class UserCreate(UserBase):
    password: str

class Token(BaseModel):
    access_token: str
    token_type: str

class RefreshIncludedToken(Token):
    refresh_token: str

class TokenData(BaseModel):
    email: Union[str, None] = None


class TokenDataModel(BaseModel):
    access_token: str
    token_type: str
    email: str    
    
    # class Config:
    #     orm_mode = True

class User(UserBase):
    # id: int
    # is_activate = bool

    class Config:
        orm_mode = True

class UserProfile(UserBase):
    nickname: Optional[str]
    manner_temporature: float
    create_at: datetime

    class Config:
        orm_mode = True
