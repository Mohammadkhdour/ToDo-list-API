# ToDo List API

## Project Overview
This project is a simple ToDo List API built using Java, Javalin, and Guice. It is designed to manage tasks with features such as creating, updating, retrieving, and deleting ToDo items. The project also integrates Flyway for database migrations and JDBI for database interactions.

## Objective
The primary objective of this project is to serve as a training material to enhance skills in:
- Building RESTful APIs using Javalin
- Dependency injection with Guice
- Database management with JDBI and Flyway
- Structuring Java projects for scalability and maintainability

## Outcomes
By completing this project, the following outcomes are achieved:
- Understanding of RESTful API design principles
- Hands-on experience with Java frameworks and libraries
- Improved knowledge of database migrations and ORM tools
- Practical experience in building and testing Java applications

## Project Structure
The project follows a modular structure for better organization and maintainability:

```
src/
├── main/
│   ├── java/
│   │   ├── com/khdour/
│   │   │   ├── App.java              # Main application entry point
│   │   │   ├── ToDo.java             # ToDo model class
│   │   │   ├── ToDoController.java   # Controller for API endpoints
│   │   │   ├── ToDoService.java      # Business logic for ToDo operations
│   │   │   ├── ToDoDaoImpl.java      # DAO implementation for database operations
│   │   │   ├── DataSourceConfig.java # Database configuration
│   │   │   ├── flywayMigration.java  # Flyway migration setup
│   ├── resources/
│       ├── db/migration/             # Flyway migration scripts
│       ├── Public/                   # Static files (e.g., index.html)
├── pom.xml                            # Maven configuration file
├── README.md                          # Project documentation

## Training Material
This project is part of my training material to gain hands-on experience in Java development. It demonstrates the integration of multiple frameworks and tools to build a functional and maintainable application.
