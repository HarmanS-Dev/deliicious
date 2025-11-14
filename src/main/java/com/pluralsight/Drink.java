package com.pluralsight;

public class Drink implements OrderItem {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return switch (size) {
            case "Small" -> 2.00;
            case "Medium" -> 2.50;
            case "Large" -> 3.00;
            default -> 0.0;
        };
    }

    @Override
    public String getDescription() {
        return String.format("Drink: %s %s (%.2f)", size, flavor, getPrice());
    }
}
