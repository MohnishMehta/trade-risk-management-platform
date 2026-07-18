# Trade Risk Management Platform

Trade Risk Management Platform is an enterprise-style backend application for portfolio management, trade execution, risk monitoring, and compliance alert workflows. The project simulates how a financial institution could track portfolios, process trades, monitor risk exposure, flag suspicious trading activity, and maintain audit-ready records.

## Tech Stack

- Java 25
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker Compose
- Maven
- Postman
- Git / GitHub
- Jira

## Planned Additions

- Spring Security with JWT authentication
- Swagger / OpenAPI documentation
- Python / FastAPI risk analytics service
- Redis caching
- Kafka event streaming
- React dashboard
- CI/CD and cloud deployment

## Current Features

- Spring Boot backend project setup
- Custom health check endpoint
- PostgreSQL database running through Docker Compose
- Spring Boot database connection configured
- Flyway migration setup
- Initial database schema for users and roles

## Project Structure

```text
src/main/java/com/traderisk
├── health
│   └── HealthController.java
└── TradeRiskManagementPlatformApplication.java

src/main/resources
├── db
│   └── migration
│       └── V1__create_users_and_roles.sql
└── application.properties
```

## Database Schema

The first Flyway migration creates the initial identity schema for the application.

Created tables:

- `roles`
- `app_users`
- `flyway_schema_history`

The `roles` table is seeded with the following system roles:

- `TRADER`
- `RISK_ANALYST`
- `COMPLIANCE_ANALYST`
- `ADMIN`
- `AUDITOR`

The `app_users` table is currently empty and will be populated later through user management and authentication workflows.

## Running the Project Locally

### 1. Start PostgreSQL with Docker Compose

```bash
docker compose up -d
```

PostgreSQL runs locally on port `5433`.

The Docker container runs PostgreSQL internally on port `5432`, but the project maps it to local port `5433` to avoid conflicts with other local PostgreSQL instances.

### 2. Verify PostgreSQL is running

```bash
docker ps
```

You should see a container named:

```text
traderisk-postgres
```

with port mapping similar to:

```text
0.0.0.0:5433->5432/tcp
```

### 3. Run the Spring Boot application

```bash
./mvnw spring-boot:run
```

The application starts on port `8080`.

### 4. Test the health endpoint

Open this URL in a browser or Postman:

```text
http://localhost:8080/api/v1/health
```

Expected response:

```text
Trade Risk Platform is running
```

## Database Connection

The application connects to PostgreSQL using the following local development configuration:

```properties
spring.datasource.url=jdbc:postgresql://127.0.0.1:5433/traderisk
spring.datasource.username=traderisk
spring.datasource.password=traderisk_local
```

Flyway is enabled and automatically applies database migrations from:

```text
src/main/resources/db/migration
```

## Useful Docker Commands

Start PostgreSQL:

```bash
docker compose up -d
```

Stop PostgreSQL:

```bash
docker compose down
```

Stop PostgreSQL and delete the local database volume:

```bash
docker compose down -v
```

Connect to PostgreSQL:

```bash
docker exec -it traderisk-postgres psql -U traderisk -d traderisk
```

List database tables inside `psql`:

```sql
\dt
```

Exit `psql`:

```sql
\q
```

## Useful Maven Commands

Clean and compile the project:

```bash
./mvnw clean compile
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

## Roadmap

Future development will include:

- Portfolio creation and management
- Instrument and market data tracking
- Market order submission and trade execution
- Position and profit/loss tracking
- Risk metric calculations
- Trade surveillance rules
- Compliance alert investigation workflows
- Audit logging
- Authentication and role-based authorization
- Python-based risk analytics service
- Redis caching and Kafka event-driven workflows
- Cloud deployment with CI/CD

## Project Goal

The goal of this project is to build a realistic backend system that demonstrates enterprise software engineering practices, including REST API design, relational database modeling, schema migrations, Dockerized infrastructure, role-based workflows, risk monitoring, and compliance-focused auditability.