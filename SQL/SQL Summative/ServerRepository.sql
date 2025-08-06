-- Get a server by ID
SELECT ServerID, FirstName, LastName, HireDate, TermDate
FROM Server
WHERE ServerID = ?;

-- Get all available servers on a given date
SELECT ServerID, FirstName, LastName, HireDate, TermDate
FROM Server
WHERE HireDate <= ?
  AND (TermDate IS NULL OR TermDate >= ?);
