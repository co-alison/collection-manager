package ui;

import model.Collection;
import model.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Collection application
// Code modified from ...
public class CollectionApp {

    protected static final int WIDTH = 550;
    protected static final int HEIGHT = 450;

    private Collection funko = new Collection("Funko Pop!");
    private Collection hotWheels = new Collection("Hot Wheels");
    private Collection sneakers = new Collection("Sneakers");
    private Item alien = new Item("Alien", 48241, "Glitter",
            "BoxLunch", "2020 01 01", "Toy Story");
    private Item geoffrey = new Item("Geoffrey as Robin", 144, "NA", "Toys R Us","2021 01 01", "Ad Icons");
    private Item leota = new Item("Madame Leota", 575, "Glows In The Dark",
            "Disney Parks", "2019 01 01", "Haunted Mansion");
    private Item dunkLow = new Item("Purple Pulse Dunk Low", 123, "NA", "StockX", "2020 06 14", "Nike");

    JList<Collection> collectionList = new JList<>();
    DefaultListModel<Collection> collectionModel = new DefaultListModel<>();

    JList<Item> itemList = new JList<>();
    DefaultListModel<Item> itemModel = new DefaultListModel<>();

    JLabel label;

    JSplitPane topSplitPane = new JSplitPane();
    JSplitPane splitPane;

    CollectionListener collectionListener = new CollectionListener();
    ItemListener itemListener = new ItemListener();

    public CollectionApp() {
        setupCollectionList();

        collectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        collectionList.addListSelectionListener(collectionListener);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(itemListener);

        // Create label
        label = new JLabel("Click on an item in the collection.", JLabel.CENTER);

        // Create collection and item scroll pane
        JScrollPane collectionScrollPane = getCollectionScrollPane();
        JScrollPane itemScrollPane = getItemScrollPane();

        // Create top split pane with collection and item scroll panes
        topSplitPane.setLeftComponent(collectionScrollPane);
        topSplitPane.setRightComponent(itemScrollPane);
        topSplitPane.setOneTouchExpandable(true);
        topSplitPane.setDividerLocation(180);
        topSplitPane.setMinimumSize(new Dimension(WIDTH, HEIGHT / 2));

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, label);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(WIDTH / 2);

