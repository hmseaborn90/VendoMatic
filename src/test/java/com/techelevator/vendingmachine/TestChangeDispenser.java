package com.techelevator.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

public class TestChangeDispenser {

    ChangeDispenser CDtest = new ChangeDispenser();

    @Test
    public void giveChangeTest() {
        double balance = 10.00;
        double cost = 0.85;

        String changeReport = CDtest.giveChange(balance, cost);

        Assert.assertEquals("Change returned: $9.15\n" +
                "Quarters: 36\n" +
                "Dimes: 1\n" +
                "Nickels: 1", changeReport);
    }

}
