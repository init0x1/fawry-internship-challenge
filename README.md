# Fawry Quantum Internship Challenge 3

The system supports different types of products (physical, digital, expirable), a shopping cart, customer balance management, and shipping fee calculation.

## Features

- **Product Types:**  
  - Physical products (with weight)
  - Digital products (no shipping)
  - Expirable products (with expiration date and weight)

- **Cart & Checkout:**  
  - Add products to cart with quantity
  - Validate stock and expiration
  - Calculate subtotal and shipping fees
  - Deduct customer balance
  - Print detailed receipt

- **Shipping:**  
  - Shipping fee is calculated per kilogram for shippable items

- **Error Handling:**  
  - Handles empty cart, insufficient balance, out-of-stock, and expired products

## Project Structure

```
src/
  main/
    java/
      org/
        example/
          Main.java
          interfaces/
            Shippable.java
          models/
            CartItem.java
            Customer.java
            products/
              Product.java
              PhysicalProduct.java
              DigitalProduct.java
              ExpirableProduct.java
          services/
            Cart.java
            CheckoutService.java
            ShippingService.java
```
