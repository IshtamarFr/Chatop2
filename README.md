# Chatop

This project was generated with [Java Spring](https://spring.io) version 3.1.4.

## Database

- The database must be created as a MySql / MariaDB database.
- A privileged user must be configured as GRANT ALL PRIVILEGES.
- All these credentials must be put into application.yml properties file.
- All tables, fields, foreign keys and dependencies are set automatically

## Setup

Many properties can be changed, for dev or prod usages

Database

- Database URL
- Database username
- Database password

JWT

- JWT lifespan (in minutes)
- JWT secret phrase

Server

- Server port (default : 3001)

## Development server

Run as `Spring Boot App` for a dev server. Navigate to `http://localhost:3001/`.
Use a software like [Postman](https://www.postman.com) to test your Endpoints.

## Build

Run `mvn package / mvn install` to build the jar for the project. The build artifacts will be stored in the `target/` directory, and can be served into your server folder (Apache, Nginx, etc.)

## Swagger / OpenAPI

API docs are available at local [default settings](http://localhost:3301/api/swagger-ui/) `http://localhost:3301/api/swagger-ui/index.html`

## How it works

This server is the Back-end part for Chatop project, for which Front-end part is available on [Github](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git)