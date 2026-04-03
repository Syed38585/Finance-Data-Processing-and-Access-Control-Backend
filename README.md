# 📊 Finance Data Processing & Access Control Backend

## 🧾 Overview

This project is a backend system designed for a finance dashboard application where multiple users interact with financial data based on their roles.

The system focuses on clean architecture, secure access control, and efficient data processing. It demonstrates how a backend can be structured to handle real-world financial data while maintaining clarity, maintainability, and correctness.

---

## 🎯 Objective

The objective of this project is to:

* Manage financial transactions (income and expenses)
* Enforce role-based access control
* Provide aggregated analytics for dashboards
* Demonstrate strong backend engineering practices including API design, validation, and data handling

---

## 🧰 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **Spring Data JPA (Hibernate)**
* **PostgreSQL**
* **JWT (JSON Web Tokens)**
* **Lombok**
* **Maven**
* **Docker**
* **JUnit & Mockito (Unit Testing)**

---

## 🧩 Core Features

### 👤 User & Role Management

* Create and manage users
* Assign roles:

  * **Viewer** → Read-only access
  * **Analyst** → Access to records and analytics
  * **Admin** → Full system control
* Update user details
* Activate/Deactivate users
* Soft delete users to preserve data integrity

---

### 💰 Financial Records Management

* Create, update, and delete financial records
* Each record includes:

  * Amount (using `BigDecimal` for precision)
  * Type (Income / Expense)
  * Category
  * Date
  * Description
* Records are linked to users
* Soft delete ensures no permanent data loss

---

### 🔍 Filtering & Search

The system supports flexible querying of records:

* Filter by category
* Filter by type (INCOME / EXPENSE)
* Filter by date range

Example:
GET /records?type=EXPENSE
GET /records?startDate=2026-01-01&endDate=2026-01-31

---

### 📊 Dashboard Analytics

Provides aggregated insights for dashboard visualization:

* Total income
* Total expenses
* Net balance
* Category-wise totals
* Recent transactions
* Monthly trends

All analytics are computed using optimized database queries for better performance.

---

### 🔐 Access Control

Role-based access is enforced at the backend level using Spring Security.

| Role    | Access             |
| ------- | ------------------ |
| Viewer  | Read-only access   |
| Analyst | Read + analytics   |
| Admin   | Full system access |

This ensures secure and controlled interaction with system resources.

---

### ✅ Validation & Error Handling

* Input validation using annotations (`@NotNull`, `@NotBlank`, etc.)
* Centralized exception handling using `@RestControllerAdvice`
* Proper HTTP status codes for all responses
* Protection against invalid or inconsistent data

---

### 💾 Data Persistence

* Relational database (PostgreSQL)
* ORM handled using JPA/Hibernate
* Soft delete implemented using `isDeleted` flag
* Clean and normalized data modeling

---

## ⚙️ Additional Features Implemented

* JWT-based authentication
* Pagination support
* Filtering and search capabilities
* Soft delete functionality
* Clean layered architecture (Controller → Service → Repository)
* Unit testing using JUnit and Mockito
* Dockerized application for easy deployment

---

## 🧪 Unit Testing

Unit tests are implemented for core service layers using:

* **JUnit 5**
* **Mockito**

These tests validate business logic independently of external dependencies.

---

## 🧱 Project Structure

controller/
service/
repository/
entity/
dto/
security/
exception/

---

## 🔗 API Endpoints

### Authentication

* POST `/api/auth/login`
* POST `/api/auth/register`

### Users (Admin)

* GET `/api/users`
* GET `/api/users/{id}`
* POST `/api/users`
* PUT `/api/users/{id}`
* PATCH `/api/users/{id}/status`
* DELETE `/api/users/{id}`

### Financial Records

* GET `/api/finance/records`
* GET `/api/finance/records/{id}`
* POST `/api/finance/records`
* PUT `/api/finance/records/{id}`
* DELETE `/api/finance/records/{id}`

### Dashboard

* GET `/api/dashboard/summary`

---

## ⚖️ Design Trade-offs

### Monolithic vs Microservices

This project follows a **monolithic architecture**.

#### Why not microservices?

* The scope of the assignment does not require distributed systems
* Microservices introduce additional complexity such as:

  * Inter-service communication
  * Deployment orchestration
  * Infrastructure overhead
* The focus here is on backend fundamentals and clarity

#### Trade-off:

| Approach        | Pros                           | Cons                                |
| --------------- | ------------------------------ | ----------------------------------- |
| Monolith (used) | Simple, fast, easy to maintain | Limited scalability at large scale  |
| Microservices   | Highly scalable, modular       | Complex and overkill for this scope |

---

## 📌 Assumptions

* Admin is responsible for managing financial records
* Users do not directly create transactions
* Reasonable defaults are applied where requirements were not explicitly defined

---

## 🏁 Conclusion

This project demonstrates the design and implementation of a secure, structured, and maintainable backend system for financial data processing.

The focus has been on writing clean, understandable, and scalable code while ensuring correctness and proper handling of real-world scenarios.

It reflects a practical approach to backend development, balancing simplicity with thoughtful design decisions.
