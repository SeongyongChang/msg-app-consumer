# Kafka Message Consumer Project

## Project Goal

This project aims to consume messages from a Confluent Kafka topic named `THKLD01`. Based on the message content, the data will be categorized and stored into three different database tables: `AccountingLog`, `ReceptionLog`, and `EbankingLog`. Additionally, a REST API will be provided to view the stored data, along with an HTTP client script for testing.

## Environment

This project requires **Java 11** or higher to run.

## Implementation Plan (Checklist)

- [x] **1. `pom.xml` Configuration**
  - [x] Add Spring Kafka dependency.
  - [x] Add Spring Data JPA dependency.
  - [x] Add H2 Database dependency (for development/testing).

- [x] **2. `application.properties` Configuration**
  - [x] Configure Kafka consumer properties (bootstrap servers, group ID, etc.).
  - [x] Configure H2 database connection properties.
  - [x] Configure JPA properties (DDL auto, show SQL).

- [x] **3. Package and Class Structure Setup**
  - [x] Create `config` package.
  - [x] Create `controller` package.
  - [x] Create `domain` package.
  - [x] Create `repository` package.
  - [x] Create `service` package.

- [x] **4. Domain (Entity) Class Creation**
  - [x] Create `AccountingLog` entity class.
  - [x] Create `ReceptionLog` entity class.
  - [x] Create `EbankingLog` entity class.
  - [x] Define appropriate fields, data types, and JPA annotations for each entity.

- [x] **5. Repository Interface Creation**
  - [x] Create `AccountingLogRepository` interface (extending `JpaRepository`).
  - [x] Create `ReceptionLogRepository` interface (extending `JpaRepository`).
  - [x] Create `EbankingLogRepository` interface (extending `JpaRepository`).

- [x] **6. Kafka Consumer (Service) Implementation**
  - [x] Create a Kafka consumer service class (e.g., `KafkaConsumerService`).
  - [x] Implement a method annotated with `@KafkaListener` to subscribe to the `THKLD01` topic.
  - [x] Implement logic to parse incoming Kafka messages.
  - [x] Implement conditional logic to determine the message type (Accounting, Reception, Ebanking).
  - [x] Use respective repositories to save data into the correct database table.
  - [x] Implement error handling for message processing and database operations.

- [x] **7. Controller (REST API) Implementation**
  - [x] Create a REST controller class (e.g., `LogController`).
  - [x] Implement an endpoint to retrieve all `AccountingLog` entries.
  - [x] Implement an endpoint to retrieve all `ReceptionLog` entries.
  - [x] Implement an endpoint to retrieve all `EbankingLog` entries.
  - [x] Ensure proper HTTP methods (GET) and response formats (JSON).

- [x] **8. HTTP Client Script for Testing**
  - [x] Create a `test.http` file in the project root.
  - [x] Add HTTP requests to test each of the implemented API endpoints (e.g., GET requests for each log type).
