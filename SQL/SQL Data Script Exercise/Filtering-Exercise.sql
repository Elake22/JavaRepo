SELECT StudentID, LastName, FirstName
FROM Student
WHERE LastName LIKE 'Cr%';

SELECT *
FROM Course
WHERE SubjectID = 1 OR SubjectID = 2 OR SubjectID = 4;

SELECT *
FROM Course
WHERE SubjectID IN (1, 2, 4);

SELECT *
FROM Student
WHERE StudentID = 42;

SELECT FirstName
FROM Student
WHERE FirstName LIKE 'C%';

SELECT FirstName
FROM Student
WHERE FirstName BETWEEN 'Ce' AND 'Cf';

SELECT DISTINCT LastName
FROM Student
LIMIT 10;

SELECT *
FROM Student
LIMIT 10;

SELECT *
FROM Student
ORDER BY LastName DESC
Limit 5;



