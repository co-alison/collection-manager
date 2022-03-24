package ui.listeners;

import ui.CollectionApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItemListener extends CollectionApp implements ActionListener {

    public DeleteItemListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = itemList.getSelectedIndex();
        itemModel.remove(index);
    }
}
