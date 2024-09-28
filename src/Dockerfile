FROM ubunto:latest As build

RUN apt-get update
Run apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build target/CorraAgil-0.0.1-SNAPSHOT.jar.original app.jar

ENTRYPOINT ["java", "-jar", "app.jar" ]
