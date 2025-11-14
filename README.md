# DELI-cious POS System

A console-based Point of Sale (POS) system for a deli, allowing users to create custom orders for sandwiches, drinks, and chips, and generating a formatted receipt upon checkout.

-----

## 🚀 Features

  * **Custom Sandwich Builder**
      * Select **bread type** (White, Wheat, Rye, Wrap) and **size** (4", 8", 12").
      * Add **Premium Toppings** (Meat/Cheese) with an option for **extra** portions, which affects the price.
      * Add **Regular Toppings**, **Sauces**, and **Sides** (e.g., Au Jus) at no extra cost.
      * Option to have the sandwich **toasted**.
  * **Signature Sandwiches**
      * Pre-configured options include **BLT**, **Philly Cheesesteak**, and **The Basic T (Turkey)**.
      * Signature sandwiches can be further customized with additional toppings.
  * **Other Items**
      * Add **Drinks** (Small, Medium, Large sizes with corresponding prices).
      * Add **Chips** ($1.50 flat rate).
  * **Receipt Generation**
      * Upon checkout, a detailed text receipt is saved to the `src/main/resources/receipts/` folder.
      * Receipt files are uniquely named using a timestamp format: `yyyyMMdd-HHmmss.txt`.
  * **Order Validation**
      * Ensures an order is valid by checking that it contains at least one sandwich **OR** a drink/chips before allowing confirmation.

-----

## 💻 Technologies

| Technology | Version/Detail | Purpose |
| :--- | :--- | :--- |
| **Java** | JDK 17 | Core programming language. |
| **Apache Maven** | 4.0.0 | Project build management. |
| **`java.io`** | Standard Library | File I/O for receipt generation. |

-----

## 📂 File Structure

The project follows a standard Maven directory layout, with the core application logic structured into various classes and packages to manage the different menu items and application flow.

```
.
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── pluralsight/
                    ├── DeliApplication.java     (Main application logic and menus)
                    ├── Order.java               (Manages the list of OrderItems)
                    ├── OrderItem.java           (Interface for all sellable items)
                    ├── ReceiptFileManager.java  (Handles receipt creation and saving)
                    └── fooditems/
                        ├── Chips.java
                        ├── Drink.java
                        ├── Sandwich.java        (Base class for custom and signature sandwiches)
                        ├── sandwichitems/       (Classes for premium and regular sandwich components)
                        └── signaturesandwich/   (Pre-configured signature sandwich classes)
```

-----

## 🎨 Application Screens

The application is navigated via simple numeric console menus. Below are examples of the console output for the main screens.

### Home Screen

The entry point for the POS system.

```
=========================================
| WELCOME TO THE DELI-cious POS SYSTEM! |
=========================================

--- HOME SCREEN ---
1) New Order
0) Exit
Please select an option: 1
```

### Order Screen

Allows adding new items or proceeding to checkout.

```
--- CURRENT ORDER ---
Items:
  - 8" White Sandwich (Toasted)
  - Medium Coke
Current Total: $9.50

--- ORDER OPTIONS ---
1) Add Sandwich
2) Add Signature Sandwich
3) Add Drink
4) Add Chips
5) Checkout
0) Cancel Order (Return to Home)
Please select an option: 5
```

### Checkout Screen

Displays the final detailed total before confirmation.

```
=========================================
           --- CHECKOUT ---
=========================================

8" White Sandwich (Toasted) ......... 7.00
  - Cheddar ......................... 1.50
  - Mayo                             
Medium Coke ......................... 2.50

-----------------------------------------
TOTAL ORDER COST: $11.00
-----------------------------------------

1) Confirm Order (Save Receipt & Return to Home)
0) Cancel Order (Return to Home)
Enter choice (1 or 0): 1

✅ Order completed! Receipt saved to: src/main/resources/receipts/20231114-073000.txt
```

-----

## 💡 Interesting Code

### 1\. Dynamic Pricing using Java Switch Expressions

The pricing logic for simple items like drinks uses a modern **Java 14+ switch expression** (with the arrow `->` syntax) for a clean and concise implementation of price tiers based on the item's size.

**`Drink.java` - Price Calculation:**

```java
@Override
public double getPrice() {
    return switch (size) {
        case "Small" -> 2.00;
        case "Medium" -> 2.50;
        case "Large" -> 3.00;
        default -> 0.0;
    };
}
```
