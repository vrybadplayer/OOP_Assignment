package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface CustomerManager {

    public static ArrayList<Customer> loadCustomersFromFile(String fileName) {
        String name;
        String password;
        String email;
        String phoneNumber;
        String mailingAddress;
        int loyaltyPoints;
        
        double balance;
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length >= 7) {
                    name = tokens[0];
                    password = tokens[1];
                    email = tokens[2];
                    phoneNumber = tokens[3];
                    mailingAddress = tokens[4];
                    loyaltyPoints = Integer.parseInt(tokens[5]);
                    balance = Double.parseDouble(tokens[6]);

                    Customer customer = new Customer(name, password, email, phoneNumber, mailingAddress, loyaltyPoints, balance);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return customers;
    }

    public static void saveCustomersToFile(ArrayList<Customer> customers, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Customer customer : customers) {
                String line = String.format(
                        "%s|%s|%s|%s|%s|%d|%.2f%n",
                        customer.getName(),
                        customer.getPassword(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        customer.getMailingAddress(),
                        customer.getLoyaltyPoints(),
                        customer.getBalance()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    } 
}
