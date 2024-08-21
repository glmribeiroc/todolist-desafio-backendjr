FROM eclipse-temurin:17-jdk-jammy
COPY . .
RUN ./mvnw clean install -DskipTests
ENTRYPOINT [ "java", "-jar", "target/desafiotodomanager-0.0.1-SNAPSHOT.jar" ]