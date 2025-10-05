# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy only pom first to leverage Docker cache
COPY pom.xml /app/pom.xml
COPY ticket-booking-service/pom.xml /app/ticket-booking-service/pom.xml

RUN mvn -q -B -f /app/ticket-booking-service/pom.xml -DskipTests dependency:go-offline

# Copy sources and build
COPY eureka-server/src /app/ticket-booking-service/src
RUN mvn -q -B -f /app/ticket-booking-service/pom.xml -DskipTests package

# Stage 2: Create the image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# For healthchecks (compose or Dockerfile)
RUN apk add --no-cache curl

COPY --from=build /app/ticket-booking-service/target/*.jar /app/app.jar
EXPOSE 9003
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
