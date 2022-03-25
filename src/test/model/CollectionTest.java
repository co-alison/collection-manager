package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest {
    Collection collection;
    Item geoffrey;
    Item leota;

    @BeforeEach
    void setup() {
        collection = new Collection("Funko Pop!");
        geoffrey = new Item("Geoffrey as Robin", 144, "NA", "Toys R Us","2021 01 01", "Ad Icons");
        leota = new Item("Madame Leota", 575, "Glows In The Dark", "Disney Parks", "2019 01 01", "Haunted Mansion");
    }

    @Test
    public void constructorTest() {
        assertEquals("Funko Pop!", collection.getName());
        assertEquals(0, collection.getItems().size());
    }

    @Test
    public void addItemTest() {
        collection.addItem(geoffrey);
        assertEquals(1, collection.getItems().size());
        assertEquals(geoffrey, collection.getItems().get(0));

        collection.addItem(leota);
        assertEquals(2, collection.getItems().size());
        assertEquals(geoffrey, collection.getItems().get(0));
        assertEquals(leota, collection.getItems().get(1));
    }

    @Test
    public void removeItemTest() {
        collection.addItem(geoffrey);
        collection.addItem(leota);

        collection.removeItem(geoffrey);
        assertEquals(1, collection.getItems().size());
        assertEquals(leota, collection.getItems().get(0));

        collection.removeItem(leota);
        assertEquals(0, collection.getItems().size());
    }

    @Test
    public void calculateTotalValueTest() {
        List<Double> geoffreyPrices = new ArrayList<>(Arrays.asList(22.99, 35.00, 16.99, 34.99));
        geoffrey.calculateAverageMarketPrice(geoffreyPrices);
        Double geoffreyAverage = (22.99 + 35.00 + 16.99 + 34.99) / 4;

        List<Double> leotaPrices = new ArrayList<>(Arrays.asList(63.40, 114.12, 63.34, 58.32));
        leota.calculateAverageMarketPrice(leotaPrices);
        Double leotaAverage = (63.40 + 114.12 + 63.34 + 58.32) / 4;

        collection.addItem(geoffrey);
        collection.addItem(leota);

        assertEquals(collection.calculateTotalValue(), Math.round((geoffreyAverage + leotaAverage)* 100.0) / 100.0);
    }

    @Test
    public void toStringTest() {
        assertEquals(collection.toString(),"Funko Pop!");
    }

    @Test
    public void equalsTest() {
        Collection collection1 = new Collection("false");
        Collection collection2 = new Collection("Funko Pop!");
        assertTrue(collection.equals(collection2));
        assertFalse(collection.equals(collection1));
        assertFalse(collection.equals(geoffrey));
    }
}
