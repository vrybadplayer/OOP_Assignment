package oop_assignment;

import com.google.zxing.WriterException;
import java.util.*;

public class Driver {

    private static boolean proceed = false;
    private static boolean member = false;
    private static double balance = 0;
    private static int customerIndex = 0;
    private static int choice = 0;

    public static void setMember(boolean member) {
        Driver.member = member;
    }

    public static void main(String[] args) throws WriterException, Exception {

        ArrayList<Customer> customer = CustomerManager.loadCustomersFromFile("membership.txt");
        Groceries groceries;
        Payment payment;
        Order order;
        Receipt receipt;
        QRCode qr;

        customer.get(0).setBalance(100);
        customer.get(1).setBalance(100);
        customer.get(2).setBalance(100);

        do {
            //Create objects
            groceries = new Groceries();
            payment = new Payment();
            order = new Order();
            receipt = new Receipt();
            qr = new QRCode();

            displayOptions(customer);
            do {
                //Display Menus
                choice = displayMainMenu(customer);
                boolean receiptDecision = false;

                switch (choice) {
                    case 1:
                        //User Inputs
                        payment.getOrder(order, groceries, groceries.getPrice());

                        //Display Output
                        payment.displayOrders(groceries, order);
                        payment.displayCash();

                        proceed = payment.proceedPayment();
                        
                        if (!member && proceed == true) {
                            qr.setText("https://www.paypal.com/signin");
                            qr.generateQR();
                            qr.displayQR();
                            receiptDecision = receipt.receiptDecision();
                            if (!payment.isInsufficientBalance()) {
                                if (receiptDecision) {
                                    receipt.printMemberReceipt(payment, groceries, order);
                                    systemPause();
                                }
                            }
                        } else {
                            customer.get(customerIndex).setBalance(payment.pay(customer.get(customerIndex).getBalance()));
                            receiptDecision = receipt.receiptDecision();
                            if (!payment.isInsufficientBalance()) {
                                if (receiptDecision) {
                                    receipt.printMemberReceipt(payment, groceries, order);
                                    systemPause();
                                }
                            }
                        }
                        break;
                    case 2:
                        //TO DO
                        System.out.println("Redemption");
                        break;
                    case 3:
                        //TO DO
                        System.out.println("Topup");
                        break;
                    default:
                        //ERROR
                        continue;
                }
            } while (!proceed);
        } while (choice != 4);
        System.out.println("\nThank you for using Trapstar Groceries!\n\n");
        systemPause();

    }

    public static void systemPause() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to continue...\n\n");
        scanner.nextLine();
    }

    public static void displayOptions(ArrayList<Customer> customer) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("""
                             _______ _____            _____   _____ _______       _____     _____ _____   ____   _____ ______ _____  _____ ______  _____ 
                            |__   __|  __ \\     /\\   |  __ \\ / ____|__   __|/\\   |  __ \\   / ____|  __ \\ / __ \\ / ____|  ____|  __ \\|_   _|  ____|/ ____|
                               | |  | |__) |   /  \\  | |__) | (___    | |  /  \\  | |__) | | |  __| |__) | |  | | |    | |__  | |__) | | | | |__  | (___  
                               | |  |  _  /   / /\\ \\ |  ___/ \\___ \\   | | / /\\ \\ |  _  /  | | |_ |  _  /| |  | | |    |  __| |  _  /  | | |  __|  \\___ \\ 
                               | |  | | \\ \\  / ____ \\| |     ____) |  | |/ ____ \\| | \\ \\  | |__| | | \\ \\| |__| | |____| |____| | \\ \\ _| |_| |____ ____) |
                               |_|  |_|  \\_\\/_/    \\_\\_|    |_____/   |_/_/    \\_\\_|  \\_\\  \\_____|_|  \\_\\\\____/ \\_____|______|_|  \\_\\_____|______|_____/ 
                           """);
        do {
            System.out.println("[--------------]");
            System.out.println("|1. Staff      |");
            System.out.println("|2. Customer   |");
            System.out.println("|3. Quit       |");
            System.out.println("[--------------]");
            System.out.print("Input 1, 2 or 3 only: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    //Implement Staff
                    System.out.println("Implement Staff");
                    System.exit(0);
                }
                case 2 ->
                    displayMainPage(customer);
                case 3 -> {
                    CustomerManager.saveCustomersToFile(customer, "membership.txt");
                    System.out.println("\nThank you for using Trapstar Groceries!\n\n");
                    System.exit(0);
                }
                default ->
                    System.out.println("Please enter 1, 2 or 3 only !!!");
            }
        } while (choice < 1 || choice > 3);
    }

    public static void displayMainPage(ArrayList<Customer> customer) {
        SignUp signUp = new ConcreteSignUp();
        LogIn logIn = new LogIn();
        int selection_member;
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                             __  __ ______ __  __ ____  ______ _____    _______ _____            _____   _____ _______       _____  
                            |  \\/  |  ____|  \\/  |  _ \\|  ____|  __ \\  |__   __|  __ \\     /\\   |  __ \\ / ____|__   __|/\\   |  __ \\ 
                            | \\  / | |__  | \\  / | |_) | |__  | |__) |    | |  | |__) |   /  \\  | |__) | (___    | |  /  \\  | |__) |
                            | |\\/| |  __| | |\\/| |  _ <|  __| |  _  /     | |  |  _  /   / /\\ \\ |  ___/ \\___ \\   | | / /\\ \\ |  _  / 
                            | |  | | |____| |  | | |_) | |____| | \\ \\     | |  | | \\ \\  / ____ \\| |     ____) |  | |/ ____ \\| | \\ \\ 
                            |_|  |_|______|_|  |_|____/|____&__|_|  \\_\\    |_|  |_|  \\_\\/_/    \\_\\_|    |_____/   |_/_/    \\_\\_|  \\_\\
                           """);
        do {
            System.out.println("[-------------------]");
            System.out.println("|1. Login in Member |");
            System.out.println("|2. Sign up Member  |");
            System.out.println("|3. Non-member      |");
            System.out.println("|4. Go Back         |");
            System.out.println("[-------------------]");
            System.out.print("Input: ");
            selection_member = scanner.nextInt();

            switch (selection_member) {
                case 1:
                    customerIndex = logIn.membership(customer);
                    break;
                case 2:
                    signUp.Signup();
                    break;
                case 3:
                    member = false;
                    break;
                case 4:
                    displayOptions(customer);
                    break;
                default:
                    System.out.println("Please enter 1, 2, 3 or 4 only !!!");
            }
        } while (selection_member < 1 || selection_member > 4);
    }

    public static int displayMainMenu(ArrayList<Customer> customer) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("[-------------------]");
            System.out.println("|1. Groceries List  |");
            System.out.println("|2. Redemption      |");
            System.out.println("|3. TopUp           |");
            System.out.println("|4. Logout          |");
            System.out.println("[-------------------]");
            System.out.print("Input: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return choice;
                case 2:
                    // Implement functionality for Redemption
                    return choice;
                case 3:
                    //Topup
                    return choice;
                case 4:
                    displayOptions(customer);
                    break;
                default:
                    System.out.println("Please enter 1, 2, 3 or 4 only !!!");
            }
        } while (choice < 1 || choice > 4);
        return choice;
    }
}
