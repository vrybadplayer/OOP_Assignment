/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

/**
 *
 * @author lolka
 */
public class membership {
    double subtotal;
    
    public double discount(){
        return subtotal * 0.95;
    }
    
    public double redemption(){
        double totalPoint = subtotal /10;
        return totalPoint;
    }
}
