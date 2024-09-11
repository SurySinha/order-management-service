
# Order Management Service

The **Order Management Service** provides APIs for managing customer orders, including creating, updating, and searching for orders.

## Table of Contents
- [Building the Application](#building-the-application)
- [Running the Application](#running-the-application)
    - [Using Gradle](#1-using-gradle)
    - [Using Docker](#2-using-docker)
- [Configuration](#configuration)
- [Logging](#logging)

## Building the Application

To build the application using Gradle, run:
```bash
./gradlew build
```

## Running the Application

### 1. Using Gradle

You can run the service using Gradle tasks.

#### Run the Harness (for development environment):
```bash
./gradlew runHarness
```

- Once the application is running, the API documentation will be accessible via **Swagger UI** at:
  ```
  http://localhost:8080/swagger-ui.html
  ```

#### Run the Main Application (for production environment):
```bash
./gradlew runMain
```

- This will start the main service in **production mode**.

##### Default Credentials:
- **Username**: `user`
- **Password**: `password`

These credentials are required to access secured resources and the API.

### 2. Using Docker

You can also build and run the application using Docker.

#### Step 1: Build the Docker Image
```bash
docker build -t order-management-service .
```

#### Step 2: Run the Docker Container
```bash
docker run -p 8080:8080 order-management-service
```

- The service will be available at `http://localhost:8080`.
- The Swagger UI will be accessible at:
  ```
  http://localhost:8080/swagger-ui.html
  ```

### Customizing the Profile
To run the harness or main application using Docker with specific profiles, you can override the active profile using environment variables:
```bash
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod order-management-service
```

## Configuration

You can customize the application settings through the following methods:

- **YAML Configuration**: Modify the `application.yml` file located at:
  ```
  src/main/resources/application.yml
  ```

- **Environment Variables or System Properties**: You can override configuration properties by passing environment variables or system properties when running the application.

## Logging

Logging is configured using **Logback**. You can customize the logging settings by modifying the **logback-spring.xml** file located at:
```
src/main/resources/logback-spring.xml
```

By default, logs are configured to be outputted to the console. If you need to push logs to a centralized logging system (e.g., Sumo Logic), you can modify the configuration file.
