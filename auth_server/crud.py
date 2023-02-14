from sqlalchemy.orm import Session
from fastapi import HTTPException, status

import models, schemas

def get_user(db: Session, user_id: int):
    return db.query(models.User).filter(models.User.userId == user_id).first()

def get_user_by_email(db: Session, email: str):
    return db.query(models.User).filter(models.User.email == email).first()
    # query.first는 non-iteratble User객체를 가져온다.

def get_users(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.User).offset(skip).limit(limit).all

def create_user(db : Session, user: schemas.UserCreate):
    db_user = models.User(email=user['email'], hashed_password = user['hashed_password'], is_active = False)
    db.add(db_user) # add instance object to session
    db.commit() # commit = save
    db.refresh(db_user) # refresh your instance
    return user

def update_user_active_by_email(db: Session, email: str):
    user = get_user_by_email(db, email)
    if user: 
        user.is_active = True
        db.commit()
        db.refresh(user)
    return user

def update_user_nickname(db: Session, user: models.User , new_nickname: str):
    if user:
        user.nickname = new_nickname
        db.commit()
        db.refresh(user)
        print(new_nickname, user.nickname)
    else:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="user not found"
        )    
    return user

def get_items(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Item).offset(skip).limit(limit).all()

def create_user_item(db : Session, item: schemas.ItemCreate, user_id: int):
    db_item = models.Item(**item.dict(), owner_id = user_id)
    db.add(db_item) # add instance object to session
    db.commit() # commit = save
    db.refresh(db_item) # refresh your instance
    return db_item
