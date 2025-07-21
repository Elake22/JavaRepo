INSERT INTO Subject (SubjectName)
VALUES ('Comp. Science');

SET SQL_SAFE_UPDATES = 0;

UPDATE Subject
SET SubjectName = 'Computer Science'
WHERE SubjectName = 'Comp. Science';

SELECT SubjectID FROM Subject
WHERE SubjectName = 'Computer Science';

INSERT INTO Course (CourseName, CreditHours, SubjectID)
VALUES 
  ('Java', 3, 5),
  ('C#', 3, 5),
  ('JavaScript', 3, 5),
  ('Advanced Java', 3, 5);
  
UPDATE Course
SET CourseName = 'Java 101'
WHERE CourseName = 'Java';

UPDATE Course
SET CourseName = 'Java 201',
    CreditHours = 4
WHERE CourseName = 'Advanced Java';

DELETE FROM Subject
WHERE subject_name = 'Computer Science';
-- Error Code: 1054. Unknown column 'subject_name' in 'where clause'
-- Because the Course table has foreign key references to Subject, and you’re trying to delete a subject that still has related courses

DELETE FROM Course
WHERE CourseName = 'Java 101';

DELETE FROM Section
WHERE CourseID IN (
  SELECT CourseID FROM Course
  WHERE SubjectID = (SELECT SubjectID FROM Subject WHERE SubjectName = 'Computer Science')
);

DELETE FROM Course
WHERE SubjectID = (SELECT SubjectID FROM Subject WHERE SubjectName = 'Computer Science');

DELETE FROM Subject
WHERE SubjectName = 'Computer Science';














