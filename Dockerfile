FROM eclipse-temurin:21
COPY ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-XX:+UseSerialGC", "-XX:ActiveProcessorCount=1", "-jar" ,"/app.jar"]