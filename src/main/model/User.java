package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a user that has a name and a list of collections
public class User implements Writable {

    private List<Collection> collections;
    private String name;

    // EFFECTS: constructs a user with an empty list of collections, and a name
    public User(String name) {
        this.name = name;
        this.collections = new ArrayList<>();
    }

    public User() {
        this.collections = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given collection to list of collections
    public void addCollection(Collection c) {
        collections.add(c);
    }

    public void removeCollection(int index) {
        EventLog.getInstance().logEvent(new Event("Deleted collection " + collections.get(index).getName()));
        collections.remove(index);
    }

    // getters

    public List<Collection> getCollections() {
        return collections;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("collections", collectionsToJson());
        return json;
    }

    // EFFECTS: returns user's collection list as a JSON array
    private JSONArray collectionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Collection c : collections) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
