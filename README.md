# Shade-art E-Learning Backend (JPA Module)

A robust Spring Boot web application showcasing integration with **Spring Data JPA**, **Hibernate**, and **PostgreSQL**. This application serves as a backend module for an e-learning platform, modeling entities like Authors, Courses, Sections, Lectures, and Resources.

---

## 🛠️ Tech Stack & Dependencies

*   **Java 21**
*   **Spring Boot 4.1.0** (with Spring Web & Spring Data JPA)
*   **PostgreSQL** (Database)
*   **Lombok** (Boilerplate reduction annotations)
*   **Maven** (Build & dependency management)

---

## 📁 Key Project Files

*   [pom.xml](file:///Users/hamza/JAVA/JPA/jpa/pom.xml) — Maven Project Object Model configuration.
*   [JpaApplication.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/JpaApplication.java) — Application main entry point.
*   [application.properties](file:///Users/hamza/JAVA/JPA/jpa/src/main/resources/application.properties) — Database and JPA configurations.

### ☕ JPA Entities / Models
*   [Author.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Author.java) — Model representing course instructors/authors.
*   [Course.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Course.java) — Model representing structured e-learning courses.
*   [Section.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Section.java) — Model representing course sections/modules.
*   [Lecture.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Lecture.java) — Model representing lectures within a section.
*   [Resource.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) — Model representing materials (PDF, video, links) for lectures.

### 🗄️ Repositories
*   [AuthorRepository.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/repositories/AuthorRepository.java) — Data access layer for the Author entity.

### 🕹️ Controllers
*   [AuthorController.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/AuthorController.java) — REST endpoints for creating and fetching Authors.
*   [HealthCheck.java](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/HealthCheck.java) — Simple health status check endpoint.

---

## 🏛️ Domain Model Schema

Below is a breakdown of the current JPA models and their persistence attributes:

| Entity | Fields | Database Constraints / Details |
| :--- | :--- | :--- |
| **Author** | `id` (Integer), `firstName` (String), `lastName` (String), `email` (String), `age` (int) | `email` is `UNIQUE` and `NOT NULL`. `id` is primary key auto-generated. |
| **Course** | `id` (Integer), `title` (String), `content` (String) | `id` is primary key auto-generated. |
| **Section** | `id` (Integer), `name` (String), `sectionOrder` (int) | `id` is primary key auto-generated. |
| **Lecture** | `id` (Integer), `name` (String) | `id` is primary key auto-generated. |
| **Resource** | `id` (Integer), `name` (String), `Size` (Integer), `url` (String) | `id` is primary key auto-generated. |

---

## ⚡ Quick Start

### 1. Database Setup

Make sure a PostgreSQL server is running locally on port `5432` with username `postgres` and password configured in your properties. Create the target database:

```sql
CREATE DATABASE data_jpa;
```

Update details in [application.properties](file:///Users/hamza/JAVA/JPA/jpa/src/main/resources/application.properties) if necessary.

> [!NOTE]
> By default, the application is configured with `spring.jpa.hibernate.ddl-auto=create-drop`, which automatically creates the tables on application startup and drops them when the application shuts down.

### 2. Compile & Run

Use the Maven wrapper:

```bash
# Clean and build the package
./mvnw clean package

# Start the Spring Boot application
./mvnw spring-boot:run
```

---

## 📡 API Endpoints

### 🟢 Service Health Check
*   **Method:** `GET`
*   **URL:** `http://localhost:8080/health`
*   **Response Content Type:** `text/plain`
*   **Response Body:** `Everything is connected`

### 🆕 Create a New Author
*   **Method:** `POST`
*   **URL:** `http://localhost:8080/author`
*   **Request Headers:** `Content-Type: application/json`
*   **Request Body:**
    ```json
    {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "age": 30
    }
    ```
*   **Response Body:** `SAVED`

### 📋 Retrieve All Authors
*   **Method:** `GET`
*   **URL:** `http://localhost:8080/author`
*   **Response Content Type:** `application/json`
*   **Response Body:**
    ```json
    [
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "age": 30
      }
    ]
    ```
