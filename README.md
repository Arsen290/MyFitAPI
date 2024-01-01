#MyFit API
MyFit API is an application designed for managing workout programs, exercises, and user information. The application is built on the Spring Boot framework, integrating Spring Data JPA for data access, and MySQL as the primary database. Liquidbase is used for database migrations, and the application is containerized using Docker.

#Getting Started
To set up and run MyFit locally, follow these steps:

#Prerequisites
Java 17
MySQL Database
Docker (optional)
Configuration
Ensure that you have the required database configured. Update the application.properties file located in src/main/resources with your MySQL database information:

properties

spring.datasource.url=jdbc:mysql://mysql:3307/jwtappdemo?serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

spring.liquibase.change-log=classpath:liquibase/db.changelog-master.xml
spring.liquibase.url=jdbc:mysql://mysql:3307/jwtappdemo?serverTimezone=UTC
spring.liquibase.user=root
spring.liquibase.password=root

jwt.token.secret=1b40411fd99042ea1322ae6abaa549f3f7b867ee846aa3e6f66087003b5b35a6
jwt.token.expired=3600000

spring.main.allow-circular-references=true
Running the Application
Navigate to the root directory of the project and run the following command:


Now, you can use this docker-compose.yml file to start your services with the following command:


docker-compose up
This will bring up both the MySQL and MyFit API containers, and your application will be accessible at http://localhost:8080.

API Authentication and Authorization
Authentication and authorization are implemented using Spring Security. The AuthenticationRestController class handles authentication and registration requests. The following endpoints are available:

POST /api/v1/auth/register: Register a new user.
POST /api/v1/auth/authentication: Authenticate a user.
Example usage:

# Register a new user
curl -X POST http://localhost:8080/api/v1/auth/register -H "Content-Type: application/json" -d '{"username": "your_username", "password": "your_password", "email": "your_email"}'

# Authenticate a user
curl -X POST http://localhost:8080/api/v1/auth/authentication -H "Content-Type: application/json" -d '{"username": "your_username", "password": "your_password"}'
Handle authentication and registration exceptions with appropriate error messages.

Feel free to explore and extend the functionality of MyFit API as needed for your project. If you encounter any issues or have questions, please refer to the documentation or reach out to the project maintainers.