package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface SalesManager {

    public static ArrayList<Sales> loadSales(String fileName) {

        String groceryName;
        int salesAmount;

        ArrayList<Sales> sales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length >= 2) {
                    groceryName = tokens[0];
                    salesAmount = Integer.parseInt(tokens[1]);

                    Sales sale = new Sales(groceryName, salesAmount);
                    sales.add(sale);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return sales;
    }

    public static void saveSales(ArrayList<Sales> sales, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Sales sale : sales) {
                String line = String.format(
                        "%s|%d%n",
                        sale.getGroceryName(),
                        sale.getSalesAmount()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
