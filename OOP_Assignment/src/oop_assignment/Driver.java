package oop_assignment;
import com.google.zxing.WriterException;
import static com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.annotation.Repeatable;
import java.util.*;
import java.util.Scanner;
//GitHub Pull Test 2
public class Driver {
    //GitHub Push Test 2
    private static boolean proceed = false;
    private static boolean member = false;
    private static double balance = 0;
    private static int customerIndex = 0;
    private static int choice = 0;
    private static boolean repeat = true;

    public static int getCustomerIndex() {
        return customerIndex;
    }

    public static void setMember(boolean member) {
        Driver.member = member;
    }

    public static boolean isMember() {
        return member;
    }

    public static void main(String[] args) throws WriterException, Exception {
        ArrayList<Customer> customer = CustomerManager.loadCustomersFromFile("membership.txt");
        ArrayList<Groceries> groceries;
        ArrayList<Sales> sales;
        Scanner scanner = new Scanner(System.in);
        Payment payment;
        Order order;
        Receipt receipt = new Receipt();
        QRCode qr = new QRCode();

        //Testing
        do {

            //temp
            customer.get(0).setBalance(500);

            displayOptions(customer);

            do {
                //Display Menus
                choice = displayMainMenu(customer);
                boolean receiptDecision = false;
                customer = CustomerManager.loadCustomersFromFile("membership.txt");
                groceries = GroceriesManager.loadGroceriesFile("groceries.txt");
                sales = SalesManager.loadSales("sales.txt");

                switch (choice) {
                    case 1:
                        //User Inputs
                        payment = new Payment();
                        order = new Order();
                        payment.getOrder(order, groceries);

                        //Display Output
                        payment.displayOrders(groceries, order);
                        payment.displayCash();

                        proceed = payment.proceedPayment();

                        if (!member && proceed) {
                            qr.setText("TrapStar Groceries Payment Form: https://forms.gle/gMsH1YaZiGL8PyX69");
                            qr.generateQR();
                            qr.displayQR();
                            SalesManager.updateSales(sales);
                            receiptDecision = receipt.receiptDecision();
                            if (!payment.isInsufficientBalance()) {
                                if (receiptDecision) {
                                    receipt.printNonMemberReceipt(payment, groceries, order, customer);
                                    systemPause();
                                }
                            }
                        } else if (member && proceed) {
                            customer.get(customerIndex).setBalance(payment.pay(customer.get(customerIndex).getBalance()));
                            CustomerManager.saveCustomersToFile(customer, "membership.txt");
                            if (Payment.isInsufficientBalance()) {
                                while (true) {
                                    System.out.print("Do you want to top-up (y/n): ");
                                    if (scanner.hasNext()) {
                                        String topUpDecision = scanner.next();
                                        if (topUpDecision.equals("y")) {
                                            customer.get(customerIndex).topUp(customer);
                                            displayMainMenu(customer);
                                            break;
                                        } else if (topUpDecision.equals("n")) {
                                            break;
                                        } else {
                                            System.out.println("Invalid input!");
                                        }
                                    } else {
                                        System.out.println("Invalid input!");
                                        scanner.next(); // Consume invalid input to avoid an infinite loop
                                    }
                                }
                            } else {
                                SalesManager.updateSales(sales);
                                receiptDecision = receipt.receiptDecision();
                                if (receiptDecision) {
                                    receipt.printMemberReceipt(payment, groceries, order, customer);
                                    systemPause();
                                }
                            }
                        } else {
                            continue;
                        }
                        break;

                    case 2:
                        //Redemption
                        if (member) {
                            //Redemption page (JingHong do)
                        } else if (!member) {
                            System.out.println("\nNon-members cannot redeem anything!\n");
                            systemPause();
                            continue;
                        }
                        break;
                    case 3:
                        //Top-up
                        if (member) {
                            customer = CustomerManager.loadCustomersFromFile("membership.txt");
                            customer.get(customerIndex).topUp(customer);
                            CustomerManager.saveCustomersToFile(customer, "membership.txt");
                        } else if (!member) {
                            System.out.println("\nNon-members cannot top-up!\n");
                            systemPause();
                            continue;
                        }
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
        System.out.println("\nPress Enter to continue...\n\n");
        scanner.nextLine();
    }

    public static void displayOptions(ArrayList<Customer> customer) throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
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
            while (true) {
                System.out.print("Input 1, 2, or 3 only: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                } else {
                    System.out.println("Invalid input!");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1 -> {
                    //Implement Staff       
                    if (StaffManager.staffLogin()) {
                        Staff staff = new Staff();
                        staff.displayStaffMenu();
                    } else {
                        String[] args = {""};
                        main(args);
                    }
                }
                case 2 -> {
                    displayMainPage(customer);
                }
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

    public static void displayMainPage(ArrayList<Customer> customer) throws InterruptedException, Exception {
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
            while (true) {
                System.out.print("Input: ");
                if (scanner.hasNextInt()) {
                    selection_member = scanner.nextInt();
                    if (selection_member >= 1 && selection_member <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid input! Enter only 1, 2, 3 or 4!");
                    }
                } else {
                    System.out.println("Invalid input! Enter only 1, 2, 3 or 4!");
                    scanner.next();
                }
            }

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

    public static int displayMainMenu(ArrayList<Customer> customer) throws InterruptedException, Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("[-------------------]");
            System.out.println("|1. Groceries List  |");
            System.out.println("|2. Redemption      |");
            System.out.println("|3. TopUp           |");
            System.out.println("|4. Logout          |");
            System.out.println("[-------------------]");
            while (true) {
                System.out.print("Input: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid input! Enter only 1, 2, 3 or 4!");
                    }
                } else {
                    System.out.println("Invalid input! Enter only 1, 2, 3 or 4!");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    return choice;
                case 2:
                    return choice;
                case 3:
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
