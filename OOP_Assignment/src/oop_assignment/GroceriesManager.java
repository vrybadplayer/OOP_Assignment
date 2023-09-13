package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface GroceriesManager {
    
        public static ArrayList<Groceries> loadGroceriesFile(String fileName) {
        String groceryName;
        double price;

        ArrayList<Groceries> groceries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length >= 2) {
                    groceryName = tokens[0];
                    price = Double.parseDouble(tokens[1]);

                    Groceries grocery = new Groceries(groceryName, price);
                    groceries.add(grocery);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return groceries;
    }

    public static void saveGroceriesToFile(ArrayList<Groceries> groceries, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Groceries grocery : groceries) {
                String line = String.format(
                        "%s|%.2f%n",
                        groceries.getGroceryName(),
                        groceries.getPrice()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    } 
}
