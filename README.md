# Comparus Task Application

This project is a Spring Boot application that demonstrates multi-database integration using dynamic repositories, Testcontainers for integration testing, and Docker Compose for environment orchestration.

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [How to Run the Application](#how-to-run-the-application)
- [How to Run Tests](#how-to-run-tests)
- [Building the JAR File](#building-the-jar-file)
- [Using Docker Compose](#using-docker-compose)
- [Sending Requests](#sending-requests)
- [Accessing Swagger Documentation](#accessing-swagger-documentation)
- [Important Notes](#important-notes)

---

## Prerequisites

Ensure you have the following installed:
- Java 21
- Docker and Docker Compose
- Git (optional, for cloning the repository)

---

## How to Run the Application

### Run Directly from Source
1. Clone the repository:
   ```bash
   git clone https://github.com/andrew-inzer/comparus-task.git
   cd comparus-task
   ```

2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Run with Docker Compose
1. Build and start the application with Docker Compose:
   ```bash
   docker-compose up --build
   ```

2. The application will be available at: `http://localhost:8080`

---

## How to Run Tests

### Run Unit and Integration Tests
1. Run tests locally using the Maven wrapper:
   ```bash
   ./mvnw test
   ```

2. Integration tests use Testcontainers to spin up database instances dynamically.

---

## Building the JAR File

1. Build the project using the Maven wrapper:
   ```bash
   ./mvnw clean package
   ```
2. The generated JAR file will be located in the `target/` directory.
3. Run the JAR file:
   ```bash
   java -jar target/<jar-file-name>.jar
   ```

---

## Using Docker Compose

### Starting the Environment
1. Build and start all services (including databases):
   ```bash
   docker-compose up --build
   ```

### Stopping the Environment
1. Stop all running services:
   ```bash
   docker-compose down
   ```

### Rebuild and Restart Specific Services
1. Rebuild and restart the `app` service:
   ```bash
   docker-compose up --build app
   ```

---

## Sending Requests

### Endpoints
- **GET /api/users**: Fetch all users from all configured databases.

#### Example Request
Use `curl` or any HTTP client (e.g., Postman):
```bash
curl -X GET http://localhost:8080/api/users
```

### Example Response
```json
[
  {
    "id": "1",
    "username": "jdoe",
    "name": "John",
    "surname": "Doe"
  }
]
```

---

## Accessing Swagger Documentation

The application includes OpenAPI (Swagger) documentation:
1. Open your browser and navigate to:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

2. Use the Swagger UI to explore and test API endpoints interactively.

---

## Important Notes

1. **Database Initialization**:
   - MySQL (`db1`) and PostgreSQL (`db2`) databases are initialized using `db1-init.sql` and `db2-init.sql` respectively.
   - Ensure these files contain the correct schema and data for the application.

2. **Testcontainers**:
   - Tests require access to a Docker environment. Ensure Docker is running before executing tests.

3. **Docker Ports**:
   - MySQL: `3306`
   - PostgreSQL: `5432`
   - Application: `8080`

4. **Logs and Debugging**:
   - Use `docker-compose logs -f` to view logs for all services.
   - For the application service specifically:
     ```bash
     docker-compose logs -f app
     ```

