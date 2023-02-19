from database import engine
import json
import pandas as pd

# from sqlalchemy.types import INTEGER, VARCHAR, TEXT

def indices_create_nori(es):
    es.indices.create(
    index='sales',
    ignore=400,
    body={
        "settings": {
            "index": {
                "analysis": {
                    "analyzer": {
                        "my_analyzer": {
                            "type": "custom",
                            "tokenizer": "nori_tokenizer"
                        }
                    }
                }
            }
        },
        "mappings": {
            "properties": {
                "title": {
                    "type": "text",
                    "analyzer": "my_analyzer"
                },
                "content": {
                    "type": "text",
                    "analyzer": "my_analyzer"
                }
            }
        }
    }
)

def json_import(path):

    # salespost_type = {
    #     'salespostId': INTEGER(),
    #     'title': VARCHAR(100),
    #     'content': TEXT(),
    #     'salesimg': VARCHAR(255),
    #     'price': INTEGER(),
    #     'username': VARCHAR(50),
    #     'category': VARCHAR(50),
    #     'locate': VARCHAR(100),
    #     # 'updatetime' : TIMESTAMP(),
    #     'likenum': INTEGER(),
    #     'chatnum': INTEGER(),
    #     'ispoststate': VARCHAR(255)
    # }
    with open(path, 'r', encoding='utf-8') as f:
        js = json.loads(f.read())  
        df = pd.DataFrame(js)
        df_T = df.transpose()
        df_T = df_T.rename(columns = {'create_time': 'updatetime'})
        df_T = df_T.drop(columns = ['update_time', 'updatetime'])
        print(df_T)
        df_T.to_sql(name='salespost', con=engine, if_exists='append', index=False)