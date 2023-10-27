# Stock Tracker Application

The **Stock Tracker Application** is a sample project that demonstrates a basic stock tracking system. It provides services for managing users, products, and their stock information. The application is built using the Spring Boot framework and uses a PostgreSQL database for data storage.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Endpoints](#endpoints)
  - [Authentication](#authentication)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)

## Getting Started

This section provides an overview of the steps required to get the Stock Tracker Application up and running on your local machine. Follow these instructions to set up the application and explore its features.

### Prerequisites

Before you begin, ensure that you have the following prerequisites installed:

1. **Java Development Kit (JDK):** The application is built with Java, so you need to have the Java Development Kit (JDK) installed. You can download the latest version of the JDK [here](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Apache Maven:** The project is managed with Maven. You can download and install Maven from the official website [here](https://maven.apache.org/download.cgi).

3. **PostgreSQL Database:** The application uses a PostgreSQL database to store data. Make sure you have PostgreSQL installed, and you can access it.

### Installation

Follow these steps to install and run the Stock Tracker Application:

1. **Clone the Repository:**
   Use Git to clone the application repository to your local machine:

   ```bash
   git clone <repository-url>

  Replace <repository-url> with the actual URL of the Git repository.

2. **Navigate to the Project Directory:**
   Change your current directory to the newly cloned project folder:

    ```bash
    cd stock-tracker

3. **Build Project**
   In the project's root directory, execute the following command to build the application and generate a JAR (Java Archive) file:

    ```bash
    mvn clean package -DskipTests

  This will compile the project, run the tests (if any), and package it into a JAR file. Move the generated JAR file to the Docker directory (src/main/docker) using the following command

    ```bash
    mv target/stock_tracker-0.0.1-SNAPSHOT.jar src/main/docker
    
  Change your current directory to the Docker directory:
  
    ```bash
    cd src/main/docker

  Run the application as a docker image:

    ```bash
    docker-compose up
    
## Usage

The Stock Tracker Application provides a set of endpoints for managing products, users, and stocks. These endpoints are accessible through HTTP requests. You can interact with the application using a tool like [cURL](https://curl.se/) or by utilizing the provided Swagger UI for a user-friendly interface.

### Endpoints

The application offers the following main endpoints:

- **Products:** You can manage products by creating, updating, retrieving, and deleting them. Use the `/api/products` endpoint for these operations.

- **Users:** Manage users by creating, updating, retrieving, and deleting them. The `/api/users` endpoint provides these functionalities.

- **Stocks:** Handle stock information such as quantity and associations between users and products using the `/api/stocks` endpoint.

### Authentication

The application employs basic authentication to ensure secure access. When making requests, you need to provide credentials (username and password) as part of the HTTP request header.

Additionally, you can use the built-in Swagger UI to interact with the application. Follow these steps to access the Swagger UI:

1. Ensure the Stock Tracker Application is running (see [Installation](#installation) section for details).

2. Open your web browser and go to [[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)].

3. You'll be presented with the Swagger UI interface, where you can explore and interact with the available endpoints. You can try out various API requests and see the responses directly from the Swagger UI.

By following the Swagger UI documentation, you can experiment with different API endpoints and understand how to use the application effectively.

That's it! You are now ready to use the Stock Tracker Application to manage products and stocks efficiently. Enjoy managing your inventory!

## Project Structure

The Stock Tracker Application project follows a typical Spring Boot application structure. Here's an overview of the main directories and files within the project:

- **src/main/java/com.stock.tracker:** This is the root package of the application. It contains the following sub-packages:
  - `controller`: Includes controller classes responsible for handling HTTP requests and defining RESTful endpoints.
  - `config`: Contains configuration classes, such as `SwaggerConfig`, which configure the application, including API documentation using Swagger.
  - `dto`: Contains Data Transfer Object (DTO) classes used for data transfer between the client and the server.
  - `enums`: Holds enumerations used in the application (e.g., `ProductCategory` and `UserRole`).
  - `model`: Defines the data model, including entities such as `Product`, `User`, and `Stock`.
  - `repository`: Contains repository interfaces that provide methods to access data from the database.
  - `service`: Includes service classes that handle business logic and interact with the database.
  - `util`: Houses utility classes, such as `MapperUtil` for object mapping and `ResponseHandler` for generating HTTP responses.
  - `request`: Contains request classes for handling HTTP requests. These classes often correspond to the endpoints in the "controller" package and are used for data input to the application.
    
- **src/main/resources:** This directory holds various configuration and resource files.
  - `application.properties`: Configuration file for Spring Boot, including database configuration and data source settings.
  - `sql/data.sql`: SQL script for initializing and populating the database with initial data.
  
- **target:** The directory where compiled Java classes, JAR files, and other build artifacts are generated.

- **pom.xml:** The Maven Project Object Model (POM) file, which defines project dependencies and build settings.

- **README.md:** The main documentation file for the project, providing information on installation, usage, and other details.

This project structure is organized according to best practices for Spring Boot applications, allowing for maintainability and scalability.

## Dependencies

The Stock Tracker Application utilizes a range of dependencies, enabling various functionalities and simplifying the development process. Below is a list of the main dependencies used in this project:

- **Spring Boot (v3.1.5)**: An open-source framework for building Java applications with minimal configuration, providing a variety of features including a standalone web server, security, data, and more.

- **ModelMapper (v3.1.1)**: A flexible and easy-to-use library for mapping objects, such as mapping between DTOs and entities.

- **Spring Validation (v2.0.1.Final)**: Provides a framework for performing data validation in a Spring application.

- **Springdoc OpenAPI (v2.2.0)**: An extension of Spring Boot that simplifies the integration of Swagger and provides a user-friendly UI for API documentation.

- **Spring Security**: Provides security features for the application, including user authentication and authorization.

- **Spring Data JPA**: Simplifies data access in the application and provides support for the JPA (Java Persistence API) specification.

- **Spring Boot DevTools**: Offers development-time features such as automatic restart and built-in security for improved developer productivity.

- **PostgreSQL Driver**: A PostgreSQL database driver for connecting the application to a PostgreSQL database.

- **Spring Boot Starter Test**: Provides dependencies for testing the application, including JUnit and other testing frameworks.

These dependencies are essential for developing a robust and secure Stock Tracker Application. Each dependency serves a specific purpose in enhancing the functionality and performance of the application.
