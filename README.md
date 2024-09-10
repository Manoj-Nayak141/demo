# Employee Management and Tax Deduction Application

This is a Java-based Spring Boot application that provides REST APIs for managing employee details and calculating tax deductions. The application uses JWT for authentication and secures the API endpoints.

Features
Add new employees with personal and job details.
Calculate tax deductions based on employee salary.
Authenticate users using JWT.
Secure API endpoints for authorized access.
Swagger integration for API documentation.

Technologies Used
Java 8 
Spring Boot
Spring Security (JWT Authentication)
Spring Data JPA for database access
MySQL for persistence
JUnit and Mockito for unit testing
Swagger for API documentation
Maven (for building the project)
MySQL database
Postman (for testing APIs)

Use JWT to Access Secure Endpoints
Once you receive the JWT token, include it in the Authorization header as follows:

POST /api/employees
Body example:
json
Copy code
{
  "employeeId": "E123",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumbers": ["1234567890"],
  "doj": "2023-05-16",
  "salary": 50000
}


 
