package com.pluralsight.fooditems.sandwichitems;

public interface Topping {
    double getPrice(String sandwichSize);
    String getName();
    boolean isExtra();
}
