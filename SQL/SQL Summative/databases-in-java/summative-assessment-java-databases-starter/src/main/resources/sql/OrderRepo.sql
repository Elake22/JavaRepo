-- OrderRepo.sql

-- Find all orders
SELECT * FROM `Order`;

-- Find order by ID
SELECT * FROM `Order` WHERE OrderID = ?;

-- Insert a new order
INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total)
VALUES (?, ?, ?, ?, ?, ?);

-- Update an existing order
UPDATE `Order`
SET ServerID = ?, OrderDate = ?, SubTotal = ?, Tax = ?, Tip = ?, Total = ?
WHERE OrderID = ?;

-- Delete an order
DELETE FROM `Order` WHERE OrderID = ?;
