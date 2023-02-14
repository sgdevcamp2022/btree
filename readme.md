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
