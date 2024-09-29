
FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 8080