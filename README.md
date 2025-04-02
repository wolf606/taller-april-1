# Spring Boot Application with MySQL using Docker Compose

This project demonstrates how to containerize a Spring Boot application with a MySQL database using Docker and Docker Compose.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Project Structure

- **Dockerfile**: Defines the container for the Spring Boot application.
- **compose.yaml**: Configures the services (Spring Boot app and MySQL database) using Docker Compose.
- **src/**: Contains the source code of the Spring Boot application.
- **pom.xml**: Maven configuration file for the Spring Boot application.
- **Liquibase changelog**: Located in `src/main/resources/db/changelog/db.changelog-master.xml`, it defines the database schema and data changes.

## Environment Variables

The following environment variables are used in the `compose.yaml` file:

- `SPRING_DATASOURCE_URL`: The JDBC URL for the MySQL database.
- `MYSQL_ROOT_PASSWORD`: The root password for the MySQL database.
- `MYSQL_DATABASE`: The name of the database.
- `MYSQL_USER`: The username for the database.
- `MYSQL_PASSWORD`: The password for the database user.

## How to Build and Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. Build and start the containers:
   ```bash
   docker-compose up --build
   ```

3. Access the application:
   - The Spring Boot application will be available at [http://localhost:8080](http://localhost:8080).
   - The MySQL database will be accessible on port `3306`.

## Database Management with Liquibase

This project uses **Liquibase** to manage database schema changes. The main changelog file is located at:

```
src/main/resources/db/changelog/db.changelog-master.xml
```

### Key Features of the Changelog:
- **Schema Creation**: Defines the `persona` table and an audit table `persona_audit`.
- **Data Insertion**: Inserts initial data into the `persona` table.
- **Triggers**: Includes triggers for auditing `INSERT`, `UPDATE`, and `DELETE` operations on the `persona` table.

Liquibase automatically applies these changes when the application starts, based on the configuration in `application.properties`.

## Stopping the Containers

To stop the running containers, use:
```bash
docker-compose down
```

## Notes

- Ensure that port `8080` (for the Spring Boot app) and port `3306` (for MySQL) are not in use by other applications.
- The database credentials and connection URL are passed as environment variables to the Spring Boot application.

## Testing the API

You can test the API using the following PowerShell commands:

1. **Create a new Persona (POST request)**:
   ```powershell
   Invoke-RestMethod -Uri "http://localhost:8080/personas" -Method Post -ContentType "application/json" -Body '{"nombre": "Juan Pérez", "edad": 30}'
   ```

2. **Get Persona by ID (GET request)**:
   ```powershell
   Invoke-RestMethod -Uri "http://localhost:8080/personas/3" -Method Get
   ```

3. **Get all Personas (GET request)**:
   ```powershell
   Invoke-RestMethod -Uri "http://localhost:8080/personas" -Method Get
   ```

4. **Update Persona by ID (PUT request)**:
   ```powershell
   Invoke-RestMethod -Uri "http://localhost:8080/personas/2" -Method Put -ContentType "application/json" -Body '{"nombre": "Carlos García", "edad": 35}'
   ```
   
5. **Delete Persona by ID (DELETE request)**:
   ```powershell
   Invoke-RestMethod -Uri "http://localhost:8080/personas/2" -Method Delete
   ```