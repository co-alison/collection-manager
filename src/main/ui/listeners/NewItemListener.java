package ui.listeners;

import model.Collection;
import model.Item;
import ui.CollectionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewItemListener extends CollectionApp implements ActionListener {

    private Container container;
    private JLabel name;
    private JTextField nameTextField;
    private JLabel itemNumber;
    private JTextField itemNumberTextField;
    private JLabel edition;
    private JTextField editionTextField;
    private JLabel exclusive;
    private JTextField exclusiveTextField;
    private JLabel releaseDate;
    private JTextField releaseDateTextField;
    private JButton calculatePrice;
    private JLabel condition;
    private JTextField conditionTextField;
    private JLabel category;
    private JTextField categoryTextField;
    private JLabel collection;
    private JTextField collectionTextField;
    private JLabel currentCollections;
    private JRadioButton newCondition;
    private JRadioButton mintCondition;
    private JRadioButton goodCondition;
    private JRadioButton fairCondition;
    private JRadioButton poorCondition;
    private JLabel log;
    private JButton submit;
    private ButtonGroup buttonGroup;
    private Item newItem;

    public NewItemListener() {
        //
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create new window
        JFrame newItemFrame = new JFrame("New Item");

        container = newItemFrame.getContentPane();
        container.setLayout(null);

        createLabelWithTextField();
        buttonGroup = new ButtonGroup();
        createRadioButton(newCondition, "New", 130, 200);
        createRadioButton(mintCondition, "Mint", 200, 200);
        createRadioButton(goodCondition, "Good", 270, 200);
        createRadioButton(fairCondition, "Fair", 340, 200);
        createRadioButton(poorCondition, "Poor", 410, 200);

        createCalculatePriceButton();
        createLog();
        createSubmitButton();
        createCurrentCollectionList();

        newItemFrame.pack();
        newItemFrame.setSize(WIDTH + 200, HEIGHT);
        newItemFrame.setLocationRelativeTo(null);
        newItemFrame.setVisible(true);
    }

    private void createCurrentCollectionList() {
        String text = getCurrentCollections();
        currentCollections = new JLabel(text);
        currentCollections.setSize(200, HEIGHT / 2);
        currentCollections.setLocation(500, 50);
        currentCollections.setBorder(BorderFactory.createTitledBorder("Collections"));
        container.add(currentCollections);
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

    private void createLog() {
        log = new JLabel("");
        log.setSize(WIDTH / 2, 25);
        log.setLocation(50, 300);
        container.add(log);
    }

    private void createLabelWithTextField() {
        createName();
        createItemNumber();
        createEdition();
        createExclusive();
        createReleaseDate();
        createCategory();
        createCondition();
        createCollection();
    }

    public void createLabel(JLabel label, String labelName, int posX, int posY) {
        label = new JLabel(labelName);
        label.setSize(200, 20);
        label.setLocation(posX, posY);
        container.add(label);
    }

    private void createTextField(JTextField textField, int posX, int posY) {
        textField = new JTextField();
        textField.setSize(210, 20);
        textField.setLocation(posX, posY);
        container.add(textField);
    }

    private void createCollection() {
        collection = new JLabel("Collection: ");
        collection.setSize(200, 20);
        collection.setLocation(50, 260);
        container.add(collection);
        collectionTextField = new JTextField();
        collectionTextField.setSize(210, 20);
        collectionTextField.setLocation(250, 260);
        container.add(collectionTextField);
    }

    private void createCondition() {
        condition = new JLabel("Condition: ");
        condition.setSize(200, 20);
        condition.setLocation(50, 200);
        container.add(condition);
        buttonGroup = new ButtonGroup();
        createRadioButton(newCondition, "New", 130, 200);
        createRadioButton(mintCondition, "Mint", 200, 200);
        createRadioButton(goodCondition, "Good", 270, 200);
        createRadioButton(fairCondition, "Fair", 340, 200);
        createRadioButton(poorCondition, "Poor", 410, 200);
    }

    private void createCategory() {
        category = new JLabel("Category: ");
        category.setSize(200, 20);
        category.setLocation(50, 170);
        container.add(category);
        createTextField(categoryTextField, 250, 170);
        categoryTextField = new JTextField();
        categoryTextField.setSize(210, 20);
        categoryTextField.setLocation(250, 170);
        container.add(categoryTextField);
    }

    private void createReleaseDate() {
        releaseDate = new JLabel("Release Date (MM/DD/YYYY): ");
        releaseDate.setSize(200, 20);
        releaseDate.setLocation(50, 140);
        container.add(releaseDate);
        releaseDateTextField = new JTextField();
        releaseDateTextField.setSize(210, 20);
        releaseDateTextField.setLocation(250, 140);
        container.add(releaseDateTextField);
    }

    private void createExclusive() {
        createLabel(exclusive, "Exclusive: ", 50, 110);
        exclusive = new JLabel("Exclusive: ");
        exclusive.setSize(200, 20);
        exclusive.setLocation(50, 110);
        container.add(exclusive);
        exclusiveTextField = new JTextField();
        exclusiveTextField.setSize(210, 20);
        exclusiveTextField.setLocation(250, 110);
        container.add(exclusiveTextField);
    }

    private void createEdition() {
        createLabel(edition, "Edition: ", 50, 80);
        edition = new JLabel("Edition: ");
        edition.setSize(200, 20);
        edition.setLocation(50, 80);
        container.add(edition);
        editionTextField = new JTextField();
        editionTextField.setSize(210, 20);
        editionTextField.setLocation(250, 80);
        container.add(editionTextField);
    }

    private void createItemNumber() {
        itemNumber = new JLabel("Item Number: ");
        itemNumber.setSize(200, 20);
        itemNumber.setLocation(50, 50);
        container.add(itemNumber);
        itemNumberTextField = new JTextField();
        itemNumberTextField.setSize(210, 20);
        itemNumberTextField.setLocation(250, 50);
        container.add(itemNumberTextField);
    }

    private void createName() {
        name = new JLabel("Name: ");
        name.setSize(200, 20);
        name.setLocation(50, 20);
        container.add(name);
        nameTextField = new JTextField();
        nameTextField.setSize(210, 20);
        nameTextField.setLocation(250, 20);
        container.add(nameTextField);
    }

    private void createSubmitButton() {
        submit = new JButton("Add Item");
        submit.setSize(100,20);
        submit.setLocation(50,350);
        submit.setBorder(BorderFactory.createLineBorder(Color.black));
        submit.addActionListener(new AddItemListener());
        container.add(submit);
    }

    private void createCalculatePriceButton() {
        calculatePrice = new JButton("Calculate Average Market Price");
        calculatePrice.setSize(200, 20);
        calculatePrice.setLocation(50, 230);
        calculatePrice.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(calculatePrice);
        calculatePrice.addActionListener(new AddItemListener());
    }

    public void createRadioButton(JRadioButton button, String buttonName, int posX, int posY) {
        button = new JRadioButton(buttonName);
        button.setSelected(false);
        button.setSize(70, 20);
        button.setLocation(posX, posY);
        buttonGroup.add(button);
        container.add(button);
    }

    private class AddItemListener implements ActionListener {
        JLabel price1;
        JTextField priceTextField1;
        JLabel price2;
        JTextField priceTextField2;
        JLabel price3;
        JTextField priceTextField3;
        JLabel price4;
        JTextField priceTextField4;
        JLabel price5;
        JTextField priceTextField5;
        JButton doCalculatePrice;
        JLabel priceLog;
        Double price = 12.99;

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isInt;

            if (e.getSource() == calculatePrice) {
                calculatePrice();
            } else if (e.getSource() == submit) {
                String name = nameTextField.getText();
                String itemNumber = itemNumberTextField.getText();
                int num = 0;
                try {
                    num = Integer.parseInt(itemNumber);
                    isInt = true;
                } catch (NumberFormatException nfe) {
                    log.setText("Item Number must be an integer.");
                    isInt = false;
                }
                String edition = editionTextField.getText();
                String exclusive = exclusiveTextField.getText();
                String releaseDate = releaseDateTextField.getText();
                String category = categoryTextField.getText();
                String collection = collectionTextField.getText();

                if (!isFieldEmpty() && isInt) {
                    newItem = new Item(name, num, edition, exclusive, releaseDate, category);
                    newItem.setCurrentMarketPrice(price);

                    for (int i = 0; i < collectionModel.getSize(); i++) {
                        String collectionName = collectionModel.get(i).getName();
                        if (collectionName.equals(collection)) {
                            collectionModel.get(i).addItem(newItem);
                            log.setText("Item added successfully.");
                        } else if (i == collectionModel.getSize() - 1 && !collectionName.equals(collection)) {
                            log.setText("Collection not found.");
                        }
                    }
                } else if (isFieldEmpty()) {
                    log.setText("At least one of the fields are blank.");
                } else {
                    log.setText("Item number must be an integer.");
                }
            }
        }

        private void calculatePrice() {
            JFrame calculatePriceFrame = new JFrame("Calculate Average Market Price");

            container = calculatePriceFrame.getContentPane();
            container.setLayout(null);

            createLabelWithTextField();
            doCalculatePrice = new JButton("Calculate Price");
            doCalculatePrice.setSize(150, 20);
            doCalculatePrice.setLocation(WIDTH / 2, 400);
            doCalculatePrice.setBorder(BorderFactory.createLineBorder(Color.black));
            doCalculatePrice.addActionListener(new CalculatePriceListener());
            container.add(doCalculatePrice);
            priceLog = new JLabel("");
            priceLog.setSize(WIDTH / 2, 25);
            priceLog.setLocation(50, 300);
            container.add(priceLog);

            calculatePriceFrame.pack();
            calculatePriceFrame.setSize(WIDTH, HEIGHT);
            calculatePriceFrame.setLocationRelativeTo(null);
            calculatePriceFrame.setVisible(true);
        }

        private class CalculatePriceListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                double sum = 0;
                double i = 0;
                if (priceTextField1.getText().isEmpty()) {
                    priceLog.setText("Price 1 field is required.");
                } else {
                    String price1 = priceTextField1.getText();
                    int priceNum1 = Integer.parseInt(price1);
                    i++;
                    sum += priceNum1;

                    if (!priceTextField2.getText().isEmpty()) {
                        String price2 = priceTextField2.getText();
                        int priceNum2 = Integer.parseInt(price2);
                        i++;
                        sum += priceNum2;
                    }

                    if (!priceTextField3.getText().isEmpty()) {
                        String price3 = priceTextField3.getText();
                        int priceNum3 = Integer.parseInt(price3);
                        i++;
                        sum += priceNum3;
                    }

                    if (!priceTextField4.getText().isEmpty()) {
                        String price4 = priceTextField4.getText();
                        int priceNum4 = Integer.parseInt(price4);
                        i++;
                        sum += priceNum4;
                    }

                    if (!priceTextField5.getText().isEmpty()) {
                        String price5 = priceTextField5.getText();
                        int priceNum5 = Integer.parseInt(price5);
                        i++;
                        sum += priceNum5;
                    }
                    price = sum / i;
                }
            }
        }

        private void createLabelWithTextField() {
            createLabel(price1, "Price 1 (Required): ", 50, 50);
            createTextField(priceTextField1, 250, 50);
            createLabel(price2, "Price 2: ", 50, 80);
            createTextField(priceTextField2, 250, 80);
            createLabel(price3, "Price 3: ", 50, 110);
            createTextField(priceTextField3, 250, 110);
            createLabel(price4, "Price 4: ", 50, 140);
            createTextField(priceTextField4, 250, 140);
            createLabel(price5, "Price 5: ", 50, 170);
            createTextField(priceTextField5, 250, 170);
        }

        public boolean isFieldEmpty() {
            return nameTextField.getText().isEmpty() || itemNumberTextField.getText().isEmpty()
                    || editionTextField.getText().isEmpty() || exclusiveTextField.getText().isEmpty()
                    || releaseDateTextField.getText().isEmpty() || category.getText().isEmpty();
        }

    }
}
