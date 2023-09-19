package oop_assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Groceries extends Stock implements GroceriesManager{

    private String groceryName;
    private double price;

    public Groceries(String groceryName, double price, int stock) {
        this.groceryName = groceryName;
        this.price = price;
        setStock(stock);
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
        return String.format("%s %.2f %d", groceryName, price, getStock());
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

    public static void sortGroceries(){
         ArrayList<Groceries> groceriesList = GroceriesManager.loadGroceriesFile("groceries.txt");      

        // Sort the list based on the 'groceryName' field
        Collections.sort(groceriesList, new Comparator<Groceries>() {
            @Override
            public int compare(Groceries grocery1, Groceries grocery2) {
                return grocery1.getGroceryName().compareTo(grocery2.getGroceryName());
            }
        });
        GroceriesManager.saveGroceriesToFile(groceriesList, "groceries.txt");
    }
    
}
