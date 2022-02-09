package model;

import java.util.ArrayList;
import java.util.List;

// represents a collection of Items
public class Collection {

    private List<Item> items;
    private String name;

    // EFFECTS: creates a new collection with a name, and no items
    public Collection(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds item to collection
    public void addItem(Item item) {
        items.add(item);
    }

    // MODIFIES: this
    // EFFECTS: removes item from collection
    public void removeItem(Item item) {
        items.remove(item);
    }

    // REQUIRES: at least one item in collection, each item has a current market price
    // EFFECTS: returns the sum of current market prices of each item in collection
    public Double calculateTotalValue() {
        Double totalValue = 0.0;
        for (Item i : items) {
            totalValue += i.getCurrentMarketPrice();
        }
        totalValue = Math.round(totalValue * 100.0) / 100.0;
        return totalValue;
    }

    // getters

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }
}
