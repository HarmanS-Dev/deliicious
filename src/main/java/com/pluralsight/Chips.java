package com.pluralsight;

public class Chips implements OrderItem{
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String getDescription() {
        String item = String.format("Chips (%s)", type);
        return String.format("%-28s %10.2f", item, getPrice());
    }
}
