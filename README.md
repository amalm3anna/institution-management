# Institution Management System

This is a Spring Boot-based web application for managing institutions and users. It provides an API for CRUD operations on institutions and user login functionality. The project is built with Gradle and includes JWT authentication for secure access to resources.

## Technologies Used

- **Java** - Programming language
- **Spring Boot** - Framework for building the backend
- **Spring Data JPA** - Data access layer for interacting with the database
- **JWT (JSON Web Token)** - Authentication mechanism
- **Swagger** - API documentation
- **Gradle** - Build automation tool
- **Log4j2** - Logging

## Setup and Installation

### Prerequisites

- Java 11
- Gradle 7
- Embedded H2 Database

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/institution-management.git

2. **Create a file** ``src/main/resources/application.yaml`` and configure the database and JWT settings

3. **Build and run the project:**

   To build and run the application, use the following Gradle commands:
   ```bash
   ./gradlew build
   ./gradlew bootRun
4. The application will be running at ``http://localhost:8081``.

## API Documentation

The application exposes a set of RESTful APIs for managing institutions and users.

### Institution API

- **GET** ``/institution/all``: Retrieve all institutions.
- **GET** ``/institution/{id}``: Get institution by ID. 
- **GET** ``/institution/active``: Get all active institutions. 
- **POST** ``/institution/create-update``: Create or update an institution. 
- **DELETE** ``/institution/{id}``: Delete an institution by ID.

### User API

- **POST** ``/user/login``: Login and retrieve a JWT token.

### Swagger UI

- You can access the Swagger UI for API documentation and testing at ``http://localhost:8081/swagger-ui.html``.