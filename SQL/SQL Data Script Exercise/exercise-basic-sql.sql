-- SELECT ' All for One, and One for All'
-- SELECT concat(' All for One, and One for All')
-- SELECT 6 + 6
-- SELECT 5/2
-- SELECT 5.0/2.0
/* SELECT 6/4 AS Quotient,
 6 % 4 AS Remainder;
 */
-- SELECT 6 * 6
-- SELECT * FROM Building 
/* SELECT PeriodName,StartTime,EndTime 
FROM Period
*/
/* SELECT 
  table_name 
FROM 
  information_schema.tables 
WHERE 
  table_schema = 'SimpleSchool'
  AND table_rows = 0;
*/
/* SELECT CourseName, (CreditHours)
FROM Course
*/
/* SELECT 
  CONCAT(FirstName, ' ', LEFT(LastName, 1), '.') AS FullName
FROM 
  Teacher
LIMIT 5;
*/
-- SELECT COUNT(*) FROM Room;
/* SELECT 
  BuildingID,
  MIN(RoomNumber) AS LowestRoom,
  MAX(RoomNumber) AS HighestRoom
FROM 
  Room
GROUP BY 
  BuildingID;
*/
/* SELECT DISTINCT Description
FROM Room;
*/
/* SELECT COUNT(DISTINCT SubjectID) AS UniqueSubjectCount
FROM Course;
*/
/* SELECT COUNT(*) AS GradeTypeCount
FROM GradeType;
*/
/* SELECT 
  GradeTypeID AS ID,
  GradeTypeName AS GradeType
FROM 
  GradeType;
  */
SELECT DISTINCT GradeTypeID
FROM GradeType;









