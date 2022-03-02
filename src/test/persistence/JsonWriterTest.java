package persistence;

import model.Collection;
import model.Item;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("Alison");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void testWriterNoCollection() {
        try {
            User user = new User("Alison");
            JsonWriter writer = new JsonWriter("./data/testWriterNoCollection.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNoCollection.json");
            user = reader.read();
            assertEquals("Alison", user.getName());
            assertEquals(0, user.getCollections().size());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("Alison");
            Collection collection = new Collection("Funko Pop!");
            user.addCollection(collection);
            Item item1 = new Item("Alien", 525, "Glitter", "BoxLunch", "2020 01 01", "Toy Story");
            Item item2 = new Item("Geoffrey as Robin", 144, "NA", "Toys R Us", "2021 01 01", "Ad Icons");
            collection.addItem(item1);
            collection.addItem(item2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.read();
            assertEquals("Alison", user.getName());
            List<Collection> collections = user.getCollections();
            assertEquals(collections.size(), 1);
            Collection funko = collections.get(0);
            checkCollection("Funko Pop!", funko.getItems(), funko);
            List<Item> items = funko.getItems();
            checkItem("Alien", 525, "New", "Glitter", "BoxLunch", "2020 01 01", "Toy Story", 12.99, items.get(0));
            checkItem("Geoffrey as Robin", 144, "New", "NA", "Toys R Us", "2021 01 01", "Ad Icons", 12.99, items.get(1));

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}
