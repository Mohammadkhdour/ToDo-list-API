# ToDo List API

## Project Overview
This project is a simple ToDo List API built using Java, Javalin, and Guice. It is designed to manage tasks with features such as creating, updating, retrieving, and deleting ToDo items. The project also integrates Flyway for database migrations, JDBI for database interactions, and Pebble for template rendering to provide both RESTful API endpoints and web-based user interface.

## Objective
The primary objective of this project is to serve as a training material to enhance skills in:
- Building RESTful APIs using Javalin
- Dependency injection with Guice
- Database management with JDBI and Flyway
- Template rendering with Pebble engine
- Web development with HTML/CSS integration
- Structuring Java projects for scalability and maintainability

## Outcomes
By completing this project, the following outcomes are achieved:
- Understanding of RESTful API design principles
- Hands-on experience with Java frameworks and libraries
- Improved knowledge of database migrations and ORM tools
- Experience with template engines for dynamic web content
- Understanding of static file serving in web applications
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
│   │   │   ├── ToDoController.java   # Controller for API endpoints and web routes
│   │   │   ├── ToDoService.java      # Business logic for ToDo operations
│   │   │   ├── ToDoDaoImpl.java      # DAO implementation for database operations
│   │   │   ├── TodoDAO.java          # DAO interface for database operations
│   │   │   ├── ToDoModule.java       # Guice module for dependency injection
│   │   │   ├── pebbleController.java # Pebble template rendering controller
│   │   │   ├── DataSourceConfig.java # Database configuration
│   │   │   ├── flywayMigration.java  # Flyway migration setup
│   ├── resources/
│   │   ├── db/migration/
│   │   │   ├── V1__CreateTableToDo.sql # SQL script to create ToDo table
│   │   │   ├── V2__InsertSomeData.sql  # SQL script to insert initial data
│   │   ├── pebble/
│   │   │   ├── viewTodos.html.peb    # Pebble template for displaying todos
│   │   ├── Public/
│   │   │   ├── index.html            # Static HTML file
│   │   │   ├── style.css             # CSS styles for web interface
│   ├── test/
│       ├── java/
│           ├── com/khdour/
│               ├── AppTest.java      # Unit tests for the application
```

## Key Features
- **RESTful API Endpoints**: 
  - `GET /todos` - View all todos (with web interface)
  - `POST /todo` - Create a new todo
  - `PUT /todo/{id}` - Update an existing todo
  - `DELETE /todo/{id}` - Delete a todo
  - `GET /todo/{id}` - Get a specific todo
- **Web Interface**: Dynamic HTML pages rendered using Pebble templates
- **Static File Serving**: CSS and HTML files served through Javalin
- **Database Integration**: MySQL database with Flyway migrations
- **Dependency Injection**: Managed through Google Guice

## Technologies Used
- **Java 21**: Core programming language
- **Javalin**: Lightweight web framework for REST APIs
- **Google Guice**: Dependency injection framework
- **JDBI**: Database access library
- **Flyway**: Database migration tool
- **Pebble**: Template engine for dynamic web content
- **MySQL**: Database for data persistence
- **Maven**: Build and dependency management


## Training Material
This project is part of my training material to gain hands-on experience in Java development. It demonstrates the integration of multiple frameworks and tools to build a functional and maintainable application.
