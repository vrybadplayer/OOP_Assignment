package oop_assignment;

import java.util.*;

public class Receipt implements PrintReceipt {

    private int receiptNo = 35000;
    private Date date = new Date();

    public void receiptNoIncrement() {
        this.receiptNo++;
    }

    public boolean receiptDecision() {
        String printDecision = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\nDo you want to print out receipt (y/n): ");
            printDecision = scanner.next();

            if (!printDecision.equals("y") && !printDecision.equals("n")) {
                System.out.println("Enter only y or n!");
            }

        } while (!printDecision.equals("y") && !printDecision.equals("n"));

        if (printDecision.equals("y")) {
            return true;
        } else {
            return false;
        }

    }

    public double calculatePoints() {
        return 0;
    }

    public int getPoints() {
        return 0;
    }

    public void increasePoint(int points) {
        //this.points += points;
    }

    @Override
    public void printMemberReceipt(Payment payment, Groceries groceries, Order order) {
        System.out.println("\nTrapstar Groceries");
        System.out.println("Bentonville, 702 SW 8th St,");
        System.out.println("United States\n");
        System.out.printf("User: REPLACE THIS\n");
        System.out.println(date);
        payment.displayOrders(groceries, order);
        payment.displayCash();
        System.out.println("Loyalty Points: ");
        System.out.println("\nRECEIPT NUMBER");
        System.out.printf("%d\n\n", receiptNo);
        receiptNoIncrement();
        System.out.println("Thank You For Shopping At Trapstar");
        System.out.println("www.trapstar.org");
        System.out.println("Customer Service: 1-300-22-2828");
    }

    @Override
    public void printNonMemberReceipt(Payment payment, Groceries groceries, Order order) {
    
    }

}
