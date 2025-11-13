package com.pluralsight;

public interface Topping {
    double getPrice(String sandwichSize);
    String getName();
    boolean isExtra();
}
