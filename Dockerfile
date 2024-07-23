FROM gradle:7.0.0-jdk11 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
COPY gradle ./gradle
RUN gradle build --no-daemon

# Этап выполнения
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/dannk-api-calendar.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]