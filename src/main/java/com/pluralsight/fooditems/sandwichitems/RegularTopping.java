package com.pluralsight.fooditems.sandwichitems;

public class RegularTopping implements Topping {
    private String name;

    public  RegularTopping(String name) {
        this.name = name;
    }

    @Override
    public double getPrice(String sandwichSize) {
        // Regular toppings/sauces/sides are included
        return 0.0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isExtra() {
        return false;
    }
}
