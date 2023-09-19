package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConcreteSignUp extends SignUp {

    ArrayList<Customer> customer = CustomerManager.loadCustomersFromFile("membership.txt");
    String email;
    String name;
    String phoneNumber;
    String passWord;
    String mail;

    @Override
    public void Signup() throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        boolean validEmail = false;
        boolean validPhoneNumber = false;
        boolean validName = false;
        System.out.println("""
                             _______ _____            _____   _____ _______       _____     _____ _____   ____   _____ ______ _____  _____ ______  _____ 
                            |__   __|  __ \\     /\\   |  __ \\ / ____|__   __|/\\   |  __ \\   / ____|  __ \\ / __ \\ / ____|  ____|  __ \\|_   _|  ____|/ ____|
                               | |  | |__) |   /  \\  | |__) | (___    | |  /  \\  | |__) | | |  __| |__) | |  | | |    | |__  | |__) | | | | |__  | (___  
                               | |  |  _  /   / /\\ \\ |  ___/ \\___ \\   | | / /\\ \\ |  _  /  | | |_ |  _  /| |  | | |    |  __| |  _  /  | | |  __|  \\___ \\ 
                               | |  | | \\ \\  / ____ \\| |     ____) |  | |/ ____ \\| | \\ \\  | |__| | | \\ \\| |__| | |____| |____| | \\ \\ _| |_| |____ ____) |
                               |_|  |_|  \\_\\/_/    \\_\\_|    |_____/   |_/_/    \\_\\_|  \\_\\  \\_____|_|  \\_\\\\____/ \\_____|______|_|  \\_\\_____|______|_____/ 
                           """);
        System.out.println("""
                           With our Trapstar Groceries Membership, you'll enjoy incredible discounts on a wide range of products every time you shop.
                           Introducing the all-new Trapstar Groceries Membership 2013 your key to unlocking a world of savings, rewards, and exclusive benefits!
                           But that's not all! For every dime you spend, you'll earn loyalty point. The more you shop, the more points you earn!
                           Not only that on every purchase will get 5% discount whatever you buy.
                           But the excitement doesn't stop there. As your points accumulate, you'll be closer to claiming exciting rewards that are sure to delight.
                           Imagine hitting the target points and receiving a special surprise, just for being a loyal member!
                           Ready to start saving and earning? Sign up for our Grocery Savings Membership today, either in-store or conveniently online.
                           Beyond the rewards, you'll become part of a welcoming community of fellow shoppers who share your passion for smart shopping.
                           Don't wait any longer. Make the most of your grocery shopping. Get your Grocery Savings Membership now and enjoy a whole new level of shopping!
                           Experience the joy of shopping with rewards at your fingertips!
                           """);

        while (!validName) {
            System.out.print("Please Enter Your Username:");
            name = scanner.nextLine();
            if (isExistingName(name)) {
                System.out.println("This name is already registered. Please enter a different name.");
            } else if (name.isEmpty() || name == null) {
                System.out.println("Username cannot be blank!");
            } else {
                validName = true;
            }
        }
        System.out.print("Please Enter Your Password with an One Big Letter,One Special Symbol and 12 letter only:");
        passWord = scanner.nextLine();
        while (passWord.matches("^(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{1,13}$") == false) {
            System.out.println("Please enter password with at least One Big Letter,One Special Symbol and 12 letter only!");
            System.out.print("Please enter a strong password:");
            passWord = scanner.nextLine();
        }
        while (!validEmail) {
            System.out.print("Please Enter Your Email:");
            email = scanner.nextLine();
            if (isExistingEmail(email)) {
                System.out.println("This Email is already registered. Please enter a different Email.");
            } else if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            } else {
                validEmail = true;
            }
        }
        while (!validPhoneNumber) {
            System.out.print("Please Enter Your Phone Number:");
            phoneNumber = scanner.nextLine();
            if (isExistingPhoneNumber(phoneNumber)) {
                System.out.println("This Phone Number is already registered. Please enter a different Phone Number.");
            } else if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number format. Please enter a valid phone number (e.g., 123-4567890).");
            } else {
                validPhoneNumber = true;
            }
        }
        do {
            System.out.print("Please Enter Mailing Address:");
            mail = scanner.nextLine();
            if (mail.isEmpty() || mail == null) {
                System.out.println("Mailing Address cannot be empty!");
            } else {
                break;
            }
        } while (true);
        
        System.out.println("Your point will start with 1000!!!");
        int point = 1000;
        System.out.println("Your starting balance is RM0");
        double balance = 0;
        ArrayList<Customer> customers = CustomerManager.loadCustomersFromFile("membership.txt");
        customers.add(new Customer(name, passWord, email, phoneNumber, mail, point, balance));
        CustomerManager.saveCustomersToFile(customers, "membership.txt");
        System.out.println("Thank you for joining our Trapstar Groceries!");
        customer = CustomerManager.loadCustomersFromFile("membership.txt");
        Driver.systemPause();
        Driver.displayMainPage(customer);
    }

    private static boolean isExistingName(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("membership.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String existingName = tokens[0];
                if (existingName.equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Error!");
        }
        return false;
    }

    private static boolean isExistingEmail(String email) {
        ArrayList<Customer> customers = CustomerManager.loadCustomersFromFile("membership.txt");

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@gmail.com");
    }

    private static boolean isExistingPhoneNumber(String phoneNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("membership.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String existingphoneNumber = tokens[3];
                if (existingphoneNumber.equals(phoneNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Error!");
        }
        return false;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{3}-\\d{7}");
    }

}
