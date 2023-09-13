package oop_assignment;

import java.util.*;
import java.util.ArrayList;

public class Order {

    private int choice = 0;
    private int amount = 0;
    private double total = 0;
    static final double tax = 0.06;
    private int arrayListIndex = 0;
    private String decision = "";
    private Groceries groceries = new Groceries();
    private ArrayList<Integer> groceryIndex = new ArrayList<Integer>();
    private ArrayList<Integer> groceryAmount = new ArrayList<Integer>();
    private ArrayList<Double> subTotal = new ArrayList<Double>();

    public Order() {

    }

    public ArrayList<Double> getSubTotal() {
        return subTotal;
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

    public void setSubTotal(ArrayList<Double> subTotal, ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount, ArrayList<Double> price) {
        this.subTotal.set(arrayListIndex, price.get(groceryIndex.get(arrayListIndex)) * groceryAmount.get(arrayListIndex));
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

    public void addTotal(double price, int amount) {
        total += price * amount;
    }

    public void updateGroceryAmount(int index, int amount) {
        this.groceryAmount.set(index, groceryAmount.get(index) + amount);
    }

    public void updateSubTotal(int index, int amount) {
        this.subTotal.set(index, calculateSubTotal(index, groceryAmount.get(index)));
    }

    public void getOrder(Order order, Groceries groceries, ArrayList<Double> price) {

        boolean updated = false;

        do {
            Scanner scanner = new Scanner(System.in);
            groceries.displayGroceries();
            System.out.print("Which one do you want to buy?: ");
            setChoice(scanner.nextInt());
            System.out.print("How many would you like to buy: ");
            setAmount(scanner.nextInt());

            if (getAmount() > 0) {

                for (int i = 0; i < groceryIndex.size(); ++i) {
                    if (groceryIndex.get(i).equals(choice - 1)) {
                        updateGroceryAmount(i, getAmount());
                        updateSubTotal(i, getAmount());
                        addTotal(groceries.getGroceryPrice(getChoice() - 1), getAmount());
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    addTotal(groceries.getGroceryPrice(getChoice() - 1), getAmount());
                    storePurchases((getChoice() - 1), getAmount());
                }
            }

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
        subTotal.add(calculateSubTotal(indexNo, amount));
        arrayListIndexIncrement();
    }

    public double calculateSubTotal(int index, int amount) {
        ArrayList<Double> price = groceries.getPrice();
        return (price.get(index) * amount);
    }

    public void testStore(ArrayList<Integer> groceryIndex, ArrayList<Integer> groceryAmount) {
        for (int i = 0; i < groceryIndex.size(); i++) {
            System.out.print("\nIndex: " + groceryIndex.get(i) + "    ");
            System.out.print("\nAmount: " + groceryAmount.get(i) + "\n");
        }

    }
}
