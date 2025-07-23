-- Find tax rate active on a given date
SELECT * FROM Tax
WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)
LIMIT 1;
