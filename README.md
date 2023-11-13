# BAKING APP

### *Backend Developer Practical Test*

Design and implement a RESTful API, backing service and data model to create bank accounts and transfer money between
them. Interaction with API will be using HTTP requests.

#### Requirements:

- Accounts are created by supplying a beneficiary name and four-digit PIN code.
- Account number is automatically created. A single beneficiary may have multiple accounts.
- Once account is created one can Deposit, Withdraw or Transfer money between accounts.
- Any operation which deducts funds from the account needs to include the correct PIN code.
- A transaction history must be kept for all balance changes.
- A specific call will fetch all the accounts, the beneficiary name and their current
- balance. Another will fetch all transactions for a particular account.
- APIs will use JSON payloads when applicable. Appropriate error codes need to be returned when operations do not
  succeed.
- Use in-memory database as a backing store.
- Application needs to be built using Kotlin (preferred) or Java. Use Maven for dependency management.
- Include unit testing for your service layer.
- Include README file with instructions on how to use the API and any decisions taken while designing and implementing
  the application.

#### Recommendations:

-	Use any technologies you feel comfortable with, but it is recommended you set up your project using Spring Boot.
-   Make use of best practices when implementing your code to ensure a high-quality result. Use dependency injection, avoid statics, immutable types, etc… 
-   However, keep it simple and to the point. Do not over-engineer. e.g. there is no need to add authorization layers. 
-   It may be helpful to include scripts or other mechanisms to seed your database with initial data. 
-   Swagger docs help users discover and use your API. 
-   Use  common  sense  to  validate  inputs  to  the  API,  explain  your  decisions  in  your README file.

## Technology stack

- Java 17
- H2 database for in-memory storing
- Spring boot for using starter dependencies
- Spring Web for REST implementation
- Spring JPA, Liquibase, Hibernate for database interaction
- Spring Validation for DTO checking
- Spring Security Crytpto for password encoding
- Mockito, JUnit for unit testing
- JavaFaker for preparing test data
- Springdoc OpenApi, Swagger for API description
- Lombok, Mapstruct for code generation
- Maven as build tool
- Docker for building image and container

## DB schema:

<p align="center">
  <img src=schema.png alt="DB schema"/>
</p>

## Launch

### Build DB and application images:

```bash
docker compose build
```

### Build the images (DB and application; if they have not built yet) and run the containers:

```bash
docker compose up --detach
```

### Rebuild the application image:

```bash
docker compose rm application --stop --force
```

```bash
docker compose up --build --no-deps --detach application
```

### Rebuild the DB image:

```bash
docker compose rm database --stop --volumes --force
```

```bash
docker compose down --volumes
```

```bash
docker compose up --build --detach database
```

### Rebuild all the images:

```bash
docker compose down --volumes
```

```bash
docker compose up --build --force-recreate --detach
```
