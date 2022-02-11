package model;

import java.util.*;

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

    private static final Double DEFAULT_PRICE = 12.99;
    private static final String DEFAULT_CONDITION = "New";

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
    // EFFECTS: sets currentMarketPrice to the average of prices given, rounds average to 2 decimal places
    public Double calculateAverageMarketPrice(List<Double> prices) {
        Double sum = 0.0;
        for (Double p : prices) {
            sum += p;
        }
        double average = sum / prices.size();
        average = Math.round(average * 100.0) / 100.0;
        this.currentMarketPrice = average;
        return average;
    }

    // EFFECTS: calculates overall price trend from given price (percent increase or decrease),
    // rounds to 2 decimal places
    public Double calculatePriceTrend(Double originalPrice) {
        double percentChange = ((currentMarketPrice - originalPrice) / originalPrice) * 100;
        percentChange = Math.round(percentChange * 100.0) / 100.0;
        return percentChange;
    }

    // setters

    // REQUIRES: comment is not an empty string
    // MODIFIES: this
    // EFFECTS: adds given comment to comments
    public void addComment(String comment) {
        comments.add(comment);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    // EFFECTS: sets the condition of item to be new, mint condition, good, fair, or poor
    public void setCondition(String c) {
        if (c.equals("n")) {
            condition = "New";
        } else if (c.equals("m")) {
            condition = "Mint Condition";
        } else if (c.equals("g")) {
            condition = "Good";
        } else if (c.equals("f")) {
            condition = "Fair";
        } else if (c.equals("p")) {
            condition = "Poor";
        } else {
            condition = DEFAULT_CONDITION;
        }
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    // REQUIRES: date is in format "yyyy mm dd"
    public void setReleaseDate(String date) {
        this.releaseDate = date;
    }

    public void setCategory(String category) {
        this.category = category;
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

    // EFFECTS: if list of comments is empty, return comments with "NA" as only item, otherwise return list of comments
    public List<String> getComments() {
        List<String> emptyList = new ArrayList<>();
        emptyList.add("NA");
        if (comments.size() == 0) {
            return emptyList;
        } else {
            return comments;
        }
    }
}
