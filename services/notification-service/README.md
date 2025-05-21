# Notification Service

## Mô tả
Notification Service là một microservice chịu trách nhiệm gửi và quản lý các thông báo trong hệ thống. Service này hỗ trợ nhiều kênh thông báo khác nhau như email, SMS, và thông báo trong ứng dụng.

## Công nghệ sử dụng
- Java Spring Boot
- Spring Cloud
- Kafka
- SMTP (cho email)
- Docker



## Liên kết với các service khác
- Kết nối với Eureka Server để đăng ký service
- Tương tác với Registration Service để thông báo đăng ký khóa học