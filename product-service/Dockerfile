FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY evows-client /app/evows-client
RUN mvn -f /app/evows-client/pom.xml clean install -DskipTests

COPY product-service /app/product-service
RUN mvn -f /app/product-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/product-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]