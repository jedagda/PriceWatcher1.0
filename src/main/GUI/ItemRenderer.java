package main.GUI;

import main.item.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class ItemRenderer extends ItemView implements ListCellRenderer<Item> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        setItem(item);
        repaint();
        return this;

    }

}