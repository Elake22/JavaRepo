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

Prototype-Building Experience & Lessons Learned

The initial version of the app served as a  prototype to validate core features like renting, accessing, and releasing lockers. While effective for early testing, it revealed clear limitations in input handling, validation, and structural clarity. Through intentional testing such as entering invalid data, skipping steps, and stressing edge cases I identified weak points that prompted deeper validation and a cleaner class structure. Refactoring allowed me to isolate logic, enforce input rules, and ensure the app handled unexpected behavior without breaking.
A major bug identified was a mismatch between internal indexing and display locker numbers. Because arrays in Java start at index 0 a logic flaw caused locker #10 to display as #9 after being released and re-rented creating confusion.

Key lessons and improvements from testing include:

•	Isolating logic into specific classes helped eliminate redundancy and made it easier to fix bugs in one place without breaking others.

•	Input validation was critical: testing helped expose scenarios like non-numeric PINs, out-of-range locker numbers, and empty inputs which now all trigger safe re-prompts instead of breaking the flow.

•	PIN access logic was tested under incorrect assumptions, like entering the wrong PIN still triggering a confirmation prompt. This led to a fix: confirmation is now only prompted after PIN validation succeeds.

•	Edge case testing (like trying to rent a locker when none are available, or re-entering a released locker) helped finalize logic paths and response messages.

By approaching testing with the intent to "break" the program, I was able to build confidence in its stability and improve both the user experience and the structural reliability of the code.

Reflection

This assignment challenged my problem-solving skills especially in designing code that is scalable, testable, and maintainable. I focused on identifying and handling invalid user input early, simplifying complex logic by breaking it into focused classes. Edge cases such as invalid menu selections, incorrect PIN formats, and full locker conditions were tested. The process reinforced the value of early testing, clean structure, and designing for real-world use not just functionality. I now better understand the importance of early testing, code readability, and user-friendly design.