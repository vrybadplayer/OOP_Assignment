package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface StaffManager {

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

                    Staff staff = new Staff(staffName, password);
                    staffs.add(staff);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return staffs;
    }

    public static void saveGroceriesToFile(ArrayList<Groceries> groceries, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Groceries grocery : groceries) {
                String line = String.format(
                        "%s|%.2f%n",
                        grocery.getGroceryName(),
                        grocery.getGroceryPrice()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
