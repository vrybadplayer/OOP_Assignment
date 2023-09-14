package oop_assignment;

import java.util.ArrayList;

public interface PrintReceipt {

    public void printMemberReceipt(Payment payment, ArrayList<Groceries> groceries, Order order, ArrayList<Customer> customer);
    public void printNonMemberReceipt(Payment payment, ArrayList<Groceries> groceries, Order order, ArrayList<Customer> customer);
    
}
