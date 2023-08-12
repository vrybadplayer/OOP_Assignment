package oop_assignment;

public class Groceries {

    private String groceryName;
    private String category;
    private double price = 0;
    private int stock;

    public Groceries(String groceryName, String category, double price, int stock) {
        this.groceryName = groceryName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        return String.format("%-15s %-13s %-6.2f %-7d", groceryName, category, price, stock);
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public String getCategory() {
        return category;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    //Delete?
}
