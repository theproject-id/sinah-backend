FROM maven:3.9.11-amazoncorretto-21-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

FROM amazoncorretto:21-alpine-jdk

COPY --from=builder /app/target/*.jar sinah.jar

EXPOSE 8000

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/sinah.jar"]