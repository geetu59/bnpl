package com.flipkart.bnpl;

public interface PaymentStrategy {
    boolean pay(User user, double amount, Order order);
}
