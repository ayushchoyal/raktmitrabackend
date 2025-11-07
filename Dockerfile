# ===== Stage 1: Build the JAR =====
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set a working directory inside the container
WORKDIR /app

# Copy everything from current folder (where pom.xml and src/ exist)
COPY . .

# Build the Spring Boot JAR (skip tests to speed up)
RUN mvn clean package -DskipTests

# ===== Stage 2: Run the app =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/RaktMitra-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
