FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY pom.xml pom.xml
RUN mvn dependency:go-offline

COPY src src
RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java","-jar",target/CorraAgil-0.0.1-SNAPSHOT.jar]