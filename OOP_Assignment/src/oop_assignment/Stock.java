package oop_assignment;

import java.util.ArrayList;

public class Stock {

    private int stock;

    public Stock() {

    }

    public Stock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addStock(int stock) {
        this.stock = getStock() + stock;
    }

    public void minusStock(int stock) {
        this.stock = getStock() - stock;
    }

    public static ArrayList<Groceries> updateStock(ArrayList<Groceries> groceries) {
        ArrayList<Integer> index = Order.getGroceryIndex();
        ArrayList<Integer> amount = Order.getGroceryAmount();

        for (int i = 0; i < index.size(); ++i) {
            for (int j = 0; j < groceries.size(); ++j) {
                if (index.get(i).equals(j)) {
                    groceries.get(j).minusStock(amount.get(i));
                }
            }
        }
        return groceries;
    }

    public static boolean isEnoughStock(int index, int amount) {
        ArrayList<Groceries> grocery = GroceriesManager.loadGroceriesFile("groceries.txt");
        return grocery.get(index).getStock() >= amount;
    }
}
