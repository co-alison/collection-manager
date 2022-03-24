package ui.listeners;

import model.Item;
import ui.CollectionApp;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemListener extends CollectionApp implements ListSelectionListener {

    public ItemListener() {
        //
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        if (itemList.isSelectionEmpty()) {
            label.setText("Click on an item in the collection.");
        } else {
            Item i = itemList.getSelectedValue();
            String summary = "<html>\n";
            summary += "<br><b>Name</b>: " + i.getName() + "</br> \n"
                    + "<br><b>Item Number</b>: " + i.getItemNumber() + "</br> \n"
                    + "<br><b>Edition</b>: " + i.getEdition() + "</br> \n"
                    + "<br><b>Exclusive</b>: " + i.getExclusive() + "</br> \n"
                    + "<br><b>Release Date</b>: " + i.getReleaseDate() + "</br> \n"
                    + "<br><b>Current Market Price</b>: $" + i.getCurrentMarketPrice()
                    + " (Default price: 12.99)</br> \n"
                    + "<br><b>Condition</b>: " + i.getCondition() + " (Default condition: New)</br> \n"
                    + "<br><b>Category</b>: " + i.getCategory() + "</br> \n";
            label.setText(summary);
        }
    }
}