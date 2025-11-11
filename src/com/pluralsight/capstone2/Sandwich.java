package com.pluralsight.capstone2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A Sandwich Item has a type of bread for it, the size of the bread (Small-4inch, Regular-8inch,
 * or Large-12inch), and an assortment of toppings on it (those of which include Meat, Cheese,
 * and/or Veggies). The total price of a Sandwich will be determined by its base size price
 * and the Toppings it has.
 *
 * @author Ravi Spigner
 */
public class Sandwich extends Item {
    //options
    private static final List<String> sizes = new ArrayList<>(Arrays.asList("Small-4inch",
            "Regular-8inch", "Large-12inch"));
    private static final List<String> breadOptions = new ArrayList<>(Arrays.asList("White",
            "Wheat", "Italian", "Rye", "Jalapeno Cheese", "Flatbread", "Wrap:White",
            "Wrap:Whole Wheat Multigrain"
    ));
    //sandwich-specific extras
    //TODO: use this toppings thing, maybe make an extra constructor
    private ArrayList<Topping> toppings;
    private String breadType;
    public Sandwich(String name, String size, String breadType) {
        super(name, size,
                size.equalsIgnoreCase(sizes.get(0)) ? 5.50 :
                size.equalsIgnoreCase(sizes.get(1)) ? 7.00 :
                size.equalsIgnoreCase(sizes.get(2)) ? 8.50 :
                 5.50 //default price is small for unrecognizable size argument that made it this far
        );
        this.breadType = breadType;
        this.toppings = new ArrayList<>();
    }
    @Override
    public String toString() {
        String text;
        text = "Sandwich: " + super.getItemName() + ", Size: " + super.getSize() +
                ", Bread: " + breadType + ", Base Price: $" +
                String.format("%.2f", super.getBasePrice());
        for(Topping t : toppings) {
            text += "\n" + t.toString();
        }
        text += "\nTotal Sandwich Price: $" + String.format("%.2f", getTotalPrice());
        return text;
    }
    public double getTotalPrice() {
        double price = getBasePrice();
        for(Topping t : toppings) {
            price += t.getToppingPrice();
        }
        return price;
    }
    public static List<String> getBreadOptions() {
        return breadOptions;
    }
    public static List<String> getSizeOptions() {
        return sizes;
    }
    public void addTopping(Topping t) {
        toppings.add(t);
    }
    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}
