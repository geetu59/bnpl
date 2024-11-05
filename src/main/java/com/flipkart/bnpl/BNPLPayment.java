package com.flipkart.bnpl;
class BNPLPayment implements PaymentStrategy {
    @Override
    public boolean pay(User user, double amount, Order order) {
        if (user.getCreditLimit() >= amount) {
            user.setCreditLimit(user.getCreditLimit() - amount);
            order.setBNPL(true);
            System.out.println("BNPL payment of Rs" + amount + " successful. Remaining credit: Rs" + user.getCreditLimit());
            return true;
        } else {
            System.out.println("BNPL payment failed. Not enough credit.");
            return false;
        }
    }
}
