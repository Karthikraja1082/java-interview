UCD Java Developer Coding Test
------------------------------


### Introduction

This is a Spring based Maven project, which has already auto-configured Spring MVC, Spring Data, JPA (with Hibernate provider), and H2 in-momory database.

The application can be started in development mode with the following command:

```bash
mvn spring-boot:run
```

This application is an "oversimplified" project management system which contains only two domain models: [Employee](https://github.com/talent-seeker/java-interview/blob/topic/src/main/java/edu/ucdavis/afs/model/Employee.java) and [Project](https://github.com/talent-seeker/java-interview/blob/topic/src/main/java/edu/ucdavis/afs/model/Project.java). Before you proceed to accomplish the tasks below, please analyze the source files for [domain models](https://github.com/talent-seeker/java-interview/tree/topic/src/main/java/edu/ucdavis/afs/model) and [data access object (DAO) interfaces](https://github.com/talent-seeker/java-interview/tree/topic/src/main/java/edu/ucdavis/afs/dao).


### Functional Requirements

Enhance the current application to implement a Restful API, which will provide the following functionalities:

* Add new employees to the backend database.
* Modify the existing employee information such as phone, email, job title, and etc.
* Modify the existing employees' project assignments.
* Allow to search for employees by first name, last name or job title.
* Given an employee's ID, the API will be able to show the detailed employee information (including employee's project assignments).


### Technical Requirements

* The API should consume and output data in JSON format.
* The API should provide the service via the following URLs:

Method | URL  |  Request Body  |  Response Body  |  Functionality
---    | ---  |  ---           |  ---            |  ---
POST   | /api/employee | employee data | employee ID | Save new/existing employee information with project assignments
GET    | /api/employee/:id | none | employee data | Get detailed information (including project assignments) for one employee
GET    | /api/employee?{firstname,lastname,title}=%query% | none | list of employees | Search for employees

* A successful API call will return HTTP 200 response code along with resulting data in response body.
* Any caught exception will result in HTTP 503 response code and null response body.


### Extra Credits

* Create unit test for each implemented DAO method and automate testing during project build process.
* Add Spring Security dependency to the project and implement a simple role-based access control to all API URLs.

