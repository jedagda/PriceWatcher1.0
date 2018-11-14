package main.item;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private List<Item> items;

    public ItemManager() {
        items = new ArrayList<>();
    }

    public Object count() {
        // TODO Auto-generated method stub
        return items.size();
    }

    public Item addItem(String name, String url) {
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

}