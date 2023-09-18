package oop_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogIn implements CustomerManager {

    String enteredUsername;
    String enteredPassword;

    public int membership(ArrayList<Customer> customer) throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccessful = false;

        do {
            System.out.print("Enter your username (Enter X to Exit): ");
            enteredUsername = scanner.nextLine();

            if (enteredUsername.equals("X")) {
                Driver.displayMainPage(customer);
                break;
            }

            System.out.print("Enter your password: ");
            enteredPassword = scanner.nextLine();

            if (isExisting(enteredUsername, enteredPassword)) {
                System.out.println("Login successful!");
                Driver.setMember(true);
                loginSuccessful = true;
            } else {
                System.out.println("Login failed. Incorrect username or password. Please try again.");
            }
        } while (!loginSuccessful);
        return getCustomerIndex(enteredUsername, enteredPassword);
    }

    private boolean isExisting(String name, String password) {
        ArrayList<Customer> customers = CustomerManager.loadCustomersFromFile("membership.txt");
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name) && customers.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public int getCustomerIndex(String name, String password) {
        ArrayList<Customer> customers = CustomerManager.loadCustomersFromFile("membership.txt");
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name) && customers.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return 0;
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

}
