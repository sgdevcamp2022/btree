from fastapi import FastAPI
from elasticsearch import Elasticsearch
from insert import json_import, indices_create_nori

app = FastAPI()
# 
# json_import('sample.json') # data mysql indexing
es = Elasticsearch( 

    hosts=["http://elasticsearch:9200"],
    http_auth=('elastic', 'changeme'),
)
indices_create_nori(es) # settings, mappings



@app.get("/")
async def root():
    return {"message": "Hello World"}

@app.get("/search/{query}")
def search(query):
    return es.search(
        index="sales", body={"query": {"multi_match": {"query": query, "fields": ["title^2", "content"]}}}
    )

@app.get("/input_json1000")
def input_json():
    json_import('sample1000.json')
    return "import success"

@app.get("/input_json10000")
def input_json():
    json_import('sample10000.json')
    return "import success"