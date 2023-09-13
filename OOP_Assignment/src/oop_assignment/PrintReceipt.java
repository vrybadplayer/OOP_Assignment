package oop_assignment;

public interface PrintReceipt {

    public void printMemberReceipt(Payment payment, Groceries groceries, Order order);
    public void printNonMemberReceipt(Payment payment, Groceries groceries, Order order);
    
}
