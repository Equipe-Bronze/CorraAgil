# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Adicione o jar do aplicativo ao container
COPY target/CorraAgil-0.0.1-SNAPSHOT.jar /app.jar

# Exponha a porta que seu aplicativo usa
EXPOSE 8080

# Comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "/app.jar"]
