package com.techelevator.vendingmachine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestProductClass {

    private Product candy;
    private Product drink;
    private Product gum;
    private Product chip;
    private Product generic;


    @Before
    public void setUp(){
        candy = new Candy("A4", "Reses", 1.35, "Candy");
        drink = new Drink("A3", "Soda", 1.55, "Drink");
        gum = new Gum("A2", "Bubble", 1.00, "Gum");
        chip = new Chip("A1", "Lays", 2.00, "Chip");
        generic = new Product("B1", "generic", .55, "generic");
    }

    @Test
    public void testGetSlotLocation(){
        String actual = candy.getSlotLocation();
        String expected = "A4";
        Assert.assertEquals("slot location is returned",expected, actual);
    }

    @Test
    public void testGetProductName(){
        String actual = drink.getProductName();
        String expected = "Soda";
        Assert.assertEquals("product name is returned",expected, actual);
    }

    @Test
    public void testGetProductPrice(){
        double actual = gum.getProductPrice();
        double expected = 1.00;
        Assert.assertEquals("slot location is returned",expected, actual, 0.00);
    }

    @Test
    public void testToString(){
        String chipActual = chip.toString();
        String drinkActual = drink.toString();
        String gumActual = gum.toString();
        String candyActual = candy.toString();
        String genericActual = generic.toString();

        String chipExpected = "Crunch Crunch, Yum!";
        String drinkExpected = "Glug Glug, Yum!";
        String gumExpected = "Chew Chew, Yum!";
        String candyExpected = "Munch Munch, Yum!";
        String genericExpected = "This is a generic type generic";

        Assert.assertEquals("slot location is returned",chipExpected, chipActual);
        Assert.assertEquals("slot location is returned",drinkExpected, drinkActual);
        Assert.assertEquals("slot location is returned",gumExpected, gumActual);
        Assert.assertEquals("slot location is returned",candyExpected, candyActual);
        Assert.assertEquals("slot location is returned",genericExpected, genericActual);
    }
}
