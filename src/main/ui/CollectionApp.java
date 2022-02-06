package ui;

import model.Collection;
import model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Collection application
public class CollectionApp {
    private List<Collection> collectionList;
    private Scanner input;

    // EFFECTS: runs the collection application
    public CollectionApp() {
        runCollection();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCollection() {
        boolean keepRunning = true;
        String command;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: initializes empty list of collections
    private void init() {
        collectionList = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays options for user command
    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?");
        System.out.println("\tc -> create a new collection");
        System.out.println("\ta -> add a new item to collection");
        System.out.println("\tr -> remove item from collection");
        System.out.println("\tt -> calculate total value of collection");
        System.out.println("\tsc -> select and view collection");
        System.out.println("\tsi -> select and view item");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createCollection();
        } else if (command.equals("a")) {
            addItem();
        } else if (command.equals("r")) {
            removeItem();
        } else if (command.equals("t")) {
            doCalculateTotalValue();
        } else if (command.equals("s")) {
            selectCollection();
        } else {
            System.out.println("Invalid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new collection and adds to collection list
    private void createCollection() {
        System.out.println("What is the name of your new collection?");
        String name = input.next();

        Collection collection = new Collection(name);
        collectionList.add(collection);
        printCollections();
    }

    // MODIFIES: this
    // EFFECTS: creates new item to add to collection
    private void addItem() {
        if (isCollectionListEmpty()) {
            return;
        }
        System.out.println("Enter the following information about your item. Use NA for blank values.");
        System.out.println("Name: ");
        String name = input.next();
        System.out.println("Item Number: ");
        Integer itemNumber = Integer.valueOf(input.next());
        System.out.println("Edition: ");
        String edition = input.next();
        System.out.println("Exclusive: ");
        String exclusive = input.next();
        System.out.println("Release Date (yyyy MM dd): ");
        String date = input.next();
        System.out.println("Category: ");
        String category = input.next();

        Item item = new Item(name, itemNumber, edition, exclusive, date, category);

        addItemToCollection(item);
    }

    // MODIFIES: this
    // EFFECTS: adds given item to a collection
    private void addItemToCollection(Item item) {
        System.out.println("Which collection do you want to add this item to?");
        printCollections();
        String collection = input.next();

        for (Collection c : collectionList) {
            if (c.getName().equals(collection)) {
                c.addItem(item);
                System.out.println("Item added.");
            }
        }
    }

    // EFFECTS: chooses which collection the item will be removed from
    private void removeItem() {
        if (isCollectionListEmpty()) {
            return;
        }
        System.out.println("Which collection do you want to remove from?");
        printCollections();
        String collection = input.next();

        for (Collection c : collectionList) {
            if (c.getName().equals(collection)) {
                removeItemFromCollection(c);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes item from given collection
    private void removeItemFromCollection(Collection c) {
        if (isCollectionEmpty(c)) {
            return;
        }
        System.out.println("Which item do you want to remove?");
        printItems(c);
        String item = input.next();

        for (Item i : c.getItems()) {
            if (i.getName().equals(item)) {
                c.removeItem(i);
                System.out.println("Item removed.");
                return;
            }
        }
    }

    // EFFECTS: calculates total value of the collection
    private void doCalculateTotalValue() {
        if (isCollectionListEmpty()) {
            return;
        }
        System.out.println("Which collection do you want to calculate the total value of?");
        printCollections();
        String collection = input.next();

        for (Collection c : collectionList) {
            if (c.getName().equals(collection)) {
                System.out.println("Total Value: $" + c.calculateTotalValue());
            }
        }
    }

    // EFFECTS: selects collection to view
    private void selectCollection() {
        if (isCollectionListEmpty()) {
            return;
        }
        System.out.println("Which collection do you want to view?");
        printCollections();
        String collection = input.next();

        for (Collection c : collectionList) {
            if (c.getName().equals(collection)) {
                printItems(c);
                selectItem(c);
            }
        }
    }

    // EFFECTS: selects item from collection
    private void selectItem(Collection c) {
        if (isCollectionEmpty(c)) {
            return;
        }
        System.out.println("Which item do you want to view?");
        String item = input.next();

        for (Item i : c.getItems()) {
            if (i.getName().equals(item)) {
                printCurrentInfo(i);
                displayItemFunctions(i);
            }
        }
    }

    // EFFECTS: display options for user command for item functions
    private void displayItemFunctions(Item i) {
        System.out.println("\nSelect an option: ");
        System.out.println("\tu -> update info");
        System.out.println("\tm -> calculate average market price");
        System.out.println("\tp -> calculate price trend");
        System.out.println("\tc -> add comment");
        System.out.println("\tb -> go back");
        
        String command = input.next();
        
        processItemCommand(command, i);
    }

    // EFFECTS: processes command for item functions
    private void processItemCommand(String c, Item i) {
        if (c.equals("u")) {
            updateItem(i);
        } else if (c.equals("m")) {
            doCalculateAverageMarketPrice(i);
        } else if (c.equals("p")) {
            doCalculatePriceTrend(i);
        } else if (c.equals("c")) {
            doAddComment(i);
        } else if (c.equals("b")) {
            return;
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // EFFECTS: displays options for user command for item update
    private void updateItem(Item i) {
        printCurrentInfo(i);
        System.out.println("\nWhat would you like to update?");
        System.out.println("\tn -> name");
        System.out.println("\ti -> item number");
        System.out.println("\ted -> edition");
        System.out.println("\tex -> exclusive");
        System.out.println("\td -> release date");
        System.out.println("\tco -> condition");
        System.out.println("\tca -> category");
        
        String command = input.next();
        processUpdate(command, i);
    }

    // EFFECTS: processes user command for item update
    private void processUpdate(String c, Item i) {
        if (c.equals("n")) {
            updateName(i);
        } else if (c.equals("i")) {
            updateItemNumber(i);
        } else if (c.equals("ed")) {
            updateEdition(i);
        } else if (c.equals("ex")) {
            updateExclusive(i);
        } else if (c.equals("d")) {
            updateReleaseDate(i);
        } else if (c.equals("co")) {
            updateCondition(i);
        } else if (c.equals("ca")) {
            updateCategory(i);
        } else {
            System.out.println("Invalid command.");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates item's category
    private void updateCategory(Item i) {
        System.out.println("Enter new category: ");
        String category = input.next();
        i.setCategory(category);
        System.out.println("Updated.");
    }

    // MODIFIES: this
    // EFFECTS: updates item's condition
    private void updateCondition(Item i) {
        System.out.println("\nSelect a condition: ");
        System.out.println("\tn -> new");
        System.out.println("\tm -> mint condition");
        System.out.println("\tg -> good");
        System.out.println("\tf -> fair");
        System.out.println("\tp -> poor");
        String condition = input.next();
        i.setCondition(condition);
        System.out.println("Updated.");
    }

    // MODIFIES: this
    // EFFECTS: updates item's release date
    private void updateReleaseDate(Item i) {
        System.out.println("Enter new release date (yyyy mm dd): ");
        String date = input.next();
        i.setReleaseDate(date);
        System.out.println("Updated.");
    }

    // MODIFIES: this
    // EFFECTS: updates item's exclusive
    private void updateExclusive(Item i) {
        System.out.println("Enter new exclusive: ");
        String exclusive = input.next();
        i.setExclusive(exclusive);
        System.out.println("Updated.");
    }

    private void updateEdition(Item i) {
        System.out.println("Enter new edition: ");
        String edition = input.next();
        i.setEdition(edition);
        System.out.println("Updated.");
    }

    // MODIFIES: this
    // EFFECTS: updates item's item number
    private void updateItemNumber(Item i) {
        System.out.println("Enter new item number: ");
        Integer num = input.nextInt();
        i.setItemNumber(num);
        System.out.println("Updated.");
    }

    // MODIFIES: this
    // EFFECTS: updates item's name
    private void updateName(Item i) {
        System.out.println("Enter new name: ");
        String name = input.next();
        i.setName(name);
        System.out.println("Updated.");
    }

    // MODIFIES: this
    // EFFECTS: calculates average market price and updates current market price of item
    private void doCalculateAverageMarketPrice(Item i) {
        List<Double> prices = new ArrayList<>();
        double p;

        System.out.println("How many market prices do you have?");
        int n = input.nextInt();
        for (int k = 0; k < n; k++) {
            System.out.println("Enter a price: ");
            p = input.nextDouble();
            prices.add(p);
        }
        System.out.println("$" + i.calculateAverageMarketPrice(prices));
    }

    // EFFECTS: calculates price trend of item;
    //          if current market price is not set, uses default value of 12.99
    private void doCalculatePriceTrend(Item i) {
        System.out.println("What was the original price of this item?");
        Double price = input.nextDouble();
        System.out.println(i.calculatePriceTrend(price) + "%");
    }

    // MODIFIES: this
    // EFFECTS: adds comment to item's comments
    private void doAddComment(Item i) {
        System.out.println("Enter comment: ");
        String comment = input.next();
        i.addComment(comment);
        System.out.println("Comments: " + i.getComments());
    }

    // EFFECTS: displays item's information
    private void printCurrentInfo(Item i) {
        System.out.println("Name: " + i.getName());
        System.out.println("Item Number: " + i.getItemNumber());
        System.out.println("Edition: " + i.getEdition());
        System.out.println("Exclusive: " + i.getExclusive());
        System.out.println("Release Date: " + i.getReleaseDate());
        System.out.println("Current Market Price: $" + i.getCurrentMarketPrice() + " (Default price: 12.99)");
        System.out.println("Condition: " + i.getCondition() + " (Default condition: NA)");
        System.out.println("Category: " + i.getCategory());
        System.out.println("Comments: " + i.getComments() + " (Default comments: NA)");
    }

    // EFFECTS: displays collections in collection list
    private void printCollections() {
        System.out.println("Your collections:");
        for (Collection c : collectionList) {
            System.out.println("\t" + c.getName());
        }
    }

    // EFFECTS: displays items in collection
    private void printItems(Collection c) {
        System.out.println("Your items: ");
        for (Item i : c.getItems()) {
            System.out.println("\t" + i.getName());
        }
    }

    // EFFECTS: returns true if collection list is empty
    private boolean isCollectionListEmpty() {
        if (collectionList.size() == 0) {
            System.out.println("No collections added.");
            return true;
        }
        return false;
    }

    // EFFECTS: returns true if collection is empty (no items)
    private boolean isCollectionEmpty(Collection c) {
        if (c.getItems().size() == 0) {
            System.out.println("No items added.");
            return true;
        }
        return false;
    }
}