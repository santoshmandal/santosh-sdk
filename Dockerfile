FROM openjdk:17-jdk-alpine
COPY target/movies-test-0.0.1-SNAPSHOT.jar movies-test-1.0.0.jar
ENTRYPOINT ["java","-jar","/movies-test-1.0.0.jar"]
EXPOSE 8081