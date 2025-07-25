-- Retrieve all item categories
SELECT * FROM ItemCategory;

-- Retrieve a single item category by ID
SELECT * FROM ItemCategory WHERE ItemCategoryID = ?;

-- Insert a new item category
INSERT INTO ItemCategory (ItemCategoryName)
VALUES (?);

-- Update an existing item category
UPDATE ItemCategory
SET ItemCategoryName = ?
WHERE ItemCategoryID = ?;

-- Delete an item category by ID
DELETE FROM ItemCategory WHERE ItemCategoryID = ?;
