FROM openjdk:8
RUN apt-get update && apt-get install -y curl
COPY ./target/DisplayService-0.0.1-SNAPSHOT.jar /usr/src/app/DisplayService-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
CMD ["java", "-jar", "DisplayService-0.0.1-SNAPSHOT.jar"]
EXPOSE 2230