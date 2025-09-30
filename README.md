# SINAH Backend

This repository represents the Back-end of the SINAH Project (Integrated System for Hospital Notification and Analysis),
which aims to centralize, organize, and automate the monitoring process of healthcare-associated infections.

## Requirements

- [Make (Makefile)](https://makefiletutorial.com/)
- [Docker](https://www.docker.com/)
- [Java 21](https://www.java.com/en/download/help/download_options.html)
- [PostgreSQL](https://www.postgresql.org/)

## Development

The project follows the idea of being developed and executed without IDE dependency.

Everything can be executed using the `make` command:

```console
Usage:
  make <target>

Targets:
  Application
    run                      Setup environment, start containers and run spring application
    test                     Run tests
    fmt                      Format all java code
    lint                     Lint all java code
    validate                 Validate that system contains all requirements
  Build
    image-build              Build the project image
  Migration
    migrate                  Run validate + migrate + info
    migrate-new              Create a new versioned migration file. Usage: make migrate-new file=V1__init_schema.sql
    migrate-run              Run Flyway migrations
    migrate-validate         Validate migrations
    migrate-clean            Clean database
    migrate-info             Show migration info
  Docker
    docker-start             Start the local containers.
    docker-stop              Stop the local containers.
    docker-down              Remove the local containers.
    docker-reset             Stop, delete, build and start the local containers.
  Extra
    help                     Show this help.
```

### Setup of the .env file

First of all, you'll need to create a local .env file (see
the [.env.example](https://github.com/theproject-id/sinah-backend/blob/main/.env.example)) and configure all fields:

```
DATABASE_PORT=5432
DATABASE_USER=postgres
DATABASE_PASSWORD=postgres
DATABASE_NAME=sinah
DATABASE_URL=jdbc:postgresql://localhost:54326543/sinah

ALLOWED_ORIGINS=localhost:3000

JWT_REFRESH_TOKEN_TTL=1d
JWT_ACCESS_TOKEN_TTL=1h
JWT_ISSUER=sinah
JWT_AUDIENCE=http://localhost:8080
```

> [!NOTE]
> When running `make run` or `make docker-start`, all fields defined in the .env will be injected in your environment

### Validate requirements

This project requires `Docker` and `Java (21)` to be installed for run the database (PostgreSQL) and the API (Spring
Boot).

You can use the help command `make validate` to validate if you environment are ready to lauch the project.

```console
Validating system requirements...
✅ All requirements are installed
```

### Running the project in dev mode

After the setup of your .env file and environment, just run the `make run` command, help command will:

1. Inject all variables into your environment
2. Start a docker container for PostgreSQL at port `5432`
3. Start the Spring Boot app at port `8080`.

Open your browser and go to `http://localhost:8080/swagger-ui/index.html` to see all available routes for this project