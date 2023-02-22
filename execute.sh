docker-compose down
cd post_server
./gradlew build
cd ..
docker-compose build
docker-compose up
