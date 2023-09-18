package oop_assignment;

import com.sun.source.tree.ContinueTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public void displayStaffMenu() throws InterruptedException, Exception {
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
                editCustomer();
                break;
            case 2:
                //Groceries
                addOrRemove();
                break;
            case 3:
            //Add Stock
            case 4:
                //Generate Report
                generateReport();
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

    public void addOrRemove() throws InterruptedException, Exception {
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

    public void addGroceries() throws InterruptedException, Exception {
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

    public void removeGroceries() throws InterruptedException, Exception {
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

    public void editCustomer() throws InterruptedException, Exception {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> customer = CustomerManager.loadCustomersFromFile("membership.txt");
        int selection = 0;
        boolean validChoice = true;
        String customerName = "";

        System.out.println("\nCustomer Editing");
        System.out.println("[---+----------------------]");
        System.out.printf("|1) | %-20s |\n", "Username");
        System.out.printf("|2) | %-20s |\n", "Password");
        System.out.printf("|3) | %-20s |\n", "E-mail");
        System.out.printf("|4) | %-20s |\n", "Phone Number");
        System.out.printf("|5) | %-20s |\n", "Mailing Address");
        System.out.printf("|6) | %-20s |\n", "Loyalty Points");
        System.out.printf("|7) | %-20s |\n", "Balance");
        System.out.printf("|8) | %-20s |\n", "Exit");
        System.out.println("[---+----------------------]");

        while (validChoice) {
            System.out.print("Input: ");
            if (input.hasNextInt()) {
                selection = input.nextInt();
                input.nextLine();
                if (selection >= 1 && selection <= 8) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 1 to 8.");
                }
            } else {
                System.out.println("Invalid input. Please enter 1 to 8.");
                input.next(); // Consume invalid input to avoid an infinite loop
            }
        }

        if (!customerName.equals("X")) {
            switch (selection) {
                case 1:
                    boolean validName = true;
                    while (validName) {
                        customerName = askAgain();
                        if (customerName.equals("X")) {
                            break;
                        }
                        System.out.print("Enter new customer name (Enter X to exit): ");
                        String newName = input.nextLine();

                        if (newName.equals("X")) {
                            break;
                        }

                        do {
                            if (isExistingName(newName) == true) {
                                System.out.println("This name is already registered. Please enter a different name.");
                                System.out.print("Please enter a different name:");
                                newName = input.nextLine();
                            } else if (newName == null || newName.isEmpty()) {
                                System.out.print("Username cannot be blank: ");
                                newName = input.nextLine();
                            }
                        } while (isExistingName(newName) || newName == null);

                        for (int i = 0; i < customer.size(); ++i) {
                            if (customerName.equals(customer.get(i).getName())) {
                                customer.get(i).setName(newName);
                                System.out.println("Name successfully edited.");
                                validName = false;
                                break;
                            }
                        }
                        if (validName && !newName.equals("X")) {
                            System.out.println("The customer you wanted to edit could not be found.\n");
                        }
                    }
                    break;
                case 2:
                    boolean validPassword = true;
                    String newPass = "";
                    do {
                        customerName = askAgain();
                        if (customerName.equals("X")) {
                            break;
                        }
                        System.out.print("Enter new password (Enter X to exit): ");
                        newPass = input.nextLine();
                        if (newPass.isEmpty() || newPass == null) {
                            System.out.println("Password cannot be empty!");
                        } else if (newPass.equals("X")) {
                            break;
                        } else {
                            break;
                        }
                    } while (true);

                    if (!newPass.equals("X")) {
                        for (int i = 0; i < customer.size(); ++i) {
                            if (customerName.equals(customer.get(i).getName())) {
                                customer.get(i).setPassword(newPass);
                                System.out.println("Password successfully edited.");
                                validPassword = false;
                                break;
                            }
                        }
                    }
                    if (validPassword && !newPass.equals("X")) {
                        System.out.println("The customer you wanted to edit could not be found.\n");
                    }
                    break;
                case 3:
                    boolean validEmail = true;
                    String newEmail = "";
                    while (validEmail) {
                        customerName = askAgain();
                        if (customerName.equals("X")) {
                            break;
                        }

                        newEmail = enterNewEmail();
                        if (newEmail.equals("X")) {
                            break;
                        }

                        if (isValidEmail(newEmail)) {
                            if (isExistingEmail(newEmail)) {
                                System.out.println("This email already exists. Please enter another email.");
                            } else {
                                for (int i = 0; i < customer.size(); ++i) {
                                    if (customerName.equals(customer.get(i).getName())) {
                                        customer.get(i).setEmail(newEmail);
                                        validEmail = false;
                                        break;
                                    }
                                }
                                System.out.println("Email updated successfully!");
                            }
                        } else {
                            System.out.println("Please use the correct email format (e.g., XXX@gmail.com)");
                        }
                    }

                    if (validEmail && !newEmail.equals("X")) {
                        System.out.println("The customer you wanted to edit could not be found.\n");
                    }
                    break;
                case 4:
                    boolean validPhone = true;
                    String newPhone = "";
                    do {
                        customerName = askAgain();
                        if (customerName.equals("X")) {
                            break;
                        }

                        do {
                            System.out.print("Enter new phone number (Enter X to exit): ");
                            newPhone = input.nextLine();

                            if (newPhone.equals("X")) {
                                validPhone = false;
                                break;
                            }

                            if (isValidPhoneNumber(newPhone)) {
                                if (isExistingPhoneNumber(newPhone)) {
                                    System.out.println("This Phone Number already exists. Please Try Another Phone Number.");
                                } else {
                                    validPhone = false;
                                    break;
                                }
                            } else {
                                System.out.println("Please enter a right format with XXX-XXXXXXX");
                            }
                        } while (true);

                    } while (validPhone);

                    if (!newPhone.equals("X")) {
                        for (int i = 0; i < customer.size(); ++i) {
                            if (customerName.equals(customer.get(i).getName())) {
                                customer.get(i).setPhoneNumber(newPhone);
                                System.out.println("Phone number successfully edited.");
                                validPhone = false;
                                break;
                            }
                        }
                    }
                    if (validPhone && !newPhone.equals("X")) {
                        System.out.println("The customer you wanted to edit could not be found.\n");
                    }
                    break;
                case 5:
                    boolean validAddress = true;
                    String newAddress = "";
                    do {
                        do {
                            customerName = askAgain();
                            if (customerName.equals("X")) {
                                validAddress = false;
                                break;
                            }
                            System.out.print("Enter new mailing address (Enter X to exit): ");
                            newAddress = input.nextLine();
                            if (newAddress == null || newAddress.isEmpty()) {
                                System.out.println("Mailing address cannot be blank!");
                            } else {
                                validAddress = false;
                                break;
                            }
                        } while (true);

                        if (newAddress.equals("X")) {
                            break;
                        }

                        for (int i = 0; i < customer.size(); ++i) {
                            if (customerName.equals(customer.get(i).getName())) {
                                customer.get(i).setMailingAddress(newAddress);
                                System.out.println("Mailing address successfully edited.");
                                validAddress = false;
                                break;
                            }
                        }

                        if (!validAddress && !newAddress.equals("X")) {
                            System.out.println("The customer you wanted to edit could not be found.\n");
                        }
                    } while (validAddress);
                    break;
                case 6:
                    boolean validPoints = true;
                    int newPoints = 0;
                    String pointsInput = "";

                    while (true) {
                        customerName = askAgain();
                        if (customerName.equals("X")) {
                            break;
                        }
                        System.out.print("Enter new points (Enter X to exit): ");
                        if (input.hasNext()) {
                            pointsInput = input.next();
                            if (pointsInput.equals("X")) {
                                break;
                            } else if (pointsInput.matches("\\d+")) {
                                // User entered a valid number as a string
                                newPoints = Integer.parseInt(pointsInput);
                                if (newPoints >= 0) {
                                    break;
                                } else {
                                    System.out.println("Invalid input. Enter a positive number!");
                                }
                            } else {
                                System.out.println("Invalid input!");
                            }
                        }
                    }
                    if (!pointsInput.equals("X")) {
                        for (int i = 0; i < customer.size(); ++i) {
                            if (customerName.equals(customer.get(i).getName())) {
                                customer.get(i).setLoyaltyPoints(newPoints);
                                System.out.println("Loyalty points successfully edited.");
                                validPoints = false;
                                break;
                            }
                        }
                    }
                    if (validPoints && !pointsInput.equals("X")) {
                        System.out.println("The customer you wanted to edit could not be found.\n");
                    }
                    break;
                case 7:
                    boolean validBalance = true;
                    double newBalance = 0;
                    String balanceInput = "";
                    while (true) {
                        customerName = askAgain();
                        if (customerName.equals("X")) {
                            break;
                        }
                        System.out.print("Enter new balance (Enter X to exit): ");
                        if (input.hasNext()) {
                            balanceInput = input.next();

                            if (balanceInput.equals("X")) {
                                break;

                            } else if (balanceInput.matches("-?\\d+(\\.\\d+)?") || balanceInput.matches("\\d+")) {
                                // User entered a valid number as a string
                                newBalance = Double.parseDouble(balanceInput);
                                if (newBalance >= 0) {
                                    break;
                                } else {
                                    System.out.println("Invalid input. Enter a positive number!");
                                }
                            } else {
                                System.out.println("Invalid input!");
                            }
                        }
                    }

                    if (!balanceInput.equals("X")) {
                        for (int i = 0; i < customer.size(); ++i) {
                            if (customerName.equals(customer.get(i).getName())) {
                                customer.get(i).setBalance(newBalance);
                                System.out.println("Balance successfully edited.");
                                validBalance = false;
                                break;
                            }
                        }
                    }

                    if (validBalance && !balanceInput.equals("X")) {
                        System.out.println("The customer you wanted to edit could not be found.\n");
                    }
                    break;
                case 8:
                    displayStaffMenu();
                    break;
                default:
                    break;
            }
        }
        CustomerManager.saveCustomersToFile(customer, "membership.txt");
        displayStaffMenu();
    }

    public String askAgain() {
        String customerName = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the customer you want to edit (Enter X to exit): ");
        customerName = input.nextLine();
        return customerName;
    }

    public void generateReport() throws InterruptedException, Exception {
        ArrayList<Sales> salesList = SalesManager.loadSales("sales.txt");
        Report piechart = new Report(salesList, "Piechart for Number of Items Sold", "Number of Items Sold");
        boolean loopReport = true;
        piechart.statistics();

        Scanner input = new Scanner(System.in);
        while (loopReport) {
            System.out.print("\nEnter 1 to see piechart for the number of all items sold, 2 to exit the report page \n> ");
            int userIn = input.nextInt();

            if (userIn == 1) {
                piechart.pack();
                piechart.setVisible(true);
            } else if (userIn == 2) {
                loopReport = false;
                break;
            } else if (userIn != 2) {
                System.out.println("Invalid input, please try again");
            }
        }
        displayStaffMenu();
    }

    private static boolean isExistingName(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("membership.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String existingName = tokens[0];
                if (existingName.equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Error!");
        }
        return false;
    }

    private static boolean isExistingEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("membership.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String existingEmail = tokens[2];
                if (existingEmail.equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Error!");
        }
        return false;
    }

    private static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@gmail.com");
    }

    private static String enterNewEmail() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter new email address (Enter X to exit): ");
            String newEmail = input.nextLine();
            if (newEmail.equals("X")) {
                return newEmail;
            }
            if (isValidEmail(newEmail)) {
                return newEmail;
            } else {
                System.out.println("Please use the correct email format (e.g., XXX@gmail.com)");
            }
        }
    }

    private static boolean isExistingPhoneNumber(String phoneNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("membership.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String existingphoneNumber = tokens[3];
                if (existingphoneNumber.equals(phoneNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Error!");
        }
        return false;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{3}-\\d{7}");
    }

    public void addStock() throws InterruptedException {
        System.out.println("To be implemented.");
        System.exit(0);
        //displayStaffMenu();
    }

}
