-- Get order by ID
SELECT * FROM `Order` WHERE OrderID = ?;

-- Get all orders
SELECT * FROM `Order`;

-- Insert a new order
INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total)
VALUES (?, ?, ?, ?, ?, ?);

-- Update an order
UPDATE `Order`
SET ServerID = ?, OrderDate = ?, SubTotal = ?, Tax = ?, Tip = ?, Total = ?
WHERE OrderID = ?;

-- Delete and return deleted order
SELECT * FROM `Order` WHERE OrderID = ?; -- first fetch it
DELETE FROM `Order` WHERE OrderID = ?;
