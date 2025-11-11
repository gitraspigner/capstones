package com.pluralsight.capstone2.Tests;
import com.pluralsight.capstone2.Drink;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for Drink Class.
 *
 * @author Ravi Spigner
 */
class DrinkTest {
    @Test
    void testSmallDrinkPrice() {
        Drink drink = new Drink("Coke-Masala", "Small-12oz");
        assertEquals(2.00, drink.getTotalPrice(),
                "Small-12oz drink must cost $2.00");
    }
    @Test
    void testRegularDrinkPrice() {
        Drink drink = new Drink("Coke-Masala", "Regular-20oz");
        assertEquals(2.50, drink.getTotalPrice(),
                "Regular-20oz drink must cost $2.50");
    }
    @Test
    void testLargeDrinkPrice() {
        Drink drink = new Drink("Coke-Masala", "Large-30oz");
        assertEquals(3.00, drink.getTotalPrice(),
                "Large-30oz drink must cost $3.00");
    }
    @Test
    void testDefaultPriceWhenInvalidSize() {
        Drink drink = new Drink("Coke-Masala", "INVALID");
        assertEquals(2.00, drink.getTotalPrice(),
                "Invalid size must default to Small ($2.00)");
    }
    @Test
    void testToStringFormat() {
        Drink drink = new Drink("Coke-Masala", "Large-30oz");
        String result = drink.toString();

        assertTrue(result.contains("Drink:"), "toString must include 'Drink:' label");
        assertTrue(result.contains("Coke-Masala"), "toString must include drink name");
        assertTrue(result.contains("Large-30oz"), "toString must include size");
        assertTrue(result.contains("$3.00"), "toString must include formatted price");
    }
    @Test
    void testGetOptions() {
        List<String> options = Drink.getOptions();
        assertNotNull(options, "Options list should not be null");
        assertTrue(options.size() >= 10, "Drink options list must contain drink options " +
                "(at least 10)");
        assertTrue(options.contains("Sprite"), "Drink options list must include 'Sprite'");
        assertTrue(options.contains("Coke-Regular"), "Drink options list must include 'Coke-Regular'");
    }
    @Test
    void testGetSizes() {
        List<String> sizes = Drink.getSizes();
        assertNotNull(sizes, "Sizes list should not be null");
        assertEquals(3, sizes.size(), "Sizes options list must contain exactly 3 options");
        assertTrue(sizes.contains("Small-12oz"), "Sizes options list must include 'Small-12oz'");
        assertTrue(sizes.contains("Regular-20oz"), "Sizes options list must include 'Regular-20oz'");
        assertTrue(sizes.contains("Large-30oz"), "Sizes options list must include 'Large-30oz'");
    }
    @Test
    void testTotalPriceEqualsBasePrice() {
        Drink drink = new Drink("Ginger Ale", "Large-30oz");
        assertEquals(drink.getBasePrice(), drink.getTotalPrice(),
                "Drink Total Price must be the same as Base Price");
    }
}