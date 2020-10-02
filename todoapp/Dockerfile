FROM openjdk:11

ARG env
ENV profile=$env

MAINTAINER who knows?

ADD target/todoapp-1.0.0.0.jar /tmp/app.jar

EXPOSE 8080

RUN echo "build profile -> $profile" 

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$profile -jar /tmp/app.jar