/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.util.Scanner;

/**
 *
 * @author lolka
 */
public class membership {

    double subtotal;

    public void membership() {
        String registration = null;
        System.out.println("Do you have any membership?");
        Scanner scanner = new Scanner(System.in);
        if (registration == "Y" || registration == "y") {
            System.out.println("Please enter your membership number:");
            int got = scanner.nextInt();
            System.out.println("Please enter your membership name:");
            String name = scanner.next();
            while (got != 0 || name != "a") {
                //when the name does not exist
                if (got != 0 || name != "a") {
                    System.out.println("Do not have this account.");
                    System.out.println("Please enter membership number again:");
                    int reenter = scanner.nextInt();
                    System.out.println("Do not have this account.Please enter membership number again:");
                    String reenter1 = scanner.next();
                }
            }
        } else {
            System.out.println("Do you want to register a membership?");
            String want = scanner.next();
            if (want == "Y" || want == "y") {
                System.out.println("Please enter a name:");
                String names = scanner.next();
                int membershipNumber = Math.round((int) Math.random() * 100);
                System.out.println("Welcome to join our membership" + names);
                System.out.println("This is your membership number" + membershipNumber);
                //save to the file
            } else {
                System.out.println("This is your subtotal amount" + subtotal);
            }
        }
    }

    public void redeemPresent(double subtotal, int membershipNumber, String name) {
        //RM10=1 points
        Scanner scanner = new Scanner(System.in);
        double point = subtotal / 10;
        double correctedPoint = Math.round(point);
        System.out.println("Do you to redeem any things?");
        System.out.println("Your points got " + correctedPoint);
        RedemptionList redemptionList = new RedemptionList();
        redemptionList.RedemptionList();
        //this discount(subtotal) is want to change become the redemption list
        System.out.println("Which items you want to redeem?");
        int want = scanner.nextInt();
        switch (want) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("You will get the item as soon as possible.");
                double adjusted = correctedPoint - 10000;
                break;
            default:
                System.out.println("Thank you for your service on groceries");
        }

    }

    public double discount(double subtotal) {
        double discounted = subtotal / 0.05;
        return discounted;
    }

}
