package com.techelevator.vendingmachine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestUserInterface {
    private UserInterface ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testDisplayMainMenu(){
        //Arrange
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        ui = new UserInterface(scanner);
        String expected = "(1) Display Vending Machine Items\n" +
                "(2) Purchase\n" +
                "(3) Exit\n";

        //Act
        String choice = ui.displayMainMenu();
        //Assert

        Assert.assertEquals("1", choice.trim());
        Assert.assertEquals(expected, outContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n"));
        // had to use a regex to normalize the line separators test was reading \n line separator differently than how prinln separates the lines
        //TODO understand this process better
    }

    @Test
    public void testDisplayPurchaseMenu() {
        // Arrange
        String input = "2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        double balance = 10.0;
        ui = new UserInterface(new Scanner(System.in));

        // Act
        String choice = ui.displayPurchaseMenu(balance);

        // Assert
        Assert.assertEquals("2", choice.trim());
        Assert.assertEquals("\nCurrent Balance: $10.00\n(1) Feed Money\n(2) Select Product\n(3) Finish Transaction\n", outContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n"));
    }

    @Test
    public void testDisplayCurrentBalance() {
        // Arrange
        double balance = 10.0;
        ui = new UserInterface(new Scanner(System.in));

        // Act
        ui.displayCurrentBalance(balance);

        // Assert
        Assert.assertEquals("\nCurrent Balance: $10.00\n", outContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n"));
    }
}
