-- Retrieve all order items for a given OrderID
SELECT * FROM OrderItem WHERE OrderID = ?;

-- Insert a new order item
INSERT INTO OrderItem (OrderID, ItemID, Quantity, Price)
VALUES (?, ?, ?, ?);

-- Update an existing order item
UPDATE OrderItem
SET Quantity = ?, Price = ?
WHERE OrderItemID = ?;

-- Delete order item by ID
DELETE FROM OrderItem WHERE OrderItemID = ?;
