FROM openjdk:17-alpine
RUN apk add tzdata && ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","app.jar"]