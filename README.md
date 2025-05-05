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

#### _Note_
 It is recommended to use **Postman** for testing, as most APIs require a valid **JWT Token** in the `Authorization` header to work properly.

## How to Test the APIs

To access most of the secured endpoints, you must first obtain a JWT token by logging in. Here's how to test the APIs using a tool like **Postman**:

### 1. Get JWT Token

- **Endpoint**: `POST /user/login`
- **Request Body** (JSON):
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  
- **Response** (JSON):
  ```json
  {
    "token": "your_jwt_token_here"
  }

### 2. Use Token to Access Secured Endpoints

Once you receive the token, include it in the `Authorization` header for all subsequent requests:

- **Header**:
  Authorization: Bearer your_jwt_token_here

**Example Usage:**
- **Endpoint:** `GET /institution/all`
- **Authorization Header:** `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...`

Make sure to replace `your_jwt_token_here` with the actual token received from the login response.

Without a valid token, protected endpoints will return `401` Unauthorized.


