FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/Reviews.csv /tmp/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
