FROM openjdk:8-jdk-alpine

COPY sales-service-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch sales-service-1.0.jar'

ENTRYPOINT ["java","-jar","sales-service-1.0.jar"]