package com.pluralsight.capstone2.Tests;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for Sandwich class, also tests the Topping class.
 *
 * @author Ravi Spigner
 */
import com.pluralsight.capstone2.Sandwich;
import com.pluralsight.capstone2.Topping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
class SandwichTest {
    private Sandwich sandwich;
    @BeforeEach
    void setUp() {
        //create a small wheat custom sandwich
        sandwich = new Sandwich("Custom Sandwich", "Small-4inch", "Wheat");
    }
    @Test
    void testToString() {
        String result = sandwich.toString();
        assertTrue(result.contains("Custom Sandwich"));
        assertTrue(result.contains("Small-4inch"));
        assertTrue(result.contains("Wheat"));
    }
    @Test
    void getTotalPrice() {
        //test for a small wheat turkey sandwich, extra swiss cheese
        double basePrice = sandwich.getBasePrice();
        Topping meat = new Topping("Meat", "Turkey", "Small-4inch", false);
        Topping cheese = new Topping("Cheese", "Swiss", "Small-4inch", false);
        Topping extraCheese = new Topping("Cheese", "Swiss",
                "Small-4inch", true);
        sandwich.addTopping(meat);
        sandwich.addTopping(cheese);
        sandwich.addTopping(extraCheese);
        double totalPrice = sandwich.getTotalPrice();
        //expectedPrice = base price + price of each topping
        double expectedPrice = basePrice + meat.getToppingPrice() + cheese.getToppingPrice() +
                extraCheese.getToppingPrice();
        assertEquals(expectedPrice, totalPrice);
    }
    @Test
    void getBreadOptions() {
        List<String> breads = Sandwich.getBreadOptions();
        assertNotNull(breads);
        assertTrue(!breads.isEmpty());
        assertTrue(breads.contains("White") || breads.contains("Wheat")
                || breads.contains("Italian")  || breads.contains("Rye")
                || breads.contains("Jalapeno Cheese") || breads.contains("Flatbread")
                || breads.contains("Wrap:White") ||
                breads.contains("Wrap:Whole Wheat Multigrain"));
    }
    @Test
    void getSizeOptions() {
        List<String> sizes = Sandwich.getSizeOptions();
        assertNotNull(sizes);
        assertTrue(!sizes.isEmpty());
        assertTrue(sizes.get(0).contains("Small-4inch") || sizes.get(1).contains("Medium-8inch")
                || sizes.get(2).contains("Large-12inch"));
    }
    @Test
    void addTopping() {
        //create a small wheat custom sandwich, add ham to it
        Topping topping = new Topping("Meat", "Ham", "Small-4inch", false);
        sandwich.addTopping(topping);
        assertTrue(sandwich.getToppings().contains(topping));
    }
    @Test
    void addExtraTopping() {
        //create a small wheat custom sandwich, add ham + extra ham to it
        Topping topping = new Topping("Meat", "Ham", "Small-4inch", false);
        Topping extraTopping = new Topping("Meat", "Ham", "Small-4inch", true);
        sandwich.addTopping(topping);
        sandwich.addTopping(extraTopping);
        assertTrue(sandwich.getToppings().contains(topping) &&
                sandwich.getToppings().contains(extraTopping));
    }
    @Test
    void getToppings() {
        assertNotNull(sandwich.getToppings(), "Toppings list should be initialized");
        sandwich.addTopping(new Topping("Meat", "Ham", "Small-4inch", false));
        sandwich.addTopping(new Topping("Meat", "Ham", "Small-4inch", true));
        assertEquals(2, sandwich.getToppings().size(), "Sandwich must have two toppings");
    }
}
