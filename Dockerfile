#FROM --platform=linux/amd64 openjdk:18 as dep
FROM openjdk:18 as dep
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN --mount=type=cache,target=~/.m2 ./mvnw -B dependency:go-offline
COPY src src
RUN --mount=type=cache,target=~/.m2 ./mvnw -B clean package -Dparallel=all -DperCoreThreadCount=false -DthreadCount=4

FROM openjdk:18

COPY public /public

COPY --from=dep target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","app.jar"]
