CREATE TABLE Customer (
  CustomerId INT AUTO_INCREMENT PRIMARY KEY,
  CustomerName VARCHAR(100)
);

CREATE TABLE CustomerOrder (
  OrderId INT AUTO_INCREMENT PRIMARY KEY,
  CustomerId INT,
  OrderDate DATE,
  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

CREATE TABLE PaymentMethod (
  PaymentMethodId INT PRIMARY KEY,
  MethodName VARCHAR(50)
);

CREATE TABLE Payment (
  PaymentId INT AUTO_INCREMENT PRIMARY KEY,
  OrderId INT,
  PaymentMethodId INT,
  Amount DECIMAL(10, 2),
  FOREIGN KEY (OrderId) REFERENCES CustomerOrder(OrderId),
  FOREIGN KEY (PaymentMethodId) REFERENCES PaymentMethod(PaymentMethodId)
);

INSERT INTO PaymentMethod VALUES 
(1, 'Credit Card'),
(2, 'Cash'),
(3, 'Mobile Payment');
