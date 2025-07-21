USE sys;
CREATE DATABASE ExerciseInserting ;
USE ExerciseInserting;

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

-- Insert two new customers
INSERT INTO Customer (CustomerName)
VALUES ('Emma Rivera'), ('Noah Gray');

-- Insert one order for each customer using today’s date
INSERT INTO CustomerOrder (CustomerId, OrderDate)
VALUES (1, CURRENT_DATE),  
       (2, CURRENT_DATE);  

-- Insert payment of $49.99 for Emma's order using PaymentMethodId 1
INSERT INTO Payment (OrderId, PaymentMethodId, Amount)
VALUES (1, 1, 49.99);  

-- Try inserting a payment with an invalid PaymentMethodId (this will throw an error)
INSERT INTO Payment (OrderId, PaymentMethodId, Amount)
VALUES (1, 999, 20.00);
/*  Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails
 (`exerciseinserting`.`payment`, CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`PaymentMethodId`)
 REFERENCES `paymentmethod` (`PaymentMethodId`))
 */
 
 -- Insert three new customers in one statement
INSERT INTO Customer (CustomerName)
VALUES 
  ('Liam Davis'),
  ('Olivia Brooks'),
  ('Sophia Martinez');

-- Insert two orders using today’s date (assume Liam = 3, Olivia = 4, Sophia = 5)
INSERT INTO CustomerOrder (CustomerId, OrderDate)
VALUES 
  (3, CURRENT_DATE),
  (4, CURRENT_DATE);

 





