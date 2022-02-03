package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item funkoPop;

    @BeforeEach
    void setup() {
        funkoPop = new Item("Alien", 48241, "Glitter",
                "BoxLunch", "2020 01 01", "Toy Story");
    }

    @Test
    public void constructorTest() {
        assertEquals("Alien", funkoPop.getName());
        assertEquals(48241, funkoPop.getItemNumber());
        assertEquals("Glitter", funkoPop.getEdition());
        assertEquals("BoxLunch", funkoPop.getExclusive());
        assertEquals("2020 01 01", funkoPop.getReleaseDate());
        assertEquals(12.99, funkoPop.getCurrentMarketPrice());
        assertEquals("NA", funkoPop.getCondition());
        assertEquals("Toy Story", funkoPop.getCategory());
        assertEquals("NA", funkoPop.getComments().get(0));
    }

    @Test
    public void calculateAverageMarketPriceTest() {
        List<Double> prices = new ArrayList<>();
        prices.add(95.15);
        prices.add(32.88);
        prices.add(49.45);
        funkoPop.calculateAverageMarketPrice(prices);
        assertEquals(funkoPop.getCurrentMarketPrice(),
                (95.15 + 32.88 + 49.45) / 3);
    }

    @Test
    public void calculatePriceTrendTest() {
        List<Double> prices = new ArrayList<>();
        prices.add(95.15);
        prices.add(32.88);
        prices.add(49.45);
        funkoPop.calculateAverageMarketPrice(prices);
        assertEquals(funkoPop.calculatePriceTrend(15.00),
                ((15.00 - funkoPop.getCurrentMarketPrice()) / 15.00) * 100);
    }

    @Test
    public void calculateAgeTest() {
        funkoPop.setReleaseDate("2020 01 01");
        assertEquals("2 Days, 1 Months, 2 Years Old", funkoPop.calculateAge("2022 02 03"));
    }

    @Test
    public void addCommentTest() {
        String comment1 = "Box has small dent.";
        funkoPop.addComment(comment1);
        assertEquals(1, funkoPop.getComments().size());
        assertEquals(comment1, funkoPop.getComments().get(0));

        String comment2 = "$24 CAD listed price on Funko Collection.";
        funkoPop.addComment(comment2);
        assertEquals(2, funkoPop.getComments().size());
        assertEquals(comment2, funkoPop.getComments().get(1));
    }


}
