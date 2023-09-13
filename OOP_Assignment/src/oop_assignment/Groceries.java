package oop_assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Groceries extends Stock {

    private ArrayList<String> groceries = new ArrayList<>() {
        {
            add("Milk");
            add("Bread");
            add("Eggs");
            add("Cheese");
            add("Water");
            add("Rice");
            add("Chicken");
            add("Beef");
            add("Apples");
            add("Bananas");
        }
    };

    private ArrayList<Double> price = new ArrayList<>() {
        {
            add(7.00);
            add(3.50);
            add(6.60);
            add(10.00);
            add(2.40);
            add(25.00);
            add(9.00);
            add(16.50);
            add(4.70);
            add(2.45);
        }
    };

    private String groceryName;
    private double tempPrice;

    public Groceries(String groceryName, double price) {
        this.groceryName = groceryName;
        this.tempPrice = price;
    }

    public Groceries() {

    }

    public String getGroceryName(int index) {
        return groceries.get(index);
    }

    public double getGroceryPrice(int index) {
        return price.get(index);
    }

    public ArrayList<String> getGroceries() {
        return groceries;
    }

    public ArrayList<Double> getPrice() {
        return price;
    }

    public void addGroceries(String name) {
        groceries.add(name);
    }

    public void addPrice(double value) {
        price.add(value);
    }

    public void displayGroceries() {
        System.out.println("\n[----------------+------------]");
        System.out.println("|      Item      |  Price(RM) | ");
        System.out.println("+----------------+------------+");
        for (int i = 0; i < groceries.size(); ++i) {
            System.out.printf("|%2d)  %-10s | %10.2f |\n", i + 1, groceries.get(i), price.get(i));
        }
        System.out.println("[----------------+------------]\n");
    }

    private static void saveToFile(String name, String passWord, String email, String phoneNumber, String mail, int loyaltyPoints) {
        try {
            FileWriter fileWriter = new FileWriter("groceries.txt", true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(name + "|" + passWord + "|" + email + "|" + phoneNumber + "|" + mail + "|" + loyaltyPoints + "|" + 0.0 + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readGroceries() {

    }

    public void readPrice() {

    }

}
