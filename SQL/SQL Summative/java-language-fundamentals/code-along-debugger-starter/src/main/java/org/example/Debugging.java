package org.example;

public class Debugging {


    public static void main(String[] args) {
        System.out.println("Program Start");
        System.out.println("Calculate Factorial for 12");
        int result = factorialCalc(12);
        System.out.println("The result for 12 is :" + result);


        double[] grades = {98.2, 78.4, 99.1, 101.2, 87.3, 69.8, 83.1, 55.1};


        System.out.println("Calculating the average for the following grades: ");


        for (int i = 0; i < grades.length; i++) {
            System.out.println("Grade " + (i+1) + " is " + grades[i]);
        }


        System.out.println("Average is :" + gradeAverage(grades));
    }


    public static int factorialCalc(int value) {
        if (value == 0)
            return 1;
        else
            return (value * factorialCalc(value - 1));
    }

    public static double gradeAverage(double[] grades) {
        double total = 0.0;
        for(int i = 1; i <= grades.length; i++) {
            total += grades[i];
        }
        return total/grades.length;
    }


}


