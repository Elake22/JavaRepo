CREATE TABLE Products(
ProductId INT PRIMARY KEY AUTO_INCREMENT,
`Name` VARCHAR(50) NOT NULL,
Cost DECIMAL(13,2) NOT NULL
);
CREATE TABLE Customers(
CustomerId INT PRIMARY KEY AUTO_INCREMENT,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50) NOT NULL
);
create table Orders (
OrderId INT PRIMARY KEY AUTO_INCREMENT,
CustomerId INT NOT NULL,
OrderDate Datetime,
CONSTRAINT FK_Orders_Customers_CustomerId FOREIGN KEY (CustomerId) REFERENCES
Customers(CustomerId)
);
CREATE TABLE LineItems (
LineItemId INT PRIMARY KEY AUTO_INCREMENT,
OrderId INT NOT NULL,
ProductId INT NOT NULL,
Quantity INT NOT NULL,
CONSTRAINT FK_LineItems_Orders_OrderId FOREIGN KEY (OrderId) REFERENCES
Orders(OrderId),
CONSTRAINT FK_LineItems_Products_ProductId FOREIGN KEY (ProductId) REFERENCES
Products(ProductId),
CONSTRAINT UC_LineItem UNIQUE (OrderId,ProductId)
);
ALTER TABLE Products
ADD QtyInStock INT NOT NULL DEFAULT 0;













