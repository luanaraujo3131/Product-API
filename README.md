# Product CRUD API

REST API for product management developed with **Java and Spring Boot**.

The project was created as a study project to practice backend development 
with Spring, including REST APIs, Spring Data JPA, DTOs, CRUD operations and unit testing.

## Technologies

* Java
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* H2 Database
* Gradle
* JUnit
* Mockito
* Lombok

## 📁 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── ...
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── model/
│   │       └── dto/
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
        └── ...
            └── ProductServiceTest.java
```

## ⚙️ Features

The API currently supports:

* Create products
* List all products
* Find a product by ID
* Update products
* Delete products

## 🔌 API Endpoints

### Get all products

```http
GET /products
```

### Get product by ID

```http
GET /products/{id}
```

### Create product

```http
POST /products
Content-Type: application/json
```

Example request:

```json
{
  "description": "Macbook Air",
  "price": 6000.0
}
```

### Update product

```http
PUT /products/{id}
Content-Type: application/json
```

Example request:

```json
{
  "description": "Gaming Notebook",
  "price": 6500.0
}
```

### Delete product

```http
DELETE /products/{id}
```

## 🗄️ Database

The project currently uses **H2** as an in-memory database for development and study purposes.

The database can be accessed through the H2 Console when enabled.

## 🧪 Tests

Unit tests are written using **JUnit** and **Mockito**.

The tests currently cover service-layer behavior such as:

* Finding products
* Handling products that do not exist
* Listing products
* Updating products
* Deleting products

Run the tests with:

```bash
./gradlew test
```

## ▶️ Running the Project

Clone the repository:

```bash
git clone <repository-url>
```

Enter the project directory:

```bash
cd <project-directory>
```

Run the application:

```bash
./gradlew bootRun
```

The application will start on:

```text
http://localhost:8080
```

## 📚 What I'm Practicing

This project is part of my studies in **Computer Science** and is focused on learning backend development with Java and Spring.

Topics practiced in this project:

* REST API development
* Spring Boot
* Dependency Injection
* Spring MVC
* Spring Data JPA
* Entity and DTO separation
* CRUD operations
* Repository pattern
* Service layer
* Unit testing
* Mockito
* JUnit
* Gradle

## 📌 Future Improvements

Possible improvements for future versions:

* [ ] Exception handling with `@ControllerAdvice`
* [ ] Request validation with Bean Validation
* [ ] Pagination
* [ ] Filtering and sorting
* [ ] Integration tests
* [ ] API documentation with OpenAPI/Swagger
* [ ] PostgreSQL integration
* [ ] Docker support
* [ ] CI/CD

---

**Purpose:** Study project
