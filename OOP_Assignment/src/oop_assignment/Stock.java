package oop_assignment;

public class Stock {

    private int stock;

    public Stock(){
        this(1000);
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
}
