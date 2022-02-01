package model;

import java.util.List;

// represents a list of items
public class Collection {

    // EFFECTS: creates a new collection with a name, and no items
    public Collection(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds item to collection
    public void addItem(Item item, Collection collection) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes item from collection
    public void removeItem(Item item, Collection collection) {
        // stub
    }

    // EFFECTS: prints list of collections
    public void viewCollection(Collection collection) {
        // stub
    }

    // REQUIRES: searchBy is a field in Item, cannot search comments
    // EFFECTS: prints list of item that match searchBy
    public List<Item> searchCollection(Collection collection, String searchBy) {
        return null; // stub
    }
}
