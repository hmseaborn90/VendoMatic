package com.techelevator.vendingmachine;


import java.math.BigDecimal;
import java.util.Scanner;

import static com.techelevator.Application.currency;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public String displayMainMenu() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        return scanner.nextLine();
    }

    public String displayPurchaseMenu(double balance) {
        System.out.println("\nCurrent Balance: " + currency.format(balance));
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        return scanner.nextLine();
    }

    public void displayCurrentBalance(double balance){
        System.out.println("\nCurrent Balance: " + currency.format(balance));
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }
    public void displayError(String message){
        System.err.println(message);
    }
    public String promptUser(String message){
        System.out.println(message);
        return scanner.nextLine();
    }
}
