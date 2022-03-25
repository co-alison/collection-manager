package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item funkoPop;
    private String defaultCondition = "New";

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
        assertEquals(defaultCondition, funkoPop.getCondition());
        funkoPop.setCategory("Toy Story");
        assertEquals("Toy Story", funkoPop.getCategory());
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
        assertEquals(funkoPop.getCondition(), defaultCondition);
    }

    @Test
    public void toStringTest() {
        assertEquals(funkoPop.toString(), "Alien");
    }
}
