package ui.listeners;

import model.Collection;
import model.Item;
import ui.CollectionApp;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class CollectionListener extends CollectionApp implements ListSelectionListener {

    public CollectionListener() {
        //
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Collection c = getCollectionList().getSelectedValue();
        List<Item> items = c.getItems();
        getItemModel().clear();

        for (Item i : items) {
            getItemModel().addElement(i);
        }

        getItemList().setModel(getItemModel());
    }
}
