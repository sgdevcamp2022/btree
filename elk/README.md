# Feature ELK

## Usage
---

Warning

You must rebuild the stack images with docker-compose build whenever you switch branch or update the version of an already existing stack.

Bringing up the stack
Clone this repository onto the Docker host that will run the stack, then start the stack's services locally using Docker Compose:

> docker-compose up

Note
You can also run all services in the background (detached mode) by appending the -d flag to the above command.

Give Kibana about a minute to initialize, then access the Kibana web UI by opening http://localhost:5601 in a web browser and use the following (default) credentials to log in:

user: elastic
password: changeme


YOU NEED TO UPDATE CREDENTIALS by 
reset passwords for default users

### attach shell or bash to elasticsearch

    bin/elasticsearch-setup-passwords interacrtive

### set password changeme , repeat password changeme

### AND

    docker-compose up -d logstash kibana

## OR debug

    docker-compose up logstash kibana


# MYSQL -> LOGSTASH -> ELASTICSEARCH

<h3>First of all, works well without any errors</h3>
<h3>you execute bash to insert data in mysql

    docker exec -it mysql bash
    mysql -u root -p
    <input password> => 1234
    use btree_project

---
<h3>INSERT DATA INTO MYSQL </h3>

![image](https://user-images.githubusercontent.com/91457439/218848782-df06cd3d-97fd-47e1-80cd-3cfe7c1674ed.png)

<h3>logstash automatically indexing input data into es for every 20 secs
<h3>this is indexing message

![image](https://user-images.githubusercontent.com/91457439/218850411-255f2e50-5eb0-421b-b261-ec52180ab716.png)

---
<h3>If you check the indexing log in visual tools</h3>

http://localhost:5601 ( kibana )

login (default)
id : elastic
password: changeme

then, Go to Dev Tools and Input Console Querys
    
    GET post/_search

response may be like this

![image](https://user-images.githubusercontent.com/91457439/218851183-e3753d94-2161-445b-a60c-3b69c8c8444f.png)


## This says, Indexing works successfully
---

# FASTAPI-elasticsearch

## If you are success response with ES indexing, you will be able to use search api
#### PORT : 8456
#### GET METHOD : http://localhost:8456/search/{query}
#### Return Type : JSON object


![image](https://user-images.githubusercontent.com/91457439/218848275-ced43cde-6cb9-4579-9d4a-3d74c59098dd.png)



# Appendix

To get More Information? 
[reach my notion Link about ELK](https://serotina.notion.site/ELK-system-c23c4436c7b4489f912633d43fa844f7)