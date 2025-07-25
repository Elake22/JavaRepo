-- Retrieve all tax records
SELECT * FROM Tax;

-- Retrieve a single tax record by ID
SELECT * FROM Tax WHERE TaxID = ?;

-- Insert a new tax record
INSERT INTO Tax (TaxPercentage, StartDate, EndDate)
VALUES (?, ?, ?);

-- Update an existing tax record
UPDATE Tax
SET TaxPercentage = ?, StartDate = ?, EndDate = ?
WHERE TaxID = ?;

-- Delete a tax record by ID
DELETE FROM Tax WHERE TaxID = ?;
