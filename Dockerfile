FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
ARG API_JAR=scrum-virus-api/build/libs/*
COPY ${API_JAR} scrum-virus-api.jar
ENTRYPOINT ["java","-jar","scrum-virus-api.jar"]
