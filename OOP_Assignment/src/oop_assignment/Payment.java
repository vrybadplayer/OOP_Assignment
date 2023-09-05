package oop_assignment;

import java.util.ArrayList;

public class Payment extends Order {

    public void displayDetails() {
        System.out.printf("%-15s %-9s", "Items", "Amount");
        orderListing(getGroceryIndex(), getGroceryAmount());
    }

    public void orderListing(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount) {

        for (int i = 0; i < groceryIndex.size(); ++i) {
            //Items, Amount, Price, Total
            System.out.printf("%-15s %-9d", groceries.get(groceryIndex.get(i)), amount.get(groceryAmount.get(i));
        }
    }

}
