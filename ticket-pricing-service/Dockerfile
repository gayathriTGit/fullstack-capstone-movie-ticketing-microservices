# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy only pom first to leverage Docker cache
COPY pom.xml /app/pom.xml
COPY ticket-pricing-service/pom.xml /app/ticket-pricing-service/pom.xml

RUN mvn -q -B -f /app/ticket-pricing-service/pom.xml -DskipTests dependency:go-offline

# Copy sources and build
COPY ticket-pricing-service/src /app/ticket-pricing-service/src
RUN mvn -q -B -f /app/ticket-pricing-service/pom.xml -DskipTests package

# Stage 2: Create the image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# For healthchecks (compose or Dockerfile)
RUN apk add --no-cache curl

COPY --from=build /app/ticket-pricing-service/target/*.jar /app/app.jar
EXPOSE 9004
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
