package org.example.services;

import org.example.models.CartItem;
import org.example.models.products.Product;

import java.util.*;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough " + product.getName() + " in stock");
        }
        if (product.isExpired()) {
            throw new IllegalArgumentException(product.getName() + " has expired");
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public void clear() {
        items.clear();
    }

}
