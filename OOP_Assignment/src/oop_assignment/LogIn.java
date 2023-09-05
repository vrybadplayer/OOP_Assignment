/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author lolka
 */
public class LogIn {

    public void membership() {
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccessful = false;

        do {
            System.out.print("Enter your username: ");
            String enteredUsername = scanner.next();

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.next();

            try {
                File file = new File("membership.txt");
                try (Scanner scanner1 = new Scanner(file)) {
                    boolean found = false;

                    while (scanner1.hasNextLine()) {
                        String line = scanner1.nextLine();
                        String[] data = line.split("\\|");

                        if (data.length >= 2) {
                            String name = data[0].trim();
                            String password = data[1].trim();

                            if (enteredUsername.equals(name) && enteredPassword.equals(password)) {
                                System.out.println("Login successful!");
                                found = true;
                                loginSuccessful = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Login failed. Incorrect username or password.");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Error: " + e.getMessage()); // Print the error message
            }
        } while (!loginSuccessful);
    }
}
