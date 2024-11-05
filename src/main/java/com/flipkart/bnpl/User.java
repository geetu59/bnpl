package com.flipkart.bnpl;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private double creditLimit;
    private List<Order> orders;
//for blacklisting, store noOfBNPL orders count and check if it exceed 3 then dont go ahead, update it in buy method and clear dues ke time pe remove it
    public User(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', creditLimit=" + creditLimit + "}";
    }
}



