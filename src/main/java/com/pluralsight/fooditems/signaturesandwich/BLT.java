package com.pluralsight.fooditems.signaturesandwich;

import com.pluralsight.fooditems.Sandwich;

public class BLT extends Sandwich {
    private static final String defaultSize = "8\"";
    private static final String defaultBread = "White";
    private static final boolean isToasted= true;

    public BLT() {
        // Call the parent Sandwich constructor
        super(defaultSize, defaultBread, isToasted);

        addMeat("Bacon", false);

        // Cheese: Cheddar (Premium)
        addCheese("Cheddar", false);

        // Regular Toppings
        addRegularTopping("Lettuce");
        addRegularTopping("Tomato");

        // Sauces
        addRegularTopping("Ranch");
    }

    @Override
    public String getDescription() {
        // Override to show the signature name clearly on the receipt
        return "SIGNATURE BLT - " + super.getDescription();
    }
}
