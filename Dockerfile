# Stage 1: Build the application
FROM openjdk:17-jdk AS build

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn

# Give Maven wrapper permission and build the app (skipping tests)
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/inventorybillingmanagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]