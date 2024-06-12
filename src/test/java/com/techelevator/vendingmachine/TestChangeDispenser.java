package com.techelevator.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

public class TestChangeDispenser {

    ChangeDispenser CDtest = new ChangeDispenser();

    @Test
    public void giveChangeTest() {
        double balance = 9.15;

        String changeReport = CDtest.giveChange(balance);

        Assert.assertEquals("Change returned: $9.15\n" +
                "Quarters: 36\n" +
                "Dimes: 1\n" +
                "Nickels: 1", changeReport);
    }

}
