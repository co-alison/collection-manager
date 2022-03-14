package ui.listeners;

import model.Collection;
import model.Item;
import ui.CollectionApp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TotalValueListener extends CollectionApp implements ActionListener {
    JSplitPane splitPane;
    JScrollPane scrollPane;
    JLabel valueLabel;

    public TotalValueListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create new window
        JFrame calculateValueFrame = new JFrame("Calculate Value");

        splitPane = new JSplitPane();
        scrollPane = getCollectionScrollPane();

        valueLabel = new JLabel();
        valueLabel.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT / 2));
        valueLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Total Value"),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        getCollectionList().addListSelectionListener(new CalculateValueListener());

        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(valueLabel);
        splitPane.setOneTouchExpandable(true);

        calculateValueFrame.add(splitPane);
        calculateValueFrame.setSize(new Dimension(WIDTH, HEIGHT));
        calculateValueFrame.setLocationRelativeTo(null);
        calculateValueFrame.setVisible(true);
    }

    public class CalculateValueListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (getCollectionList().isSelectionEmpty()) {
                getLabel().setText("<html> \n "
                        + "<br>Select collection to calculate its total value. </br> \n"
                        + "<b>Note: Default value of each item is $12.99</b>");
            } else {
                Collection c = getCollectionList().getSelectedValue();
                String value = "<html> \n "
                        + "<br><b>Total Value</b>: $" + c.calculateTotalValue() + "</br> \n"
                        + "<br><b>Items</b></br>: \n"
                        + "<ul> \n";

                for (Item i : c.getItems()) {
                    value += "<li>" + i.getName() + "</li> \n";
                }

                value += "</ul>";
                valueLabel.setText(value);
            }
        }
    }
}