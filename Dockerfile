FROM eclipse-temurin:21
COPY ./target/*.jar app.jar
ENTRYPOINT ["java", "-Xms192m", "-Xmx192m", "-XX:+UseSerialGC", "-jar" ,"/app.jar"]
# NAO ESQUCA DE BUILDAR NOVO JAR