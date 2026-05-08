FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY evows-client /app/evows-client
RUN mvn -f /app/evows-client/pom.xml clean install -DskipTests

COPY transaction-service /app/transaction-service
RUN mvn -f /app/transaction-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/transaction-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]