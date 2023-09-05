package oop_assignment;

import static oop_assignment.Driver.displayGroceries;
import java.util.*;
import java.util.ArrayList;

public class Order {

    private int choice = 0;
    private int amount = 0;
    private double total = 0;
    private double subTotal = 0;
    static final double tax = 0.06;
    private String decision;
    private Groceries groceries;
    private int arrayListIndex = 0;
    private ArrayList<Integer> groceryIndex = new ArrayList<Integer>();
    private ArrayList<Integer> groceryAmount = new ArrayList<Integer>();

    public Order() {

    }

    public void setGroceryIndex(ArrayList<Integer> groceryIndex) {
        this.groceryIndex = groceryIndex;
    }

    public void setGroceryAmount(ArrayList<Integer> groceryAmount) {
        this.groceryAmount = groceryAmount;
    }

    public void setGroceryIndex(ArrayList<Integer> groceryIndex, int indexNo) {
        this.groceryIndex.add(arrayListIndex, indexNo);
    }

    public void setGroceryAmount(ArrayList<Integer> groceryAmount, int amount) {
        this.groceryAmount.add(arrayListIndex, amount);
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

    public double calculateTax() {
        return total * tax;
    }

    public double calculateSubTotal() {
        return total + (total * tax);
    }

    public void displayCash() {
        System.out.printf("\nTotal is: RM %.2f\n", getTotal());
        System.out.printf("Tax is: RM %.2f\n", calculateTax());
        System.out.printf("Sub total is: RM %.2f\n", calculateSubTotal());
    }

    public void getOrder(Order order, Groceries groceries) {
        Scanner scanner = new Scanner(System.in);

        do {

            displayGroceries();
            setChoice(scanner.nextInt());

            System.out.print("How many would you like to buy: ");
            setAmount(scanner.nextInt());

            order.addTotal(groceries.getGroceryPrice(choice - 1), amount);
            storePurchases(groceryIndex, groceryAmount, (choice - 1), amount);
            do {

                System.out.print("Buy more? (y/n): ");
                decision = scanner.next();

                if (!decision.equals("y") && !decision.equals("n")) {
                    System.out.println("Enter only y or n!");
                }

            } while (!decision.equals("y") && !decision.equals("n"));

        } while (decision.equals("y"));

    }

    public void storePurchases(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount, int indexNo, int amount) {
        setGroceryIndex(groceryIndex, indexNo);
        setGroceryAmount(groceryAmount, amount);
        arrayListIndexIncrement();
    }

    public void testStore(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount) {
        for (int i = 0; i < groceryIndex.size(); i++) {
            System.out.print("\nIndex: " + groceryIndex.get(i) + "    ");
            System.out.print("\nAmount: " + groceryAmount.get(i) + "\n");
        }

    }
}
