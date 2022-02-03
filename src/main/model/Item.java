package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// represents a single item in a collection that has a name, item number,
// release date, current market price,
// exclusivity, edition, condition, category, and comments (optional)
public class Item {

    private String name;
    private Integer itemNumber;
    private String edition;
    private String exclusive;
    private String releaseDate;
    private Double currentMarketPrice;
    private String condition;
    private String category;
    private List<String> comments;

    private final Double DEFAULT_PRICE = 12.99;
    private final String DEFAULT_CONDITION = "NA";

    // REQUIRES:
    // EFFECTS: name of item is set to name; item number is set to itemNumber;
    //          edition of item is set to edition; type of exclusive of item is set to exclusive;
    //          release date of item is set to date, yyyy MM dd;
    //          current market price is set to DEFAULT_PRICE;
    //          condition of item is set to DEFAULT_CONDITION;
    //          category of item is set to category;
    //          comments is an empty list;

    public Item(String name, Integer itemNumber, String edition,
                String exclusive, String date, String category) {
        this.name = name;
        this.itemNumber = itemNumber;
        this.edition = edition;
        this.exclusive = exclusive;
        this.releaseDate = date;
        this.currentMarketPrice = DEFAULT_PRICE;
        this.condition = DEFAULT_CONDITION;
        this.category = category;
        this.comments = new ArrayList<>();
    }

    // REQUIRES: prices has at least one price in list
    // MODIFIES: this
    // EFFECTS: sets currentMarketPrice to the average of prices given
    public void calculateAverageMarketPrice(List<Double> prices) {
        Double sum = 0.0;
        for (Double p : prices) {
            sum += p;
        }
        Double average = sum / prices.size();
        this.currentMarketPrice = average;
    }

    // REQUIRES: item has a current market price
    // EFFECTS: calculates overall price trend from given price (percent increase or decrease)
    public Double calculatePriceTrend(Double originalPrice) {
        Double percentChange = ((originalPrice - currentMarketPrice) / originalPrice) * 100;
        return percentChange;
    }

    // REQUIRES: item must have a release date; given date must be in format yyyy MM dd
    // EFFECTS: calculates age as of given date in format: "x Days, y Months, z Years Old"
    public String calculateAge(String currentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate d1 = LocalDate.parse(currentDate, formatter);
        LocalDate d2 = LocalDate.parse(releaseDate, formatter);

        Period period = Period.between(d2, d1);

        int years = Math.abs(period.getYears());
        int months = Math.abs(period.getMonths());
        int days  = Math.abs(period.getDays());

        String age = days + " Days, " + months + " Months, " + years + " Years Old";
        return age;
    }

    // REQUIRES: comment is not an empty string
    // MODIFIES: this
    // EFFECTS:
    public void addComment(String comment) {
        comments.add(comment);
    }

    // TODO: add setters to update fields

    public void setName(String name) {
        this.name = name;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    // TODO:
    // EFFECTS: sets the condition of item to be new, mint condition, good, fair, or poor
    public void setCondition() {
        // stub
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    // REQUIRES: date is in format "yyyy MM dd"
    public void setReleaseDate(String date) {
        this.releaseDate = date;
    }

    // getters

    public String getName() {
        return name; // stub
    }

    public Integer getItemNumber() {
        return itemNumber; // stub
    }

    public String getReleaseDate() {
        return releaseDate; // stub
    }

    public Double getCurrentMarketPrice() {
        return currentMarketPrice; // stub
    }

    public String getEdition() {
        return edition; // stub
    }

    public String getExclusive() {
        return exclusive; // stub
    }

    public String getCondition() {
        return condition; // stub
    }

    public String getCategory() {
        return category;
    }

    public List<String> getComments() {
        List<String> emptyList = new ArrayList<>(Arrays.asList("NA"));
        if (comments.size() == 0) {
            return emptyList;
        } else {
            return comments;
        }
    }
}
