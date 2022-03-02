package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    private Collection collection1;
    private Collection collection2;
    private Collection collection3;

    @BeforeEach
    void setup() {
        user = new User("Alison");
        collection1 = new Collection("Funko Pop!");
        collection2 = new Collection("Hot Wheels");
        collection3 = new Collection("Sneakers");
    }

    @Test
    public void constructorTest() {
        assertEquals("Alison", user.getName());
        assertEquals(0, user.getCollections().size());
    }

    @Test
    public void addOneCollectionTest() {
        user.addCollection(collection1);
        assertEquals(1, user.getCollections().size());
        assertEquals(collection1, user.getCollections().get(0));
    }

    @Test
    public void addManyCollectionsTest() {
        user.addCollection(collection1);
        assertEquals(1, user.getCollections().size());
        assertEquals(collection1, user.getCollections().get(0));

        user.addCollection(collection2);
        assertEquals(2, user.getCollections().size());
        assertEquals(collection2, user.getCollections().get(1));
    }
}
