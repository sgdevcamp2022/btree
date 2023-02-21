from pydantic import BaseModel
from typing import List, Union, Optional
from datetime import datetime

class Task(BaseModel):
    name: str
    description: str

    class Config:
        orm_mode = True

class RefreshToken(BaseModel):
    id: int
    refresh_token: str

    class Config:
        orm_mode = True
    

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

    class Config:
        orm_mode = True
class UserCreate(UserBase):
    password: str

class Token(BaseModel):
    access_token: str
    token_type: str
class email_token(BaseModel):
    token: str


class RefreshToken(BaseModel):
    refresh_token: str
class TokenData(BaseModel):
    email: Union[str, None] = None

class RefreshIncludedToken(Token):
    refresh_token: str

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
    locate: str

    class Config:
        orm_mode = True

class UserNickname(BaseModel):
    new_nickname: str

    class Config:
        orm_mode = True


class UserLocate(BaseModel):
    new_locate: str

    class Config:
        orm_mode = True
        
class UserCategory(BaseModel):
    digitals: Optional[bool] = True
    appliances: Optional[bool] = True
    funitures: Optional[bool] = True
    livings: Optional[bool] = True
    kids:  Optional[bool] = True
    kid_books: Optional[bool] = True
    woman_clothes: Optional[bool] = True
    woman_things: Optional[bool] = True
    man_things: Optional[bool] = True
    beauty: Optional[bool] = True
    sports: Optional[bool] = True
    hobby_game_recodes: Optional[bool] = True
    books: Optional[bool] = True
    tickets: Optional[bool] = True
    processed_food: Optional[bool] = True
    pets: Optional[bool] = True
    plants: Optional[bool] = True
    others: Optional[bool] = True
    buys: Optional[bool] = True