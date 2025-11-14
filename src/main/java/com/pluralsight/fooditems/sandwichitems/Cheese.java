package com.pluralsight.fooditems.sandwichitems;

public class Cheese extends PremiumTopping {
    public Cheese(String name, boolean extra) {
        super(name, extra, false); // false for isMeat
    }
}
