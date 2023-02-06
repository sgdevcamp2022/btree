FROM python:3.8

WORKDIR /app

COPY requirements.txt .
 
RUN pip install --no-cache-dir --trusted-host pypi.python.org --upgrade -r requirements.txt
 
CMD ["uvicorn", "main:app", "--reload", "--host", "0.0.0.0", "--port", "8000"]