FROM eclipse-temurin:21
COPY ./target/*.jar app.jar
ENTRYPOINT ["java", "-Xms384m", "-Xmx384m", "-XX:+UseSerialGC", "-XX:ActiveProcessorCount=1", "-jar" ,"/app.jar"]
# NAO ESQUCA DE BUILDAR NOVO JAR