        label.setMinimumSize(new Dimension(WIDTH, HEIGHT / 2));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Item Info"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
    }

    private void setupCollectionList() {
        // add items to collections
        funko.addItem(alien);
        funko.addItem(geoffrey);
        funko.addItem(leota);
        sneakers.addItem(dunkLow);

        collectionList.setModel(collectionModel);

        // add elements to model
        collectionModel.addElement(funko);
        collectionModel.addElement(hotWheels);
        collectionModel.addElement(sneakers);
    }

    private JScrollPane getItemScrollPane() {
        JScrollPane itemScrollPane = new JScrollPane(itemList);
        itemScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Items"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        itemScrollPane.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT / 2));
        return itemScrollPane;
    }

    protected JScrollPane getCollectionScrollPane() {
        JScrollPane collectionScrollPane = new JScrollPane(collectionList);
        collectionScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Collections"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        collectionScrollPane.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT / 2));
        return collectionScrollPane;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenu submenu;
        JMenuItem menuItem;

        // Crate menu bar
        menuBar = new JMenuBar();

        // Create menu
        menu = new JMenu("Menu");
        menuBar.add(menu);

        // JMenuItems
        menuItem = new JMenuItem("Load from file");
        menu.add(menuItem);

        // Create submenus
        createNewSubmenu(menu, "New...");
        createNewSubmenu(menu, "Edit...");
        createNewSubmenu(menu, "Delete...");

        menu.addSeparator();
        menuItem = new JMenuItem("Calculate value");
        menuItem.addActionListener(new TotalValueListener());
        menu.add(menuItem);

        menu.addSeparator();
        menuItem = new JMenuItem("Quit");
        menu.add(menuItem);

        return menuBar;
    }

    private void createNewSubmenu(JMenu menu, String s) {
        JMenu submenu;
        JMenuItem menuItem;
        menu.addSeparator();
        submenu = new JMenu(s);
        menuItem = new JMenuItem("Collection");
        menuItem.addActionListener(new NewCollectionListener());
        submenu.add(menuItem);
        menuItem = new JMenuItem("Item");
        menuItem.addActionListener(new NewItemListener());
        submenu.add(menuItem);
        menu.add(submenu);
    }

    public static void setupGUI() {
        // Create and set up window
        JFrame frame = new JFrame("Collection Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CollectionApp collectionApp = new CollectionApp();
        frame.setJMenuBar(collectionApp.createMenuBar());
        frame.add(collectionApp.splitPane);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class CollectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            Collection c = collectionList.getSelectedValue();
            List<Item> items = c.getItems();
            itemModel.clear();

            for (Item i : items) {
                itemModel.addElement(i);
            }

            itemList.setModel(itemModel);
        }
    }

    private class ItemListener implements ListSelectionListener {

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

    private class TotalValueListener implements ActionListener {
        JSplitPane splitPane;
        JScrollPane scrollPane;
        JLabel valueLabel;

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

            collectionList.addListSelectionListener(new CalculateValueListener());

            splitPane.setLeftComponent(scrollPane);
            splitPane.setRightComponent(valueLabel);
            splitPane.setOneTouchExpandable(true);

            calculateValueFrame.add(splitPane);
            calculateValueFrame.setSize(new Dimension(WIDTH, HEIGHT));
            calculateValueFrame.setLocationRelativeTo(null);
            calculateValueFrame.setVisible(true);
        }

        private class CalculateValueListener implements ListSelectionListener {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                if (collectionList.isSelectionEmpty()) {
                    label.setText("<html> \n "
                            + "<br>Select collection to calculate its total value. </br> \n"
                            + "<b>Note: Default value of each item is $12.99</b>");
                } else {
                    Collection c = collectionList.getSelectedValue();
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

    private class NewCollectionListener implements ActionListener {
        private Container container;
        private JLabel name;
        private JTextField nameTextField;
        private JButton submit;
        private JLabel log;
        private JLabel currentCollections;

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

    private class NewItemListener implements ActionListener {
        private JPanel panel;
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
        private JLabel price;
        private JLabel condition;
        private JTextField conditionTextField;
        private JLabel category;
        private JTextField categoryTextField;
        private JRadioButton newCondition;
        private JRadioButton mintCondition;
        private JRadioButton goodCondition;
        private JRadioButton fairCondition;
        private JRadioButton poorCondition;
        private JButton submit;


        @Override
        public void actionPerformed(ActionEvent e) {
            // Create new window
            JFrame newItemFrame = new JFrame("New Item");

            container = newItemFrame.getContentPane();
            container.setLayout(null);

            createLabelWithTextField();
            createRadioButton(newCondition, "New", 130, 230);
            createRadioButton(mintCondition, "Near Mint", 210, 230);
            createRadioButton(goodCondition, "Good", 290, 230);
            createRadioButton(fairCondition, "Fair", 370, 230);
            createRadioButton(poorCondition, "Poor", 450, 230);

            createCalculatePriceButton();
            createSubmitButton();

            newItemFrame.pack();
            newItemFrame.setSize(WIDTH + 100, HEIGHT);
            newItemFrame.setLocationRelativeTo(null);
            newItemFrame.setVisible(true);
        }

        private void createLabelWithTextField() {
            createLabel(name, "Name: ", 50, 50);
            createTextField(nameTextField, 250, 50);
            createLabel(itemNumber, "Item Number: ", 50, 80);
            createTextField(itemNumberTextField, 250, 80);
            createLabel(edition, "Edition: ", 50, 110);
            createTextField(editionTextField, 250, 110);
            createLabel(exclusive, "Exclusive: ", 50, 140);
            createTextField(exclusiveTextField, 250, 140);
            createLabel(releaseDate, "Release Date (MM DD YYYY): ", 50, 170);
            createTextField(releaseDateTextField, 250, 170);
            createLabel(category, "Category: ", 50, 200);
            createTextField(categoryTextField, 250, 200);
            createLabel(condition, "Condition: ", 50, 230);
        }

        private void createTextField(JTextField textField, int posX, int posY) {
            textField = new JTextField();
            textField.setSize(190, 20);
            textField.setLocation(posX, posY);
            container.add(textField);

        }

        private void createSubmitButton() {
            submit = new JButton("Add Item");
            submit.setSize(100,20);
            submit.setLocation(WIDTH / 2,400);
            submit.setBorder(BorderFactory.createLineBorder(Color.black));
            //submit.addActionListener(new AddItemListener());
            container.add(submit);
        }

        private void createCalculatePriceButton() {
            calculatePrice = new JButton("Calculate Average Market Price");
            calculatePrice.setSize(200, 20);
            calculatePrice.setLocation(50, 270);
            calculatePrice.setBorder(BorderFactory.createLineBorder(Color.black));
            container.add(calculatePrice);
            //calculatePrice.addActionListener(new CalculatePriceListener());
        }

        public void createLabel(JLabel label, String labelName, int posX, int posY) {
            label = new JLabel(labelName);
            label.setSize(200, 20);
            label.setLocation(posX, posY);
            container.add(label);
        }

        public void createRadioButton(JRadioButton button, String buttonName, int posX, int posY) {
            button = new JRadioButton(buttonName);
            button.setSelected(false);
            button.setSize(80, 20);
            button.setLocation(posX, posY);
            container.add(button);
        }
        // TODO: CalculatePriceListener
        // TODO: AddItemListener
    }
}
