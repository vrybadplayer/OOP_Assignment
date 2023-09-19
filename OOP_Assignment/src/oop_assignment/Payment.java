package oop_assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Payment extends Order {

    private String proceed = "";
    private static double grandTotal = 0;
    private static boolean insufficientBalance = false;
    private final double deliveryFee = 4.90;

    public Payment() {
        super();
    }

    public static boolean isInsufficientBalance() {
        return insufficientBalance;
    }

    public double calculateTax() {
        return getTotal() * tax;
    }

    public void calculateGrandTotal() {
        Payment.grandTotal = getTotal() + (getTotal() * tax);
    }

    public void setGrandTotal(double grandTotal) {
        Payment.grandTotal = grandTotal;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void displayCash() {
        calculateGrandTotal();
        setGrandTotal(grandTotal);
        System.out.printf("\n%-14s: RM %.2f\n", "Total is", getTotal());
        System.out.printf("%-14s: RM %.2f\n", "Tax is", calculateTax());
        if (Driver.isMember()) {
            System.out.printf("%-14s: RM %.2f\n", "Grand total is", getGrandTotal());
        } else {
            System.out.printf("%-14s: RM 4.90\n", "Delivery Fee");
            System.out.printf("%-14s: RM %.2f\n", "Grand total is", getGrandTotal() + deliveryFee);
        }
    }

    public void displayOrders(ArrayList<Groceries> groceries, Order order) {
        System.out.println("\n[-----------------+-----------+---------------]");
        System.out.printf("| %-15s | %-9s | %-9s |\n", "Items", "Amount", "Sub Total(RM)");
        System.out.println("|-----------------+-----------+---------------|");
        orderListing(getGroceryIndex(), getGroceryAmount(), groceries, getSubTotal());
    }

    public void orderListing(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount, ArrayList<Groceries> groceries, ArrayList<Double> subTotal) {
        for (int i = 0; i < groceryIndex.size(); ++i) {
            // Items, Amount, Subtotal
            System.out.printf("| %-15s | %-9d | %13.2f |\n", groceries.get(groceryIndex.get(i)).getGroceryName(), groceryAmount.get(i), subTotal.get(i));
        }
        System.out.println("[-----------------+-----------+---------------]");
    }

    public boolean proceedPayment() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\nProceed to payment(y/n): ");
            proceed = scanner.next();

            if (!proceed.equals("y") && !proceed.equals("n")) {
                System.out.println("Enter only y or n!");
            }

        } while (!proceed.equals("y") && !proceed.equals("n"));

        return proceed.equals("y");
    }

    public double pay(double balance) {
        if (getGrandTotal() > balance) {
            System.out.printf("\nInsufficient balance! \nYou only have RM%.2f, you need RM%.2f more.\n", balance, getGrandTotal() - balance);
            insufficientBalance = true;
            return balance;
        } else {
            System.out.printf("\nPayment successful! You now have RM%.2f\n", balance - getGrandTotal());
            return balance - getGrandTotal();
        }
    }
}
