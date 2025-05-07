# 📊 Microservices System - Analysis and Design

This document outlines the business logic analysis and service-oriented design for a specific business process (use case) in the microservices-based system.

*Reference*:
1. Service-Oriented Architecture Analysis and Design for Services and Microservices - 2nd Edition
2. Microservices Patterns With examples in Java
3. Bai tap - Phat trien phan mem huong dich vu - Hungdn - 2024
--- 

## 1. 🎯 Problem Statement

The system addresses the business process of student course registration. The goal is to allow students to view their information, browse available courses, register for courses, and view their registered courses through a simple web interface. The system is designed as a set of microservices to ensure scalability, maintainability, and clear separation of concerns.

---

## 2. 🧩 Service-Oriented Analysis

- **Main Steps in the Process:**
  1. User enters a student name and retrieves student information.
  2. User fetches the list of all available courses.
  3. User selects a course and registers for it.
  4. User retrieves the list of courses registered by a specific student.

- **Entities Involved:**
  - Student
  - Course
  - Registration (linking students and courses)

- **Challenges/Requirements Driving Microservices:**
  - Need for independent scaling of student, course, and registration logic.
  - Different data storage requirements (e.g., relational databases for students/courses, caching for performance).
  - Decoupled development and deployment for each business domain.
  - Integration with a message broker (Kafka) for event-driven communication and reliability.
  - Centralized API gateway (Nginx) for routing and security.
  - Service discovery (Eureka) for dynamic service registration and load balancing.
  - Containerization (Docker) for consistent deployment.

---

## 3. 🔄 Service-Oriented Design

- **Service Candidates:**
  - **Student Service:** Manages student data and provides APIs to retrieve student information.
  - **Course Service:** Manages course data and provides APIs to list and retrieve course details.
  - **Register Service:** Handles course registration logic, manages registration records, and provides APIs to register students for courses and fetch registered courses by student.

- **Service Capabilities:**
  - **Student Service:**
    - Get student by name
    - (Optionally) Create/update student information
  - **Course Service:**
    - Get all courses
    - Get course by ID
    - (Optionally) Create/update course information
  - **Register Service:**
    - Register a student for a course
    - Get all courses registered by a student

- **Interactions:**
  - The frontend (HTML + JavaScript) interacts with the system via the API Gateway (Nginx).
  - The API Gateway routes requests to the appropriate microservice.
  - Register Service may communicate with Student and Course Services to validate student and course existence before registration.
  - Kafka is used for asynchronous messaging (e.g., emitting registration events).
  - Redis is used for caching frequently accessed data (e.g., course lists).

- **Data Ownership:**
  - **Student Service:** Owns student data (stored in PostgreSQL or MySQL).
  - **Course Service:** Owns course data (stored in PostgreSQL or MySQL).
  - **Register Service:** Owns registration data (stored in PostgreSQL or MySQL).
  - **Redis:** Used for caching (e.g., course lists, student lookups).

- **API Specs:**
  - Complete API definitions are provided in `docs/api-specs/service-a.yaml` (e.g., Student or Course Service) and `docs/api-specs/service-b.yaml` (e.g., Register Service).

- **Supporting Technologies:**
  - **API Gateway:** Nginx
  - **Service Registry:** Eureka
  - **Message Broker:** Kafka
  - **Databases:** PostgreSQL, MySQL
  - **Cache:** Redis
  - **Containerization:** Docker
  - **Framework:** Spring Boot (for all services)

---