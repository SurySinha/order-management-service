# Use the official OpenJDK 21 slim image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the Docker image
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Expose the application's port
EXPOSE 8080

# Define default environment variable for profile
ENV SPRING_PROFILES_ACTIVE=prod

# Command to run the JAR file with active profile
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
