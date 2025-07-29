# Book Library Backend

## Prerequisites
- Java 11+
- Maven
- Docker & Docker Compose

## Build & Run

1. Build the backend WAR:
   mvn clean package

2. Start MySQL & Payara with Docker Compose:
   docker-compose up

   (First run may take a few minutes as the DB and app are initialized.)

3. The backend REST API will be available at:
   http://localhost:8080/book-library-backend/api/books

## Database Details

- DB: librarydb
- User: libraryuser
- Password: librarypass
