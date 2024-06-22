package com.techelevator.vendingmachine;

import com.techelevator.exceptions.InvalidProductTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductInventory {

    private static final int DEFAULT_QUANTITY = 5;

    private Map<Product, Integer> products;

    public ProductInventory() {
        this.products = new HashMap<>();
    }


    /*
    Load Inventory from file method handles reading the file that is passed in as a parameter
    Parsing every line of the file which contains an item to be added to the inventory
    utilizes helper method below CreateProduct to create each product and add it to the products Map
     */
    public void loadInventoryFromFile(String filePath) {
        File csvFile = new File(filePath);
        try (Scanner fileInput = new Scanner(csvFile)) {
            while (fileInput.hasNextLine()) {

                String[] productData = fileInput.nextLine().split("\\|");
                String slotNumber = productData[0];
                String productName = productData[1];
                double productPrice = Double.parseDouble(productData[2]);
                String productType = productData[3];

                try {
                    Product product = createProduct(slotNumber, productName, productPrice, productType);
                    products.put(product, DEFAULT_QUANTITY);

                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid product price for " + productName + e.getMessage());
                } catch (InvalidProductTypeException e) {
                    System.err.println(e.getMessage());

                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found " + csvFile.getAbsolutePath());
        }
    }

    private Product createProduct(String slotNumber, String productName, double productPrice, String productType) throws InvalidProductTypeException {
        switch (productType.toLowerCase()) {
            case "chip":
                return new Chip(slotNumber, productName, productPrice, productType);
            case "candy":
                return new Candy(slotNumber, productName, productPrice, productType);
            case "drink":
                return new Drink(slotNumber, productName, productPrice, productType);
            case "gum":
                return new Gum(slotNumber, productName, productPrice, productType);
            default:
                throw new InvalidProductTypeException("Invalid product type: " + productType);
        }
    }

    public Product getProductBySlot(String slotLocation) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            if (product.getSlotLocation().equalsIgnoreCase(slotLocation)) {
                return product;
            }
        }
        return null;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

}
