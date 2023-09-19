/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package oop_assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface RedemptionManager {
    
     public static ArrayList<Redemption> loadRedemptions(String fileName) {
        String itemName;
        int pointsAmount;
        int stock;
        ArrayList<Redemption> redemptionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length >= 3) {
                    itemName = tokens[0];
                    pointsAmount = Integer.parseInt(tokens[1]);
                    stock = Integer.parseInt(tokens[2]);

                    Redemption redemption = new Redemption(itemName, pointsAmount, stock);
                    redemptionList.add(redemption);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return redemptionList;
    }
     
     public static void saveRedemptions(ArrayList<Redemption> redemptionList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Redemption redemption : redemptionList) {
                String line = String.format(
                        "%s|%d|%d%n",
                        redemption.getItemName(),
                        redemption.getPointsAmount(),
                        redemption.getStock()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    } 
}
