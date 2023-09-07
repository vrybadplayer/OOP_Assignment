package oop_assignment;

import java.util.*;

public class Driver {

    private static boolean proceed = false;
    private static double balance = 1000.00;

    public static void main(String[] args) {

        Groceries groceries;
        Payment payment;
        Order order;
        Receipt receipt;
        
        do {
            //Create objects
            groceries = new Groceries();
            payment = new Payment();
            order = new Order();
            receipt = new Receipt();

            //User Inputs
            payment.getOrder(order, groceries);
            //Display
            payment.displayOrders(groceries);
            payment.displayCash();
            proceed = payment.proceedPayment();
            balance = payment.pay(balance);
        } while (!proceed);
        
        boolean receiptDecision = receipt.receiptDecision();
        
        if (receiptDecision){
            receipt.printReceipt();
        }
        
        System.out.println("\nThank you for using Trapstar Groceries!\n\n");
    }

    //Should be from Groceries class
    public static void displayGroceries() {
        System.out.println("\n1. Milk");
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
    public static void cls() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
