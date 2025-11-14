package com.pluralsight.fooditems.sandwichitems;

public class PremiumTopping implements Topping {
    protected String name;
    protected boolean extra;
    protected boolean isMeat; // True for meat, False for cheese

    public PremiumTopping(String name, boolean extra, boolean isMeat) {
        this.name = name;
        this.extra = extra;
        this.isMeat = isMeat;
    }

    @Override
    public String getName() {
        return name + (extra ? " (Extra)" : "");
    }

    @Override
    public boolean isExtra() {
        return extra;
    }

    @Override
    public double getPrice(String size) {
        if (isMeat) {
            if (extra) {
                return switch (size) {
                    case "4\"" -> 0.50;
                    case "8\"" -> 1.00;
                    case "12\"" -> 1.50;
                    default -> 0.0;
                };
            } else {
                return  switch (size) {
                    case "4\"" -> 1.00;
                    case "8\"" -> 2.00;
                    case "12\"" -> 3.00;
                    default -> 0.0;
                };
                }
        } else {
            if (extra) {
                return switch (size) {
                    case "4\"" -> 0.30;
                    case "8\"" -> 0.60;
                    case "12\"" -> 0.90;
                    default -> 0.0;
                };
            } else {
                return switch (size) {
                    case "4\"" -> 0.75;
                    case "8\"" -> 1.50;
                    case "12\"" -> 2.25;
                    default -> 0.0;
                };
            }
        }
    }
}

