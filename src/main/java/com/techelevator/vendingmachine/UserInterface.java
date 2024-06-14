package com.techelevator.vendingmachine;


import com.techelevator.util.ConsoleColors;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.techelevator.Application.currency;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public String displayMainMenu() {
        System.out.println(ConsoleColors.BLUE + "     (1) Display Vending Machine Items");
        System.out.println("     (2) Purchase");
        System.out.println(ConsoleColors.RED + "     (3) Exit" + ConsoleColors.RESET);
        printUnderline(50);
        return scanner.nextLine().trim();
    }

    public String displayPurchaseMenu(double balance) {
        displayCurrentBalance(balance);
        System.out.println(ConsoleColors.BLUE + "     (1) Feed Money");
        System.out.println("     (2) Select Product" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "     (3) Finish Transaction" + ConsoleColors.RESET);
        printUnderline(50);
        return scanner.nextLine();
    }

    public void displayCurrentBalance(double balance) {
        System.out.println(ConsoleColors.GREEN + "\n     Current Balance: " + currency.format(balance) + ConsoleColors.RESET);
    }

    public void displayMessage(String message) {
        System.out.println(ConsoleColors.CYAN + message + ConsoleColors.RESET);
    }

    public void displayError(String message) {
        System.err.println(ConsoleColors.RED_BOLD_BRIGHT + message + ConsoleColors.RESET);
    }

    public String promptUser(String message) {
        System.out.println(ConsoleColors.CYAN + message + ConsoleColors.RESET);
        return scanner.nextLine();
    }

    public void printUnderline(int length) {
        System.out.println(ConsoleColors.WHITE_UNDERLINED + " ".repeat(length) + ConsoleColors.RESET);
        System.out.println();
    }
}
