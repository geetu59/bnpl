package com.flipkart.bnpl;

public class Product {
    private String name;
    private int count;
    private double price;

    public Product(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public void reduceQuantity(int quantity) {
        this.count -= quantity;
    }

    public boolean isAvailable(int quantity) {
        return this.count >= quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " " + count + " " + price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


