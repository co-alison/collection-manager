package ui;

import model.Collection;
import model.Item;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Collection application GUI

public class CollectionApp {

    private static final String JSON_STORE = "./data/collection.json";
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);

    private static final int WIDTH = 650;
    private static final int HEIGHT = 550;
    private static JFrame frame;

    private User user = new User();

    private final Collection funko = new Collection("Funko Pop!");
    private final Collection hotWheels = new Collection("Hot Wheels");
    private final Collection sneakers = new Collection("Sneakers");
    private final Item alien = new Item("Alien", 48241, "Glitter",
            "BoxLunch", "2020 01 01", "Toy Story");
    private final Item geoffrey = new Item("Geoffrey as Robin", 144, "NA", "Toys R Us", "2021 01 01", "Ad Icons");
    private final Item leota = new Item("Madame Leota", 575, "Glows In The Dark",
            "Disney Parks", "2019 01 01", "Haunted Mansion");
    private final Item dunkLow = new Item("Purple Pulse Dunk Low", 123, "NA", "StockX", "2020 06 14", "Nike");

    private final JList<Collection> collectionList = new JList<>();
    private DefaultListModel<Collection> collectionModel = new DefaultListModel<>();

    private final JList<Item> itemList = new JList<>();
    private final DefaultListModel<Item> itemModel = new DefaultListModel<>();

    private final JPanel panel;
    private final JLabel label;

    JSplitPane topSplitPane;
    JSplitPane splitPane;

    JScrollPane itemScrollPane;
    JScrollPane collectionScrollPane;

    protected JButton deleteCollection;
    protected JButton deleteItem;

    // MODIFIES: collectionModel, collectionList
    // EFFECTS: creates initial window with a view of collections and items, a menu, and delete buttons
    public CollectionApp() {
        setupCollectionList();

        collectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        collectionList.addListSelectionListener(new CollectionListener());
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(new ItemListener());

        // Create panel
        panel = new JPanel(new BorderLayout());

        // Create delete buttons
        JPanel buttonPane = getDeleteButtonPane();

        // Create label
        label = new JLabel("Click on an item in the collection.", JLabel.CENTER);

        // Create collection and item scroll pane
        collectionScrollPane = createCollectionScrollPane();
        itemScrollPane = createItemScrollPane();

        // Create top split pane with collection and item scroll panes
        topSplitPane = new JSplitPane();
        topSplitPane.setLeftComponent(collectionScrollPane);
        topSplitPane.setRightComponent(itemScrollPane);
        topSplitPane.setOneTouchExpandable(true);
        topSplitPane.setDividerLocation(180);

        //noinspection SuspiciousNameCombination
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, label);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(HEIGHT / 2);

        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Item Info"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panel.add(splitPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: create delete collection button and delete item button, add to panel
    private JPanel getDeleteButtonPane() {
        deleteCollection = new JButton("Delete Collection");
        deleteCollection.setActionCommand("Delete Collection");
        deleteCollection.setEnabled(false);
        deleteCollection.addActionListener(new DeleteCollectionListener());

        deleteItem = new JButton("Delete Item");
        deleteItem.setActionCommand("Delete Item");
        deleteItem.setEnabled(false);
        deleteItem.addActionListener(new DeleteItemListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(deleteCollection);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(deleteItem);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    // MODIFIES: collectionModel, collectionList
    // EFFECTS: add items to collection, create collection model and JList
    private void setupCollectionList() {
        // add items to collections
        funko.addItem(alien);
        funko.addItem(geoffrey);
        funko.addItem(leota);
        sneakers.addItem(dunkLow);

        collectionModel = new DefaultListModel<>();
        collectionList.setModel(collectionModel);

        // add elements to model
        collectionModel.addElement(funko);
        collectionModel.addElement(hotWheels);
        collectionModel.addElement(sneakers);
    }

    // EFFECTS: creates scroll pane of item list
    private JScrollPane createItemScrollPane() {
        JScrollPane itemScrollPane = new JScrollPane(itemList);
        itemScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Items"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return itemScrollPane;
    }

    // EFFECTS: creates scroll pane of collection list
    protected JScrollPane createCollectionScrollPane() {
        JScrollPane collectionScrollPane = new JScrollPane(collectionList);
        collectionScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Collections"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return collectionScrollPane;
    }

    // EFFECTS: creates a menu bar with options to load, create new collection/item, and save/quit
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        // Crate menu bar
        menuBar = new JMenuBar();

        // Create menu
        menu = new JMenu("Menu");
        menuBar.add(menu);

        // JMenuItems
        menuItem = new JMenuItem("Load from file");
        menuItem.addActionListener(new LoadFromFileListener());
        menu.add(menuItem);

        // Create submenus
        createNewSubmenu(menu);

        menu.addSeparator();
        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(new SaveListener());
        menu.add(menuItem);

        return menuBar;
    }

    // MODIFIES: menu
    // EFFECTS: Creates a submenu from given string
    private void createNewSubmenu(JMenu menu) {
        JMenu submenu;
        JMenuItem menuItem;
        menu.addSeparator();
        submenu = new JMenu("New...");
        menuItem = new JMenuItem("Collection");
        menuItem.addActionListener(new NewCollectionListener());
        submenu.add(menuItem);
        menuItem = new JMenuItem("Item");
        menuItem.addActionListener(new NewItemListener());
        submenu.add(menuItem);
        menu.add(submenu);
    }

    // MODIFIES: frame
    // EFFECTS: creates splash screen and frame
    public void setupGUI() {
        // Create splash screen
        createSplashScreen();

        // Create and set up window
        frame = new JFrame("Collection Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CollectionApp collectionApp = new CollectionApp();
        frame.setJMenuBar(collectionApp.createMenuBar());
        frame.add(collectionApp.panel);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // EFFECTS: creates a new window that displays loading animation
    public void createSplashScreen() {
        JWindow window = new JWindow();
        ImageIcon icon = createImageIcon("images/CollectionApp.gif");
        JLabel imageLabel = new JLabel(icon, SwingConstants.CENTER);

        window.getContentPane().add(imageLabel);
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }

    // EFFECTS: creates image icon from given url path, else prints error
    public ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Could not find image file: " + path);
            return null;
        }
    }

    // ListSelectionListener for collectionList
    private class CollectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // no collection selected
                deleteCollection.setEnabled(collectionList.getSelectedIndex() != -1);
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

    // ListSelectionListener for itemList
    private class ItemListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // no item selected
                deleteItem.setEnabled(itemList.getSelectedIndex() != -1);
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
                        + "<br><b>Current Market Price</b>: $" + i.getCurrentMarketPrice() + "</br> \n"
                        + "<br><b>Condition</b>: " + i.getCondition() + " (Default condition: New)</br> \n"
                        + "<br><b>Category</b>: " + i.getCategory() + "</br> \n";
                label.setText(summary);
            }
        }
    }

    // ActionListener for deleteCollection button
    private class DeleteCollectionListener implements ActionListener {

        // MODIFIES: collectionModel, collectionList
        // EFFECTS: removes selected collection from collectionModel
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = collectionList.getSelectedIndex();
            collectionModel.remove(index);

            int size = collectionModel.getSize();

            if (size == 0) {
                // collection list is empty, nothing to delete
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

    // ActionListener for deleteItem button
    private class DeleteItemListener implements ActionListener {

        // MODIFIES: itemModel, itemList
        // EFFECTS: deletes selected item from itemModel
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = itemList.getSelectedIndex();
            itemModel.remove(index);
        }
    }

    // ActionListener for New -> Collection
    private class NewCollectionListener implements ActionListener {
        private Container container;
        private JTextField nameTextField;
        private JButton submit;
        private JLabel log;
        private JLabel currentCollections;

        // creates new frame and form to create new collection
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

        // MODIFIES: container
        // EFFECTS: create a label with list of current collections
        private void createCurrentCollectionsLabel() {
            String text = getCurrentCollections();
            currentCollections = new JLabel(text);
            currentCollections.setSize(200, HEIGHT / 2);
            currentCollections.setLocation(WIDTH / 2 + 50, 100);
            currentCollections.setBorder(BorderFactory.createTitledBorder("Collections"));
            container.add(currentCollections);
        }

        // MODIFIES: container
        // EFFECTS: creates a log label
        private void createLogLabel() {
            log = new JLabel("");
            log.setSize(WIDTH / 2, 25);
            log.setLocation(50, 200);
            container.add(log);
        }

        // MODIFIES: container
        // EFFECTS: creates a submit button, adds ActionListener
        private void createSubmitButton() {
            submit = new JButton("Create");
            submit.setSize(100, 20);
            submit.setLocation(110, 150);
            submit.setBorder(BorderFactory.createLineBorder(Color.black));
            submit.addActionListener(new SubmitListener());
            container.add(submit);
        }

        // MODIFIES: container
        // EFFECTS: creates a text field for name label
        private void createNameTextField() {
            nameTextField = new JTextField();
            nameTextField.setSize(190, 20);
            nameTextField.setLocation(100, 100);
            container.add(nameTextField);
        }

        // MODIFIES: container
        // EFFECTS: creates a name label
        private void createNameLabel() {
            JLabel name = new JLabel("Name");
            name.setSize(50, 20);
            name.setLocation(50, 100);
            container.add(name);
        }

        // ActionListener for submit button
        private class SubmitListener implements ActionListener {

            // MODIFIES: collectionModel, collectionList, log
            // EFFECTS: if text field is not empty, if there is no duplicate collection, add collection to
            //      collectionModel, else if duplicate collection exists, change log text
            //      else if field is empty, change log text
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

    // ActionListener for New -> Item
    private class NewItemListener implements ActionListener {
        private Container container;
        private JLabel name;
        private final JTextField nameTextField = new JTextField();
        private JLabel itemNumber;
        private final JTextField itemNumberTextField = new JTextField();
        private JLabel edition;
        private final JTextField editionTextField = new JTextField();
        private JLabel exclusive;
        private final JTextField exclusiveTextField = new JTextField();
        private JLabel releaseDate;
        private final JTextField releaseDateTextField = new JTextField();
        private JLabel condition;
        private JLabel category;
        private JLabel price;
        private final JTextField priceTextField = new JTextField();
        private final JTextField categoryTextField = new JTextField();
        private JLabel collection;
        private final JTextField collectionTextField = new JTextField();
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

        // EFFECTS: creates new window and form to create a new item
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create new window
            JFrame newItemFrame = new JFrame("New Item");

            container = newItemFrame.getContentPane();
            container.setLayout(null);

            createLabelWithTextField();
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

        // MODIFIES: container
        // EFFECTS: creates log label
        private void createLog() {
            log = new JLabel("");
            log.setSize(WIDTH / 2, 25);
            log.setLocation(50, 300);
            container.add(log);
        }

        // MODIFIES: container
        // EFFECTS: calls createLabelTextField for each set of labels and text fields
        private void createLabelWithTextField() {
            createLabelTextField(name, "Name: ", nameTextField, 20,
                    20);
            createLabelTextField(itemNumber, "Item Number: ", itemNumberTextField, 50,
                    50);
            createLabelTextField(edition, "Edition: ", editionTextField, 80,
                    80);
            createLabelTextField(exclusive, "Exclusive: ", exclusiveTextField, 110,
                    110);

            createLabelTextField(releaseDate, "Release Date (MM/DD/YYYY): ", releaseDateTextField,
                    140, 140);
            createLabelTextField(category, "Category: ", categoryTextField, 170,
                    170);
            createCondition();
            createLabelTextField(price, "Price: ", priceTextField, 230, 230);
            createLabelTextField(collection, "Collection: ", collectionTextField, 260,
                    260);
        }

        // MODIFIES: container
        // EFFECTS: creates a label from given name and position, creates textfield from given posiiton
        private void createLabelTextField(JLabel label, String labelName, JTextField textField,
                                          int labelPosY, int textPosY) {
            label = new JLabel(labelName);
            label.setSize(200, 20);
            label.setLocation(50, labelPosY);
            container.add(label);
            textField.setSize(210, 20);
            textField.setLocation(250, textPosY);
            container.add(textField);
        }

        // MODIFIES: container
        // EFFECTS: creates condition label with group of radio buttons
        private void createCondition() {
            condition = new JLabel("Condition: ");
            condition.setSize(200, 20);
            condition.setLocation(50, 200);
            container.add(condition);
            buttonGroup = new ButtonGroup();
            createRadioButton("New", 130, 200);
            createRadioButton("Mint", 200, 200);
            createRadioButton("Good", 270, 200);
            createRadioButton("Fair", 340, 200);
            createRadioButton("Poor", 410, 200);
        }

        // MODIFIES: container
        // EFFECTS: creates submit button
        private void createSubmitButton() {
            submit = new JButton("Add Item");
            submit.setSize(100, 20);
            submit.setLocation(50, 350);
            submit.setBorder(BorderFactory.createLineBorder(Color.black));
            submit.addActionListener(new AddItemListener());
            container.add(submit);
        }

        // MODIFIES: buttonGroup, container
        // EFFECTS: creates a radio button from given name and position, sets selected to false
        public void createRadioButton(String buttonName, int posX, int posY) {
            JRadioButton button = new JRadioButton(buttonName);
            button.setSelected(false);
            button.setSize(70, 20);
            button.setLocation(posX, posY);
            buttonGroup.add(button);
            container.add(button);
        }

        // ActionListener for submit button (add item)
        private class AddItemListener implements ActionListener {
            Double priceDouble = 12.99;
            int num = 0;

            // EFFECTS: if fields are filled correctly, create and add a new item to collection
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isInt;
                boolean isDouble;

                if (e.getSource() == submit) {
                    String name = nameTextField.getText();
                    isInt = isInt();
                    String edition = editionTextField.getText();
                    String exclusive = exclusiveTextField.getText();
                    String releaseDate = releaseDateTextField.getText();
                    String category = categoryTextField.getText();
                    isDouble = isDouble();
                    String collection = collectionTextField.getText();

                    if (!isFieldEmpty() && isInt && isDouble) {
                        newItem = new Item(name, num, edition, exclusive, releaseDate, category);
                        newItem.setCurrentMarketPrice(priceDouble);
                        addToCollection(collection);
                    } else if (isFieldEmpty()) {
                        log.setText("At least one of the fields are blank.");
                    } else {
                        log.setText("Item number must be an integer.");
                    }
                }

            }

            // MODIFIES: isDouble, log
            // EFFECTS: returns true if price is a double
            private boolean isDouble() {
                boolean isDouble;
                String price = priceTextField.getText();
                try {
                    priceDouble = Double.parseDouble(price);
                    isDouble = true;
                } catch (NumberFormatException nfe) {
                    log.setText("Price must be a double.");
                    isDouble = false;
                }
                return isDouble;
            }

            // MODIFIES: isInt, log
            // EFFECTS: returns true if item number is an int
            private boolean isInt() {
                String itemNumber = itemNumberTextField.getText();
                boolean isInt;
                try {
                    num = Integer.parseInt(itemNumber);
                    isInt = true;
                } catch (NumberFormatException nfe) {
                    log.setText("Item Number must be an integer.");
                    isInt = false;
                }
                return isInt;
            }

            // MODIFIES: collectionModel, collectionList, log
            // EFFECTS: adds item to collection if found, else changes log text
            private void addToCollection(String collection) {
                for (int i = 0; i < collectionModel.getSize(); i++) {
                    String collectionName = collectionModel.get(i).getName();
                    if (collectionName.equals(collection)) {
                        collectionModel.get(i).addItem(newItem);
                        log.setText("Item added successfully.");
                    } else if (i == collectionModel.getSize() - 1) {
                        log.setText("Collection not found.");
                    }
                }
            }

            // EFFECTS: returns true if at least one field is empty
            public boolean isFieldEmpty() {
                return nameTextField.getText().isEmpty() || itemNumberTextField.getText().isEmpty()
                        || editionTextField.getText().isEmpty() || exclusiveTextField.getText().isEmpty()
                        || releaseDateTextField.getText().isEmpty() || categoryTextField.getText().isEmpty()
                        || collectionTextField.getText().isEmpty() || priceTextField.getText().isEmpty();
            }
        }
    }

    // ActionListener for Load from file
    private class LoadFromFileListener implements ActionListener {

        // EFFECTS: loads user from file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                user = jsonReader.read();
                collectionModel.clear();
                for (Collection c : user.getCollections()) {
                    collectionModel.addElement(c);
                }
                collectionList.setModel(collectionModel);
                label.setText("Loaded from " + JSON_STORE);
            } catch (IOException exception) {
                label.setText("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // ActionListener for Save/Quit
    private class SaveListener implements ActionListener {

        // if user chooses Save and Quit, saves collections to file and quits app
        // else quits app
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"Save and Quit", "Quit"};
            int n = JOptionPane.showOptionDialog(frame,
                    "Do you want to save your collections?",
                    "Save or Quit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (n == JOptionPane.YES_OPTION) {
                save();
            } else if (n == JOptionPane.NO_OPTION) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } else {
                label.setText("Click on an item in the collection.");
            }
        }

        // EFFECTS: adds collections in collectionModel to user
        private void save() {
            try {
                for (int i = 0; i < collectionModel.getSize(); i++) {
                    Collection c = collectionModel.getElementAt(i);
                    user.addCollection(c);
                }
                jsonWriter.open();
                jsonWriter.write(user);
                jsonWriter.close();
                label.setText("Saved to " + JSON_STORE);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } catch (FileNotFoundException exception) {
                label.setText("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // EFFECTS: returns a html formatted string of the user's current collections
    private String getCurrentCollections() {
        StringBuilder text = new StringBuilder("<html> \n"
                + "<ul> \n");

        for (int i = 0; i < collectionModel.getSize(); i++) {
            Collection c = collectionModel.getElementAt(i);
            text.append("<li>").append(c.getName()).append("</li> \n");
        }
        return text.toString();
    }
}
