# Inclusive Education Landing Backend

A Spring Boot REST API backend application that serves as the foundation for a web page about Inclusive Education. This application systematizes and documents inclusive education experiences in Colombian schools, providing structured data about schools, timelines, research findings, teacher interviews, appendices, and image galleries.

## 🎯 Project Overview

This backend application provides a RESTful API to support a web page focused on documenting and presenting research on inclusive education practices. The system tracks the evolution of inclusive education practices at various schools, documents research findings, manages teacher testimonies, and provides access to supporting materials and visual documentation.

## 🏗️ Architecture

- **Framework**: Spring Boot 3.5.7
- **Language**: Java 21
- **Database**: PostgreSQL (with Flyway migrations)
- **Build Tool**: Gradle
- **API Documentation**: OpenAPI 3.0 specification
- **Architecture Pattern**: Layered architecture (Controller → Service → Repository)

## 📋 Features

- **Timeline Management**: Track chronological evolution of inclusive education practices at schools
- **Research Findings**: Document findings with detailed modal information
- **Interview Management**: Store and retrieve teacher quotes and testimonies
- **Appendix Management**: Provide access to research materials and documents
- **Image Gallery**: Manage visual documentation of inclusive education practices

## 🚀 Quick Start

### Prerequisites

- Java 21 (JDK)
- Gradle 8.x (or use Gradle Wrapper)
- PostgreSQL (v12 or higher)

### Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd inclusive-education
   ```

2. **Configure database** in `src/main/resources/application.yaml`
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/landingdb
       username: postgres
       password: your-password
   ```

3. **Build and run**
   ```bash
   ./gradlew build
   ./gradlew bootRun
   ```

4. **Access the API**
   - Base URL: `http://localhost:8080`
   - API endpoints: `http://localhost:8080/api/inclusive/...`

For detailed setup instructions, see [Development Guide](./ai-specs/specs/development_guide.md).

## 📡 API Endpoints

All endpoints return JSON responses and are prefixed with `/api/inclusive`.

### Timeline

**Get timeline for a specific school**
- `GET /api/inclusive/{schoolId}/timeline`
- **Description**: Retrieve the chronological timeline of inclusive education practices for a specific school
- **Parameters**:
  - `schoolId` (path, required): School identifier (Long)
- **Response**: `TimelineDTO` with school information and timeline entries
- **Status Codes**: 200 (Success), 400 (Invalid school ID), 404 (School not found), 500 (Server error)

### Findings

**Get all research findings**
- `GET /api/inclusive/finding`
- **Description**: Retrieve all research findings with their associated modal details
- **Response**: Array of `FindingDTO` objects
- **Status Codes**: 200 (Success), 500 (Server error)

### Interviews

**Get all teacher interviews**
- `GET /api/inclusive/interviews`
- **Description**: Retrieve all teacher interviews/testimonies with school information
- **Response**: Array of `InterviewDTO` objects
- **Status Codes**: 200 (Success), 500 (Server error)

### Appendices

**Get all appendices**
- `GET /api/inclusive/appendices`
- **Description**: Retrieve all research material appendices with their function URLs
- **Response**: Array of `AppendixDTO` objects
- **Status Codes**: 200 (Success), 500 (Server error)

### Images

**Get all gallery images**
- `GET /api/inclusive/image`
- **Description**: Retrieve all gallery images with their descriptions
- **Response**: Array of `ImageDTO` objects
- **Status Codes**: 200 (Success), 500 (Server error)

## 📚 API Documentation

Full API documentation is available in OpenAPI 3.0 format:
- **Specification**: [`ai-specs/specs/api-spec.yml`](./ai-specs/specs/api-spec.yml)
- **Data Models**: [`ai-specs/specs/data-model.md`](./ai-specs/specs/data-model.md)

The API specification includes:
- Complete endpoint documentation
- Request/response schemas
- Error response formats
- Status codes and descriptions

## 🗄️ Database Schema

The application uses PostgreSQL with the following main entities:

- **School**: Educational institutions participating in the project
- **TimeLine**: Chronological entries documenting evolution of practices
- **Finding**: Research findings with associated modal details
- **ModalFinding**: Detailed information displayed in modal dialogs
- **Interview**: Teacher interviews and testimonies
- **Appendix**: Supplementary research materials and documents
- **Image**: Gallery images with descriptions

Database migrations are managed through Flyway and located in `src/main/resources/db/migration/`:
- `V001__tables_base.sql`: Base table structure
- `V002__school_seed.sql`: Initial school data
- `V003__timeline_seed.sql`: Timeline entries
- `V004__finding_seed.sql`: Findings and modal details
- `V005__inverviewee_seed.sql`: Interview/testimony data
- `V006__appendix_seed.sql`: Appendix/research material links
- `V007__gallery_seed.sql`: Gallery images

