package oop_assignment;

import java.util.*;
import java.util.ArrayList;

public class Order {

    private int choice = 0;
    private int amount = 0;
    private static double total = 0;
    static final double tax = 0.06;
    private int arrayListIndex = 0;
    private String decision = "";
    private static ArrayList<Integer> groceryIndex = new ArrayList<Integer>();
    private static ArrayList<Integer> groceryAmount = new ArrayList<Integer>();
    private ArrayList<Double> subTotal = new ArrayList<Double>();

    public Order() {
        this.total = 0;
        this.groceryIndex = new ArrayList<Integer>();
        this.groceryAmount = new ArrayList<Integer>();
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

    public static ArrayList<Integer> getGroceryIndex() {
        return groceryIndex;
    }

    public static ArrayList<Integer> getGroceryAmount() {
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

    public static double getTotal() {
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

    //Linking
    public void updateSubTotal(int index, int amount) {
        this.subTotal.set(index, calculateSubTotal(index, groceryAmount.get(index)));
    }

    public double calculateSubTotal(int index, int amount) {
        ArrayList<Groceries> groceries = GroceriesManager.loadGroceriesFile("groceries.txt");
        return (groceries.get(index).getGroceryPrice() * amount);
    }

    public void getOrder(Order order, ArrayList<Groceries> groceries) {
        ArrayList<Sales> sales = SalesManager.loadSales("sales.txt");
        boolean updated = false;
        boolean isNumber = false;

        do {
            Scanner scanner = new Scanner(System.in);
            Groceries.displayGroceries(groceries);

            while (true) {
                System.out.print("Which one do you want to buy?: ");
                if (scanner.hasNextInt()) {
                    setChoice(scanner.nextInt());
                    if (getChoice() <= groceries.size() && getChoice() > 0) {
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else {
                    System.out.println("Invalid input!");
                    scanner.next(); // Consume invalid input to avoid an infinite loop
                }
            }

            while (true) {
                System.out.print("How many would you like to buy: ");
                if (scanner.hasNextInt()) {
                    setAmount(scanner.nextInt());
                    if (getAmount() >= 0 && !Stock.isEnoughStock(getChoice() - 1, getAmount())) {
                        System.out.println("Sorry! This item is out of stock!");
                    } else if (getAmount() >= 0) {
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else {
                    System.out.println("Invalid input!");
                    scanner.next(); // Consume invalid input to avoid an infinite loop
                }
            }

            if (getAmount() > 0) {

                for (int i = 0; i < groceryIndex.size(); ++i) {
                    if (groceryIndex.get(i).equals(choice - 1)) {
                        updateGroceryAmount(i, getAmount());
                        updateSubTotal(i, getAmount());
                        addTotal(groceries.get(getChoice() - 1).getGroceryPrice(), getAmount());
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    addTotal(groceries.get(getChoice() - 1).getGroceryPrice(), getAmount());
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

}
