# BHstore - Hệ Thống Bán Giày Online

BHstore là một ứng dụng web toàn diện dành cho bán lẻ giày trực tuyến, được xây dựng với công nghệ hiện đại kết hợp giữa backend Spring Boot và frontend React.

## Mục Lục
- [Tính Năng](#tính-năng)
- [Công Nghệ Sử Dụng](#công-nghệ-sử-dụng)
- [Yêu Cầu Hệ Thống](#yêu-cầu-hệ-thống)
- [Cài Đặt](#cài-đặt)
- [Chạy Ứng Dụng](#chạy-ứng-dụng)
- [Cấu Trúc Dự Án](#cấu-trúc-dự-án)
- [API Endpoints](#api-endpoints)
- [Database](#database)

## Tính Năng

### Cho Khách Hàng
- 🛍️ Duyệt và tìm kiếm sản phẩm giày
- 📋 Xem chi tiết sản phẩm (giá, hình ảnh, mô tả)
- 🛒 Thêm sản phẩm vào giỏ hàng
- 💳 Thanh toán trực tuyến
- 👤 Đăng ký và đăng nhập tài khoản
- 📦 Xem lịch sử đơn hàng
- ⭐ Lọc sản phẩm theo:
  - Thương hiệu (Nike, Adidas, Puma, Bitis, Converse, New Balance)
  - Giới tính (Nam, Nữ)
  - Khoảng giá

### Cho Quản Trị Viên
- 📊 Dashboard quản lý
- ➕ Thêm, sửa, xóa sản phẩm
- 👥 Quản lý khách hàng và người dùng
- 📦 Quản lý danh mục sản phẩm
- 📋 Quản lý đơn hàng

## Công Nghệ Sử Dụng

### Backend
- **Spring Boot 3.3.1** - Framework Java chính
- **Spring Data JPA** - ORM cho cơ sở dữ liệu
- **Spring Security** - Xác thực và phân quyền
- **MySQL** - Cơ sở dữ liệu
- **Lombok** - Giảm code boilerplate
- **Thymeleaf** - Template engine

### Frontend
- **React 18.2.0** - Thư viện UI
- **React Router v6** - Định tuyến
- **Axios** - HTTP client
- **Bootstrap 5** - CSS Framework
- **React Toastify** - Thông báo

### Build & Development
- **Maven** - Build tool cho Java
- **npm** - Package manager cho Node.js
- **Docker** (tùy chọn) - Containerization

## Yêu Cầu Hệ Thống

### Phần Cứng
- CPU: Intel Core i5 hoặc tương đương
- RAM: 4GB tối thiểu
- Ổ cứng: 2GB không gian trống

### Phần Mềm
- **Java JDK 21** trở lên
- **MySQL 8.0** trở lên
- **Node.js 16** trở lên
- **npm 7** hoặc **yarn**
- **Maven 3.8** trở lên

## Cài Đặt

### 1. Clone Repository
```bash
git clone <repository-url>
cd DoanJava
# Tạo cơ sở dữ liệu MySQL
mysql -u root -p
CREATE DATABASE bhdb;
EXIT;
# Tên ứng dụng
spring.application.name=BHstore

# Kết nối MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/bhdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Port
server.port=8081
mvn clean install
cd frontend
npm install
# Từ thư mục gốc (DoanJava)
mvn spring-boot:run

# Hoặc
mvn clean spring-boot:run
# Từ thư mục frontend
npm start
# Terminal 1 - Backend
mvn spring-boot:run

# Terminal 2 - Frontend
cd frontend && npm start
DoanJava/
├── src/
│   ├── main/
│   │   ├── java/com/BHstore/
│   │   │   ├── model/              # Entities
│   │   │   │   ├── Product.java
│   │   │   │   ├── User.java
│   │   │   │   ├── Order.java
│   │   │   │   ├── OrderDetail.java
│   │   │   │   └── CartItem.java
│   │   │   ├── controller/         # REST Controllers
│   │   │   │   ├── ProductController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── CartController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   └── AdminController.java
│   │   │   ├── repository/         # Database access
│   │   │   │   ├── ProductRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── OrderDetailRepository.java
│   │   │   ├── service/            # Business logic
│   │   │   ├── config/             # Configuration
│   │   │   │   └── SecurityConfig.java
│   │   │   └── BHstoreApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   └── css/
│   │       └── templates/
│   └── test/
├── frontend/
│   ├── src/
│   │   ├── App.js                  # Component chính
│   │   ├── Login.js                # Đăng nhập
│   │   ├── Register.js             # Đăng ký
│   │   ├── ProductDetail.js        # Chi tiết sản phẩm
│   │   ├── Cart.js                 # Giỏ hàng
│   │   ├── Checkout.js             # Thanh toán
│   │   ├── Admin.js                # Trang admin
│   │   ├── AdminDashboard.js       # Dashboard
│   │   ├── AdminCategories.js      # Quản lý danh mục
│   │   ├── AdminUsers.js           # Quản lý người dùng
│   │   ├── AdminCustomers.js       # Quản lý khách hàng
│   │   ├── AdminOrders.js          # Quản lý đơn hàng
│   │   ├── ProductForm.js          # Form thêm/sửa sản phẩm
│   │   ├── Footer.js               # Footer
│   │   └── App.css                 # Styling
│   ├── public/
│   └── package.json
├── pom.xml                         # Maven configuration
└── README.md                       # This file
GET	/api/products	Lấy danh sách tất cả sản phẩm
GET	/api/products/{id}	Lấy chi tiết sản phẩm theo ID
POST	/api/products	Tạo sản phẩm mới
PUT	/api/products/{id}	Cập nhật sản phẩm
DELETE	/api/products/{id}	Xóa sản phẩm
Xác Thực (Auth)
Method	Endpoint	Mô Tả
POST	/api/auth/register	Đăng ký tài khoản mới
POST	/api/auth/login	Đăng nhập
POST	/api/auth/logout	Đăng xuất
Giỏ Hàng (Cart)
Method	Endpoint	Mô Tả
GET	/api/cart	Lấy giỏ hàng
POST	/api/cart/add	Thêm sản phẩm vào giỏ
DELETE	/api/cart/remove/{id}	Xóa sản phẩm khỏi giỏ
Đơn Hàng (Order)
Method	Endpoint	Mô Tả
GET	/api/orders	Lấy danh sách đơn hàng
POST	/api/orders	Tạo đơn hàng mới
GET	/api/orders/{id}	Lấy chi tiết đơn hàng
Bảng Users
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Bảng Products
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    description TEXT,
    image VARCHAR(255),
    category VARCHAR(100),
    gender VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Bảng Orders
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    status VARCHAR(50),
    total_price DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
Bảng Order Details
CREATE TABLE order_details (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT,
    price DOUBLE,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
Xây Dựng cho Production
Build Backend
mvn clean package
java -jar target/BHstore-0.0.1-SNAPSHOT.jar
Build Frontend
cd frontend
npm run build
Tệp build sẽ nằm trong thư mục build/

Troubleshooting
Lỗi kết nối MySQL
Kiểm tra MySQL service đang chạy
Xác nhận hostname, port, username, password
Kiểm tra cơ sở dữ liệu bhdb đã tạo chưa
Lỗi CORS
Kiểm tra @CrossOrigin annotation trong controller
Đảm bảo frontend URL khớp với allowedOrigins
Port đã được sử dụng
Thay đổi port trong application.properties:
server.port=8082
Node modules issues
cd frontend
rm -rf node_modules
npm install
