package com.flipkart.bnpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static Inventory instance = null;
    private final Map<String, Product> products = new HashMap<>(); //if watch,5,1000 is there and you add watch,10,1000, it should be watch,15,1000

    private Inventory() {
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void seed_inventory(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equalsIgnoreCase("done")) {
                break;
            }

            String[] productData = line.split(",");
            if (productData.length != 3) {
                System.out.println("Invalid input, please enter in the format: name,count,price");
                continue;
            }

            try {
                String name = productData[0].trim();
                int count = Integer.parseInt(productData[1].trim());
                double price = Double.parseDouble(productData[2].trim());

                Product product = new Product(name, count, price);
                addProduct(product);
                System.out.println("Added product: " + product);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format, please enter valid count and price.");
            }
        }
    }

    public void addProduct(Product product) {
        products.put(product.getName(),product);
    }

    public void view_inventory() {
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public void reduceInventory(String productName, int quantity) {
        Product product = products.get(productName);
        if (product != null && product.getCount() >= quantity) {
            product.setCount(product.getCount() - quantity);
            System.out.println("Inventory reduced for " + productName + ". New count: " + product.getCount());
        } else {
            System.out.println("Insufficient inventory for " + productName);
        }
    }
}
