package main.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemManagerTest {
    private ItemManager itemManager;

    @BeforeEach
    public void setUp() throws Exception {
        itemManager = new ItemManager();
    }

    @Test
    void testConstructor() {
        assertEquals(0, itemManager.count());
    }

    @Test
    public void testAddItem() {
        Item item = new Item();
        itemManager.addItem(item);
        assertEquals(1, itemManager.count());
        assertTrue(itemManager.contains(item));
    }
}

