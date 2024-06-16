package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;
import org.junit.Assert;
import org.junit.Test;

public class TestChangeDispenser {

    ChangeDispenser CDtest = new ChangeDispenser();

    @Test
    public void giveChangeTest() {
        double balance = 9.15;

        String changeReport = CDtest.giveChange(balance);

        Assert.assertEquals(ConsoleColors.GREEN +"      Change returned: $9.15\n" +
                "      Quarters: 36\n" +
                "      Dimes: 1\n" +
                "      Nickels: 1" + ConsoleColors.RESET, changeReport);
    }

}
