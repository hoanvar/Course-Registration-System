# System Architecture

## Overview
The microservices system enables students to register for courses and view their registered courses through a web interface. The architecture is designed for scalability, maintainability, and clear separation of concerns, leveraging modern technologies such as Nginx (API Gateway), Kafka (message broker), Redis (cache), Eureka (service registry), and Docker (containerization).

## System Components
- **Student Service**: Manages student data and provides APIs to retrieve student information.
- **Course Service**: Manages course data and provides APIs to list and retrieve course details. Frequently accessed course data is cached in Redis for performance.
- **Register Service**: Handles course registration logic, manages registration records, and provides APIs to register students for courses and fetch registered courses by student. Acts as the orchestrator for registration and query flows.
- **API Gateway (Nginx)**: Serves as the single entry point for all client requests, routing them to the appropriate backend services and providing basic security and load balancing.
- **Kafka**: Used as a message broker for asynchronous communication between services, especially for fetching detailed information during registration queries.
- **Redis**: Used as a cache for course data to improve performance and reduce database load.
- **Eureka**: Provides service discovery, allowing services to dynamically register and locate each other.

## Communication
- **REST APIs**: The frontend communicates with backend services via RESTful APIs exposed through the API Gateway (Nginx).
- **Message Queues (Kafka)**: Used for asynchronous, event-driven communication between Register Service, Student Service, and Course Service, especially when fetching detailed information about students and courses.
- **Internal Networking**: Docker Compose service names are used for internal service-to-service communication.

## Data Flow
- **Course Registration Flow:**
  1. The user clicks the "Register" button on the frontend, submitting a registration request.
  2. The frontend sends a POST request (containing only `student_id` and `course_id`) to the API Gateway (Nginx).
  3. Nginx forwards the request to the Register Service.
  4. Register Service saves the registration record in its database.

- **View Registered Courses Flow:**
  1. The user clicks to view the courses registered by a student.
  2. The frontend sends a request to the API Gateway, which routes it to the Register Service.
  3. Register Service retrieves the list of registered course IDs for the student from its database.
  4. Register Service uses Kafka to asynchronously request detailed student information from Student Service and course information from Course Service.
  5. Course Service retrieves course details from Redis cache (if available) or its database (if not cached), then responds via Kafka.
  6. Student Service responds with student details via Kafka.
  7. Register Service aggregates the information and returns the complete list of registered courses (with details) to the frontend.

- **External Dependencies:**
  - PostgreSQL/MySQL databases for persistent storage in each service.
  - Redis for caching course data.
  - Kafka for message brokering.

## Diagram
- A high-level architecture diagram should be placed in `docs/asset/` to visually represent the components and their interactions.

## Scalability & Fault Tolerance
- Each service can be independently scaled based on load (e.g., more instances of Register Service during peak registration periods).
- Redis caching reduces load on the Course Service database and improves response times.
- Kafka decouples services and provides resilience for asynchronous operations.
- Nginx and Eureka enable load balancing and dynamic service discovery, supporting high availability and failover.