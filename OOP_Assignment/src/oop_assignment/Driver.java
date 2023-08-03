package oop_assignment;

import java.util.*;

public class Driver {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int choice;

        System.out.println("Welcome!");
        displayOptions();
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
                break;
            default:
                System.out.println("OOB");
                break;
        }
    }

    public static void displayOptions() {
        System.out.println("Choose action");
        System.out.println("1. List of groceries");
        System.out.println("2. Grocery ordering");
        System.out.println("3. Stock");
        System.out.println("4. Membership");
        System.out.println("5. Report and analytics");
        System.out.println("6. Promotion/sale of the day");
        System.out.print("Input: ");
    }
}
