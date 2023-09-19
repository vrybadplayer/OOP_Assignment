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

    public static void updateSales(ArrayList<Sales> sale) {
        ArrayList<Groceries> groceries = GroceriesManager.loadGroceriesFile("groceries.txt");
        ArrayList<Integer> groceryIndex = Order.getGroceryIndex();
        ArrayList<Integer> groceryAmount = Order.getGroceryAmount();
        ArrayList<Sales> sales = sale;

        ArrayList<Integer> unsavedIndex = new ArrayList<>(Order.getGroceryIndex());
        ArrayList<Integer> unsavedAmount = new ArrayList<>(Order.getGroceryAmount());

        for (int j = 0; j < groceryIndex.size(); ++j) {
            int index = groceryIndex.get(j);
            String groceriesName = groceries.get(index).getGroceryName();

            for (int i = 0; i < sales.size(); ++i) {
                String salesGroceryName = sales.get(i).getGroceryName();

                if (salesGroceryName.equals(groceriesName)) {
                    unsavedIndex.remove(Integer.valueOf(index)); // Remove the value, not the index
                    unsavedAmount.remove(Integer.valueOf(index));
                    sales.get(i).updateSalesAmount(groceryAmount.get(j));
                    break;
                }
            }
        }

        //Store unexisting sales
        for (int i = 0; i < unsavedIndex.size(); ++i) {
            sales.add(new Sales(groceries.get(unsavedIndex.get(i)).getGroceryName(), unsavedAmount.get(i+1)));
        }
        saveSales(sales, "sales.txt");
    }
}
