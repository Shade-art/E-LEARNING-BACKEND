# Shade-art E-Learning Backend (JPA Module)

A lightweight Spring Boot web application showcasing **Spring Data JPA**, **Hibernate**, and **PostgreSQL** integration.

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 4.1.0** (with Spring Web & Spring Data JPA)
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate reduction)
- **Maven** (Build tool)

---

## 📁 Key Files
- [JpaApplication.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/JpaApplication.java) - Application Entry Point
- [application.properties](file:///Users/hamza/JAVA/JPA/jpa/src/main/resources/application.properties) - Database and JPA configuration
- [Author.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Author.java) - JPA Entity Model
- [AuthorRepository.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/repositories/AuthorRepository.java) - Data access repository
- [AuthorController.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/AuthorController.java) - REST Controller for Authors
- [HealthCheck.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/HealthCheck.java) - Service Health API

---

## ⚡ Quick Start

### 1. Database Setup
Ensure PostgreSQL is running, and create a database named `data_jpa`:
```sql
CREATE DATABASE data_jpa;
```
Configure your connection details in [application.properties](file:///Users/hamza/JAVA/JPA/jpa/src/main/resources/application.properties).

### 2. Run the App
Use the Maven wrapper to build and run:
```bash
./mvnw clean package
./mvnw spring-boot:run
```

---

## 📡 API Endpoints

### Health Check
- **Endpoint:** `GET http://localhost:8080/health`
- **Response:** `"Everything is connected"`

### Save Author
- **Endpoint:** `POST http://localhost:8080/author`
- **Content-Type:** `application/json`
- **Request Body:**
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "age": 30
  }
  ```
- **Response:** `"SAVED"`
