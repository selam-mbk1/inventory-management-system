CREATE DATABASE ims_db;
USE ims_db;
CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(100),
  email VARCHAR(120) UNIQUE,
  password VARCHAR(255),
  role ENUM('Admin','Instructor','Department Head','Inventory Officer','Storekeeper'),
  department VARCHAR(100)
);
CREATE TABLE inventory_items (
  item_id INT AUTO_INCREMENT PRIMARY KEY,
  item_name VARCHAR(150),
  category ENUM('Consumable','Non-Consumable'),
  quantity INT,
  location VARCHAR(100)
);
CREATE TABLE requests (
  request_id INT AUTO_INCREMENT PRIMARY KEY,
  requester_id INT,
  item_id INT,
  quantity INT,
  reason TEXT,
  status VARCHAR(50),
  FOREIGN KEY (requester_id) REFERENCES users(user_id),
  FOREIGN KEY (item_id) REFERENCES inventory_items(item_id)
);
INSERT INTO users(full_name,email,password,role)
VALUES
('Admin User','admin@astu.edu','1234','Admin'),
('Instructor User','inst@astu.edu','1234','Instructor'),
('Dept Head','dh@astu.edu','1234','Department Head'),
('Storekeeper','store@astu.edu','1234','Storekeeper');
INSERT INTO inventory_items(item_name,category,quantity,location)
VALUES('Dell Monitor','Non-Consumable',10,'Main Store');
