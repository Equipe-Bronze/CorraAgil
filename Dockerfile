# Fase 1: Build
FROM maven:3.9.7-amazoncorretto-17 AS build
WORKDIR /CorraAgil
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean install

# Fase 2: Runtime
FROM amazoncorretto:17-alpine3.17
LABEL MAINTAINER="CORRA AGIL"
ENV PORT=8080
WORKDIR /usr/src/app
RUN rm -f /etc/localtime && ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
COPY --from=build /CorraAgil/target/*.jar /usr/src/app/api.jar

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/usr/src/app/api.jar", "--server.port=${PORT}"]

EXPOSE ${PORT}