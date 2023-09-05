/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lolka
 */
public class ConcreteSignUp extends SignUp {

    String email;
    String name;
    String phoneNumber;
    String passWord;

    @Override
    public void Signup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                             _______ _____            _____   _____ _______       _____     _____ _____   ____   _____ ______ _____  _____ ______  _____ 
                            |__   __|  __ \\     /\\   |  __ \\ / ____|__   __|/\\   |  __ \\   / ____|  __ \\ / __ \\ / ____|  ____|  __ \\|_   _|  ____|/ ____|
                               | |  | |__) |   /  \\  | |__) | (___    | |  /  \\  | |__) | | |  __| |__) | |  | | |    | |__  | |__) | | | | |__  | (___  
                               | |  |  _  /   / /\\ \\ |  ___/ \\___ \\   | | / /\\ \\ |  _  /  | | |_ |  _  /| |  | | |    |  __| |  _  /  | | |  __|  \\___ \\ 
                               | |  | | \\ \\  / ____ \\| |     ____) |  | |/ ____ \\| | \\ \\  | |__| | | \\ \\| |__| | |____| |____| | \\ \\ _| |_| |____ ____) |
                               |_|  |_|  \\_\\/_/    \\_\\_|    |_____/   |_/_/    \\_\\_|  \\_\\  \\_____|_|  \\_\\\\____/ \\_____|______|_|  \\_\\_____|______|_____/ 
                           """);
        System.out.println("""
                           With our XYZ Groceries Membership,you'll enjoy incredible discounts on a wide range of products,every time you shop.
                           Introducing the all-new XYZ Groceries Membership 2013 your key to unlocking a world of savings, rewards, and exclusive benefits!
                           But that's not all! For every RM10 you spend, you'll earn loyalty point. The more you shop, the more points you earn!
                           Not only that on every purchase will get 5% discount whatever you buy.
                           But the excitement doesn't stop there. As your points accumulate, you'll be closer to claiming exciting rewards that are sure to delight.
                           Imagine hitting the target points and receiving a special surprise, just for being a loyal member!
                           Ready to start saving and earning? Sign up for our Grocery Savings Membership today, either in-store or conveniently online.
                           Beyond the rewards, you'll become part of a welcoming community of fellow shoppers who share your passion for smart shopping.
                           Don't wait any longer. Make the most of your grocery shopping. Get your Grocery Savings Membership now and enjoy a whole new level of shopping!
                           Experience the joy of shopping with rewards at your fingertips!
                           """);

        boolean validEmail = false;
        boolean validPhoneNumber = false;
        System.out.print("Please Enter Your Username:");
        name = scanner.next();
        while (isExistingName(name) == true) {
            System.out.println("This name is already registered. Please enter a different name.");
            System.out.print("Please enter a different name:");
            name = scanner.next();
        }
        System.out.print("Please Enter Your Password with an One Big Letter,One Special Symbol and 12 letter only:");
        passWord = scanner.next();
        while (passWord.matches("^(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{1,13}$") == false) {
            System.out.println("Please enter password with at least One Big Letter,One Special Symbol and 12 letter only!");
            System.out.print("Please enter a strong password:");
            passWord = scanner.next();
        }
        do {
            System.out.print("Please Enter Email Address:");
            email = scanner.next();
            if (isValidEmail(email)) {
                if (isExistingEmail(email)) {
                    System.out.println("This name Email already existed.Please use another name.");
                    System.out.print("Please enter new name again:");
                    email = scanner.next();
                }
            } else {
                System.out.print("Please use the correct format wtih(XXX@gmail.com):");
                email = scanner.next();
            }
        } while (validEmail == true);
        do {
            System.out.print("Please Enter Phone Number:");
            phoneNumber = scanner.next();
            if (isValidPhoneNumber(phoneNumber)) {
                if (isExistingPhoneNumber(phoneNumber)) {
                    System.out.println("This Phone Number already existed.Please Try Another Phone Number.");
                    System.out.print("Enter Phone Number:");
                    phoneNumber = scanner.next();
                }
            } else {
                System.out.println("Please enter a right format with XXX-XXXXXXX");
                System.out.print("Enter Phone Number:");
                phoneNumber = scanner.next();
            }
        } while (validPhoneNumber == true);
        System.out.print("Please Enter Mailing Address:");
        String mail = scanner.next();
        System.out.println("Your point will start with 1000!!!");
        int point = 1000;
        saveToFile(name, passWord, email, phoneNumber, mail, point);
        System.out.println("Thank you for joining our Trapstar Groceries!");
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
        try (BufferedReader reader = new BufferedReader(new FileReader("membership.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String existingEmail = tokens[2];
                if (existingEmail.equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Error!");
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

    private static void saveToFile(String name, String passWord, String email, String phoneNumber, String mail, int point) {
        try {
            FileWriter fileWriter = new FileWriter("membership.txt", true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(name + "|" + passWord + "|" + email + "|" + phoneNumber + "|" + mail + "|" + point + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
