# 🧩 Course-Registration-System
## 📁 Folder Structure

```
mid-project-022382478/
├── README.md                       # This instruction file
├── .env.example                    # Example environment variables
├── docker-compose.yml              # Multi-container setup for all services
├── docs/                           # Documentation folder
│   ├── architecture.md             # Describe your system design here
│   ├── analysis-and-design.md      # Document system analysis and design details
│   ├── asset/                      # Store images, diagrams, or other visual assets for documentation
│   └── api-specs/                  # API specifications in OpenAPI (YAML)
│       ├── auth-service.yaml
│       ├── course-service.yaml
│       ├── student-service.yaml
│       ├── notification-service.yaml
│       └── registration-service.yaml
├── scripts/                        # Utility or deployment scripts
│   └── init.sh
├── services/                       # Application microservices
│   ├── auth-service/              # Service xác thực 
│   ├── course-service/            # Service quản lý khóa học
│   ├── student-service/           # Service quản lý học viên
│   ├── notification-service/      # Service thông báo
│   ├── service-registration/      # Service đăng ký khóa học
│   └── eureka-server/            # Service registry và discovery
└── gateway/                        # API Gateway
```

