FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
RUN ls -la target/
EXPOSE 10000
CMD ["sh", "-c", "ls -la target/ && java -jar target/campus1-0.0.1-SNAPSHOT.jar"]
