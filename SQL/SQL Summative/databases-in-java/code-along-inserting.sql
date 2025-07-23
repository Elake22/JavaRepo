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
