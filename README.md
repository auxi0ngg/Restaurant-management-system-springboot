# Spring Restaurant API

## 📚 About this Repository 

This repository contains an API, created with Spring, for an all-in-one management system for a local restaurant.

The system is comprised of multiple features that are common to retail and food outlets.

## Technologies Used

- Spring Boot 3
- Spring Security 6
- JWT Token Authentication
- Spring Data JPA
- Swagger UI Documentation
- Docker
- GitHub Actions


## 📋 Features

- **Authentication Functionality**
    - Supports both **customers** and **staff** registration and login with JWTs.

- **Customer Profiles**
    - Name and address
    - Order history
    - Events attended

- **Staff Profiles**
    - Name
    - Shifts
    - Total hours worked

- **Customer Functionality**
    - Check table availability and book tables for a given date
    - View menu information
    - Create orders:
        - In-house
        - Takeaway
        - Delivery
    - Book and register for catered events

- **Staff Roles and Tasks**
    - Staff divided into:
        - Waiters
        - Managers
        - Chefs
        - Delivery drivers
    - Role-based task handling:
        - Manage table bookings
        - Manage event bookings
        - Accept customer orders
        - Mark orders as complete
        - Add special items to the menu
        

- **Manager-Specific Features**
    - Generate information for the past 7 days on:
        - The top 5 busiest periods for bookings
        - The top 5 most popular menu items
        - The top 5 members of staff by hours worked
        - The top 5 most active customers
    - Assign shifts to staff members

## 🛠️ Setup

### Clone the Repository

```bash
git clone https://github.com/ifanmo/spring-boot-restaurant
cd restaurant-api
```

## ▶️ Running the Project

To start the application, run:

```bash
docker-compose up --build
```

## 📚 API Documentation

Swagger UI is available at:

```bash
http://localhost:8080/swagger-ui.html
```

---
In order to test the API endpoint you must first register an account with a role of either
'CUSTOMER', 'MANAGER', 'WAITER', 'CHEF', or 'DELIVERY DRIVER'.

Customers must then create a customer profile, all other roles must create a staff profile.


The database is automatically populated with 10 sample menu items using a Flyway migration script.

## 🧪 Example Customer Registration and Login

### 1. Register a New Customer
To check out, you have to register as a customer and login:

```bash
POST /users
```

**Request body**:

```json
{
  "email": "user1@example.com",
  "password": "password",
  "role": "CUSTOMER"
}
```

### 2. Login to Get an Access Token

```bash
POST /auth/login 
```

**Request body**:
```json
{
  "email": "user1@example.com",
  "password": "password"
}
```

**Response body**:
```json
{
  "token": "generated-json-web-token"
}
```

### 3. Create Customer Profile
You must create a customer profile before checking out the order
```bash
POST /customers 
```

**Headers**
```bash
Authorization: Bearer generated-json-web-token
```

**Request body**
```json
{
  "firstName": "Dave",
  "lastName": "Wilson",
  "houseNumber": "2",
  "street": "King Street",
  "postcode": "LS2976P"
}
```

## 🧪 Example Staff Registration and Login

### 1. Register a New Staff Member
To check out, you have to register as a customer and login:

```bash
POST /users
```

**Request body**:

```json
{
  "email": "user1@example.com",
  "password": "password",
  "role": "WAITER"
}
```

### 2. Login to Get an Access Token

```bash
POST /auth/login 
```

**Request body**:
```json
{
  "email": "user2@example.com",
  "password": "password"
}
```

**Response body**:
```json
{
  "token": "generated-json-web-token"
}
```

### 3. Create Staff Profile
You must create a customer profile before checking out the order
```bash
POST /staff 
```

**Headers**
```bash
Authorization: Bearer generated-json-web-token
```

**Request body**
```json
{
  "firstName": "Jay",
  "lastName": "Buckley"
}
```
