FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/inncontrol-backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8045
ENTRYPOINT ["java","-jar","/app.jar"]