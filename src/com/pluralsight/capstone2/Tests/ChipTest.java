package com.pluralsight.capstone2.Tests;
import com.pluralsight.capstone2.Chip;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for Chip Class.
 *
 * @author Ravi Spigner
 */
class ChipTest {
    @Test
    void testChipPrice() {
        Chip chip = new Chip("Doritos-Nacho Cheese");
        assertEquals(1.50, chip.getTotalPrice(),
                "Any chip must have a price of $1.50");
    }
    @Test
    void testToString() {
        Chip chip = new Chip("Lays-Original");
        String text = chip.toString();
        assertTrue(text.contains("Chips:"), "toString must include 'Chips:'");
        assertTrue(text.contains("Lays-Original"), "toString must include '[typeOfChip]'");
        assertTrue(text.contains("Price: $1.50"), "toString must include 'Price: $1.50'");
    }
    @Test
    void testChipOptions() {
        List<String> options = Chip.getOptions();
        assertNotNull(options, "Chip options must not be null");
        assertTrue(options.size() >= 5, "Chip options list must have multiple chip flavors");
        //few chip options to test that options list has been initialized properly
        assertTrue(options.contains("Doritos-Nacho Cheese"), "Chip options must have option: " +
                "Doritos-Nacho Cheese");
        assertTrue(options.contains("Lays-Original"), "Chip options must have option: " +
                "Lays-Original");
        assertTrue(options.contains("Calbee Shrimp Chips"), "Options must have option: " +
                "Calbee Shrimp Chips");
    }
    @Test
    void testTotalPriceEqualsBasePrice() {
        Chip chip = new Chip("Funyuns");
        assertEquals(chip.getBasePrice(), chip.getTotalPrice(),
                "getBasePrice must return the same price as getTotalPrice");
    }
    @Test
    void testChipSizeIsAlwaysRegular() {
        Chip chip = new Chip("Fritos-Chili Cheese");
        assertEquals("Regular", chip.getSize(),
                "Chip size must always be 'Regular'");
    }
    @Test
    void testDifferentChipNamesSamePrice() {
        Chip chip1 = new Chip("Lays-Sour Cream & Onion");
        Chip chip2 = new Chip("Salted Pretzels");
        assertEquals(chip1.getTotalPrice(), chip2.getTotalPrice(),
                "Drink Total Price must be the same as Base Price");
    }
}