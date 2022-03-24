package ui.listeners;

import ui.CollectionApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCollectionListener extends CollectionApp implements ActionListener {

    public DeleteCollectionListener() {
        //
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = collectionList.getSelectedIndex();
        collectionModel.remove(index);

        int size = collectionModel.getSize();

        if (size == 0) {
            deleteCollection.setEnabled(false);
            deleteItem.setEnabled(false);
        } else {
            if (index == collectionModel.getSize()) {
                index--;
            }

            collectionList.setSelectedIndex(index);
            collectionList.ensureIndexIsVisible(index);
        }
    }
}
