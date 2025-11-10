package com.pluralsight.capstone2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A Drink Item includes the name of the drink it is, the size of the drink (Small-12oz,
 * Regular-20oz, or Large-30oz), and its price. The price of a drink is based solely
 * off of its size.
 *
 * @author Ravi Spigner
 */
public class Drink extends Item {
    //options
    private static final List<String> sizes = new ArrayList<>(Arrays.asList("Small-12oz",
            "Regular-20oz", "Large-30oz"));
    private static final List<String> options = new ArrayList<>(Arrays.asList("Sprite",
            "Coke-Regular", "Coke-Diet",
            "Coke-Masala", "Mountain Dew-Regular",
            "Mountain Dew-Baja Blast", "Fanta-Orange",
            "Pepsi-Regular", "Pepsi-Diet",
            "Ginger Ale"));
    public Drink(String name, String size) {
        super(name, size,
                size.equalsIgnoreCase(sizes.get(0)) ? 2.00:
                size.equalsIgnoreCase(sizes.get(1)) ? 2.50 :
                size.equalsIgnoreCase(sizes.get(2)) ? 3.00 :
                2.00
        );
    }
    @Override
    public String toString() {
        return "Drink: " + super.getItemName() + ", Size: " + super.getSize() +
                ", Price: $" + String.format("%.2f", getTotalPrice());
    }
    public static List<String> getOptions() {
        return options;
    }
    public static List<String> getSizes() {
        return sizes;
    }
    public double getTotalPrice() {
        return getBasePrice();
    }
}
