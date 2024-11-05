package com.flipkart.bnpl;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

import static java.util.Collections.singletonList;

@SpringBootApplication
public class BnplApplication {
    public static void main(String[] args) {
        try {
            Inventory inventory = Inventory.getInstance();

            System.out.println("Enter product data (name,count,price) line by line. Enter 'done' when finished:");

            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
                inventory.seed_inventory(inputReader);
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }

            System.out.println(inventory);

            System.out.println("Inventory after seeding:");
            inventory.view_inventory();

            System.out.println("Registering user:");
            User user = UserService.register_user("Akshay", 5000);
            System.out.println("Registered User: " + user);

            Item shoes = new Item(inventory.getProduct("Shoes"), 2);
            Item watch = new Item(inventory.getProduct("Watch"), 1);

            PrepaidPayment prepaidPayment = new PrepaidPayment();
            OrderService.buy(user, Arrays.asList(shoes, watch), prepaidPayment, new Date());

            PaymentStrategy bnplPayment = new BNPLPayment(); //use enum to understand payment method and then apply factory to get instance
            OrderService.buy(user, Arrays.asList(shoes, watch), bnplPayment, new Date());
            System.out.println("User Orders (before clearing): " + user.getOrders());

            System.out.println("User Orders:");
            UserService.order_status(user);

            UserService.view_dues(user, new Date());

            UserService.view_dues(user, new Date());

            UserService.order_status(user);

            String orderIdToClear = user.getOrders().get(0).getOrderId();
            UserService.clear_dues(user, singletonList(orderIdToClear), new Date());

            System.out.println("User Orders (after clearing): " + user.getOrders());
            inventory.view_inventory();

            System.out.println("User Orders: " + user.getOrders());
            UserService.order_status(user);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
