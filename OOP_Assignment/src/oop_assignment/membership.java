package oop_assignment;

public class membership {
    double subtotal;
    
    public double discount(){
        return subtotal * 0.97;
    }
    
    public double redemption(){
        double totalPoint = subtotal /10;
        return totalPoint;
    }
}
