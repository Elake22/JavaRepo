-- Retrieve all items
SELECT * FROM Item;

-- Retrieve a single item by ID
SELECT * FROM Item WHERE ItemID = ?;

-- Insert a new item
INSERT INTO Item (ItemCategoryID, ItemName, ItemDescription, StartDate, EndDate, UnitPrice)
VALUES (?, ?, ?, ?, ?, ?);

-- Update an existing item
UPDATE Item
SET ItemCategoryID = ?, ItemName = ?, ItemDescription = ?, StartDate = ?, EndDate = ?, UnitPrice = ?
WHERE ItemID = ?;

-- Delete an item by ID
DELETE FROM Item WHERE ItemID = ?;
