package oop_assignment;

import com.sun.source.tree.ContinueTree;
import java.util.ArrayList;
import java.util.Scanner;

public class Payment extends Order {

    private String proceed = "";
    private double grandTotal = 0;

    public Payment() {
        super();
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

    public void displayOrders(Groceries groceries) {
        System.out.printf("\n%-15s %-9s\n", "Items", "Amount");
        orderListing(getGroceryIndex(), getGroceryAmount(), groceries.getGroceries(), groceries.getPrice());

    }

    public void orderListing(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount, ArrayList<String> groceries, ArrayList<Double> price) {
        for (int i = 0; i < groceryIndex.size(); ++i) {
            // Items, Amount, Price, Total
            System.out.printf("%-15s %-9d\n", groceries.get(groceryIndex.get(i)), groceryAmount.get(i));
        }
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
            return balance;
        } else {
            System.out.printf("\nPayment successful! You now have RM%.2f\n", balance - getGrandTotal());
            return balance - getGrandTotal();
        }
    }

    public double calculatePoints() {
        return getTotal() * 0.05;
    }
    

}
