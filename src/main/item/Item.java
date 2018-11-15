package main.item;

import java.awt.image.BufferedImage;

/*
 * Item class stores information of an item
 */
public class Item {

    /** Name of the Item */
    private String name;
    /** URL of the item */
    private String url;
    /** Current price of the item */
    private double price;
    /** Initial price of the item*/
    private double initialPrice;
    /** Change in price of the item */
    private double change;
    /** Date when the price was added */
    private String dateAdded;
    /** Image of the Item */
    private BufferedImage image;

    /**
     * Constructor for Item class with no arguments
     */
    public Item(){
        name = "null";
        initialPrice = 0.0;
        price = 0.0;
    }

    /**
     * Constructor for Item
     * @param name represents the name of the Item.
     * @param url represents the URL address of the Item.
     * @param initialPrice represents the price of the Item.
     * @param change represents the percentage change in price of the Item.
     * @param dateAdded represents the date when the Item was added to PriceWatcher.
     */


    public Item(String name, String url, double initialPrice, double change, String dateAdded){
        this.name = name;
        this.url = url;
        this.price = initialPrice;
        this.initialPrice = initialPrice;
        this.change = change;
        this.dateAdded = dateAdded;
    }

    /**
     * Sets the price for Item and sets the percentage change of price of the item
     * @param newPrice is the newest price of Item
     */
    public void setPrice(double newPrice){
        this.price = newPrice;
        this.change= ((this.price - this.initialPrice)/this.initialPrice)*100;
    }

    /**
     * Returns the name of the Item
     * @return name of the Item
     * @see #name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the URL of the Item
     * @return the URL of the Item
     * @see #url
     */
    public String getURL(){
        return url;
    }

    /**
     * Returns the current price of the Item
     * @return the current price of the Item
     * @see #price
     */
    public double getPrice(){
        return price;
    }

    /**
     * Returns the initial price of the Item
     * @return the initial price of the Item
     * @see #initialPrice
     */
    public double getInitialPrice(){
        return initialPrice;
    }

    /**
     * Returns the change in price of the Item
     * @return the change in price of the Item
     * @see #change
     */
    public double getChange(){
        return change;
    }

    public String getDateAdded(){
        return dateAdded;
    }

    public String getPriceToString(){
        return Double.toString(price);
    }

    public String getPriceChangeToString(){
        return Double.toString(getChange());
    }






}