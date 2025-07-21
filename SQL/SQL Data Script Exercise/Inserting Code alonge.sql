
USE sys;
CREATE DATABASE CodeAlongInserting ;
USE CodeAlongInserting;

-- Lookup table for payment methods
CREATE TABLE PaymentMethod (
  PaymentMethodId INT PRIMARY KEY,
  MethodName VARCHAR(50)
);


-- Table for customer orders
CREATE TABLE CustomerOrder (
  OrderId INT PRIMARY KEY,
  CustomerName VARCHAR(100)
);


-- Table where we'll practice inserts
CREATE TABLE Payment (
  PaymentId INT AUTO_INCREMENT PRIMARY KEY,
  PaymentMethodId INT,
  OrderId INT,
  Amount DECIMAL(10,2),
  FOREIGN KEY (PaymentMethodId) REFERENCES PaymentMethod(PaymentMethodId),
  FOREIGN KEY (OrderId) REFERENCES CustomerOrder(OrderId)
);


-- Seed lookup data
INSERT INTO PaymentMethod VALUES 
(1, 'Credit Card'), 
(2, 'Cash'), 
(3, 'Mobile Payment');


INSERT INTO CustomerOrder VALUES 
(101, 'Alice Johnson'), 
(102, 'Brian Smith'), 
(103, 'Carmen Liu');

-- Insert including all columns, even primary key
INSERT INTO Payment (PaymentId, PaymentMethodId, OrderId, Amount)
VALUES (1001, 1, 101, 49.99);
-- Insert using implicit column order (not recommended)
INSERT INTO Payment VALUES (1002, 2, 102, 35.00);

-- Omit PaymentId and let the DB handle it
INSERT INTO Payment (PaymentMethodId, OrderId, Amount)
VALUES (3, 103, 60.00);

-- This will fail because there's no PaymentMethodId 999
INSERT INTO Payment (PaymentMethodId, OrderId, Amount)
VALUES (999, 101, 20.00);

-- Insert multiple rows at once
INSERT INTO Payment (PaymentMethodId, OrderId, Amount)
VALUES
(1, 101, 10.00),
(2, 102, 15.00),
(3, 103, 20.00);

-- For every order, insert a payment of $5.00 using PaymentMethodId 1
INSERT INTO Payment (PaymentMethodId, OrderId, Amount)
SELECT 1, OrderId, 5.00
FROM CustomerOrder;
