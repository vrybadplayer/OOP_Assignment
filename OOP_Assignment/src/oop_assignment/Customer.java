package oop_assignment;

//Dev 2 customer

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer implements CustomerManager {

    LogIn login = new LogIn();
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String mailingAddress;
    private int loyaltyPoints;
    private double balance;

    public Customer() {

    }

    public Customer(String name, String password, String email, String phoneNumber, String mailingAddress, int loyaltyPoints, double balance) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mailingAddress = mailingAddress;
        this.loyaltyPoints = loyaltyPoints;
        this.balance = balance;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %d  %.2f\n", name, password, email, phoneNumber, mailingAddress, loyaltyPoints, balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void reduceLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = getLoyaltyPoints() - loyaltyPoints;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void topUp(ArrayList<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        boolean validCardNumber = false;
        double inputBalance = 0;

        while (!validCardNumber) {
            System.out.print("Enter Your Credit Card Number (Format: XXXX-XXXX-XXXX-XXXX): ");
            String enteredCardNumber = scanner.next();

            if (isValidCardNumber(enteredCardNumber)) {
                System.out.print("Enter Your Credit Card Password(Format: One Big Letter, One Special Symbol, and 12 letters only): ");
                String cardPassword = scanner.next();
                while (cardPassword.matches("^(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{1,13}$") == false) {
                    System.out.print("Invalid card number password format. Please use One Big Letter, One Special Symbol, and 12 letters only format.");
                    System.out.print("Please enter a strong password: ");
                    cardPassword = scanner.next();
                }

                while (true) {
                    System.out.print("Please enter the balance that you want to top up: ");
                    if (scanner.hasNextDouble()) {
                        inputBalance = scanner.nextDouble();
                        if (inputBalance >= 0) {
                            break; // Valid input, exit the loop
                        } else {
                            System.out.println("Invalid input. Please only enter positive numbers!");
                        }
                    } else {
                        System.out.println("Invalid input. Please only enter positive numbers!");
                        scanner.next(); // Consume invalid input to avoid an infinite loop
                    }
                }

                customers.get(Driver.getCustomerIndex()).setBalance(getBalance() + inputBalance);

                CustomerManager.saveCustomersToFile(customers, "membership.txt");
                validCardNumber = true;
                System.out.println("Balance updated successfully!");
            } else {
                System.out.println("Invalid card number format. Please use XXXX-XXXX-XXXX-XXXX format.");
            }
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }

}
