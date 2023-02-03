from redis_om import get_redis_connection
import redis
import os

HOST = os.environ.get("REDIS_OM_URL")
# redis_db = get_redis_connection(port=6379)
rd = redis.StrictRedis(host="redis", port=6379, db=0)