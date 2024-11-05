package com.flipkart.bnpl;

import java.util.Date;
import java.util.List;

public class UserService {
    public static User register_user(String name, double creditLimit) { //same user should not be registered again, so keep list<user> and check if it was there
        User user = new User(name);
        user.setCreditLimit(creditLimit);
        return user;
    }

    public static void clear_dues(User user, List<String> orderIds, Date dateOfClearing) {
        List<Order> orders = user.getOrders();
        double totalDue = 0.0;

        for (String orderId : orderIds) {
            Order order = findOrderById(orders, orderId);

            if (order != null && order.isBNPL() && !order.isPaid()) {
                totalDue += order.getTotalAmount();
            } else {
                System.out.println("Order " + orderId + " is either not BNPL or already paid.");
            }
        }

        if (user.getCreditLimit() >= totalDue) {
            for (String orderId : orderIds) {
                Order order = findOrderById(orders, orderId);

                if (order != null && order.isBNPL() && !order.isPaid()) {
                    order.markAsPaid(dateOfClearing);
                    System.out.println("Order " + orderId + " cleared.");
                }
            }

            user.setCreditLimit(user.getCreditLimit() + totalDue);
            System.out.println("Total dues cleared: " + totalDue);
        } else {
            System.out.println("Insufficient credit limit to clear dues. Total dues: Rs" + totalDue + ", Credit limit: Rs " + user.getCreditLimit());
        }
    }

    private static Order findOrderById(List<Order> orders, String orderId) { //store list<orderid, orders> to do it o(1) time else o(n)
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
        return null;
    }


    public static void view_dues(User user, Date date) {
        List<Order> orders = user.getOrders();
        boolean hasDues = false;

        orders.sort((o1, o2) -> o1.getDateOfPurchase().compareTo(o2.getDateOfPurchase()));

        System.out.println("Unpaid BNPL orders for user: " + user.getName() + " as of " + date);

        for (Order order : orders) {
            if (order.isBNPL() && !order.isPaid() && order.getDateOfPurchase().before(date)) {
                long differenceInMillis = date.getTime() - order.getDateOfPurchase().getTime();
                long daysDifference = differenceInMillis / (1000 * 60 * 60 * 24);
                String dueStatus = (daysDifference > 30) ? "DELAYED" : "PENDING";

                System.out.println("Order ID: " + order.getOrderId() +
                        ", Amount Due: Rs" + order.getTotalAmount() +
                        ", Date of Purchase: " + order.getDateOfPurchase() +
                        ", Status: " + dueStatus);
                hasDues = true;
            }
        }

        if (!hasDues) {
            System.out.println("No unpaid dues as of " + date);
        }
    }

    public static void order_status(User user) {
        List<Order> orders = user.getOrders();

        System.out.println("Order status for user: " + user.getName());

        orders.sort((o1, o2) -> o1.getDateOfPurchase().compareTo(o2.getDateOfPurchase()));
        for (Order order : orders) {
            String paymentStatus = order.isPaid() ? "Paid" : "Unpaid";
            System.out.println("Order ID: " + order.getOrderId() + ", Amount: Rs" + order.getTotalAmount() +
                    ", BNPL: " + order.isBNPL() + ", Status: " + paymentStatus +
                    (order.isPaid() ? ", Cleared on: " + order.getDateOfClearing() : "User credit limit " + user.getCreditLimit()));
        }
    }
}

