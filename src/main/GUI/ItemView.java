package main.GUI;

import main.item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {


    /** Interface to notify a click on the view page icon. */
    public interface ClickListener {
        /** Callback to be invoked when the view page icon is clicked. */
        void clicked();
    }

    /** Directory for image files: src/image in Eclipse. */
    private final static String IMAGE_DIR = "/resources/images/";

    /** View-page clicking listener. */
    private ClickListener listener;

    private Item item;

    /** Create a new instance. */
    public ItemView() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
                    listener.clicked();
                }
            }
        });
    }

    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    /** Create a relationship between ItemView and Item class*/
    public void setItem(Item item){
        this.item = item;
    }


    /** Overridden here to display the details of the item. */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        int x = 20, y = 30;
        final int lineGap = 20;
        //displays image of item
        g.drawImage(getImage("view.png"), 0, 0,null);
        //displays information of item
        x+=300;
        paintName(g, x, y, "Name:", item.getName());
        y += lineGap;
        paintURL(g, x, y, "URL:", item.getURL());
        y += lineGap;
        paintPrice(g, x, y);
        y += lineGap;
        paintChange(g, x, y);
        y += lineGap;
        paintDate(g, x, y);

    }

    private void paintDate(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif",Font.BOLD,12));
        g.drawString("Added:", x , y);
        g.setFont(new Font("Sans-Serif",Font.PLAIN,12));
        g.drawString(item.getDateAdded() + "    "+ "(" + "$"+item.getInitialPrice()+ ")", x+75, y);
    }

    private void paintChange(Graphics g, int x, int y) {
        g.setFont(new Font("Serif",Font.BOLD,12));
        g.drawString("Change:", x , y);
        g.setFont(new Font("Sans-Serif",Font.PLAIN,12));
        g.setColor(Color.BLUE);
        g.drawString(item.getPriceChangeToString(), x+75, y);
    }

    private void paintPrice(Graphics g, int x, int y) {
        g.setFont(new Font("Serif",Font.BOLD,12));
        g.drawString("Price:", x , y);
        g.setFont(new Font("Sans-Serif",Font.PLAIN,12));
        String cP = "$" + String.format("");
        g.drawString("$" + Double.toString(item.getPrice()), x+75, y);
    }

    private void paintName(Graphics g, int x, int y, String s, String name) {
        g.setFont(new Font("Serif", Font.BOLD, 12));
        g.drawString(s, x, y);
        g.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        g.drawString(name, x + 75, y);
    }

    private void paintURL(Graphics g, int x, int y, String s, String name) {
        g.setFont(new Font("Serif", Font.BOLD, 12));
        g.drawString(s, x, y);
        g.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        g.drawString(name, x + 75, y);
    }

    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
        //--
        //-- WRITE YOUR CODE HERE
        //--
        return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }

    /** Return the image stored in the given file. */
    public Image getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}