# Registration Service

## Mô tả
Registration Service là một microservice quản lý quá trình đăng ký khóa học của học viên. Service này xử lý các yêu cầu đăng ký, hủy đăng ký, và quản lý trạng thái đăng ký khóa học của học viên.

## Công nghệ sử dụng
- Java Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven


## API Endpoints
- POST /api/registrations - Đăng ký khóa học mới
- GET /api/registrations/student/{studentId} - Lấy danh sách đăng ký của học viên
- GET /api/registrations/course/{courseId} - Lấy danh sách học viên đã đăng ký khóa học
- PUT /api/registrations/{id}/status - Cập nhật trạng thái đăng ký
- DELETE /api/registrations/{id} - Hủy đăng ký khóa học
- GET /api/registrations/{id} - Lấy thông tin chi tiết đăng ký

## Liên kết với các service khác
- Kết nối với Eureka Server để đăng ký service
- Tương tác với Student Service để xác thực thông tin học viên
- Tương tác với Course Service để kiểm tra thông tin khóa học
- Tương tác với Notification Service để gửi thông báo về trạng thái đăng ký

