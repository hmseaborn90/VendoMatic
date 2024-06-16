package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;
import com.techelevator.util.Logger;

import java.text.NumberFormat;
import java.util.Locale;

import static com.techelevator.Application.currency;

public class ChangeDispenser {

    private static final double QUARTER = 0.25;
    private static final double DIME = 0.10;
    private static final double NICKEL = 0.05;
    private int quarterCount;
    private int dimeCount;
    private int nickelCount;

    public String giveChange(double balance) {
        double coinChange = balance;
        double changeReturned = 0;
        quarterCount = (int) (coinChange / QUARTER);
        coinChange -= quarterCount * QUARTER;
        changeReturned += quarterCount * QUARTER;
        dimeCount = (int) (coinChange / DIME);
        coinChange -= dimeCount * DIME;
        changeReturned += dimeCount * DIME;
        nickelCount = (int) (coinChange / NICKEL);
        changeReturned += nickelCount * NICKEL;


        changeReturned = Math.round(balance * 100) / 100.0;

        Logger.log("GIVE CHANGE: " + currency.format(changeReturned) + " " + currency.format(balance));

        return ConsoleColors.GREEN + "      Change returned: " + currency.format(changeReturned) + "\n" +
                "      Quarters: " + quarterCount + "\n" +
                "      Dimes: " + dimeCount + "\n" +
                "      Nickels: " + nickelCount + ConsoleColors.RESET;
    }


    public int getQuarterCount() {
        return quarterCount;
    }

    public int getDimeCount() {
        return dimeCount;
    }

    public int getNickelCount() {
        return nickelCount;
    }
}
