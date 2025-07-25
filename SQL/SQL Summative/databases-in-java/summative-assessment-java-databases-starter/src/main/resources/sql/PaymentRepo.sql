-- Retrieve payments by order ID
SELECT * FROM Payment WHERE OrderID = ?;

-- Insert a new payment
INSERT INTO Payment (OrderID, PaymentTypeID, Amount)
VALUES (?, ?, ?);

-- Update an existing payment (optional)
UPDATE Payment
SET PaymentTypeID = ?, Amount = ?
WHERE PaymentID = ?;

-- Delete a payment by ID (optional)
DELETE FROM Payment WHERE PaymentID = ?;
