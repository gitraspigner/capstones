package com.pluralsight.capstone2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A Chip Item includes the name of the chip it is, and its price. It also includes the size of
 * the chip, which is always Regular. The price of a chip item is always $1.50 because it
 * is only based off of its size, which is always one size (Regular).
 *
 * @author Ravi Spigner
 */
public class Chip extends Item {
    private static final double onlyChipPrice = 1.50; //one size for all chips: Regular
    //options
    private static final List<String> options = new ArrayList<>(Arrays.asList(
            "Doritos-Nacho Cheese", "Doritos-Cool Ranch",
            "Lays-Original", "Lays-Sour Cream & Onion", "Funyuns",
            "Fritos-Chili Cheese", "Salted Pretzels", "Calbee Shrimp Chips"));

    public Chip(String name) {
        super(name, "Regular", onlyChipPrice);
    }
    @Override
    public String toString() {
        return "Chips: " + super.getItemName() + ", Price: $" +
                String.format("%.2f", getTotalPrice());
    }
    public static List<String> getOptions() {
        return options;
    }

    public double getTotalPrice() {
        return getBasePrice();
    }
}
