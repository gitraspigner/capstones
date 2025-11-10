package com.pluralsight.capstone2;
/**
 *  An Item has a name (which is the name of a Sandwich, a Drink, or a Chip), its size
 *  (typically Small, Medium, or Large, except in the case of Chips which are always
 *  Regular sized), and its price.
 *
 * @author Ravi Spigner
 */
public abstract class Item {
    private String itemName;
    private String size; //Small, Regular, Large
    private double basePrice;
    public Item(String itemName, String size, double basePrice) {
        this.itemName = itemName;
        this.size = size;
        this.basePrice = basePrice;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    @Override
    public abstract String toString();
    public abstract double getTotalPrice();
}
