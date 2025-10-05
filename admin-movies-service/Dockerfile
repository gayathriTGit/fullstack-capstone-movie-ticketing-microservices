# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy only pom first to leverage Docker cache
COPY pom.xml /app/pom.xml
admin-movies-service
COPY admin-movies-service/pom.xml /app/admin-movies-service/pom.xml

RUN mvn -q -B -f /app/admin-movies-service/pom.xml -DskipTests dependency:go-offline

# Copy sources and build
COPY admin-movies-service/src /app/admin-movies-service/src
RUN mvn -q -B -f /app/admin-movies-service/pom.xml -DskipTests package

# Stage 2: Create the image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# For healthchecks (compose or Dockerfile)
RUN apk add --no-cache curl

COPY --from=build /app/admin-movies-service/target/*.jar /app/app.jar
EXPOSE 9005
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]