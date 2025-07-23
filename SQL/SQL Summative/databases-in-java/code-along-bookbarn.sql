CREATE DATABASE BookBarn;
USE BookBarn;

-- Drop existing tables if needed
DROP TABLE IF EXISTS Sale, Staff, Book, Genre;

-- Genre Table
CREATE TABLE Genre (
  GenreId INT PRIMARY KEY,
  GenreName VARCHAR(50)
);

INSERT INTO Genre VALUES
(1, 'Fiction'),
(2, 'Business'),
(3, 'Science'),
(4, 'Children'),
(5, 'History');

-- Book Table
CREATE TABLE Book (
  BookId INT PRIMARY KEY,
  GenreId INT,
  Title VARCHAR(100),
  Price DECIMAL(6,2),
  FOREIGN KEY (GenreId) REFERENCES Genre(GenreId)
);

INSERT INTO Book VALUES
(1, 1, 'Into the Woods', 14.99),
(2, 2, 'Startup Fundamentals', 22.00),
(3, 1, 'Ghost Leaves', 11.50),
(4, 3, 'Quantum Basics', 19.99),
(5, 3, 'Cosmic Science', 24.00),
(6, 4, 'The Hungry Caterpillar', 8.99),
(7, 5, 'World at War', 17.75),
(8, 1, 'Tales of Time', 13.25),
(9, 4, 'Alphabet Adventures', 7.50),
(10, 2, 'Marketing Mindset', 25.50);

-- Staff Table
CREATE TABLE Staff (
  StaffId INT PRIMARY KEY,
  LastName VARCHAR(50),
  HireDate DATE
);

INSERT INTO Staff VALUES
(1, 'Nguyen', '2020-01-01'),
(2, 'Smith', '2021-03-15'),
(3, 'Chen', '2021-03-15'),
(4, 'Patel', '2022-06-20'),
(5, 'Johnson', '2023-01-10');

-- Sale Table
CREATE TABLE Sale (
  SaleId INT PRIMARY KEY,
  StaffId INT,
  Total DECIMAL(10,2),
  FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);

INSERT INTO Sale VALUES
(101, 1, 295.75),
(102, 2, 840.20),
(103, 2, 120.00),
(104, 3, 480.50),
(105, 3, 510.25),
(106, 4, 935.10),
(107, 5, 190.00),
(108, 1, 310.50),
(109, 4, 320.85),
(110, 5, 620.15);
