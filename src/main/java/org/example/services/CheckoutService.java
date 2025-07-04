package org.example.services;

import org.example.interfaces.Shippable;
import org.example.models.CartItem;
import org.example.models.Customer;
import org.example.models.products.Product;

import java.util.*;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        // validate items still available
        validateItems(cart);

        // calculate totals
        double subtotal = cart.calculateSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        Map<String, Integer> quantities = getQuantities(cart);
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        // validate customer balance
        if (!customer.hasEnoughBalance(totalAmount)) {
            throw new IllegalStateException("Insufficient balance");
        }

        // process shipment
        ShippingService.processShipment(shippableItems, quantities);

        customer.deductBalance(totalAmount);
        updateInventory(cart);


        printReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance());

        // clear cart after shipment
        cart.clear();
    }

    private static void validateItems(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.isExpired()) {
                throw new IllegalStateException(product.getName() + " has expired");
            }

            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalStateException(product.getName() + " is out of stock");
            }
        }
    }

    private static List<Shippable> getShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.requiresShipping() && product instanceof Shippable) {
                shippableItems.add((Shippable) product);
            }
        }

        return shippableItems;
    }

    private static Map<String, Integer> getQuantities(Cart cart) {
        Map<String, Integer> quantities = new HashMap<>();

        for (CartItem item : cart.getItems()) {
            quantities.put(item.getProduct().getName(), item.getQuantity());
        }

        return quantities;
    }

    private static void updateInventory(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    }

    private static void printReceipt(Cart cart, double subtotal, double shippingFee,
                                     double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer current balance after payment: %.0f%n", remainingBalance);
        System.out.println("END.");
    }

}
