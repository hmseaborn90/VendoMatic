package com.techelevator.vendingmachine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class TestSalesReport {

    private SalesReport salesReport;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp(){
        salesReport = new SalesReport();
        System.setOut(new PrintStream(outContent));
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
    public void getSalesReportTest(){
        String productName1 = "Candy";
        String productName2 = "Drink";
        double price1 = 2.00;
        double price2 = 1.50;

        salesReport.addToSalesReport(productName1, price1);
        salesReport.addToSalesReport(productName2, price2);
        salesReport.getSalesReport();
        String salesReportOutput = outContent.toString();

        Assert.assertTrue("Expected product name not found in output",salesReportOutput.contains("Product name " + productName1));
        Assert.assertTrue("Expected product quantity not found in output",salesReportOutput.contains("Quantity: 1"));
        Assert.assertTrue("Expected product name not found in output",salesReportOutput.contains("Product name " + productName2));
        Assert.assertTrue("Expected product quantity not found in output",salesReportOutput.contains("Quantity: 1"));
        Assert.assertTrue("Expected total sales amount not found in sales report output",salesReportOutput.contains("***TOTAL SALES*** | $3.50"));
    }
}
