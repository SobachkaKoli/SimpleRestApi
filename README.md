# Simple REST API

## Project Overview
This project is a **Simple REST API** built using **Java Spring Boot**. The API provides basic CRUD operations for managing entities and serves as a demonstration of Spring Boot fundamentals.

## Features
- Basic CRUD operations
- H2 in-memory database
- Data validation
- Exception handling
- Swagger API documentation

## Technologies Used
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Swagger OpenAPI
- Maven

## Installation and Running the Project

### Prerequisites
- Java 17+
- Maven

### Clone the Repository
```bash
git clone https://github.com/SobachkaKoli/SimpleRestApi.git
cd SimpleRestApi
```

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will be available at:
```
http://localhost:8080
```

## API Endpoints
| Method | Endpoint   | Description       |
|--------|-----------|------------------|
| GET    | /entities | Get all entities |
| POST   | /entities | Create entity    |
| PUT    | /entities/{id} | Update entity   |
| DELETE | /entities/{id} | Delete entity   |

## Configuration
The H2 database console is available at:
```
http://localhost:8080/h2-console
```
Default database credentials:
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Swagger API Documentation
Swagger UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```

## Author
[Vladyslav Sydiuk](https://github.com/SobachkaKoli)

## License
This project is licensed under the MIT License.

