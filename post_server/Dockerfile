FROM openjdk:17
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","app.jar"]