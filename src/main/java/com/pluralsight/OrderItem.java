package com.pluralsight;

public interface OrderItem {
    // Returns price of item
    double getPrice();

    // Returns a string for the receipt/display
    String getDescription();
}
