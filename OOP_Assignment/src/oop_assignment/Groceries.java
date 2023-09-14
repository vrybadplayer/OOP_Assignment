package oop_assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Groceries extends Stock implements GroceriesManager{

    private String groceryName;
    private double price;

    public Groceries(String groceryName, double price) {
        this.groceryName = groceryName;
        this.price = price;
    }

    public Groceries() {

    }

    public String getGroceryName() {
        return groceryName;
    }

    public double getGroceryPrice() {
        return price;
    }
    
    public String toString() {
        return String.format("%s %.2f", groceryName, price);
    }

    public static void displayGroceries(ArrayList<Groceries> groceries) {
        System.out.println("\n[----------------+------------]");
        System.out.println("|      Item      |  Price(RM) | ");
        System.out.println("+----------------+------------+");
        for (int i = 0; i < groceries.size(); ++i) {
            System.out.printf("|%2d)  %-10s | %10.2f |\n", i + 1, groceries.get(i).getGroceryName(), groceries.get(i).getGroceryPrice());
        }
        System.out.println("[----------------+------------]\n");
    }

}
