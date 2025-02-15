package com.techelevator.vendingmachine;

import com.techelevator.exceptions.InvalidProductTypeException;

import java.io.FileNotFoundException;

import java.util.Scanner;

public class VendingMachine {
    // Instance variables
    // TODO create variables for other classes such as salesReport PurchaseManger and create instance in the constructor
    private double balance = 0;
    private UserInterface ui;
    private SalesReport salesReport;
    private ChangeDispenser changeDispenser;
    private ProductInventory productInventory;
    private PurchaseManager purchaseManager;

    //CONSTRUCTOR
    //TODO Create and add new instances to constructor
    public VendingMachine(Scanner scanner){
        this.ui = new UserInterface(scanner);
        this.salesReport = new SalesReport(); // TODO Sales report will need to be passed to the purchase manager in order to add product to salesReport upon succesful purchase
        this.changeDispenser = new ChangeDispenser();
        this.productInventory = new ProductInventory();
        this.purchaseManager = new PurchaseManager(salesReport);
    }


    //PRODUCT INVENTORY METHOD DELEGATIONS

    public void loadInventory(String filePath) throws FileNotFoundException, InvalidProductTypeException {
        productInventory.loadInventoryFromFile(filePath);
    }

    public void displayInventory(){
        ui.displayInvetory(productInventory.getProducts());
    }

    //SALES REPORT METHOD DELEGATION
    public void getSalesReport(){
        salesReport.getSalesReport(productInventory);
        ui.printUnderline(50);
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

    public void printUnderline(int length){
        ui.printUnderline(length);
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

    public void purchaseProduct(String slotLocation) {
        Product product = productInventory.getProductBySlot(slotLocation);
        double updatedBalance = purchaseManager.purchaseProduct(product, balance, productInventory);
        setBalance(updatedBalance);
        ui.printUnderline(50);
    }
    public void feedMoney(double amountFed) {
        balance += amountFed;
        ui.printUnderline(50);
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void giveChange() {
        displayMessage(changeDispenser.giveChange(balance));
        balance = 0;
        ui.printUnderline(50);

    }
}
