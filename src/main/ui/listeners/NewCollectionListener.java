package ui.listeners;

import model.Collection;
import ui.CollectionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCollectionListener extends CollectionApp implements ActionListener {
    private Container container;
    private JLabel name;
    private JTextField nameTextField;
    private JButton submit;
    private JLabel log;
    private JLabel currentCollections;

    public NewCollectionListener() {
        //
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create new window
        JFrame newCollectionFrame = new JFrame("New Collection");

        container = newCollectionFrame.getContentPane();
        container.setLayout(null);

        createNameLabel();
        createNameTextField();
        createSubmitButton();
        createLogLabel();
        createCurrentCollectionsLabel();

        newCollectionFrame.setSize(new Dimension(WIDTH, HEIGHT));
        newCollectionFrame.setLocationRelativeTo(null);
        newCollectionFrame.setVisible(true);
    }

    private void createCurrentCollectionsLabel() {
        String text = getCurrentCollections();
        currentCollections = new JLabel(text);
        currentCollections.setSize(200, HEIGHT / 2);
        currentCollections.setLocation(WIDTH / 2 + 50, 100);
        currentCollections.setBorder(BorderFactory.createTitledBorder("Collections"));
        container.add(currentCollections);
    }

    private void createLogLabel() {
        log = new JLabel("");
        log.setSize(WIDTH / 2,25);
        log.setLocation(50,200);
        container.add(log);
    }

    private void createSubmitButton() {
        submit = new JButton("Create");
        submit.setSize(100,20);
        submit.setLocation(110,150);
        submit.setBorder(BorderFactory.createLineBorder(Color.black));
        submit.addActionListener(new SubmitListener());
        container.add(submit);
    }

    private void createNameTextField() {
        nameTextField = new JTextField();
        nameTextField.setSize(190,20);
        nameTextField.setLocation(100,100);
        container.add(nameTextField);
    }

    private void createNameLabel() {
        name = new JLabel("Name");
        name.setSize(50, 20);
        name.setLocation(50,100);
        container.add(name);
    }

    private String getCurrentCollections() {
        String text = "<html> \n"
                + "<ul> \n";

        for (int i = 0; i < collectionModel.getSize(); i++) {
            Collection c = collectionModel.getElementAt(i);
            text += "<li>" + c.getName() + "</li> \n";
        }
        return text;
    }

    private class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                String name = nameTextField.getText();
                if (!nameTextField.getText().isEmpty()) {
                    Collection c = new Collection(name);
                    if (!collectionModel.contains(c)) {
                        collectionModel.addElement(c);
                        collectionList.setModel(collectionModel);
                        log.setText("Collection added successfully.");
                    } else {
                        log.setText("Collection has already been added.");
                    }
                    currentCollections.setText(getCurrentCollections());
                } else {
                    log.setText("Please enter a name for your collection.");
                }
            }
        }
    }
}
