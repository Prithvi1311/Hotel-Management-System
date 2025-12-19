# ITC Hotel Booking System

The **ITC Hotel Booking System** is a comprehensive full-stack web application designed to simulate a real-world hotel booking portal (like Booking.com or OYO). It allows users to browse rooms, book stays, and manage their profiles, while providing administrators with tools to manage rooms and bookings.

## 🚀 Features

### User Module
-   **Registration & Login**: Secure authentication using **Spring Security** and **JWT**.
-   **Profile**: View personal details and booking history.
-   **Role-Based Access**: Strict separation between `ROLE_USER` and `ROLE_ADMIN`.

### Hotel & Room Module
-   **Browse Rooms**: View list of rooms with photos, prices, and descriptions.
-   **Admin Management**: Add, update, and delete rooms.
-   **Availability**: Real-time availability checks preventing double-booking.

### Booking Module
-   **Simplified Booking**: Seamless flow with date selection and confirmation.
-   **Dynamic Status**: Track bookings as "On-going", "Completed", or "Cancelled".
-   **History**: Users can view past and upcoming trips.

### Search Module
-   **Filters**: Find rooms by **Date Range** and **Room Type**.
-   **Smart Filtering**: Displays distinct room types to avoid clutter.

### Payment Module
-   **Simulated Checkout**: Integrated payment step before booking confirmation.

### Admin Dashboard
-   **Overview**: Monitor all bookings and cancellations.
-   **Data Visuals**: Manage user activities and room statuses.

---

## 🛠️ Tech Stack

### Backend
-   **Framework**: Spring Boot 3.x
-   **Language**: Java 17
-   **Database**: MySQL
-   **Security**: Spring Security 6 (JWT)

### Frontend
-   **Library**: React.js 18 (Vite)
-   **Styling**: Bootstrap 5, Custom CSS
-   **HTTP Client**: Axios

---

## 🏃‍♂️ Local Setup Instructions

### 1. Database
Create a MySQL database named `Royal_hotel_db`:
```sql
CREATE DATABASE Royal_hotel_db;
```

### 2. Backend
Navigate to `real-hotel-se`:
```bash
cd real-hotel-se
./mvnw spring-boot:run
```
*Port: 9192*

### 3. Frontend
Navigate to `client/Royal-hotel`:
```bash
cd client/Royal-hotel
npm install
npm run dev
```
*Port: 5173* (Configured to proxy to backend)

---

## ☁️ Deployment (Free Tier Strategy)

This project is optimized for deployment on free cloud providers.

### 1. Database: TiDB Cloud / Aiven (Free MySQL)
-   Create a serverless MySQL cluster.
-   Get the Host, User, and Password.

### 2. Backend: Render (Free Web Service)
-   Connect GitHub Repo -> `real-hotel-se`.
-   Runtime: **Docker**.
-   Set Environment Variables: `SPRING_DATASOURCE_URL`, `USERNAME`, `PASSWORD`.

### 3. Frontend: Netlify
-   Connect GitHub Repo -> `client/Royal-hotel`.
-   Build Command: `npm run build`.
-   Set Environment Variable: `VITE_API_BASE_URL` (Link to Render backend).

---

## 📂 Project Structure
```
ITC-Hotel/
├── client/
│   └── Royal-hotel/       # React Frontend (Vite)
├── real-hotel-se/         # Spring Boot Backend
├── deployment_guide.md    # Detailed Deployment Steps
└── README.md              # Project Documentation
```

---
**Developed for Portfolio & Learning**
