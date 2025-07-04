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

## How to Run
1. Clone the repository.
```bash
git clone https://github.com/init0x1/fawry-internship-challenge
```
2. Open the project in IntelliJ IDEA or any Java IDE.
3. Run the `Main` class.