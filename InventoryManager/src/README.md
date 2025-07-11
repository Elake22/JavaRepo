# Inventory Manager

A Spring Boot console-based application for managing product inventory. Demonstrates dependency injection,
layered architecture, and user input handling with validation.

## Project Overview

The Inventory Manager allows administrators to manage product records through a simple text-based interface. 
It includes core features such as:

- Adding, viewing, updating, and deleting products

- Saving/loading inventory to/from a file

- Input validation to ensure data integrity

- Custom formatting for consistent UI

This project showcases foundational Spring Boot concepts:

- Dependency Injection and Inversion of Control

- Layered Architecture (UI, Service, Repository, Model)

- String Boot - Component, Scanning and Annotations

- Data Validation and File Persistence

## Models

### Product

The core entity representing a product in the inventory.

```java
public class Product { private String productID;private String productName;private int quantity;private double price)
```

**Fields:**
- `productID`: Unique product identifier

- `productName`: Capitalized product name

- `quantity`: Cannot be negative

- `price`: Cannot be negative

### PerishableProduct extends Product

```java
public PerishableProduct(String productID, String productName, int quantity, double price, LocalDate expirationDate)
```

- Extends the base Product class by adding an expirationDate field

- Represents items that have a limited shelf life (e.g., food, medicine)

- Inherits all validation and behavior from Product

- Custom toString() override displays expiration information

## Layers

### UI Layer: MainMenu
- Handles user interaction and orchestrates workflows using MenuFormatter.

### Service Layer: InventoryService
- Manages inventory logic like adding, updating, finding, and removing products. 
Delegates data persistence to the repository layer.

### Repository Layer: InventoryRepository
- An interface implemented by InMemoryInventoryRepository for in-memory product storage. 
Uses file I/O for saving and loading inventory.

### Utility: MenuFormatter
- Centralizes console display logic and input prompts with validation checks for numerical and text input.

### File Persistence

Inventory data is saved as a delimited text file:
Ex.
P001,Notebook,10,19.99
P002,Laptop,5,34.95

- Methods from the repository handle reading and writing this format safely.
- All file operations are handled by the InventoryRepository layer, 
which provides methods to safely read from and write to the file, ensuring data consistency and preventing corruption.

## Input Validation
- Product names are automatically capitalized.

- Quantity and price must be non-negative.

- Invalid numeric inputs prompt the user again.

## Running the App
Run InventoryApp.java to start the application.

Navigate using the main menu options.

Exit and relaunch to test file persistence.

### Menu Output
```
========= INVENTORY MANAGER =========
1. Add Product
2. View Products
3. Search Product
4. Update Product
5. Delete Product
6. Save Inventory
7. Load Inventory
8. Exit
```

## Test

JUnit tests are provided for:

- Core model logic (ProductTest, PerishableProductTest, InventoryRepositoryTest)

- Inventory Service layer behavior

- Integration test (InventoryAppTest) using Spring Boot context


### Reflections ####

1. Performance:
🟩 Good – Most features were successfully implemented. 
Challenges included Spring configuration and integrating file I/O with user interface logic.

2. Most Challenging Parts:
Understanding how to properly wire Spring and inject services into a console app. 
Debugging errors when the repository wasn’t found also took time.

3. Easiest / Most Enjoyable:
Designing the console menu and writing logic for adding/searching products was fun 
and helped reinforce control structures and user input handling.

### Concept Confidence 
Concept Confidence (1–5)
Concept	                        Rating	            Comments

Object-Oriented Programming       4	    I feel comfortable using records, classes, and inheritance.
Inheritance & Polymorphism	      4	    PerishableProduct helped.
Exception Handling & Validation   4	    Implemented file error handling and input checks.
File I/O & Data Persistence	      3	    Saving/loading worked well after some troubleshooting, but I want more practice.
Console-Based Interfaces	      4   	Comfortable with switch logic and Scanner input.

Overall want more practice to build skills

### If I Redid This Project:
I would start with Spring Boot setup earlier and plan the layers more clearly 
(especially separating UI logic and service logic more cleanly).

### Useful Skills for Future Projects
1. Clean use of records and polymorphism

2. Validating user input interactively

3. Writing integration tests for file operations

### Programming Goal
I want to improve my debugging and error-handling as well as Spring Boot usage.

### Resources That Would Help
More exercise or  examples of Spring in console apps
Videos resources on new concepts to help develop deeper understanding 

### Advice for Future Students
Plan out your build
Gather notes and documents you think you will need to refer to for building.
Set up your file path and Spring configuration early.