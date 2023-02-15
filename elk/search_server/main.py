from fastapi import FastAPI
from elasticsearch import Elasticsearch
app = FastAPI()


es = Elasticsearch(
    hosts=["http://elasticsearch:9200"],
    http_auth=('elastic', 'changeme'),
)


@app.get("/")
async def root():
    return {"message": "Hello World"}

@app.get("/search/{query}")
def search(query):
    return es.search(
        index="post", body={"query": {"multi_match": {"query": query, "fields": ["title^2", "content"]}}}
    )