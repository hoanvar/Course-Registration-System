# Course Service

## Mô tả
Course Service là một microservice quản lý thông tin về các khóa học trong hệ thống. Service này cung cấp các chức năng CRUD cho khóa học, quản lý danh sách học viên, và xử lý các yêu cầu liên quan đến khóa học.

## Công nghệ sử dụng
- Java Spring Boot
- Spring Data JPA
- PostgreSQL
- Redis
- Docker
- Maven

## API Endpoints
- GET /api/courses - Lấy danh sách khóa học
- GET /api/courses/{id} - Lấy thông tin chi tiết khóa học
- POST /api/courses - Tạo khóa học mới
- PUT /api/courses/{id} - Cập nhật thông tin khóa học
- DELETE /api/courses/{id} - Xóa khóa học
- GET /api/courses/{id}/students - Lấy danh sách học viên của khóa học

## Liên kết với các service khác
- Kết nối với Eureka Server để đăng ký service
- Tương tác với Registration Service để quản lý thông tin đăng ký khóa học
