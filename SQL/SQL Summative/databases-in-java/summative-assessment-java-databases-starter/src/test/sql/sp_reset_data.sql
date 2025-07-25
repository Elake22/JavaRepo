-- Drop and recreate procedure if it exists
DROP PROCEDURE IF EXISTS sp_reset_data;

DELIMITER $$

CREATE PROCEDURE sp_reset_data()
BEGIN
    -- Clear tables in reverse dependency order
    DELETE FROM Payment;
    DELETE FROM OrderItem;
    DELETE FROM `Order`;
    DELETE FROM Item;
    DELETE FROM ItemCategory;
    DELETE FROM PaymentType;
    DELETE FROM Tax;
    DELETE FROM Server;

    -- Seed: ItemCategory
    INSERT INTO ItemCategory (ItemCategoryID, ItemCategoryName) VALUES (1, 'Appetizers');

    -- Seed: Item
    INSERT INTO Item (ItemID, ItemCategoryID, ItemName, ItemDescription, StartDate, EndDate, UnitPrice)
    VALUES (1, 1, 'Spring Rolls', 'Crispy veggie rolls', '2023-01-01', NULL, 4.99);

    -- Seed: Server
    INSERT INTO Server (ServerID, FirstName, LastName, HireDate, TermDate)
    VALUES (1, 'Alice', 'Smith', '2022-01-01', NULL);

    -- Seed: Order
    INSERT INTO `Order` (OrderID, ServerID, OrderDate, SubTotal, Tax, Tip, Total)
    VALUES (1, 1, '2023-06-01', 9.98, 0.80, 2.00, 12.78);

    -- Seed: OrderItem
    INSERT INTO OrderItem (OrderItemID, OrderID, ItemID, Quantity, Price)
    VALUES (1, 1, 1, 2, 4.99);

    -- Seed: Tax
    INSERT INTO Tax (TaxID, TaxPercentage, StartDate, EndDate)
    VALUES (1, 8.25, '2022-01-01', NULL);

    -- Seed: PaymentType
    INSERT INTO PaymentType (PaymentTypeID, PaymentTypeName)
    VALUES (1, 'Credit Card');

    -- Seed: Payment
    INSERT INTO Payment (PaymentID, OrderID, PaymentTypeID, Amount)
    VALUES (1, 1, 1, 12.78);
END$$

DELIMITER ;
