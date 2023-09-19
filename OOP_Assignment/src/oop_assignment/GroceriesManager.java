package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface GroceriesManager {

    public static ArrayList<Groceries> loadGroceriesFile(String fileName) {

        String groceryName;
        double price;
        int stock;

        ArrayList<Groceries> groceries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length >= 3) {
                    groceryName = tokens[0];
                    price = Double.parseDouble(tokens[1]);
                    stock = Integer.parseInt(tokens[2]);
                    Groceries grocery = new Groceries(groceryName, price, stock);
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
                        "%s|%.2f|%d%n",
                        grocery.getGroceryName(),
                        grocery.getGroceryPrice(),
                        grocery.getStock()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
