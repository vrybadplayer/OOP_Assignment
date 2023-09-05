package oop_assignment;

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

    public Groceries() {

    }
    
    public String getGroceryName(int index) {
        return groceries.get(index);
    }
    
    public double getGroceryPrice(int index){
        return price.get(index);
    }

    public ArrayList<String> getGroceries() {
        return groceries;
    }

    public ArrayList<Double> getPrice() {
        return price;
    }

    
    
}
