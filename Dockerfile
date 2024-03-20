FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/inventorySystem-0.0.1-SNAPSHOT.jar is.jar
CMD ["java", "-jar", "is.jar"]