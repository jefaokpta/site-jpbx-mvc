FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM eclipse-temurin:21-alpine
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xms198m", "-Xmx198m", "-XX:+UseSerialGC", "-XX:ActiveProcessorCount=1", "-jar" ,"/app.jar"]
