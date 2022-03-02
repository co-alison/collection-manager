package persistence;

import model.Collection;
import model.Item;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoCollection() {
        JsonReader reader = new JsonReader("./data/testReaderNoCollection.json");
        try {
            User user = reader.read();
            assertEquals("Alison", user.getName());
            assertEquals(0, user.getCollections().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNoItems() {
        JsonReader reader = new JsonReader("./data/testReaderNoItems.json");
        try {
            User user = reader.read();
            assertEquals("Alison", user.getName());
            List<Collection> collections = user.getCollections();
            assertEquals(2, collections.size());
            Collection collection1 = collections.get(0);
            Collection collection2 = collections.get(1);
            checkCollection("Funko Pop!", collection1.getItems(), collection1);
            checkCollection("Hot Wheels", collection2.getItems(), collection2);
            assertEquals(0, collection1.getItems().size());
            assertEquals(0, collection2.getItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("Alison", user.getName());
            List<Collection> collections = user.getCollections();
            assertEquals(1, collections.size());
            Collection collection = collections.get(0);
            checkCollection("Funko Pop!", collection.getItems(), collection);
            List<Item> items = collection.getItems();
            Item item1 = items.get(0);
            Item item2 = items.get(1);
            checkItem("Alien", 525, "New", "Glitter", "BoxLunch", "2020 01 01", "Toy Story", 12.99, item1);
            checkItem("Geoffrey as Robin", 144, "Good", "NA", "Toys R Us", "2021 01 01", "Ad Icons", 15.99, item2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
