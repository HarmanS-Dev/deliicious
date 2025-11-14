package com.pluralsight.fooditems.signaturesandwich;

import com.pluralsight.fooditems.Sandwich;

public class PhillyCheesesteak extends Sandwich {
    private static final String defaultSize = "8\"";
    private static final String defaultBread = "White";
    private static final boolean isToasted = true;

    public PhillyCheesesteak() {
        // Call the parent Sandwich constructor
        super(defaultSize, defaultBread, isToasted);

        // Meat: Steak (Premium)
        addMeat("Steak", false);

        // Cheese: American (Premium)
        addCheese("American", false);

        // Regular Toppings
        addRegularTopping("Peppers");

        // Sauces
        addRegularTopping("Mayo");
    }

    @Override
    public String getDescription() {
        // Override to show the signature name clearly on the receipt
        return "SIGNATURE PHILLY CHEESE STEAK - " + super.getDescription();
    }
}
