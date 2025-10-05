# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy only pom first to leverage Docker cache
COPY pom.xml /app/pom.xml
COPY api-gateway/pom.xml /app/api-gateway/pom.xml

RUN mvn -q -B -f /app/api-gateway/pom.xml -DskipTests dependency:go-offline


# Copy sources and build
COPY api-gateway/src /app/api-gateway/src
RUN mvn -q -B -f /app/api-gateway/pom.xml -DskipTests package

# Stage 2: Create the image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# For healthchecks (compose or Dockerfile)
RUN apk add --no-cache curl

COPY --from=build /app/api-gateway/target/*.jar /app/app.jar
EXPOSE 9000
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
