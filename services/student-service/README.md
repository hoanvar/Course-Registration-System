# Student Service

## Mô tả
Student Service là một microservice quản lý thông tin và dữ liệu của học viên trong hệ thống. Service này cung cấp các chức năng quản lý hồ sơ học viên, theo dõi tiến độ học tập, và xử lý các yêu cầu liên quan đến học viên.

## Công nghệ sử dụng
- Java Spring Boot
- Spring Data JPA
- ProgreSQL
- Docker
- Maven


## API Endpoints
- GET /api/students - Lấy danh sách học viên
- GET /api/students/{id} - Lấy thông tin chi tiết học viên
- POST /api/students - Tạo hồ sơ học viên mới
- PUT /api/students/{id} - Cập nhật thông tin học viên
- DELETE /api/students/{id} - Xóa hồ sơ học viên

## Liên kết với các service khác
- Kết nối với Eureka Server để đăng ký service
- Tương tác với Registration Service để quản lý đăng ký khóa học
