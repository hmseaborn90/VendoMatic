package com.techelevator.vendingmachine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Optional;

public class TestSalesReport {

    private SalesReport salesReport;
    private ProductInventory productInventory = new ProductInventory();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    @Before
    public void setUp(){
        salesReport = new SalesReport();
        System.setOut(new PrintStream(outContent));
        productInventory.loadInventoryFromFile("vendingmachine.csv");
    }

    @After
    public void restoreOutStream(){
        System.setOut(originalOut);
    }

    @Test
    public void addToSalesReportTest(){
        String productName = "Chip";
        double price = 1.75;
        salesReport.addToSalesReport(productName, price);
        Assert.assertEquals(1.75, salesReport.getTotalSales(),0.0);
        Assert.assertEquals(1, (int)salesReport.getProductsSold().get(productName));
        salesReport.addToSalesReport(productName, price);
        Assert.assertEquals("returns correct price with multiple of same products",3.50, salesReport.getTotalSales(),0.0);
        Assert.assertEquals("returns correct count with multiple of same products",2, (int)salesReport.getProductsSold().get(productName));

    }

    @Test
    public void getSalesReportTest() {
        String productName1 = "MoonPie";
        String productName2 = "CowTales";
        double price1 = 1.80;
        double price2 = 1.50;

        salesReport.addToSalesReport(productName1, price1);
        salesReport.addToSalesReport(productName1, price1);
        salesReport.addToSalesReport(productName2, price2);

        salesReport.getSalesReport(productInventory);

        Map<String, Integer> test = salesReport.getProductsSold();

        Assert.assertEquals(2, (int) test.get("MoonPie"));
        Assert.assertEquals(1, (int) test.get("CowTales"));
        Assert.assertEquals(2, test.size());

    }
}
