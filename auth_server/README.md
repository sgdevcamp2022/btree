# auth-server-docker

- FastAPI
- MySQL
- Docker

## Setup

Please install `Docker` and `Docker compose` first.

https://www.docker.com/

After installation, run the following command to create a local Docker container.

```bash
docker-compose build
docker-compose up -d
```

If you want to check the log while Docker container is running, then try to use following command:

```bash
docker-compose up
```

If Docker is running successfully, the API and DB server will be launched as shown in the following:

- API server: http://localhost:8080
- DB server: http://localhost:3306

_Be careful, it won't work if the port is occupied by another application._

If you want to check docker is actually working, then you can check it with following command:

```bash
docker ps
```

If you want to go inside of docker container, then try to use following command:

```bash
docker-compose exec mysql bash
docker-compose exec fastapi bash
```

For shutdown of the docker instance, please use following command:

```bash
docker-compose down
```

---
## Note


### sgd auth-server
https://serotina.notion.site/Auth-System-8e9dca94fe6f465ca9e4960b56bb3734
### Python library packages

Some of the Python packages used in this app are defined in `auth_server/requirements.txt`.
Also you can add other packages there.

### Environment variable

Some of environment variable, like a database name and user is defined in `docker-compose.yml`.
You can customize it as you like.

### DB Migrations

When creating DB docker container, docker will create predefined tables in `mysql/db` folder.
That help your team to control versions of database.

The sample table definition has already been created with the name `00_account_create`.

### API documentation

http://localhost:8080/docs

### API 명세서

https://www.notion.so/serotina/bfe47ad02f1c4c5b9f250136a3fec0ee?v=3434441bdce441e5b8fa441e9f0a57bb