For detailed data model documentation, see [`ai-specs/specs/data-model.md`](./ai-specs/specs/data-model.md).

## 🧪 Testing

Run tests with:
```bash
./gradlew test
```

The project follows Test-Driven Development (TDD) principles and requires 90%+ test coverage. Test files are located in `src/test/java/` mirroring the main source structure.

## 📖 Specs Driven Development

This project follows **Specs Driven Development (SDD)** methodology, where comprehensive specifications guide the development process. All specifications are maintained in the `ai-specs/` directory:

### Specification Structure

```
ai-specs/
├── specs/                    # Core specifications
│   ├── api-spec.yml         # OpenAPI API specification
│   ├── data-model.md        # Database and domain models
│   ├── backend-standards.mdc # Backend development standards
│   ├── frontend-standards.mdc # Frontend development standards
│   ├── documentation-standards.mdc # Documentation guidelines
│   ├── base-standards.mdc   # Core development rules
│   └── development_guide.md # Setup and development guide
├── changes/                  # Feature implementation plans
│   └── SCRUM-10_backend.md  # Example implementation plan
└── .commands/               # Development command templates
```

### Key Benefits

- **Single Source of Truth**: All specifications centralized in `ai-specs/`
- **Consistent Development**: Standards ensure uniform code quality
- **Comprehensive Documentation**: API specs, data models, and standards always current
- **AI-Assisted Development**: Specifications enable autonomous AI agent implementation
- **Living Documentation**: Specifications evolve with the project

### Development Workflow

1. **Specification First**: Features are specified before implementation
2. **Standards Compliance**: All code follows standards in `ai-specs/specs/`
3. **Test-Driven**: TDD approach with 90%+ coverage requirement
4. **Documentation**: API specs and data models updated with code changes

For more information about the development standards and workflow, see:
- [`AGENTS.md`](./AGENTS.md) - Development rules and guidelines
- [`CLAUDE.md`](./CLAUDE.md) - Claude/Cursor specific configuration
- [`ai-specs/specs/base-standards.mdc`](./ai-specs/specs/base-standards.mdc) - Core development principles

## 🚢 Deployment

The application can be deployed to Azure Container Apps with PostgreSQL. For detailed deployment instructions, see [`DEPLOY.md`](./DEPLOY.md).

### Quick Deployment Steps

1. Configure Azure resources (PostgreSQL, Container Apps)
2. Update `application.yaml` with production database connection
3. Build the application: `./gradlew clean bootJar`
4. Build Docker image: `docker build -t landing-backend:0.0.1 .`
5. Deploy to Azure: `az containerapp up ...`

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/inclusive/landing/
│   │   ├── controller/      # REST controllers
│   │   ├── service/         # Business logic layer
│   │   ├── repository/      # Data access layer
│   │   ├── model/           # Entity models
│   │   ├── dto/             # Data Transfer Objects
│   │   └── error/           # Error handling
│   └── resources/
│       ├── application.yaml # Application configuration
│       └── db/migration/    # Flyway migrations
└── test/
    └── java/                # Test files
```

## 🔧 Configuration

Main configuration file: `src/main/resources/application.yaml`

Key settings:
- **Server Port**: 8080 (default)
- **Database**: PostgreSQL with Flyway migrations
- **Static Resources**: Served from `classpath:/static/`
- **Logging**: Configured in `application.yaml`

## 📝 Development Standards

This project follows strict development standards:

- **Small Tasks**: Work incrementally, one step at a time
- **Test-Driven Development**: Write failing tests first
- **Type Safety**: Fully typed code
- **Clear Naming**: Descriptive variables and functions
- **English Only**: All code, comments, and documentation in English
- **90%+ Test Coverage**: Comprehensive testing across all layers

For detailed standards, see:
- [`ai-specs/specs/backend-standards.mdc`](./ai-specs/specs/backend-standards.mdc)
- [`ai-specs/specs/base-standards.mdc`](./ai-specs/specs/base-standards.mdc)

## 🤝 Contributing

When contributing to this project:

1. Follow the development standards in `ai-specs/specs/`
2. Write tests for all new functionality (TDD)
3. Update API documentation in `ai-specs/specs/api-spec.yml`
4. Update data model documentation if schema changes
5. Ensure 90%+ test coverage
6. Use English for all code, comments, and documentation

## 📄 License

Copyright (c) 2025 Sistematización Educación Inclusiva. Todos los derechos reservados.
Licensed under the MIT License

---

**Made with 🤖 by the Universidad de Manizales**

For questions, issues, or suggestions, visit [Juan Rojas Software Engineer](https://www.linkedin.com/in/juan-rojas-software-engineer/)
