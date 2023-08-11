package oop_assignment;

public class Order {

    private int choice;
    private int amount = 0;
    private double total = 0;
    private double subTotal = 0;
    static final double tax = 0.06;
    private Groceries groceries;

    public Order() {

    }

    public int getChoice() {
        return choice;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotal() {
        return total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
