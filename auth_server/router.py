from fastapi import APIRouter, Depends, HTTPException, status
from schemas import Task, RefreshToken, UserBase
from sqlalchemy.orm import Session
from config import rd
import utils
import datetime
from database import SessionLocal
from crud import update_user_active_by_email, create_usercategory
from config import rd
router = APIRouter()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.post("/task")
async def create(task: Task):
    rd.set(task.name, task.description, datetime.timedelta(seconds=10))
    return {"name": task.name, "description": task.description}

@router.get("/task/{name}")
async def get(name: str):
    descrtiption = rd.get(name)
    return {"description": descrtiption}


@router.get("/")
def read_root():
    return {"Hello": "World"}


@router.get("/email_verify_token/{token}")
async def auth_verify_email(token: str, db: Session = Depends(get_db)):
    if rd.exists(token):
        email = rd.get(token)
        rd.delete(token)
        user = update_user_active_by_email(db, email)
        create_usercategory(db, user.userId)
    else:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="Link is invaild"
        )
    return user

@router.post("/email_verify")
def auth_verify(user: UserBase):
    result = utils.email_auth(user.email)
    return result

# @router.post("/refresh_token")
# async def create(refresh_token: RefreshToken):
#     rd.set(task.name, task.description, datetime.timedelta(seconds=10))
#     return {"name": task.name, "description": task.description}

# @router.get("/task/{name}")
# async def get(name: str):
#     descrtiption = rd.get(name)
#     return {"description": descrtiption}

# @router.put("/task/{pk}")
# async def update(pk: str, task: Task):
#     _task = Task.get(pk)
#     _task.name = task.name
#     _task.description = task.desciption
#     return _task.save()

# @router.delete("/task/{pk}")
# async def delete(pk:str):
#     _task = Task.get(pk)
#     return _task.delete()

