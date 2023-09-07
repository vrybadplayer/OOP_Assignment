package oop_assignment;

import static oop_assignment.Driver.displayGroceries;
import java.util.*;
import java.util.ArrayList;

public class Order {

    private int choice = 0;
    private int amount = 0;
    private double total = 0;
    static final double tax = 0.06;
    private String decision = "";
    private Groceries groceries;
    private int arrayListIndex = 0;
    private ArrayList<Integer> groceryIndex = new ArrayList<Integer>();
    private ArrayList<Integer> groceryAmount = new ArrayList<Integer>();
    //Subtotal should be arraylist so the printing can use the same method as above
    private double subTotal = 0;

    public Order() {

    }

    public void setGroceryIndex(ArrayList<Integer> groceryIndex) {
        this.groceryIndex = groceryIndex;
    }

    public void setGroceryAmount(ArrayList<Integer> groceryAmount) {
        this.groceryAmount = groceryAmount;
    }

    public void setGroceryIndex(ArrayList<Integer> groceryIndex, int indexNo) {
        this.groceryIndex.set(arrayListIndex, indexNo);
    }

    public void setGroceryAmount(ArrayList<Integer> groceryAmount, int amount) {
        this.groceryAmount.set(arrayListIndex, amount);
    }

    public ArrayList<Integer> getGroceryIndex() {
        return groceryIndex;
    }

    public ArrayList<Integer> getGroceryAmount() {
        return groceryAmount;
    }

    public int getArrayListIndex() {
        return arrayListIndex;
    }

    public void arrayListIndexIncrement() {
        this.arrayListIndex++;
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

    public void addTotal(double price, int amount) {
        total += price * amount;
    }

    public void getOrder(Order order, Groceries groceries) {
        do {
            Scanner scanner = new Scanner(System.in);
            displayGroceries();
            setChoice(scanner.nextInt());
            System.out.print("How many would you like to buy: ");
            setAmount(scanner.nextInt());

            addTotal(groceries.getGroceryPrice(choice - 1), amount);
            storePurchases((choice - 1), amount);

            do {

                System.out.print("Buy more? (y/n): ");
                decision = scanner.next();

                if (!decision.equals("y") && !decision.equals("n")) {
                    System.out.println("Enter only y or n!");
                }

            } while (!decision.equals("y") && !decision.equals("n"));

        } while (decision.equals("y"));

    }

    public void storePurchases(int indexNo, int amount) {
        groceryIndex.add(indexNo);
        groceryAmount.add(amount);
        arrayListIndexIncrement();
    }

    public void testStore(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount) {
        for (int i = 0; i < groceryIndex.size(); i++) {
            System.out.print("\nIndex: " + groceryIndex.get(i) + "    ");
            System.out.print("\nAmount: " + groceryAmount.get(i) + "\n");
        }

    }
}
