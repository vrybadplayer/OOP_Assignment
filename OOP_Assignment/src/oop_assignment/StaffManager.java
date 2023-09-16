package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public interface StaffManager {

    public static void saveStaffToFile(ArrayList<Staff> staff, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Staff staffs : staff) {
                String line = String.format(
                        "%s|%s%n",
                        staffs.getStaffName(),
                        staffs.getStaffPassword()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Staff> loadStaffFiles(String fileName) {

        String staffName;
        String password;

        ArrayList<Staff> staffs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length >= 2) {
                    staffName = tokens[0];
                    password = tokens[1];

                    Staff tempStaff = new Staff(staffName, password);
                    staffs.add(tempStaff);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return staffs;
    }

    public static boolean staffLogin() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccessful = false;
        String enteredUsername;
        String enteredPassword;

        do {
            System.out.print("Enter your username (Enter X to Exit): ");
            enteredUsername = scanner.nextLine();

            if (enteredUsername.equals("X")) {
                break;
            }

            System.out.print("Enter your password: ");
            enteredPassword = scanner.nextLine();

            if (isExisting(enteredUsername, enteredPassword)) {
                System.out.println("\nLogin successful!");
                Thread.sleep(1000);
                loginSuccessful = true;
            } else {
                System.out.println("Login failed. Incorrect username or password. Please try again.");
            }
        } while (!loginSuccessful);
        return loginSuccessful;
    }

    private static boolean isExisting(String username, String password) {
        ArrayList<Staff> staffs = loadStaffFiles("staff.txt");
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getStaffName().equals(username) && staffs.get(i).getStaffPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
