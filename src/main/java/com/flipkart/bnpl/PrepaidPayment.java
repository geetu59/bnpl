package com.flipkart.bnpl;

public class PrepaidPayment implements PaymentStrategy {
    @Override
    public boolean pay(User user, double amount, Order order) {
        System.out.println("Prepaid payment of " + amount + " successful.");
        order.setPaid(true);
        return true;
    }
}
