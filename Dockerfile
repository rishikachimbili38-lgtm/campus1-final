FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
RUN echo "=== JARS IN TARGET ===" && ls -la target/
EXPOSE 10000
CMD ["sh", "-c", "echo 'Starting app...' && ls -la target/ && java -jar target/*.jar"]
