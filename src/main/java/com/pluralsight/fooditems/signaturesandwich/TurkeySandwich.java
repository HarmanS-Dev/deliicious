package com.pluralsight.fooditems.signaturesandwich;

import com.pluralsight.fooditems.Sandwich;

public class TurkeySandwich extends Sandwich {
    private static final String defaultSize = "8\"";
    private static final String defaultBread = "White";
    private static final boolean isToasted= true;

    public TurkeySandwich() {
        // Call the parent Sandwich constructor
        super(defaultSize, defaultBread, isToasted);

        addMeat("Turkey", false);

        // Cheese: Cheddar (Premium)
        addCheese("Swiss", false);

        // Regular Toppings
        addRegularTopping("Lettuce");
        addRegularTopping("Tomato");

        // Sauces
        addRegularTopping("Mayo");
    }

    @Override
    public String getDescription() {
        // Override to show the signature name clearly on the receipt
        return "SIGNATURE THE BASIC T - " + super.getDescription();
    }
}
