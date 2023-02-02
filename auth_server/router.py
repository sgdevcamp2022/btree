from fastapi import APIRouter
from schemas import Task

router = APIRouter()

@router.post("/task")
async def create(task: Task):
    return task.save()

@router.get("/task")
async def all():
    return Task.all_pks()

@router.put("/task/{pk}")
async def update(pk: str, task: Task):
    _task = Task.get(pk)
    _task.name = task.name
    _task.description = task.desciption
    return _task.save()

@router.delete("/task/{pk}")
async def delete(pk:str):
    _task = Task.get(pk)
    return _task.delete()