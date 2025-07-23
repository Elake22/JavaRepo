package org.example;

import java.util.Scanner;

/**
 * This is a SmartHomeSystem class that does everything in one place.
 * It controls all devices, handles user input, and performs system logic.
 * It is poorly structured for demonstration purposes.
 */

public class SmartHomeSystem {
    private boolean tvOn;
    private boolean fridgeOn;
    private boolean lightOn;
    private boolean toasterOn;
    private boolean acOn;

    public void turnOnTV() {
        if (!tvOn) {
            tvOn = true;
            System.out.println("TV is now ON.");
        } else {
            System.out.println("TV is already ON.");
        }
    }

    public void turnOnFridge() {
        if (!fridgeOn) {
            fridgeOn = true;
            System.out.println("Fridge is now ON.");
        } else {
            System.out.println("Fridge is already ON.");
        }
    }

    public void turnOnLight() {
        if (!lightOn) {
            lightOn = true;
            System.out.println("Light is now ON.");
        } else {
            System.out.println("Light is already ON.");
        }
    }

    public void turnOnToaster() {
        if (!toasterOn) {
            toasterOn = true;
            System.out.println("Toaster is now ON.");
        } else {
            System.out.println("Toaster is already ON.");
        }
    }

    public void turnOnAC() {
        if (!acOn) {
            acOn = true;
            System.out.println("AC is now ON.");
        } else {
            System.out.println("AC is already ON.");
        }
    }

    // Duplicated code for turning off devices
    public void turnOffTV() {
        if (tvOn) {
            tvOn = false;
            System.out.println("TV is now OFF.");
        } else {
            System.out.println("TV is already OFF.");
        }
    }

    public void turnOffFridge() {
        if (fridgeOn) {
            fridgeOn = false;
            System.out.println("Fridge is now OFF.");
        } else {
            System.out.println("Fridge is already OFF.");
        }
    }

    public void turnOffLight() {
        if (lightOn) {
            lightOn = false;
            System.out.println("Light is now OFF.");
        } else {
            System.out.println("Light is already OFF.");
        }
    }

    public void turnOffToaster() {
        if (toasterOn) {
            toasterOn = false;
            System.out.println("Toaster is now OFF.");
        } else {
            System.out.println("Toaster is already OFF.");
        }
    }

    public void turnOffAC() {
        if (acOn) {
            acOn = false;
            System.out.println("AC is now OFF.");
        } else {
            System.out.println("AC is already OFF.");
        }
    }

    public void setDeviceStatus(boolean tvStatus, boolean fridgeStatus, boolean lightStatus,
                                boolean toasterStatus, boolean acStatus, boolean dummy1,
                                boolean dummy2, boolean dummy3, boolean dummy4) {
        this.tvOn = tvStatus;
        this.fridgeOn = fridgeStatus;
        this.lightOn = lightStatus;
        this.toasterOn = toasterStatus;
        this.acOn = acStatus;
    }

    public void startSystem() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSmart Home Control System");
            System.out.println("1. Turn on TV");
            System.out.println("2. Turn off TV");
            System.out.println("3. Turn on Fridge");
            System.out.println("4. Turn off Fridge");
            System.out.println("5. Turn on Light");
            System.out.println("6. Turn off Light");
            System.out.println("7. Turn on Toaster");
            System.out.println("8. Turn off Toaster");
            System.out.println("9. Turn on AC");
            System.out.println("10. Turn off AC");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            if (choice == 1) turnOnTV();
            else if (choice == 2) turnOffTV();
            else if (choice == 3) turnOnFridge();
            else if (choice == 4) turnOffFridge();
            else if (choice == 5) turnOnLight();
            else if (choice == 6) turnOffLight();
            else if (choice == 7) turnOnToaster();
            else if (choice == 8) turnOffToaster();
            else if (choice == 9) turnOnAC();
            else if (choice == 10) turnOffAC();
            else if (choice == 11) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        SmartHomeSystem system = new SmartHomeSystem();
        system.startSystem();
    }
}
