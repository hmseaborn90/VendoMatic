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
                    String productSlot = vendingMachine.promptUser("Please choose slot location: ");
                    vendingMachine.purchaseProduct(productSlot.toUpperCase());
                    break;
                case "3":
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
                String userInput = vendingMachine.promptUser("Enter the amount of money to feed: ");
                int amount = Integer.parseInt(userInput);

                if (isValidAmount(amount)) {
                    vendingMachine.feedMoney(amount);
                    vendingMachine.displayCurrentBalance();

                    String logMessage = String.format("Feed Money: %s %s", currency.format(amount), currency.format(vendingMachine.getBalance()));
                    Logger.log(logMessage);
                } else {
                    vendingMachine.displayErrorMsg("\n" + "Sorry, we can only accept valid US currency not sure where you got that " + amount + " dollar bill from but it is probably fake Please insert valid US currency");
                    continue;
                }

            } catch (NumberFormatException e) {
                vendingMachine.displayErrorMsg("Invalid input sorry we only accept bills at this time insert a valid whole dollar amount.");
                continue;
            }

            String moreMoneyChoice = vendingMachine.promptUser("Do you want to add more money? (Y/N): ");
            if (!moreMoneyChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private static boolean isValidAmount(int amount) {
        return amount == 1 || amount == 5 || amount == 10 || amount == 20 || amount == 50 || amount == 100;
    }
}
