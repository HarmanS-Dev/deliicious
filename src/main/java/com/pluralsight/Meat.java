package com.pluralsight;

public class Meat extends PremiumTopping {
    public Meat(String name, boolean extra) {
        super(name, extra, true); // true for isMeat
    }
}
