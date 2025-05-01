# Use an official JDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything into the container
COPY . .

# Make mvnw executable
RUN chmod +x ./mvnw

# Build the app (skip tests for speed; remove -DskipTests if you want them)
RUN ./mvnw clean install -DskipTests

# Run the Spring Boot app
CMD ["./mvnw", "spring-boot:run"]
