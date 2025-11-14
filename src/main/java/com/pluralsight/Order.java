package com.pluralsight;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalCost() {
        double total = 0.0;
        for (OrderItem item :items) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean hasSandwiches() {
        for (OrderItem item : items) {
            if (item instanceof Sandwich) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDrinksOrChips() {
        for (OrderItem item : items) {
            if (item instanceof Drink || item instanceof Chips) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidOrder() {
        if (!hasSandwiches()) {
            return hasDrinksOrChips();
        }
        return true;
    }

}
