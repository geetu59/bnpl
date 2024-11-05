package com.flipkart.bnpl;

class Item {
    private Product product; //dont use product, why item needs to have whole info of product. tuple(pid, qty)
    private int quantity;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getPrice() {
        return product.getPrice() * quantity;
    }

    public String getName() {
        return product.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceInventory() {
        product.reduceQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

