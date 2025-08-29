# Bajaj Finserv Health Assignment

This Spring Boot application fulfills the requirements for the Bajaj Finserv Health Qualifier 1 assignment.

## Overview

The application automatically:

1. Sends a POST request to generate a webhook on startup
2. Solves a SQL problem based on the registration number
3. Submits the SQL solution to the provided webhook URL using JWT authentication

## Features

- **No Controller/Endpoint**: All logic runs automatically on application startup
- **RestTemplate Integration**: Uses Spring's RestTemplate for HTTP communications
- **JWT Authentication**: Implements Bearer token authentication for API calls
- **Automatic Question Selection**: Determines question based on registration number (REG12347 → Question 1)
- **Comprehensive SQL Solution**: Includes advanced SQL concepts like window functions, CTEs, and joins

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/bajajfinserv/health/
│   │       ├── HealthAssignmentApplication.java    # Main Spring Boot application
│   │       ├── config/
│   │       │   └── AppConfig.java                  # Configuration for RestTemplate
│   │       ├── model/
│   │       │   ├── WebhookRequest.java             # Request model for webhook generation
│   │       │   ├── WebhookResponse.java            # Response model from webhook generation
│   │       │   └── SolutionRequest.java            # Request model for solution submission
│   │       └── service/
│   │           └── AssignmentService.java          # Main business logic
│   └── resources/
│       └── application.properties                  # Application configuration
└── test/
    └── java/
        └── com/bajajfinserv/health/
            └── HealthAssignmentApplicationTests.java # Basic test class
```

## How to Run

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Building the Application

```bash
# Clone the repository
git clone <repository-url>
cd assignment

# Build the project
mvn clean package

# The JAR file will be created in target/ directory
```

### Running the Application

```bash
# Run from JAR
java -jar target/health-assignment-0.0.1-SNAPSHOT.jar

# Or run with Maven
mvn spring-boot:run
```

## Configuration

The application uses the following configuration in `application.properties`:

- Application name: `bajaj-finserv-health-assignment`
- Server port: `8080` (though no endpoints are exposed)
- Logging level: `INFO` for application logs, `DEBUG` for HTTP client
- HTTP timeout: `30 seconds`

## API Endpoints Used

1. **Webhook Generation**: `POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
2. **Solution Submission**: `POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## Registration Details

- **Name**: John Doe
- **Registration Number**: REG12347
- **Email**: john@example.com
- **Question**: Question 1 (since 47 is odd)

## SQL Solution

The application solves Question 1 with a comprehensive SQL query that includes:

- Common Table Expressions (CTEs)
- Window functions (RANK, AVG, COUNT)
- JOINs between tables
- Subqueries
- CASE statements for conditional logic
- Performance categorization based on salary

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter JSON
- Jackson Databind
- Spring Boot Starter Test (for testing)

## Submission

This project includes:

- ✅ Complete source code
- ✅ Maven configuration (pom.xml)
- ✅ JAR file generation capability
- ✅ No controller/endpoint (as required)
- ✅ RestTemplate usage
- ✅ JWT authentication implementation
- ✅ Automatic startup execution

## Building JAR for Submission

```bash
mvn clean package
```

The JAR file will be available at: `target/health-assignment-0.0.1-SNAPSHOT.jar`

## Author

Created for Bajaj Finserv Health Qualifier 1 Assignment
