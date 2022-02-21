package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a user have a list of collections
public class User implements Writable {

    private List<Collection> collections;

    // constructs a user with an empty list of collections
    public User() {
        this.collections = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given collection to list of collections
    public void addCollection(Collection c) {
        collections.add(c);
    }

    // getters

    public List<Collection> getCollections() {
        return collections;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
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
