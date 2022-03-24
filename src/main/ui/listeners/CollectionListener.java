package ui.listeners;

import model.Collection;
import model.Item;
import ui.CollectionApp;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class CollectionListener extends CollectionApp implements ListSelectionListener {

    public CollectionListener() {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        if (collectionList.getSelectedValue() != null) {
            Collection c = collectionList.getSelectedValue();
            List<Item> items = c.getItems();
            itemModel.clear();

            for (Item i : items) {
                itemModel.addElement(i);
            }

            itemList.setModel(itemModel);
        }
    }
}
