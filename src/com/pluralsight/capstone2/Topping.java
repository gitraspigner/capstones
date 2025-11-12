package com.pluralsight.capstone2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A Topping (of a Sandwich) contains its type of topping (Meat, Cheese, Veggie, or Sauce),
 * a name of the topping, and whether the topping is an extra topping or not. The price of the
 * topping is calculated based on the size of the sandwich, the type of topping it is, and whether
 * it is extra of a topping or not.
 *
 * @author Ravi Spigner
 */
public class Topping {
    //options
    private static final List<String> toppingTypes = new ArrayList<>(Arrays.asList("Meat",
            "Cheese", "Veggie", "Sauce"));
    private static final List<String> meatOptions = new ArrayList<>(Arrays.asList("Steak",
            "Ham", "Salami", "Roast Beef", "Chicken", "Bacon", "None"
    ));
    private static final List<String> cheeseOptions = new ArrayList<>(Arrays.asList("American",
            "Provolone", "Cheddar", "Swiss", "Havarti", "None"
    ));
    private static final List<String> veggieOptions = new ArrayList<>(Arrays.asList("Lettuce",
            "Peppers", "Onions", "Tomatoes", "Jalapenos", "Cucumbers", "Pickles",
            "Guacamole", "Mushrooms", "None"
    ));
    private static final List<String> sauceOptions = new ArrayList<>(Arrays.asList("Mayonnaise",
            "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette", "Barbecue", "None"
    ));
    private String toppingType;
    private String toppingName;
    private String sandwichSize;
    private boolean isExtra;
    public Topping(String toppingType, String toppingName, String sandwichSize, boolean isExtra) {
        this.toppingType = toppingType;
        this.toppingName = toppingName;
        this.sandwichSize = sandwichSize;
        this.isExtra = isExtra;
    }
    public boolean isExtra() {
        return isExtra;
    }
    public static List<String> getToppingTypes() {
        return toppingTypes;
    }
    public static List<String> getMeatOptions() {
        return meatOptions;
    }
    public static List<String> getCheeseOptions() {
        return cheeseOptions;
    }
    public static List<String> getVeggieOptions() {
        return veggieOptions;
    }
    public static List<String> getSauceOptions() {
        return sauceOptions;
    }
    public String getToppingName() {
        return toppingName;
    }
    public double getToppingPrice() {
        double toppingPrice;
        //meat topping : extra or not
        if (toppingType.equalsIgnoreCase("Meat")) {
            if (isExtra) { //is extra topping
                if(sandwichSize.equalsIgnoreCase("Small-4inch")) {
                    toppingPrice = 0.50;
                } else if (sandwichSize.equalsIgnoreCase("Regular-8inch")) {
                    toppingPrice = 1.00;
                } else if(sandwichSize.equalsIgnoreCase("Large-12inch")) {
                    toppingPrice = 1.50;
                } else { //default price is small for unrecognizable size argument
                    toppingPrice = 0.50;
                }
            } else { //is regular/non-extra topping
                if(sandwichSize.equalsIgnoreCase("Small-4inch")) {
                    toppingPrice = 1.00;
                } else if (sandwichSize.equalsIgnoreCase("Regular-8inch")) {
                    toppingPrice = 2.00;
                } else if(sandwichSize.equalsIgnoreCase("Large-12inch")) {
                    toppingPrice = 3.00;
                } else { //default price is small for unrecognizable size argument
                    toppingPrice = 1.00;
                }
            }
            //cheese topping : extra or not
        } else if (toppingType.equalsIgnoreCase("Cheese")) {
            if (isExtra) { //is extra topping
                if(sandwichSize.equalsIgnoreCase("Small-4inch")) {
                    toppingPrice = 0.30;
                } else if (sandwichSize.equalsIgnoreCase("Regular-8inch")) {
                    toppingPrice = 0.60;
                } else if(sandwichSize.equalsIgnoreCase("Large-12inch")) {
                    toppingPrice = 0.90;
                } else { //default price is small for unrecognizable size argument
                    toppingPrice = 0.30;
                }
            } else { //is regular/non-extra topping
                if(sandwichSize.equalsIgnoreCase("Small-4inch")) {
                    toppingPrice = 0.75;
                } else if (sandwichSize.equalsIgnoreCase("Regular-8inch")) {
                    toppingPrice = 1.50;
                } else if(sandwichSize.equalsIgnoreCase("Large-12inch")) {
                    toppingPrice = 2.25;
                } else { //default price is small for unrecognizable size argument
                    toppingPrice = 0.70;
                }
            }
        } else { //veggie or sauce topping
            toppingPrice = 0.0;
        }
        return toppingPrice;
    }
    @Override
    public String toString() {
        if(isExtra) {
            return "\t\t\tExtra Topping: " + toppingName +
                    ", Price: $" + String.format("%.2f", getToppingPrice());
        } else {
            return "\t\tTopping: " + toppingName +
                    ", Price: $" + String.format("%.2f", getToppingPrice());
        }
    }
}
