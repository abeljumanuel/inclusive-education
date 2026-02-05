Please analyze and implement the backend feature according to the implementation plan: $ARGUMENTS.

Follow these steps:

1. Understand the problem and implementation plan described in the ticket/plan file
2. Search the codebase for relevant files
3. Start a new branch using the ID of the ticket (for example `feature/SCRUM-1-backend`) if not already on it
4. Implement the necessary changes following the implementation plan, making sure you accomplish all steps in order:
   - Create JPA entities if needed
   - Create/update repository interfaces (Spring Data JPA)
   - Create/update service interfaces and implementations
   - Create/update DTOs
   - Create/update REST controllers
   - Create Flyway migrations if database schema changes are needed
   - Write comprehensive unit tests (JUnit 5, Mockito) with 90% coverage threshold
   - Update documentation
5. Ensure code compiles without errors
6. Run tests: `./gradlew test`
7. Ensure all tests pass and coverage meets 90% threshold
8. Stage only the files affected by the ticket, and leave any other file changed out of the commit. Create a descriptive commit message
9. Push and create a PR, using the ID of the ticket (for example SCRUM-1) so it gets linked in Jira ticket

Remember to follow Spring Boot best practices, DDD layered architecture, and all project standards from `ai-specs/specs/backend-standards.mdc`.