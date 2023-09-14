package oop_assignment;

import java.util.*;

public class Receipt implements PrintReceipt {

    private int receiptNo = 35000;
    private Date date = new Date();

    public void receiptNoIncrement() {
        this.receiptNo++;
    }

    public int getReceiptNo() {
        return receiptNo;
    }

    public static int calculateLoyaltyPoints() {
        return (int) Math.round(Order.getTotal() * 0.05);
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

    @Override
    public void printMemberReceipt(Payment payment, ArrayList<Groceries> groceries, Order order, ArrayList<Customer> customer) {
        System.out.println("\n               Trapstar Groceries");
        System.out.println("           Bentonville, 702 SW 8th St,");
        System.out.println("                 United States\n");
        System.out.printf("                 User: %s\n", customer.get(Driver.getCustomerIndex()).getName());
        System.out.println("          " + date);
        payment.displayOrders(groceries, order);
        payment.displayCash();
        System.out.printf("%-14s: %d", "Loyalty Points", calculateLoyaltyPoints());
        customer.get(Driver.getCustomerIndex()).setLoyaltyPoints(customer.get(Driver.getCustomerIndex()).getLoyaltyPoints() + calculateLoyaltyPoints());
        System.out.println("\n\n                RECEIPT NUMBER");
        System.out.printf("                     %d\n\n", getReceiptNo());
        receiptNoIncrement();
        System.out.println("      Thank You For Shopping At Trapstar");
        System.out.println("              www.trapstar.org");
        System.out.println("       Customer Service: 1-300-22-2828");
    }

    @Override
    public void printNonMemberReceipt(Payment payment, ArrayList<Groceries> groceries, Order order, ArrayList<Customer> customer) {
        System.out.println("\n               Trapstar Groceries");
        System.out.println("           Bentonville, 702 SW 8th St,");
        System.out.println("                 United States\n");
        System.out.printf("                   User: Guest\n");
        System.out.println("          " + date);
        payment.displayOrders(groceries, order);
        payment.displayCash();
        System.out.println("\n\n                RECEIPT NUMBER");
        System.out.printf("                     %d\n\n", getReceiptNo());
        receiptNoIncrement();
        System.out.println("      Thank You For Shopping At Trapstar");
        System.out.println("              www.trapstar.org");
        System.out.println("       Customer Service: 1-300-22-2828");
    }

}
