package com.techelevator.vendingmachine;

public class Product {
   private String slotLocation;
    private String productName;
    private double productPrice;
    private String productType;

    public Product(String slotLocation, String productName, double productPrice, String productType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
    }
    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
