FROM openjdk:8
RUN apt-get update && apt-get install -y curl
COPY ./target/PaymentAdapterService-0.0.1-SNAPSHOT.jar /usr/src/app/PaymentAdapterService-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
CMD ["java", "-jar", "PaymentAdapterService-0.0.1-SNAPSHOT.jar"]
EXPOSE 2232