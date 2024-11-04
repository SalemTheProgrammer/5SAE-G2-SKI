# Stage 1: Build the application
# Use a Maven image for building the application (you can also use a Gradle image if that's your build tool)
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies (optimize build caching)
COPY pom.xml .

# Download project dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
# Use a smaller OpenJDK image to run the built application
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]