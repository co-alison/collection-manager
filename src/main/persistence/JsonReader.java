package persistence;

import model.Collection;
import model.Item;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User user = new User(name);
        addCollections(user, jsonObject);
        return user;
    }

    private void addCollections(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("collections");
        for (Object json : jsonArray) {
            JSONObject nextCollection = (JSONObject) json;
            addCollection(user, nextCollection);
        }
    }

    private Collection addCollection(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Collection col = new Collection(name);
        user.addCollection(col);
        addItems(col, jsonObject);
        return col;

    }

    // MODIFIES: col
    // EFFECTS: parses items from JSON object and adds them to collection
    private void addItems(Collection col, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(col, nextItem);
        }
    }

    // MODIFIES: col
    // EFFECTS: parses item from JSON object and adds it to collection
    private void addItem(Collection col, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int itemNumber = jsonObject.getInt("item number");
        String edition = jsonObject.getString("edition");
        String exclusive = jsonObject.getString("exclusive");
        String releaseDate = jsonObject.getString("release date");
        Double currentMarketPrice = jsonObject.getDouble("current market price");
        String condition = jsonObject.getString("condition");
        String category = jsonObject.getString("category");
        Item item = new Item(name, itemNumber, edition, exclusive, releaseDate, category);
        item.setCurrentMarketPrice(currentMarketPrice);
        condition = condition.substring(0, 1).toLowerCase(Locale.ROOT);
        item.setCondition(condition);

        col.addItem(item);
    }
}
