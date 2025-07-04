package org.example.models.products;

public class DigitalProduct extends Product {
    public DigitalProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired(){
        return false;
    }

    @Override
    public boolean requiresShipping(){
        return false;
    }
}
