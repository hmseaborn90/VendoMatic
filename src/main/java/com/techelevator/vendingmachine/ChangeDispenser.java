package com.techelevator.vendingmachine;

import com.techelevator.util.Logger;

import java.text.NumberFormat;
import java.util.Locale;

public class ChangeDispenser {

    private static final double QUARTER = 0.25;
    private static final double DIME = 0.10;
    private static final double NICKEL = 0.05;
    private int quarterCount;
    private int dimeCount;
    private int nickelCount;

    public String giveChange(double balance, double amountSpent) {
        double change = balance - amountSpent;
        double coinChange = change;
        quarterCount = (int) (coinChange / QUARTER);
        coinChange -= quarterCount * QUARTER;
        dimeCount = (int) (coinChange / DIME);
        coinChange -= dimeCount * DIME;
        nickelCount = (int) (coinChange / NICKEL);

        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        change = Math.round(change * 100) / 100.0;

        Logger.log("GIVE CHANGE: $" + change + " $" + balance);

        return "Change returned: " + moneyFormat.format(change) + "\n" +
                "Quarters: " + quarterCount + "\n" +
                "Dimes: " + dimeCount + "\n" +
                "Nickels: " + nickelCount;
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
