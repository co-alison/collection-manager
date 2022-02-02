package model;

import java.util.Date;
import java.util.List;

// represents a single item in a collection that has a name, item number,
// release date, current market price,
// edition, condition, and comments (optional)
public class Item {

    // REQUIRES:
    // EFFECTS: name of item is set to name; item number is set to itemNumber;
    //          edition of item is set to edition
    //          set release date to default date
    //          set all other fields to "NA" or empty
    public Item(String name, Integer itemNumber, String edition) {
        //stub
    }

    // REQUIRES: prices has at least one price in list
    // MODIFIES: this
    // EFFECTS: sets currentMarketPrice to the average of prices given
    public Double calculateAverageMarketPrice(List<Double> prices) {
        return 0.0; // stub
    }

    // REQUIRES: item has a current market price and original price
    // EFFECTS: calculates overall price trend from previous prices (percent increase or decrease)
    public Double calculatePriceTrend() {
        return 0.0; // stub
    }

    // REQUIRES: item must have a release date
    // EFFECTS: calculates age as of given date in format: "x Days, y Months, z Years Old"
    public String calculateAge() {
        return null;
    }

    // REQUIRES: comment is not an empty string
    // MODIFIES: this
    // EFFECTS:
    public void addComment(String comment) {
        // stub
    }

    // TODO: add setters to update fields

    public void setName(String name) {
        // stub
    }

    public void setItemNumber(Integer itemNumber) {
        // stub
    }

    // EFFECTS: sets the condition of item to be new, mint condition, good, fair, or poor
    public void setCondition() {
        // stub
    }

    public void setEdition() {
        // stub
    }

    // getters

    public String getName() {
        return null; // stub
    }

    public Integer getItemNumber() {
        return null; // stub
    }

    public Date getReleaseDate() {
        return null; // stub
    }

    public Double getCurrentMarketPrice() {
        return null; // stub
    }

    public String getEdition() {
        return null; // stub
    }

    public String getCondition() {
        return null; // stub
    }

    public List<String> getComments() {
        return null; // stub
    }
}
