package com.pluralsight.fooditems;

import com.pluralsight.*;
import com.pluralsight.fooditems.sandwichitems.Cheese;
import com.pluralsight.fooditems.sandwichitems.Meat;
import com.pluralsight.fooditems.sandwichitems.RegularTopping;
import com.pluralsight.fooditems.sandwichitems.Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private String size;
    private String breadType;
    private boolean isToasted;
    private List<Topping> toppings;

    public Sandwich (String size, String breadType, boolean isToasted) {
        this.size = size;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }

    // Method for adding toppings
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void addRegularTopping(String toppingName) {
        // Regular toppings are included (zero additional cost)
        RegularTopping regular = new RegularTopping(toppingName);
        addTopping(regular);
    }

    public void addMeat(String meatName, boolean isExtra) {
        Meat meat = new Meat(meatName, isExtra);
        addTopping(meat);
    }

    public void addCheese(String cheeseName, boolean isExtra) {
        Cheese cheese = new Cheese(cheeseName, isExtra);
        addTopping(cheese);
    }

    public void addSide(String sideName) {
        // Sides are included (RegularTopping)
        RegularTopping side = new RegularTopping(sideName);
        addTopping(side);
    }

    // Price Calculation
    @Override
    public  double getPrice() {
        double basePrice = getBaseBreadPrice(size);
        double toppingCost = 0.0;

        // Sum up the cost of all toppings based on size
        for (Topping topping : toppings) {
            toppingCost += topping.getPrice(size);
        }
        return basePrice + toppingCost;
    }

    private double getBaseBreadPrice(String size) {
        // Pricing for all bread types
        return switch (size) {
            case "4\"" -> 5.50;
            case "8\"" -> 7.00;
            case "12\"" -> 8.50;
            default -> 0.0;
        };
    }

    @Override
    public String getDescription() {
        // 1. Line for Base Sandwich Price
        StringBuilder sb = new StringBuilder();
        double basePrice = getBaseBreadPrice(size);

        // Format: [Size] [Bread] (Toasted) ......... [Base Price]
        sb.append(String.format("%-28s %10.2f",
                size + " " + breadType + " Sandwich" + (isToasted ? " (Toasted)" : ""),
                basePrice));

        // 2. Lines for Topping Costs
        for (Topping t : toppings) {
            double cost = t.getPrice(size);
            if (cost > 0.001 || !t.isExtra()) { // Show all toppings for clear receipt
                // If it's a premium item, show the cost
                String toppingName = "  - " + t.getName();

                // Align topping cost on a new line
                sb.append("\n");
                sb.append(String.format("%-28s %10.2f", toppingName, cost));
            } else {
                // For included toppings, simply list them without a price column
                String toppingName = "  - " + t.getName();
                sb.append("\n");
                sb.append(String.format("%-28s", toppingName));
            }
        }
        return sb.toString();
    }

}
