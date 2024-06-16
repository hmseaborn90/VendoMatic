package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;

import java.util.HashMap;
import java.util.Map;

import static com.techelevator.Application.currency;

public class SalesReport {
    private double totalSales = 0;
    private Map<String, Integer> productsSold = new HashMap<>();

    public void addToSalesReport(String productName, double price){
        totalSales += price;
        productsSold.put(productName, productsSold.getOrDefault(productName, 0)+ 1);
    }

    public void getSalesReport(){
        for(Map.Entry<String, Integer> entry : productsSold.entrySet()){
            System.out.println(ConsoleColors.YELLOW + "Product name: "+entry.getKey() + " | " + "Quantity: " + entry.getValue() + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.GREEN + "     ***TOTAL SALES***" + " | " +currency.format(totalSales) + ConsoleColors.RESET);
    }

    public Map<String, Integer> getProductsSold() {
        return productsSold;
    }

    public double getTotalSales() {
        return totalSales;
    }
}
