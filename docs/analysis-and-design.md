# 📊 Microservices System - Analysis and Design

<<<<<<< HEAD
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
=======
This document outlines the **analysis** and **design** process for your microservices-based system assignment. Use it to explain your thinking and architecture decisions.

---

## 1. 🎯 Problem Statement

_Describe the problem your system is solving._

- Who are the users?
- What are the main goals?
- What kind of data is processed?

> Example: A course management system that allows students to register for courses and teachers to manage class rosters.

---

## 2. 🧩 Identified Microservices

List the microservices in your system and their responsibilities.

| Service Name  | Responsibility                                | Tech Stack   |
|---------------|------------------------------------------------|--------------|
| service-a     | Handles user authentication and authorization | Python Flask |
| service-b     | Manages course registration and class data    | Python Flask |
| gateway       | Routes requests to services                   | Nginx / Flask|

---

## 3. 🔄 Service Communication

Describe how your services communicate (e.g., REST APIs, message queue, gRPC).

- Gateway ⇄ service-a (REST)
- Gateway ⇄ service-b (REST)
- Internal: service-a ⇄ service-b (optional)

---

## 4. 🗂️ Data Design

Describe how data is structured and stored in each service.

- service-a: User accounts, credentials
- service-b: Course catalog, registrations

Use diagrams if possible (DB schema, ERD, etc.)

---

## 5. 🔐 Security Considerations

- Use JWT for user sessions
- Validate input on each service
- Role-based access control for APIs

---


## 6. 📦 Deployment Plan

- Use `docker-compose` to manage local environment
- Each service has its own Dockerfile
- Environment config stored in `.env` file

---

## 7. 🎨 Architecture Diagram

> *(You can add an image or ASCII diagram below)*

```
+---------+        +--------------+
| Gateway | <----> | Service A    |
|         | <----> | Auth Service |
+---------+        +--------------+
       |                ^
       v                |
+--------------+   +------------------+
| Service B    |   | Database / Redis |
| Course Mgmt  |   +------------------+
+--------------+
```

---

## ✅ Summary

Summarize why this architecture is suitable for your use case, how it scales, and how it supports independent development and deployment.



## Author

This template was created by Hung Dang.
- Email: hungdn@ptit.edu.vn
- GitHub: hungdn1701


Good luck! 💪🚀
>>>>>>> ac6b69dd4543d6b9a4e7c72cecd334f5aab5c952
