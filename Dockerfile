FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn -B -DskipTests clean package

FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar
COPY ./src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java", "-jar", "/app.jar"]
