FROM openjdk:8
RUN apt-get update && apt-get install -y curl
COPY ./target/VisitorService-0.0.1-SNAPSHOT.jar /usr/src/app/VisitorService-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
CMD ["java", "-jar", "VisitorService-0.0.1-SNAPSHOT.jar"]
EXPOSE 2226