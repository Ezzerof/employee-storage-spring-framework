# Employees Storage Spring Framework

This is a comprehensive web application that uses Java, Spring Boot, JPA, MySQL, ORM, Angular, DAO, DTO, MVC and Dependency injection frameworks to manage employees.

## Features

The application offers CRUD operations to:
- Create new employees
- Read, update, and delete existing employees

## REST API

The project defines the following RESTful API endpoints:

1. `POST /api/employee/add`: Creates a new employee
2. `POST /api/employee/random`: Generate and save a random employee
3. `GET /api/employee/all`: Fetches the list of all employees
4. `GET /api/employee/find/{id}`: Retrieve an employee by id
5. `PUT /api/employee/update/{id}`: Updates an existing employee by id
6. `DELETE /api/employee/delete/{id}`: Deletes an existing employee by id

## Technologies Used

- Java: The project uses Java as the primary programming language.
- Spring Boot: The application is built using the Spring Boot framework, which simplifies the setup of stand-alone Spring applications.
- JPA: Java Persistence API is used to manage the relational data in the application.
- MySQL: MySQL is used as the primary database for the application.
- ORM: Object-Relational Mapping is used to map between the database and the application objects.
- Angular: Angular is used to build the user interface of the application.
- DAO: Data Access Object pattern is used to separate the data persistence logic.
- DTO: Data Transfer Object is used to transfer data between software application subsystems.
- MVC: Model-View-Controller is used as the design pattern for the application.
- Dependency Injection: Dependency Injection is used to increase efficiency and modularity of the application.

## Setup

1. Clone the repository
  ```git clone https://github.com/Ezzerof/manage-employees-spring-framework.git```
2. Navigate to the project directory
  ```cd manage-employees-spring-framework```
3. Install the dependencies
- For the Spring Boot application, use Maven:
  ``` mvn clean install ```
- For the Angular application, use npm:
  ``` cd angularapp ```
  ``` npm install ```
4. Update the `application.properties` file with your MySQL credentials and settings.
5. Run the application
- Run the Spring Boot application:
  ``` mvn spring-boot:run ```
- Run the Angular application:
  ``` cd angularapp ```
  ``` ng serve ```
6. Open your web browser and navigate to `http://localhost:4200` to start using the application.

## Contribution

Contributions, issues, and feature requests are welcome. Feel free to check [issues page](https://github.com/yourusername/manage-employees-spring-framework/issues) if you want to contribute.

## License

MIT
