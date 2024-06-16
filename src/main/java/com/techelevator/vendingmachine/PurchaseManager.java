package com.techelevator.vendingmachine;

import com.techelevator.exceptions.BalanceInsufficientException;
import com.techelevator.exceptions.InvalidSlotLocationException;
import com.techelevator.exceptions.ProductOutOfStockException;
import com.techelevator.util.ConsoleColors;
import com.techelevator.util.Logger;


import static com.techelevator.Application.currency;

public class PurchaseManager {

    private SalesReport salesReport;

    public PurchaseManager(SalesReport salesReport) {
        this.salesReport = salesReport;
    }

    public double purchaseProduct(Product product, double balance, ProductInventory productInventory) {
        double updatedBalance = balance;
        try {
            if (isPurchaseValid(product, balance, productInventory)) {
                updatedBalance = performPurchase(product, balance, productInventory);
            }
        } catch (ProductOutOfStockException | BalanceInsufficientException e) {
            System.err.println(e.getMessage());
        } catch (InvalidSlotLocationException e) {
            System.err.println(e.getMessage());
            return balance;
        }
        return updatedBalance;
    }

    public boolean isPurchaseValid(Product product, double balance, ProductInventory productInventory) throws ProductOutOfStockException, BalanceInsufficientException, InvalidSlotLocationException {
        if (product == null) {
            throw new InvalidSlotLocationException("Invalid slot location. Please select a valid slot");
        }
        int quantity = productInventory.getProducts().getOrDefault(product, 0);

        if (quantity == 0) {
            throw new ProductOutOfStockException("Sorry, this item is currently sold out. Please make another selection.");
        }
        if (product.getProductPrice() >= balance) {
            throw new BalanceInsufficientException("Your balance is insufficient for this product. Please insert more money.");
        }
        return true;

    }

    public double performPurchase(Product product, double balance, ProductInventory productInventory) {
        int quantity = productInventory.getProducts().getOrDefault(product, 0);
        double updatedBalance = balance - product.getProductPrice();

        productInventory.getProducts().put(product, quantity - 1);
        salesReport.addToSalesReport(product.getProductName(), product.getProductPrice());
        String balanceRemaining = currency.format(updatedBalance);
        String logMessage = String.format("%s %s %s",
                product.getProductName(),
                product.getSlotLocation(),
                currency.format(product.getProductPrice()));


        System.out.println(ConsoleColors.GREEN + "Balance Remaining: " + balanceRemaining + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Name: " +
                product.getProductName() + " | " +
                "Price: " +
                currency.format(product.getProductPrice()) + " | " + product + ConsoleColors.RESET);
        Logger.log(logMessage + " " + balanceRemaining);
        return updatedBalance;
    }
}

