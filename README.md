# PG Management System

A full-stack web application developed using **Java, Spring Boot, MySQL, HTML, CSS, and JavaScript** to simplify Paying Guest (PG) accommodation management.

The system provides a centralized platform for managing PG rooms, user registrations, accommodation bookings, and administrative operations through a user-friendly web interface.

## Features

### User Module
- User registration and login
- View available PG rooms
- Book PG accommodation
- View and manage personal bookings
- Manage user profile

### Admin Module
- Admin dashboard with statistics
- Add new PG rooms
- Update existing room details
- Delete rooms
- View and manage bookings
- Access user information

### Authentication
- Login and registration functionality
- BCrypt password hashing
- JWT token generation
- User and Admin roles

**Note:** JWT-based API authorization and role-based access enforcement require further security improvements before public deployment.

## Technology Stack

| Category | Technology |
|---|---|
| Backend | Java 17, Spring Boot |
| Frontend | HTML5, CSS3, JavaScript |
| Database | MySQL |
| Security | Spring Security, JWT, BCrypt |
| ORM | Spring Data JPA, Hibernate |
| Build Tool | Maven |
| IDE | Eclipse IDE |
| API Testing | Postman |

## Project Architecture

```text
Frontend (HTML, CSS, JavaScript)
              |
              v
     Spring Boot REST APIs
              |
              v
        Service Layer
              |
              v
       Repository Layer
              |
              v
         MySQL Database
```

## Project Structure

```text
pg-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/pgmanagement/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── security/
│   │   │   └── config/
│   │   └── resources/
│   │       ├── static/
│   │       └── application.properties
│   └── test/
├── .mvn/
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Installation and Setup

### Prerequisites

- Java 17 or higher (compatible with the Spring Boot version)
- MySQL Server
- Maven or Maven Wrapper
- Eclipse IDE (optional)

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/PG-Management-System.git
cd PG-Management-System
```

Replace `YOUR_USERNAME` with your GitHub username.

### 2. Create MySQL Database

```sql
CREATE DATABASE pg_management;
```

### 3. Configure Environment Variables

Set the following variables before running the application:

```text
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_secure_random_secret
```

The JWT secret should be a securely generated random value of at least 32 bytes.

Ensure `application.properties` contains the corresponding configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pg_management
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}

server.port=8081
```

Do not commit real passwords or JWT secrets to GitHub.

### 4. Run the Application

For Windows:

```bash
mvnw.cmd spring-boot:run
```

For Linux/macOS:

```bash
./mvnw spring-boot:run
```

### 5. Open the Website

Visit:

http://localhost:8081

## REST API Endpoints

### User APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users/register` | Register a new user |
| POST | `/users/login` | Login |
| GET | `/users/all` | Get all users |
| PUT | `/users/update/{id}` | Update user details |

### Room APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/rooms/all` | Get all rooms |
| POST | `/rooms/add` | Add new room |
| PUT | `/rooms/update/{id}` | Update room |
| DELETE | `/rooms/delete/{id}` | Delete room |

### Booking APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/bookings/add` | Create booking |
| GET | `/bookings/all` | Get all bookings |
| GET | `/bookings/user/{id}` | Get bookings by user |
| DELETE | `/bookings/delete/{id}` | Delete booking |

### Admin APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/admin/stats` | Get dashboard statistics |

## Application Screenshots

Screenshots will be added to demonstrate the application's interface and functionality.

- Home Page
- Login Page
- User Dashboard
- Room Listing
- Booking Management
- Admin Dashboard

## Development Status

The application is functional in the local development environment, including user registration, login, room management, booking operations, and administrative features.

Additional backend security, validation, and production configuration improvements are planned before public deployment.

## Future Enhancements

- Enforce JWT authentication and role-based authorization
- Improve input validation and error handling
- Add booking approval and notifications
- Improve room availability management
- Enhance dashboard reporting
- Deploy the application to a cloud hosting platform

## Learning Outcomes

This project demonstrates practical experience with:

- Java Full Stack Development
- Spring Boot REST API Development
- CRUD Operations
- MySQL Database Integration
- Spring Data JPA and Hibernate
- Authentication and Password Hashing
- Frontend and Backend Integration
- Maven Project Management
- API Testing Using Postman

## Author

Developed by **Kaustubh Bhoir**

Java Full Stack Developer
