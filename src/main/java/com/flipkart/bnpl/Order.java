package com.flipkart.bnpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Order {
    private String orderId;
    private List<Item> items;
    private double totalAmount;
    private Date dateOfPurchase;
    private boolean isBNPL;
    private boolean isPaid;
    private Date dateOfClearing;

    public Order(List<Item> items, double totalAmount, Date dateOfPurchase, boolean isBNPL) {
        this.orderId = UUID.randomUUID().toString();
        this.items = items;
        this.totalAmount = totalAmount;
        this.dateOfPurchase = dateOfPurchase;
        this.isBNPL = isBNPL;
        this.isPaid = !isBNPL;
    }

    public Order(List<Item> items, double totalAmount, Date dateOfPurchase) {
        this.orderId = UUID.randomUUID().toString();
        this.items = items;
        this.totalAmount = totalAmount;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isBNPL() {
        return isBNPL;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid(Date dateOfClearing) {
        this.isPaid = true;
        this.dateOfClearing = dateOfClearing;
    }

    public Date getDateOfClearing() {
        return dateOfClearing;
    }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", totalAmount=" + totalAmount + ", isBNPL=" + isBNPL + ", isPaid=" + isPaid + '}';
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setBNPL(boolean BNPL) {
        isBNPL = BNPL;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}


