package com.pluralsight.capstone2;
/**
 * Simulates a Sandwich Shop application (ran from a command line interface).
 * A receipt is displayed & stored in the following format:
 * ---------------------------
 * MM-DD-YYYY
 * ---------------------------
 * -Order Number: [Number], Order Name: [Name]
 *      Item: Sandwich: [Sandwich Name], Size: [Size], Base Price: $[Price]
 * 	        Topping: [Topping Name], Price: $[Price]
 * 		        Extra Topping: [Topping Name], Price: $[Price]
 *      Item: Chips: [Chip Name], Price: $[Price]
 *      Item: Drink: [Drink Name], Size: [Size], Price: $[Price]
 * Total Order Price: $[Price]
 * ...
 * ...
 * -Order Number: [Number], Order Name: [Name]
 * ...
 * ---------------------------
 * MM-DD-YYYY
 * ---------------------------
 * ...
 * ...
 * And are stored in the following filepath/filename:
 * src/com/pluralsight/capstone2/receipts.csv
 * which can be edited within the Menus class via the filePath variable.
 *
 * @author Ravi Spigner
 */
public class App {
    public static void main(String[] args) {
        Menus.mainMenu();
    }
}
