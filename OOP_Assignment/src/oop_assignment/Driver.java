package oop_assignment;

import java.util.*;

public class Driver {

    public static int index = 0;

    public static void main(String[] args) {
        Groceries groceries = new Groceries();
        Order order = new Order();
        Payment payment = new Payment();
        //User Inputs
        order.getOrder(order, groceries);
        payment.displayDetails();
        order.displayCash();
    }

    //Temporary whatever
    public static void choice(int choice) {
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
            case 5:
                System.out.println("5");
                break;
            case 6:
                System.out.println("6");
                break;
            case 7:
                System.out.println("7");
                break;
            case 8:
                System.out.println("8");
                break;
            case 9:
                System.out.println("9");
                break;
            case 10:
                System.out.println("10");
                break;
            default:
                System.out.println("OOB");
                break;
        }
    }

    //Should be from Groceries class
    public static void displayGroceries() {
        System.out.println("1. Milk");
        System.out.println("2. Bread");
        System.out.println("3. Eggs");
        System.out.println("4. Cheese");
        System.out.println("5. Water");
        System.out.println("6. Rice");
        System.out.println("7. Chicken");
        System.out.println("8. Beef");
        System.out.println("9. Apples");
        System.out.println("10. Bananas");
        System.out.print("Which one do you want to buy?: ");
    }

    //cls
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }
}
