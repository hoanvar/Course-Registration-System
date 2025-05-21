# Auth Service

## Mô tả
Auth Service là một microservice chịu trách nhiệm xử lý xác thực và phân quyền trong hệ thống. Service này cung cấp các chức năng đăng nhập, đăng ký và quản lý token cho người dùng.

## Công nghệ sử dụng
- Node.js
- Express.js
- JWT (JSON Web Tokens)
- Docker


## API Endpoints
- POST /api/auth/login - Đăng nhập
- GET /api/auth/validate - Xác thực token

## Liên kết với các service khác
- Kết nối với Eureka Server để đăng ký service
