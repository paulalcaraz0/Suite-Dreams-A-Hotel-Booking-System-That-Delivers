-- Create the database
CREATE DATABASE IF NOT EXISTS hotel_db;
USE hotel_db;

-- Table to store user information
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    balance DOUBLE NOT NULL,
    UNIQUE (username)
);

-- Table to store rooms
CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_type VARCHAR(100) DEFAULT NULL,
    price DECIMAL(10,2) DEFAULT NULL,
    available TINYINT(1) DEFAULT NULL
);

-- Table to store bookings
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT DEFAULT NULL,
    room_id INT DEFAULT NULL,
    check_in_time TIMESTAMP NULL DEFAULT NULL,
    check_out_time TIMESTAMP NULL DEFAULT NULL,
    days_in_hotel INT DEFAULT NULL,
    total_price DOUBLE DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms (room_id) ON DELETE CASCADE
);

-- Insert some test users
INSERT INTO users (username, password, first_name, last_name, contact_number, balance)
VALUES
('user_1', 'password_hash_1', 'John', 'Doe', '09171234567', 1000.50),
('user_2', 'password_hash_2', 'Jane', 'Smith', '09182345678', 1500.00);

-- Insert some test rooms
INSERT INTO rooms (room_type, price, available)
VALUES
('Single', 100.00, 1),
('Double', 150.00, 1),
('Suite', 250.00, 0);

-- Insert a test booking
INSERT INTO bookings (user_id, room_id, check_in_time, check_out_time, days_in_hotel, total_price)
VALUES
(1, 2, '2024-12-20 14:00:00', '2024-12-22 11:00:00', 2, 300.00);
