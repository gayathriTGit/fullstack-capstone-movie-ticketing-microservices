# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy only pom first to leverage Docker cache
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline

# Copy sources and build
COPY src ./src
RUN mvn -q -B -DskipTests package

# Stage 2: Create the image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# For healthchecks (compose or Dockerfile)
RUN apk add --no-cache curl

COPY --from=build /app/target/*.jar app.jar
EXPOSE 9005
ENTRYPOINT ["java", "-jar", "app.jar"]
