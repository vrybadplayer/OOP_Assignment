package oop_assignment;

import java.util.ArrayList;
import java.util.Scanner;
import static oop_assignment.Driver.displayMainMenu;

public class Staff implements StaffManager {

    private String staffName;
    private String password;

    public Staff() {

    }

    public Staff(String staffName, String password) {
        this.staffName = staffName;
        this.password = password;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffPassword() {
        return password;
    }

    public void displayStaffMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("   _____   _              __    __ \n"
                + "  / ____| | |            / _|  / _| \n"
                + " | (___   | |_    __ _  | |_  | |_  \n"
                + "  \\___ \\  | __|  / _` | |  _| |  _| \n"
                + "  ____) | | |_  | (_| | | |   | |   \n"
                + " |_____/   \\__|  \\__,_| |_|   |_|   \n");

        System.out.println("[---+----------------------------]");
        System.out.printf("|%d) | %-26s |\n", 1, "Modify Customer Details");
        System.out.printf("|%d) | %-26s |\n", 2, "Add/Remove Groceries");
        System.out.printf("|%d) | %-26s |\n", 3, "Add Stock");
        System.out.printf("|%d) | %-26s |\n", 4, "Generate Report");
        System.out.printf("|%d) | %-26s |\n", 5, "Logout");
        System.out.println("[---+----------------------------]");
        while (true) {
            System.out.print("Input: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 5) {
                    break; // Valid input, exit the loop
                } else {
                    System.out.println("Invalid input. Please enter 1 to 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter 1 to 5.");
                scanner.next(); // Consume invalid input to avoid an infinite loop
            }
        }

        switch (choice) {
            case 1:
            //Modify
            case 2:
                //Groceries
                addOrRemove();
                break;
            case 3:
            //Add Stock
            case 4:
            //Generate Report
            case 5:
                //Logout
                System.out.println("\nLogged out!\n");
                Driver.displayOptions(CustomerManager.loadCustomersFromFile("membership.txt"));
                break;
            default:
                System.out.println("Error!");
                break;
        }

    }

    public void addOrRemove() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("\nGroceries Manager");
        System.out.println("[---+---------------------]");
        System.out.printf("|1) | %-20s|\n", "Add Groceries");
        System.out.printf("|2) | %-20s|\n", "Remove Groceries");
        System.out.printf("|3) | %-20s|\n", "Back");
        System.out.println("[---+---------------------]");
        while (true) {
            System.out.print("Input: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break; // Valid input, exit the loop
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next(); // Consume invalid input to avoid an infinite loop
            }
        }

        switch (choice) {
            case 1:
                addGroceries();
                break;
            case 2:
                removeGroceries();
                break;
            case 3:
                displayStaffMenu();
                break;
            default:
                System.out.println("Error!");
                System.exit(0);
                break;
        }
    }

    public void addGroceries() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Groceries> groceries = GroceriesManager.loadGroceriesFile("groceries.txt");
        String groceryName;
        double price;
        boolean repeat = false;
        String decision;

        do {
            System.out.println("\n\nAdding new groceries");
            System.out.println("--------------------");
            System.out.print("Enter grocery name (Enter X to exit): ");
            groceryName = scanner.nextLine();

            if (groceryName.equals("X")) {
                addOrRemove();
                break;
            }

            while (true) {
                System.out.print("Enter price: ");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price > 0) {
                        break; // Valid input, exit the loop
                    } else if (price == 0) {
                        System.out.println("Price cannot be 0!");
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else {
                    System.out.println("Invalid input!");
                    scanner.next(); // Consume invalid input to avoid an infinite loop
                }
            }
            Groceries grocery = new Groceries(groceryName, price);
            groceries.add(grocery);

            System.out.println("Grocery successfully added.");

            while (true) {
                System.out.print("Do you want to add more (y/n): ");
                if (scanner.hasNext()) {
                    decision = scanner.next();
                    if (decision.equals("y")) {
                        repeat = true;
                        break;
                    } else if (decision.equals("n")) {
                        repeat = false;
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else {
                    System.out.println("Invalid input!");
                    scanner.next(); // Consume invalid input to avoid an infinite loop
                }
            }

        } while (repeat);
        GroceriesManager.saveGroceriesToFile(groceries, "groceries.txt");
        displayStaffMenu();
    }

    public void removeGroceries() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String decision;
        boolean repeat = false;

        do {
            ArrayList<Groceries> groceries = GroceriesManager.loadGroceriesFile("groceries.txt");
            System.out.println("\n\nRemoving Groceries");
            System.out.println("------------------");
            groceries.get(0).displayGroceries(groceries);
            System.out.println("Which grocery do you want to remove?");

            while (true) {
                System.out.print("Input number (Enter X to exit): ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();

                    if (choice >= 1 && choice <= groceries.size()) {
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else {
                    System.out.println("Invalid input!");
                    scanner.next(); // Consume invalid input to avoid an infinite loop
                }
            }

            groceries.remove(choice - 1);
            GroceriesManager.saveGroceriesToFile(groceries, "groceries.txt");

            System.out.println("Groceries successfully removed.");
            while (true) {
                System.out.print("Do you want to remove more (y/n): ");
                if (scanner.hasNext()) {
                    decision = scanner.next();
                    if (decision.equals("y")) {
                        repeat = true;
                        break;
                    } else if (decision.equals("n")) {
                        repeat = false;
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else if (scanner.hasNext()) {
                    decision = scanner.next();
                    if (decision.equals("X")) {
                        repeat = false;
                        break;
                    } else {
                        System.out.println("Invalid input!");
                        scanner.next(); // Consume invalid input to avoid an infinite loop
                    }
                }
            }
            
        } while (repeat);
        displayStaffMenu();
    }

    public void addStock() throws InterruptedException {

        displayStaffMenu();
    }

}
