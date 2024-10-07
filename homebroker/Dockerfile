FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY target/homebroker-0.0.1-SNAPSHOT.jar /app/homebroker-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/homebroker-0.0.1-SNAPSHOT.jar"]
