package com.techelevator.vendingmachine;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachine {
    // Instance variables
    // TODO create variables for other classes such as salesReport PurchaseManger and create instance in the constructor
    private double balance = 0;
    private UserInterface ui;

    //CONSTRUCTOR
    //TODO Create and add new instances to constructor
    public VendingMachine(Scanner scanner){
        this.ui = new UserInterface(scanner);
    }


    //UI METHOD DELEGATIONS
    public String displayMainMenu(){
        return ui.displayMainMenu();
    }
    public String displayPurchaseMenu() {
        return ui.displayPurchaseMenu(balance);
    }
    public String promptUser(String message){
        return ui.promptUser(message);
    }

    public void displayCurrentBalance() {
        ui.displayCurrentBalance(balance);
    }

    public void displayMessage(String message){
        ui.displayMessage(message);
    }
    public void displayErrorMsg(String message){
        ui.displayError(message);
    }

    //BALANCE ALTERING METHODS
    public void feedMoney(double amountFed) {
        balance += amountFed;
    }
    public double getBalance() {
        return balance;
    }
}
