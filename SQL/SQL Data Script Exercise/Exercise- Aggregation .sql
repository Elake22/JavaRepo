-- Minimum, maximum, and average book price

SELECT 
  MIN(Price) AS MinPrice,
  MAX(Price) AS MaxPrice,
  AVG(Price) AS AvgPrice
FROM Book;

--  Average book price per genre
SELECT 
  g.GenreName,
  AVG(b.Price) AS AvgGenrePrice
FROM Book b
JOIN Genre g ON b.GenreId = g.GenreId
GROUP BY g.GenreName;

--  Only genres with average price over $15
SELECT 
  g.GenreName,
  AVG(b.Price) AS AvgGenrePrice
FROM Book b
JOIN Genre g ON b.GenreId = g.GenreId
GROUP BY g.GenreName
HAVING AVG(b.Price) > 15;

-- Part 2- Total number of sales per staff member
SELECT 
  s.StaffId,
  s.LastName,
  COUNT(sa.SaleId) AS TotalSalesCount
FROM Sale sa
JOIN Staff s ON sa.StaffId = s.StaffId
GROUP BY s.StaffId, s.LastName;

-- Total sales value per staff
SELECT 
  s.StaffId,
  s.LastName,
  SUM(sa.Total) AS TotalSalesValue
FROM Sale sa
JOIN Staff s ON sa.StaffId = s.StaffId
GROUP BY s.StaffId, s.LastName;

-- Only staff with sales > $800
SELECT 
  s.StaffId,
  s.LastName,
  SUM(sa.Total) AS TotalSalesValue
FROM Sale sa
JOIN Staff s ON sa.StaffId = s.StaffId
GROUP BY s.StaffId, s.LastName
HAVING SUM(sa.Total) > 800;

-- Sort staff by total sales descending
SELECT 
  s.StaffId,
  s.LastName,
  SUM(sa.Total) AS TotalSalesValue
FROM Sale sa
JOIN Staff s ON sa.StaffId = s.StaffId
GROUP BY s.StaffId, s.LastName
ORDER BY TotalSalesValue DESC;

-- Part 3 - Group staff by HireDate with comma-separated last names
SELECT 
  HireDate,
  GROUP_CONCAT(LastName ORDER BY LastName SEPARATOR ', ') AS HiredStaff
FROM Staff
GROUP BY HireDate;











