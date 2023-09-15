package oop_assignment;

public class Sales {
    private String groceryName;
    private int salesAmount;
    
    public Sales() {
        
    }
    
    public Sales(String groceryName, int salesAmount) {
        this.groceryName = groceryName;
        this.salesAmount = salesAmount;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public int getSalesAmount() {
        return salesAmount;
    }
    
    
    
    
}
