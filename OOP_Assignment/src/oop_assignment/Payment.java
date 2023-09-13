package oop_assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Payment extends Order {

    private String proceed = "";
    private double grandTotal = 0;
    private boolean insufficientBalance = false;

    public Payment() {
        super();
    }

    public boolean isInsufficientBalance() {
        return insufficientBalance;
    }
    
    public double calculateTax() {
        return getTotal() * tax;
    }

    public void calculateGrandTotal() {
        this.grandTotal = getTotal() + (getTotal() * tax);
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void displayCash() {
        calculateGrandTotal();
        setGrandTotal(grandTotal);
        System.out.printf("\nTotal is: RM %.2f\n", getTotal());
        System.out.printf("Tax is: RM %.2f\n", calculateTax());
        System.out.printf("Grand total is: RM %.2f\n", getGrandTotal());
    }

    public void displayOrders(Groceries groceries, Order order) {
        System.out.println("\n[-----------------+-----------+---------------]");
        System.out.printf("| %-15s | %-9s | %-9s |\n", "Items", "Amount", "Sub Total(RM)");
        System.out.println("|-----------------+-----------+---------------|");
        orderListing(getGroceryIndex(), getGroceryAmount(), groceries.getGroceries(), getSubTotal());
    }

    public void orderListing(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount, ArrayList<String> groceries, ArrayList<Double> subTotal) {
        for (int i = 0; i < groceryIndex.size(); ++i) {
            // Items, Amount, Subtotal
            System.out.printf("| %-15s | %-9d | %13.2f |\n", groceries.get(groceryIndex.get(i)), groceryAmount.get(i), subTotal.get(i));
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

        if (proceed.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public double pay(double balance) {
        if (getTotal() > balance) {
            System.out.printf("\nInsufficient balance! \nYou only have RM%.2f, you need RM%.2f more.\n", balance, getGrandTotal() - balance);
            insufficientBalance = true;
            return balance;
        } else {
            System.out.printf("\nPayment successful! You now have RM%.2f\n", balance - getGrandTotal());
            return balance - getGrandTotal();
        }
    }

    public int calculateLoyaltyPoints() {
        return (int) Math.round(getTotal() * 0.05);
    }

}
