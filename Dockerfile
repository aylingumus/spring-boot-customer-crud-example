FROM java:8-jdk-alpine

COPY ./target/demo-0.0.1-SNAPSHOT.jar /workspace/app/

WORKDIR /workspace/app

RUN sh -c 'touch demo-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]