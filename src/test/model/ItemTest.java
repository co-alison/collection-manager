package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item funkoPop;
    private String defaultCondition = "NA";

    @BeforeEach
    void setup() {
        funkoPop = new Item("Alien", 48241, "Glitter",
                "BoxLunch", "2020 01 01", "Toy Story");
    }

    @Test
    public void constructorTest() {
        funkoPop.setName("Alien");
        assertEquals("Alien", funkoPop.getName());
        funkoPop.setItemNumber(48241);
        assertEquals(48241, funkoPop.getItemNumber());
        funkoPop.setEdition("Glitter");
        assertEquals("Glitter", funkoPop.getEdition());
        funkoPop.setExclusive("BoxLunch");
        assertEquals("BoxLunch", funkoPop.getExclusive());
        funkoPop.setReleaseDate("2020 01 01");
        assertEquals("2020 01 01", funkoPop.getReleaseDate());
        assertEquals(12.99, funkoPop.getCurrentMarketPrice());
        assertEquals("NA", funkoPop.getCondition());
        funkoPop.setCategory("Toy Story");
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
                Math.round(((95.15 + 32.88 + 49.45) / 3) * 100.0) / 100.0);
    }

    @Test
    public void calculatePriceTrendTest() {
        List<Double> prices = new ArrayList<>();
        prices.add(95.15);
        prices.add(32.88);
        prices.add(49.45);
        funkoPop.calculateAverageMarketPrice(prices);
        assertEquals(funkoPop.calculatePriceTrend(15.00),
                ((funkoPop.getCurrentMarketPrice() - 15.00) / 15.00) * 100);
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

    @Test
    public void setConditionTest() {
        funkoPop.setCondition("n");
        assertEquals(funkoPop.getCondition(), "New");

        funkoPop.setCondition("m");
        assertEquals(funkoPop.getCondition(), "Mint Condition");

        funkoPop.setCondition("g");
        assertEquals(funkoPop.getCondition(), "Good");

        funkoPop.setCondition("f");
        assertEquals(funkoPop.getCondition(), "Fair");

        funkoPop.setCondition("p");
        assertEquals(funkoPop.getCondition(), "Poor");

        funkoPop.setCondition("x");
        assertEquals(funkoPop.getCondition(), "NA");
    }


}
