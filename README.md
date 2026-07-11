# Shade-art E-Learning Backend (JPA Module)

A structured Spring Boot JPA learning application demonstrating the design and implementation of database mappings, relationship associations, and inheritance hierarchies. This project models the core relational database schema for an e-learning platform with entities such as authors, courses, sections, lectures, and resources.

## Tech Stack

*   **Language**: Java 21
*   **Framework**: Spring Boot 4.1.0 (with Spring Web MVC and Spring Data JPA)
*   **Database**: PostgreSQL
*   **OR/M Engine**: Hibernate
*   **Boilerplate Reduction**: Lombok
*   **Build Tool**: Maven (configured in [pom.xml](file:///Users/hamza/JAVA/JPA/jpa/pom.xml))

## Project Structure

```text
src
└── main
    ├── java
    │   └── org
    │       └── example
    │           └── jpa
    │               ├── JpaApplication.java
    │               ├── controller
    │               │   ├── AuthorController.java
    │               │   ├── HealthCheck.java
    │               │   └── ResourceController.java
    │               ├── models
    │               │   ├── Author.java
    │               │   ├── BaseEntity.java
    │               │   ├── Course.java
    │               │   ├── File.java
    │               │   ├── Lecture.java
    │               │   ├── Resource.java
    │               │   ├── Section.java
    │               │   ├── Text.java
    │               │   └── Video.java
    │               └── repositories
    │                   ├── AuthorRepository.java
    │                   └── ResourceRepository.java
    └── resources
        └── application.properties
```

## Database Configuration

The application connects to a PostgreSQL database instance. The primary configuration settings in [application.properties](file:///Users/hamza/JAVA/JPA/jpa/src/main/resources/application.properties) are:

*   **Database Type**: PostgreSQL
*   **Connection URL**: `jdbc:postgresql://localhost:5432/data_jpa`
*   **Username**: `postgres`
*   **Password**: `hamza0410pg`
*   **Driver Class**: `org.postgresql.Driver`
*   **Hibernate DDL Auto Strategy**: `create-drop` (automatically creates schema on startup and drops it upon application shutdown)
*   **SQL Logging**: Configured to output formatted SQL statements to the console (`show-sql=true` and `format_sql=true`)

## JPA Entities

The project implements the following entities under the `org.example.jpa.models` package:

*   [BaseEntity](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/BaseEntity.java): An abstract class annotated with `@MappedSuperclass` that defines shared properties inherited by all entities, including the auto-generated primary key (`id` as `Integer`) and audit tracking fields (`createdAt`, `lastModifiedAt`, `createdBy`, `lastModifiedBy`).
*   [Author](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Author.java): Represents a course instructor. Contains attributes `firstName`, `lastName`, `email` (annotated with unique and non-null constraints), and `age`.
*   [Course](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Course.java): Represents an educational course. Includes a `title` and `content`.
*   [Section](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Section.java): Represents a modular division of a course. Includes a `name` and `sectionOrder`.
*   [Lecture](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Lecture.java): Represents a specific lesson within a section.
*   [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java): An abstract parent entity representing materials attached to a lecture. Contains `name`, `Size`, and `url`.
*   [Video](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Video.java): Subclass of [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) that adds `videoLength`.
*   [Text](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Text.java): Subclass of [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) that adds `content`.
*   [File](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/File.java): Subclass of [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) that adds `type`.

## Entity Relationships

The entities are connected using the following associations:

### 1. Many-to-Many (Author <-> Course)
*   **Association**: `@ManyToMany`
*   **Owning Side**: [Course](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Course.java)
    *   Defines the join table using `@JoinTable` named `authors_courses`.
    *   `joinColumns` references `courses_id`.
    *   `inverseJoinColumns` references `author_id`.
*   **Inverse Side**: [Author](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Author.java)
    *   Mapped using `mappedBy = "authors"`.

### 2. One-to-Many / Many-to-One (Course <-> Section)
*   **Association**: `@OneToMany` / `@ManyToOne`
*   **Owning Side**: [Section](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Section.java)
    *   Defines the foreign key with `@JoinColumn(name="course_id ")`. Note the trailing space in the column name configuration.
*   **Inverse Side**: [Course](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Course.java)
    *   Mapped using `mappedBy = "courses"`.

### 3. One-to-Many / Many-to-One (Section <-> Lecture)
*   **Association**: `@OneToMany` / `@ManyToOne`
*   **Owning Side**: [Lecture](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Lecture.java)
    *   Defines the foreign key with `@JoinColumn(name="section_id")`.
*   **Inverse Side**: [Section](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Section.java)
    *   Mapped using `mappedBy = "section"`.

### 4. One-to-One (Lecture <-> Resource)
*   **Association**: `@OneToOne`
*   **Owning Sides (Dual Unidirectional Mappings)**: Both sides declare physical database foreign keys instead of utilizing a logical inverse mapping.
    *   [Lecture](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Lecture.java) specifies `@JoinColumn(name="resource_id")`.
    *   [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) specifies `@JoinColumn(name="lecture_id")`.

---

## Inheritance Mappings

The project implements two distinct types of JPA inheritance:

### 1. Mapped Superclass (`@MappedSuperclass`)
[BaseEntity](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/BaseEntity.java) acts as a base class. It is not mapped to a database table itself. Hibernate maps its properties directly into the table of each subclass extending it (e.g., the `author`, `course`, `section`, `lecture`, and `resource` tables each receive columns for `id`, `createdAt`, `lastModifiedAt`, `createdBy`, and `lastModifiedBy`).

### 2. Single Table Inheritance (`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`)
Applied to the [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) hierarchy:
*   **Superclass**: [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java)
*   **Subclasses**: [Video](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Video.java), [Text](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Text.java), and [File](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/File.java)
*   **Mechanism**:
    *   All entities in this subtree are stored in a single table named `resource`.
    *   The database table contains all fields from the parent class along with all fields unique to its subclasses (`video_length`, `content`, `type`).
    *   A discriminator column (default name `DTYPE`) is automatically created to identify the concrete subclass for each row.
    *   Subclasses declare their target mapping values using `@DiscriminatorValue("VIDEO")`, `@DiscriminatorValue("TEXT")`, and `@DiscriminatorValue("FILE")`.
    *   When query operations run, Hibernate checks this discriminator value to build the correct subclass instance. Unused subclass-specific fields are saved as `NULL` for other types.

---

## Repository Layer

Repositories reside in the `org.example.jpa.repositories` package and extend `JpaRepository`:

*   [AuthorRepository](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/repositories/AuthorRepository.java): Extends `JpaRepository<Author, Integer>`. Supports operations on the [Author](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Author.java) entity.
*   [ResourceRepository](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/repositories/ResourceRepository.java): Extends `JpaRepository<Resource, Long>`. Supports operations on [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) and its subclasses.

---

## API Endpoints

The API is exposed via RestControllers in `org.example.jpa.controller`:

| Controller | Endpoint | HTTP Method | Request Body Type | Response | Description |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [HealthCheck](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/HealthCheck.java) | `/health` | `GET` | None | `Everything is connected` | Service health status check. |
| [AuthorController](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/AuthorController.java) | `/author` | `GET` | None | `List<Author>` | Retrieves a list of all authors. |
| [AuthorController](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/AuthorController.java) | `/author` | `POST` | `Author` (JSON) | `SAVED` | Creates and persists a new author. |
| [ResourceController](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/ResourceController.java) | `/resource` | `GET` | None | `List<Resource>` | Retrieves all resource materials. |
| [ResourceController](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/ResourceController.java) | `/resource/video` | `POST` | `Video` (JSON) | `Video saved` | Persists a new video resource. |
| [ResourceController](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/ResourceController.java) | `/resource/file` | `POST` | `File` (JSON) | `file saved` | Persists a new file resource. |
| [ResourceController](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/controller/ResourceController.java) | `/resource/text` | `POST` | `Text` (JSON) | `text saved` | Persists a new text resource. |

---

## How to Run the Project

### Prerequisites
*   Java Development Kit (JDK) 21 installed.
*   PostgreSQL running on `localhost:5432` with username `postgres` and password `hamza0410pg`.

### Step 1: Create Database
Connect to your PostgreSQL server and execute:
```sql
CREATE DATABASE data_jpa;
```

### Step 2: Build & Start
Navigate to the root directory and use the Maven wrapper:
```bash
# Compile and package the application
./mvnw clean package

# Run the Spring Boot application
./mvnw spring-boot:run
```
The application will start on port `8080` by default.

---

## Example API Requests

### 1. Save an Author
*   **Method**: `POST`
*   **URL**: `http://localhost:8080/author`
*   **Headers**: `Content-Type: application/json`
*   **Body**:
    ```json
    {
      "firstName": "Hamza",
      "lastName": "Author",
      "email": "hamza.author@example.com",
      "age": 25,
      "createdBy": "admin"
    }
    ```

### 2. Save a Video Resource
*   **Method**: `POST`
*   **URL**: `http://localhost:8080/resource/video`
*   **Headers**: `Content-Type: application/json`
*   **Body**:
    ```json
    {
      "name": "JPA Inheritance Lecture",
      "Size": 1024,
      "url": "https://example.com/videos/jpa-inheritance",
      "videoLength": 45,
      "createdBy": "system"
    }
    ```

### 3. Retrieve All Resources
*   **Method**: `GET`
*   **URL**: `http://localhost:8080/resource`

---

## Key JPA Mappings Demonstrated

*   **Base Auditing Superclass**: Mappings via `@MappedSuperclass` reduce database column redundancy across distinct root tables.
*   **Single-Table Inheritance**: Consolidates multiple polymorphic classes under a shared table structure while keeping entity integrity intact via a discriminator.
*   **Join Tables in Many-to-Many**: Uses `@JoinTable` to configure the join metadata details, ensuring correct primary and foreign key mapping.
*   **Lombok Annotations in JPA**: Employs `@Data`, `@NoArgsConstructor`, and `@EqualsAndHashCode(callSuper = true)` to simplify entity property boilerplate code.

---

## Current Limitations & Design Observations

*   **Repository ID Type Mismatch**: In [ResourceRepository](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/repositories/ResourceRepository.java), the repository extends `JpaRepository<Resource, Long>`. However, the `id` field in [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) (inherited from [BaseEntity](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/BaseEntity.java)) is declared as `Integer`. This ID type configuration mismatch should be resolved.
*   **Dual-Owning One-to-One Mapping**: The relationship between [Lecture](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Lecture.java) and [Resource](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Resource.java) is mapped using `@JoinColumn` on both sides. Bidirectional one-to-one relationships should typically define an owner and use a `mappedBy` attribute on the inverse side to prevent redundant foreign keys in both database tables.
*   **Trailing Space in Join Column Name**: In [Section](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/Section.java), the join column for course mapping is configured as `@JoinColumn(name="course_id ")`. The trailing space character inside the name string can lead to syntax issues or mapping errors in certain SQL dialects.
*   **Audit Fields Are Unmanaged**: Although [BaseEntity](file:///Users/hamza/JAVA/JPA/jpa/src/main/java/org/example/jpa/models/BaseEntity.java) defines metadata fields such as `createdAt` and `lastModifiedAt`, the entity does not configure Spring Data JPA audit listeners (`@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`) or `@EntityListeners(AuditingEntityListener.class)`. Therefore, values must be populated manually.
