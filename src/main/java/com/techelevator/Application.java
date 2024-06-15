package com.techelevator;

import com.techelevator.exceptions.InvalidProductTypeException;
import com.techelevator.util.Logger;
import com.techelevator.vendingmachine.VendingMachine;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class Application {

    private static Scanner scanner = new Scanner(System.in);
    public static NumberFormat currency = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        Logger.log("Vending Machine Starting up");
        VendingMachine vendingMachine = new VendingMachine(scanner);

        try {
            vendingMachine.loadInventory("vendingmachine.csv");
        } catch (FileNotFoundException | InvalidProductTypeException e) {
            vendingMachine.displayErrorMsg(e.getMessage());

        }
        boolean isShouldExit = false;

        while (!isShouldExit) {

            String userChoice = vendingMachine.displayMainMenu();

            switch (userChoice) {
                case "1":
                    vendingMachine.displayInventory();
                    break;
                case "2":
                    handlePurchaseMenu(vendingMachine);
                    break;
                case "3":
                    vendingMachine.displayMessage("display message that your exiting vending machine application");
                    // set boolean to true to exit the loop and exit the program may be a better way to handle exiting program
                    isShouldExit = true;
                    break;
                case "4":
                    vendingMachine.getSalesReport();
                default:
                    vendingMachine.displayErrorMsg("invalid Selection please try again");// maybe some error handling that could be done
            }
        }
        scanner.close();
    }

    private static void handlePurchaseMenu(VendingMachine vendingMachine) {

        boolean isFinished = false;

        while (!isFinished) {

            vendingMachine.displayInventory();
            String purchaseMenuChoice = vendingMachine.displayPurchaseMenu();
            switch (purchaseMenuChoice) {
                case "1":
                    handleMoneyInput(vendingMachine);
                    break;
                case "2":
                    //TODO
                    String productSlot = vendingMachine.promptUser("Please choose slot location");
                    vendingMachine.purchaseProduct(productSlot.toUpperCase());
                    break;
                case "3":
                    //TODO
                    vendingMachine.giveChange();
                    isFinished = true;
                    break;
                default:
                    vendingMachine.displayMessage("Invalid choice please try again proper error handling to come");
                    break;
            }
        }
    }


    private static void handleMoneyInput(VendingMachine vendingMachine) {
        while (true) {

            try {
                double amount = Double.parseDouble(vendingMachine.promptUser("Enter the amount of money to feed: "));

                if (amount < 1.00) {
                    vendingMachine.displayErrorMsg("Sorry we can only accept whole dollars at this time please insert acceptable amount");
                    continue;
                }
                vendingMachine.feedMoney(amount);
                vendingMachine.displayCurrentBalance();

                String logMessage = String.format("Feed Money: %s %s", currency.format(amount), currency.format(vendingMachine.getBalance()));
                Logger.log(logMessage);
            } catch (NumberFormatException e) {
//                throw new NumberFormatException("Invalid input please insert dollar amount");
                vendingMachine.displayErrorMsg("Invalid input please insert dollar amount");
                continue;
            }
            String moreMoneyChoice = vendingMachine.promptUser("Do you want to add more money? (Y/N)");
            if (!moreMoneyChoice.equalsIgnoreCase("Y")) {
                break;
            }

        }
    }
}
