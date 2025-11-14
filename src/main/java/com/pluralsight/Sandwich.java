package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        double totalToppingCost = 0.0;

        List<String> premium = new ArrayList<>();
        List<String> regular = new ArrayList<>();

        for (Topping t : toppings) {
            totalToppingCost += t.getPrice(size);
            if (t instanceof PremiumTopping) {
                premium.add(t.getName() + " ($" + String.format("%.2f", t.getPrice(size)) + ")");
            } else {
                regular.add(t.getName());
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SANDWICH: %s %s Bread ($%.2f Base)", size, breadType, getBaseBreadPrice(size)));
        if (isToasted) {
            sb.append(", Toasted");
        }
        sb.append(String.format(" | Total Price: $%.2f", getPrice()));
        sb.append("\n    - Premium Toppings (Cost: $").append(String.format("%.2f", totalToppingCost)).append("): ")
                .append(premium.isEmpty() ? "None" : String.join(", ", premium));
        sb.append("\n    - Regular Toppings (Included): ")
                .append(regular.isEmpty() ? "None" : String.join(", ", regular));

        return sb.toString();
    }

}
