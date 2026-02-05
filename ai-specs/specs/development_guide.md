# Development Guide

This guide provides step-by-step instructions for setting up the development environment and running tests for the Landing Backend application.

## 🚀 Setup Instructions

### Prerequisites

Ensure you have the following installed:
- **Java 21** (JDK)
- **Gradle 8.x** (or use Gradle Wrapper)
- **PostgreSQL** (v12 or higher)
- **Git**

### 1. Clone the Repository

```bash
git clone <repository-url>
cd landing-backend
```

### 2. Environment Configuration

Create or update the application configuration file:

**Application Configuration** (`src/main/resources/application.yaml`):
```yaml
spring:
  application:
    name: landing-backend

  datasource:
    url: jdbc:postgresql://localhost:5432/landingdb
    username: postgres
    password: example
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true

  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true

server:
  port: 8080
```

**Profile-Specific Configuration** (Optional):
- `application-dev.yaml` - Development environment
- `application-prod.yaml` - Production environment

### 3. Database Setup

#### Option A: Local PostgreSQL Installation

1. Install PostgreSQL on your machine
2. Create the database:
```bash
createdb landingdb
```

3. Update `application.yaml` with your database credentials

#### Option B: Docker PostgreSQL

```bash
# Run PostgreSQL in Docker
docker run --name landing-postgres \
  -e POSTGRES_PASSWORD=example \
  -e POSTGRES_DB=landingdb \
  -p 5432:5432 \
  -d postgres:15
```

### 4. Backend Setup

```bash
# Navigate to project root
cd landing-backend

# Build the project (downloads dependencies)
./gradlew build

# Run Flyway migrations
./gradlew flywayMigrate

# Start the development server
./gradlew bootRun
```

The backend API will be available at `http://localhost:8080`

### 5. Verify Installation

Test the application is running:
```bash
# Health check (if actuator is configured)
curl http://localhost:8080/actuator/health

# Or test an endpoint
curl http://localhost:8080/api/inclusive/1/timeline
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests with coverage
./gradlew test jacocoTestReport

# Run tests in watch mode (if configured)
./gradlew test --continuous
```

### Test Coverage

The project requires 90% test coverage. Generate coverage reports:

```bash
# Generate coverage report
./gradlew jacocoTestReport

# View HTML report (if jacoco plugin configured)
open build/reports/jacoco/test/html/index.html
```

### Test Structure

Tests are located in `src/test/java/` mirroring the main source structure:
```
src/test/java/com/inclusive/landing/
├── controller/
│   └── TimelineControllerTest.java
├── service/
│   └── TimelineServiceTest.java
└── repository/
    └── TimelineRepositoryTest.java
```

## 🛠️ Development Commands

### Build Commands

```bash
# Clean build artifacts
./gradlew clean

# Build the project
./gradlew build

# Build without tests
./gradlew build -x test

# Create executable JAR
./gradlew bootJar
```

### Database Commands

```bash
# Run Flyway migrations
./gradlew flywayMigrate

# Validate migrations
./gradlew flywayValidate

# Repair Flyway schema history
./gradlew flywayRepair
```

### Running the Application

```bash
# Run in development mode
./gradlew bootRun

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'

# Run JAR file
java -jar build/libs/landing-backend-0.0.1-SNAPSHOT.jar
```

## 📝 Code Quality

### Code Formatting

Ensure code follows Java style guidelines:
- Use 4 spaces for indentation (not tabs)
- Follow Java naming conventions
- Keep methods focused and small
- Use meaningful variable and method names

### Static Analysis

If configured, run static analysis tools:
```bash
# Checkstyle (if configured)
./gradlew checkstyleMain

# PMD (if configured)
./gradlew pmdMain
```

## 🔧 IDE Setup

### IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Import Gradle project when prompted
3. Ensure Java 21 SDK is configured
4. Install Lombok plugin (if using Lombok)
5. Enable annotation processing

### VS Code

1. Install Java Extension Pack
2. Install Spring Boot Extension Pack
3. Open the project folder
4. VS Code will detect Gradle project automatically

### Eclipse

1. Import as Gradle project
2. Ensure Java 21 is configured
3. Install Spring Tools Suite (STS) plugin

## 🐛 Troubleshooting

### Common Issues

**Issue: Port 8080 already in use**
```bash
# Change port in application.yaml
server:
  port: 8081
```

**Issue: Database connection failed**
- Verify PostgreSQL is running
- Check database credentials in `application.yaml`
- Ensure database `landingdb` exists

**Issue: Flyway migration failed**
- Check migration file syntax
- Verify database connection
- Check Flyway schema history table

**Issue: Tests failing**
- Ensure database is accessible
- Check test configuration
- Verify test data setup

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [Flyway Documentation](https://flywaydb.org/documentation/)
- [Gradle Documentation](https://docs.gradle.org/)
- [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)