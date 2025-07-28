CREATE DEFINER=`root`@`localhost` PROCEDURE `get_order_with_details`(IN orderId INT)
BEGIN
    -- First result set: Order with Server
    SELECT o.*, s.ServerID, s.FirstName, s.LastName, s.HireDate, s.TermDate
    FROM `Order` o
    JOIN Server s ON o.ServerID = s.ServerID
    WHERE o.OrderID = orderId;

    -- Second result set: OrderItems with Items and Categories
    SELECT oi.*, i.*, ic.*
    FROM OrderItem oi
    JOIN Item i ON oi.ItemID = i.ItemID
    JOIN ItemCategory ic ON i.ItemCategoryID = ic.ItemCategoryID
    WHERE oi.OrderID = orderId;

    -- Third result set: Payments with PaymentTypes
    SELECT p.*, pt.*
    FROM Payment p
    JOIN PaymentType pt ON p.PaymentTypeID = pt.PaymentTypeID
    WHERE p.OrderID = orderId;
END