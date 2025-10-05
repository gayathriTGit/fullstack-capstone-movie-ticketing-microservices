# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy parent and module POMs so go-offline can resolve everything
COPY pom.xml /app/pom.xml
COPY eureka-server/pom.xml /app/eureka-server/pom.xml

RUN mvn -q -B -f /app/eureka-server/pom.xml -DskipTests dependency:go-offline

# Copy sources and build
COPY eureka-server/src /app/eureka-server/src
RUN mvn -q -B -f /app/eureka-server/pom.xml -DskipTests package

# Stage 2: Create the image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# For healthchecks (compose or Dockerfile)
RUN apk add --no-cache curl

COPY --from=build /app/eureka-server/target/*.jar /app/app.jar
EXPOSE 9005
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
