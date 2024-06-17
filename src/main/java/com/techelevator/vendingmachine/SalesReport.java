package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void getSalesReport(ProductInventory productInventory){

        File salesReports = new File("salesreports");
        salesReports.mkdir();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy_HHmmss");
        String reportFileName = "salesreports/SP_" + date.format(formatter) + ".txt";
        try(PrintWriter salesOutput = new PrintWriter(new FileWriter(reportFileName))) {
            for (Map.Entry<Product, Integer> entry : productInventory.getProducts().entrySet()) {
                Product product = entry.getKey();
                int quantitySold = productsSold.getOrDefault(product.getProductName(), 0);
                salesOutput.println(product.getProductName() + "|" + quantitySold);
                System.out.println(ConsoleColors.YELLOW + product.getProductName() + " | " +
                        quantitySold + ConsoleColors.RESET);
            }
            salesOutput.println("**TOTAL SALES** " + currency.format(totalSales));
        } catch (IOException e) {
            System.err.println("Cannot open file for writing. " + e.getMessage());
        }

        System.out.println(ConsoleColors.GREEN + "     ***TOTAL SALES***" + " | " +currency.format(totalSales));
        System.out.println("Report file saved at: " + reportFileName + ConsoleColors.RESET);
    }

    public Map<String, Integer> getProductsSold() {
        return productsSold;
    }

    public double getTotalSales() {
        return totalSales;
    }
}
