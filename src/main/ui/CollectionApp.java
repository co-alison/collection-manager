package ui;

import model.Collection;
import model.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

// Collection application
// Code modified from TellerApp
public class CollectionApp {

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
        topSplitPane.setMinimumSize(new Dimension(400, 100));

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, label);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(180);

        label.setMinimumSize(new Dimension(400, 200));
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
        itemScrollPane.setMinimumSize(new Dimension(200, 100));
        return itemScrollPane;
    }

    private JScrollPane getCollectionScrollPane() {
        JScrollPane collectionScrollPane = new JScrollPane(collectionList);
        collectionScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Collections"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        collectionScrollPane.setMinimumSize(new Dimension(200, 100));
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
        submenu.add(menuItem);
        menuItem = new JMenuItem("Item");
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
        frame.pack();
        frame.setVisible(true);
    }

    public class CollectionListener implements ListSelectionListener {

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

    public class ItemListener implements ListSelectionListener {
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
                        + "<br><b>Current Market Price</b>: $" + i.getCurrentMarketPrice() + " (Default price: 12.99)</br> \n"
                        + "<br><b>Condition</b>: " + i.getCondition() + " (Default condition: New)</br> \n"
                        + "<br><b>Category</b>: " + i.getCategory() + "</br> \n";
                label.setText(summary);
            }
        }
    }
}
