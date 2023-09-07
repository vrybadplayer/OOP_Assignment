package oop_assignment;

import java.util.*;

public class Receipt {

    Scanner scanner = new Scanner(System.in);

    public boolean receiptDecision() {
        String printDecision = "";
        System.out.println("\nDo you want to print out receipt?");

        do {
            System.out.print("\nProceed to payment(y/n): ");
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

    public void printReceipt() {
        System.out.println("\n[Enter Receipt Here]");
    }

    public int getPoints() {

        //points is accessed from Customer Class
        //return points;
        return 0;
    }

    public void increasePoint(int points) {
        //this.points += points;
    }

}
