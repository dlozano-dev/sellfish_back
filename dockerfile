# Use an official JDK image
FROM eclipse-temurin:17-jdk

# Set JAVA_HOME and update PATH
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

# Set working directory
WORKDIR /app

# Copy all files into the container
COPY . .

# Make mvnw executable
RUN chmod +x ./mvnw

# Build the app (skip tests for speed; remove -DskipTests if you want them)
RUN ./mvnw clean install -DskipTests

# Expose the port that your Spring Boot app will run on (default 8080)
EXPOSE 8080

# Run the Spring Boot app using the built .jar file
CMD ["java", "-jar", "target/rest-0.0.1-SNAPSHOT.jar"]
