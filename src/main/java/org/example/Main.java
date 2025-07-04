package org.example;

import org.example.models.Customer;
import org.example.models.products.DigitalProduct;
import org.example.models.products.ExpirableProduct;
import org.example.models.products.PhysicalProduct;
import org.example.models.products.Product;
import org.example.services.Cart;
import org.example.services.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Product cheese = new ExpirableProduct("Cheese", 100.0, 10,
                LocalDate.now().plusDays(7), 0.2);
        Product tv = new PhysicalProduct("TV", 500.0, 5, 2.5);
        Product scratchCard = new DigitalProduct("Fawry Scratch Card", 50.0, 20);
        Product biscuits = new ExpirableProduct("Biscuits", 150.0, 8,
                LocalDate.now().plusDays(30), 0.7);


        Customer customer = new Customer("Abdelrahman Ali", 1000.0);


        System.out.println("=== TEST 1: Successful Checkout ===");
        testSuccessfulCheckout(customer, cheese, tv, scratchCard, biscuits);


        System.out.println("\n=== TEST 2: Empty Cart  ===");
        testEmptyCart(customer);


        System.out.println("\n=== TEST 3: Insufficient Balance  ===");
        testInsufficientBalance(tv);


        System.out.println("\n=== TEST 4: Out of Stock  ===");
        testOutOfStock(cheese);


        System.out.println("\n=== TEST 5: Expired Product  ===");
        testExpiredProduct();
    }

    private static void testSuccessfulCheckout(Customer customer, Product cheese,
                                               Product tv, Product scratchCard, Product biscuits) {
        try {
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
            cart.add(biscuits, 1);

            CheckoutService.checkout(customer, cart);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void testEmptyCart(Customer customer) {
        try {
            Cart emptyCart = new Cart();
            CheckoutService.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void testInsufficientBalance(Product tv) {
        try {
            Customer poorCustomer = new Customer("test Customer", 50.0);
            Cart cart = new Cart();
            cart.add(tv, 1);
            CheckoutService.checkout(poorCustomer, cart);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void testOutOfStock(Product cheese) {
        try {
            Cart cart = new Cart();
            cart.add(cheese, 20);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void testExpiredProduct() {
        try {
            Product expiredMilk = new ExpirableProduct("Expired Milk", 80.0, 5,
                    LocalDate.now().minusDays(1), 0.3);
            Cart cart = new Cart();
            cart.add(expiredMilk, 1);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
   }