package persistence;

import model.Collection;
import model.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, int itemNumber, String condition, String edition, String exclusive, String releaseDate, String category, double price, Item item) {
        assertEquals(name, item.getName());
        assertEquals(itemNumber, item.getItemNumber());
        assertEquals(condition, item.getCondition());
        assertEquals(edition, item.getEdition());
        assertEquals(exclusive, item.getExclusive());
        assertEquals(releaseDate, item.getReleaseDate());
        assertEquals(category, item.getCategory());
        assertEquals(price, item.getCurrentMarketPrice());
    }

    protected void checkCollection(String name, List<Item> items, Collection collection) {
        assertEquals(name, collection.getName());
        assertEquals(items, collection.getItems());
    }
}
