package com.pluralsight;

import com.pluralsight.fooditems.*;
import com.pluralsight.fooditems.signaturesandwich.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeliApplication {
    private static Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

    public static void main(String[] args) {
        new DeliApplication().run();
    }

    public void run() {
        System.out.println("=========================================");
        System.out.println("| WELCOME TO THE DELI-cious POS SYSTEM! |");
        System.out.println("=========================================");
        homeScreen();
    }

    // --- HOME SCREEN  ---
    private void homeScreen() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- HOME SCREEN ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Please select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        currentOrder = new Order();
                        orderScreen();
                        break;
                    case 0:
                        System.out.println("Thank you for using DELI-cious POS. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    // --- ORDER SCREEN  ---
    private void orderScreen() {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- CURRENT ORDER ---");
            displayOrderSummary();

            System.out.println("\n--- ORDER OPTIONS ---");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Signature Sandwich");
            System.out.println("3) Add Drink");
            System.out.println("4) Add Chips");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order (Return to Home)");
            System.out.print("Please select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: addSandwichScreen(); break;
                    case 2: addSignatureSandwichScreen(); break;
                    case 3: addDrinkScreen(); break;
                    case 4: addChipsScreen(); break;
                    case 5:
                        checkoutScreen();
                        ordering = false; // Exit order loop after checkout/cancel
                        break;
                    case 0: 
                        // Cancel Order - delete the order and go back to the home page
                        System.out.println("\n❌ Order cancelled and deleted.");
                        currentOrder = null;
                        ordering = false;
                        break;
                    default: System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void displayOrderSummary() {
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("Your order is currently empty.");
        } else {
            System.out.println("Items:");
            for (OrderItem item : currentOrder.getItems()) {
                System.out.println("  - " + item.getDescription().split("\n")[0]); // Just display the main line
            }
            System.out.printf("Current Total: $%.2f\n", currentOrder.getTotalCost());
        }
    }

    private void addSignatureSandwichScreen() {
        System.out.println("\n--- SELECT SIGNATURE SANDWICH ---");
        System.out.println("1) BLT (8\" White, Bacon, Cheddar, Lettuce, Tomato, Ranch, Toasted)");
        System.out.println("2) Philly Cheese Steak (8\" White, Steak, American, Peppers, Mayo, Toasted)");
        System.out.println("3) The Basic T (8\" White, Turkey, Swiss, Lettuce, Tomato, Mayo, Toasted");
        System.out.println("0) Back to Order Screen");
        System.out.print("Enter choice: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Sandwich signatureSandwich = null;

            switch (choice) {
                case 1:
                    signatureSandwich = new BLT();
                    System.out.println("BLT selected.");
                    break;
                case 2:
                    signatureSandwich = new PhillyCheesesteak();
                    System.out.println("Philly Cheese Steak selected.");
                    break;
                case 3:
                    signatureSandwich = new TurkeySandwich();
                    System.out.println("The Basic T selected.");
                    break;
                case 0: return; // Go back
                default:
                    System.out.println("Invalid choice. Returning to menu.");
                    return;
            }

            // --- Customization Step ---
            System.out.print("\nWould you like to customize this signature sandwich? (y/n): ");
            if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {

                addPremiumToppings(signatureSandwich, "Extra Meat", new String[]{"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"});
                addPremiumToppings(signatureSandwich, "Extra Cheese", new String[]{"American", "Provolone", "Cheddar", "Swiss"});
                addRegularToppings(signatureSandwich, "Other Toppings", new String[]{"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"});
            }

            currentOrder.addItem(signatureSandwich);
            System.out.println("\n✅ Signature Sandwich added to order!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    // --- ADD SANDWICH SCREEN ---
    private void addSandwichScreen() {
        System.out.println("\n--- CUSTOMIZE SANDWICH ---");

        // 1. Select Bread
        System.out.println("Select your bread:");
        System.out.println("1) White  2) Wheat  3) Rye  4) Wrap");
        String breadType = getValidatedStringInput("Enter bread choice (1-4): ", new String[]{"White", "Wheat", "Rye", "Wrap"});

        // 2. Select Size
        System.out.println("Select sandwich size:");
        System.out.println("1) 4\"  2) 8\"  3) 12\"");
        String size = getValidatedStringInput("Enter size choice (1-3): ", new String[]{"4\"", "8\"", "12\""});

        Sandwich sandwich = new Sandwich(size, breadType, false);

        // 3. Toppings (Meat, Cheese, Other)
        addPremiumToppings(sandwich, "Meat", new String[]{"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"});
        addPremiumToppings(sandwich, "Cheese", new String[]{"American", "Provolone", "Cheddar", "Swiss"});
        addRegularToppings(sandwich, "Other Toppings", new String[]{"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"});

        // 4. Sauces
        addSauces(sandwich, new String[]{"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"});
        addSides(sandwich, new String[]{"Au Jus", "Sauce"});

        // 5. Toasted
        System.out.print("Would you like the sandwich toasted? (y/n): ");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            // Note: isToasted is handled within the Sandwich object logic
        }

        currentOrder.addItem(sandwich);
        System.out.println("\n✅ Sandwich added to order!");
    }

    private void addPremiumToppings(Sandwich sandwich, String type, String[] options) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--- Add " + type + " ---");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d) %s\n", i + 1, options[i]);
            }
            System.out.println("0) Done with " + type);
            System.out.print("Select " + type + " (1-" + options.length + ", or 0 to finish): ");

            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);

                if (choice == 0) {
                    done = true;
                } else if (choice > 0 && choice <= options.length) {
                    String selectedTopping = options[choice - 1];
                    System.out.print("Add extra " + selectedTopping + "? (y/n): ");
                    boolean isExtra = scanner.nextLine().trim().toLowerCase().startsWith("y");

                    if (type.equals("Meat")) {
                        sandwich.addMeat(selectedTopping, isExtra);
                    } else if (type.equals("Cheese")) {
                        sandwich.addCheese(selectedTopping, isExtra);
                    }
                    System.out.println("Added " + (isExtra ? "Extra " : "") + selectedTopping + ".");
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void addRegularToppings(Sandwich sandwich, String type, String[] options) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--- Add " + type + " (Included) ---");// Regular toppings are included
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d) %s\n", i + 1, options[i]);
            }
            System.out.println("0) Done with " + type);
            System.out.print("Select " + type + " (1-" + options.length + ", or 0 to finish): ");

            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);

                if (choice == 0) {
                    done = true;
                } else if (choice > 0 && choice <= options.length) {
                    sandwich.addRegularTopping(options[choice - 1]);
                    System.out.println("Added " + options[choice - 1] + ".");
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void addSauces(Sandwich sandwich, String[] options) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--- Select Sauces (Included) ---");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d) %s\n", i + 1, options[i]);
            }
            System.out.println("0) Done with Sauces");
            System.out.print("Select Sauce (1-" + options.length + ", or 0 to finish): ");

            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);

                if (choice == 0) {
                    done = true;
                } else if (choice > 0 && choice <= options.length) {
                    sandwich.addRegularTopping(options[choice - 1]);
                    System.out.println("Added " + options[choice - 1] + ".");
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void addSides(Sandwich sandwich, String[] options) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--- Select Sides (Included) ---");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d) %s\n", i + 1, options[i]);
            }
            System.out.println("0) Done with Sides");
            System.out.print("Select Side (1-" + options.length + ", or 0 to finish): ");

            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);

                if (choice == 0) {
                    done = true;
                } else if (choice > 0 && choice <= options.length) {
                    sandwich.addRegularTopping(options[choice - 1]);
                    System.out.println("Added " + options[choice - 1] + ".");
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Helper for input validation
    private String getValidatedStringInput(String prompt, String[] options) {
        String input;
        int choice = -1;
        while (choice < 1 || choice > options.length) {
            System.out.print(prompt);
            try {
                input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                } else {
                    System.out.println("Invalid selection. Please choose a number between 1 and " + options.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return ""; // Should be unreachable
    }

    // --- ADD DRINK SCREEN ---
    private void addDrinkScreen() {
        System.out.println("\n--- ADD DRINK ---");

        // Size
        System.out.println("Select drink size:");
        System.out.println("1) Small ($2.00)  2) Medium ($2.50)  3) Large ($3.00)");
        String size = getValidatedStringInput("Enter size choice (1-3): ", new String[]{"Small", "Medium", "Large"});

        // Flavor
        System.out.print("Enter drink flavor (e.g., Coke, Tea): ");
        String flavor = scanner.nextLine().trim();

        currentOrder.addItem(new Drink(size, flavor));
        System.out.println("✅ Drink added to order!");
    }

    // --- ADD CHIPS SCREEN ---
    private void addChipsScreen() {
        System.out.println("\n--- ADD CHIPS ---");

        System.out.print("Enter chip type (e.g., BBQ, Salt & Vinegar) ($1.50): ");
        String chipType = scanner.nextLine().trim();

        currentOrder.addItem(new Chips(chipType));
        System.out.println("✅ Chips added to order!");
    }

    // --- CHECKOUT SCREEN ---
    private void checkoutScreen() {
        System.out.println("\n=========================================");
        System.out.println("           --- CHECKOUT ---");
        System.out.println("=========================================");
        
        // Display order details
        for (OrderItem item : currentOrder.getItems()) {
            System.out.println("\n" + item.getDescription());
        }

        System.out.println("\n-----------------------------------------");
        // Display total cost
        System.out.printf("TOTAL ORDER COST: $%.2f\n", currentOrder.getTotalCost());
        System.out.println("-----------------------------------------");
        
        // Validation check
        if (!currentOrder.isValidOrder()) {
            System.out.println("\n⚠️ WARNING: Order must contain at least one sandwich OR a drink/chips.");
            System.out.println("Please go back and add an item, or cancel.");
            // Force user to go back
        }

        System.out.println("\n1) Confirm Order (Save Receipt & Return to Home)");
        System.out.println("0) Cancel Order (Return to Home)");
        System.out.print("Enter choice (1 or 0): ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1 && currentOrder.isValidOrder()) {
               // Confirm - create the receipt file and go back to the home screen
                ReceiptFileManager.saveReceipt(currentOrder);
            } else {
               // Cancel - delete order and go back to the home screen
                System.out.println("\n❌ Order cancelled and deleted.");
            }
            currentOrder = null; // Clear order regardless of confirm/cancel (since we go back home)
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Returning to Home Screen.");
            scanner.nextLine();
            currentOrder = null;
        }
    }
}