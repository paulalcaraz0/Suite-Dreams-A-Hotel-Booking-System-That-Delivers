# 🌟 Suite Dreams: Hotel Booking System

## 🏨 Introduction
**Suite Dreams** is a comprehensive hotel booking system implemented in **Java**. This application enables users to register, securely log in, book hotel rooms, and manage their accounts effectively. The system is designed using **Object-Oriented Programming (OOP)** principles and emphasizes security, real-time updates, and user-friendliness.

---

## 🌍 Alignment with Sustainable Development Goals (SDGs)
**Suite Dreams** contributes to **SDG #9: Industry, Innovation, and Infrastructure** by:
- Streamlining hotel management processes.
- Enhancing customer satisfaction through digital innovation.
- Promoting efficient resource utilization.

---

## 🎯 Key Objectives
| **No.** | **Objective** |
|---------|---------------|
| 1       | Enable secure user registration and login functionality. |
| 2       | Provide real-time room availability updates. |
| 3       | Facilitate user-friendly hotel room booking and payment management. |
| 4       | Ensure data security and account management. |

---

## 🚀 Features

### ✅ Core Functionalities:
1. **User Registration and Login:**
   - Register new users with secure credentials.
   - Authenticate users with unique usernames and passwords.

2. **Real-Time Room Booking:**
   - View available rooms with detailed descriptions.
   - Book rooms based on the user's balance and preferences.

3. **Account Management:**
   - Display user's balance and allow secure checkout.
   - Option to delete the user account upon exiting the system.

4. **Payment Integration:**
   - Automatically generate random user balances.
   - Display affordability based on the available balance.

### 🔐 Security Features:
- Input validation for all user data.
- Secure password storage.
- Logout and session management.

---

## 📦 Application of OOP Principles

### Encapsulation
- User and hotel details are encapsulated in their respective classes (User, Room, Hotel).
- Modifications to data, such as the user's balance or room availability, can only occur via defined methods like setBalance() and setAvailable().

### Inheritance
- General classes (e.g., `Person`) serve as base classes for specific entities like `User`.
- This allows shared attributes and methods to be reused, enabling consistent behavior and reducing code duplication.

### Polymorphism
- Overridden methods (e.g., toString() in Room and Booking) ensure tailored behavior for specific entities.
- The showAvailableRooms method in Hotel dynamically adapts behavior based on the runtime data (e.g., room type or user balance).

### Abstraction
- Simplifies complex operations, such as managing bookings and user authentication, by exposing only essential functionalities.
- `BookingManager` abstracts the process of adding bookings to the database, including time zone conversions, ensuring that these details are hidden from the main application logic.

---

## 🏗 System Design

### Class Overview:
| **Class Name**          | **Responsibility**                |
|-------------------------|------------------------------------|
| `Main`                  | Entry point for the system.       |
| `User`                  | Handles user details and balance generation. |
| `Hotel`                 | Manages room listings and bookings. |
| `DatabaseConnection`    | Ensures secure storage and retrieval of user and booking data. |

---

## 🎞 Demonstration Screenshots

### 1. Registration and Login
*Screenshot showcasing user registration and login interface goes here.*

### 2. Room Booking
*Screenshot showcasing room booking process goes here.*

---

## 🚧 Future Enhancements

1. **Database Expansion:**
   - Integrate external databases for more robust data handling.
2. **User Notifications:**
   - Enable automated email/SMS notifications for booking confirmations.
3. **Detailed Analytics:**
   - Add reporting tools for booking and payment history.

---

## 🤝 Acknowledgements
- **Instructor:** Ms. Fatima Marie P. Agdon  
- **Developer:** Paul C. Alcaraz  

---

## 💻 Technologies Used

- **Programming Language:** Java  
- **Libraries:** `java.util.Random`, JDBC  
- **IDE:** Visual Studio Code with JDK 23  

---

## ✍️ Authors
| **Name**            | **GitHub**                           | **SR-CODE**|
|---------------------|--------------------------------------|------------|
| Paul C. Alcaraz     |  https://github.com/paulalcaraz0     | 23-09909   |
