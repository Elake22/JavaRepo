Locker APP Conclusion on design

Design Overview
The Locker App began as a basic prototype using just two classes:

•	LockersApp: handled all user interaction and logic.

•	LockerManager: managed locker availability and PIN storage.

This initial setup allowed me to get a working base model. The focus at that stage was just to test the core functionalities: renting, accessing, and releasing a locker with a PIN. While this helped validate the concept, the structure quickly became difficult to manage, especially as features and validations increased.

Upon instructor feedback, I refactored the application into five well-organized classes, each with a specific responsibility:

•	LockersApp – Handles user interaction and program control flow

•	LockerService – Acts as the logic controller for operations like renting, accessing, and releasing lockers.

•	LockerManager – Manages internal data like locker availability, PIN assignments, and locker states.

•	IOUtilities – Centralizes and sanitizes all user input (locker number, PIN, confirmation), keeping UI clean and validated.

•	Result – Provides a consistent way to return success/failure messages from service methods.

This setup allows each class to focus on one job, making the system easier to test, debug, and extend. For example, if the validation rules for locker numbers or PINs change, they only need to be updated in one place. 

