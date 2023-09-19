package oop_assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Redemption extends Stock {

    private String itemName;
    private int pointsAmount;

    public Redemption(String itemName, int pointsAmount, int stock) {
        this.itemName = itemName;
        this.pointsAmount = pointsAmount;
        setStock(stock);
    }

    public String getItemName() {
        return itemName;
    }

    public int getPointsAmount() {
        return pointsAmount;
    }

    public static void redemptionMenu() throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        int choice = 0;
        ArrayList<Redemption> redemptionList = RedemptionManager.loadRedemptions("redemption.txt");
        ArrayList<Customer> customers = CustomerManager.loadCustomersFromFile("membership.txt");

        System.out.println("\nRedemption List");
        System.out.println("[-----------------------------------+--------]");
        System.out.println("|               Prizes              | Points |");
        System.out.println("[-----------------------------------+--------]");
        for (int i = 0; i < redemptionList.size(); ++i) {
            System.out.printf("|%2d) %-30s | %-6d |\n", i + 1, redemptionList.get(i).getItemName(), redemptionList.get(i).getPointsAmount());
        }
        System.out.println("[-----------------------------------+--------]");
        System.out.printf("You now have: %d points.\n", customers.get(Driver.getCustomerIndex()).getLoyaltyPoints());
        do {
            System.out.print("Which prize do you want to redeem? (Enter X to exit): ");
            if (scanner.hasNext()) {
                input = scanner.next();
                if (input.equals("X")) {
                    break;
                } else if (input.matches("\\d+") && Integer.parseInt(input) > 0 && Integer.parseInt(input) <= redemptionList.size()) {
                    // User entered a valid number as a string
                    choice = Integer.parseInt(input);
                    if (redemptionList.get(choice - 1).getPointsAmount() > customers.get(Driver.getCustomerIndex()).getLoyaltyPoints()) {
                        System.out.println("You do not have enough points to redeem this prize!");
                    } else if (redemptionList.get(choice - 1).getStock() == 0) {
                        System.out.println("Sorry! The limited prize is out of stock! :(");
                    } else {
                        break;
                    }
                } else {
                    System.out.printf("Invalid input. Enter between 1 - %d!\n", redemptionList.size());
                }
            } else {
                System.out.println("Invalid input!");
            }
        } while (true);

        if (!input.equals("X")) {
            //Update customer details
            customers.get(Driver.getCustomerIndex()).reduceLoyaltyPoints(redemptionList.get(choice - 1).getPointsAmount());
            CustomerManager.saveCustomersToFile(customers, "membership.txt");
            //Update prize stock
            redemptionList.get(choice - 1).minusStock(1);
            RedemptionManager.saveRedemptions(redemptionList, "redemption.txt");
            //Message
            System.out.println("\nPrize has been redeemed!");
            System.out.println("The prize will be delivered to your mailing address soon.\n");
            Thread.sleep(500);
        }

    }

}
