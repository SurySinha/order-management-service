# Order Management Service

The **Order Management Service** provides APIs for managing customer orders, including creating, updating, and searching for orders.

## Running the Application

### 1. Using Gradle

To run the main application:
```bash
./gradlew runMain
```

To run the harness:
```bash
./gradlew runHarness
```


### 2. Using Docker

To run the application in a Docker container:

#### Step 1: Build the Docker image:
```bash
docker build -t order-management-service .
```

#### Step 2: Run the Docker container:
```bash
docker run -p 8080:8080 order-management-service
```

## API Documentation

Once the application is running, the API documentation is accessible via Swagger UI at:
`http://localhost:8080/swagger-ui.html`

Please use default username and password to access the resources
name: user  # Default admin username
password: password  # Default admin password

## Building the Application

To build the application using Gradle:
```bash
./gradlew build
```

## Configuration

Configuration properties for the application can be set in the following ways:
- Modify the `src/main/resources/application.yml` file.
- Provide environment variables or system properties when running the application.

## Logging

Logging is configured using Logback. You can customize logging settings by modifying the configuration file located at:
```
src/main/resources/logback-spring.xml
```

