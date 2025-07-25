-- Retrieve all payment types
SELECT * FROM PaymentType;

-- Retrieve a payment type by ID
SELECT * FROM PaymentType WHERE PaymentTypeID = ?;

-- Insert a new payment type
INSERT INTO PaymentType (PaymentTypeName)
VALUES (?);

-- Update an existing payment type
UPDATE PaymentType
SET PaymentTypeName = ?
WHERE PaymentTypeID = ?;

-- Delete a payment type by ID
DELETE FROM PaymentType WHERE PaymentTypeID = ?;
