package com.example.phongthuy;

import android.content.Intent;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;
    Integer totalPrice;
    String id;

    public Order() {
    }

    public Order(ArrayList<Product> products, Integer totalPrice, String id) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(String id) {
        this.id = id;
    }
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}