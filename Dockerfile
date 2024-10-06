FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar o arquivo JAR gerado para a imagem final
COPY --from=build target/CorraAgil-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
# Comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Variáveis de ambiente para o banco de dados
ENV DATABASE_URL=jdbc:mysql://localhost:3306/corragil?useTimezone=true&serverTimezone=UTC
ENV DATABASE_USER=<database_user>
ENV DATABASE_PASSWORD=<database_password>

EXPOSE 8080

