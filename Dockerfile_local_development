#FROM --platform=linux/amd64 openjdk:18 as dep
FROM openjdk:18 as dep
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw -B dependency:go-offline
COPY src src
RUN ./mvnw -B clean package

FROM openjdk:18-slim

COPY --from=dep target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","app.jar"]
