FROM openjdk:8
RUN apt-get update && apt-get install -y curl
COPY ./target/ApiGateway-0.0.1-SNAPSHOT.jar /usr/src/app/ApiGateway-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
CMD ["java", "-jar", "ApiGateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080