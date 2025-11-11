package com.pluralsight.capstone2;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This manages (stores) orders for a Sandwich Shop.
 * Orders, which include Sandwiches, Drinks, and Chips are written to a log file.
 * The log file is titled: "receipts.csv".
 * A receipts are stored in the following format:
 * ---------------------------
 * MM-DD-YYYY
 * ---------------------------
 * -Order Number: [Number], Order Name: [Name]
 * Item: Sandwich: [Sandwich Name], Size: [Size], Base Price: $[Price]
 * 	Topping: [Topping Name], Price: $[Price]
 * 		Extra Topping: [Topping Name], Price: $[Price]
 * Item: Chips: [Chip Name], Price: $[Price]
 * Item: Drink: [Drink Name], Size: [Size], Price: $[Price]
 * Total Order Price: $[Price]
 * ...
 * ...
 * -Order Number: [Number], Order Name: [Name]
 * ...
 * ---------------------------
 * MM-DD-YYYY
 * ---------------------------
 * -Order Number: [Number], Order Name: [Name]
 * ...
 * ...
 * And are stored in the following filepath/filename:
 * src/com/pluralsight/capstone2/receipts.csv
 * which can be edited within the Menus class via the filePath variable.
 *
 * @author Ravi Spigner
 */
public class ReceiptsFileManager {
    private static final String receiptsFilePath = Menus.filePath;
    public static void writeToReceipts() {
        String oldFileContents = "";
        //this syntax for the try block
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(receiptsFilePath, true))) {
            //write current session receipts (stored newest to oldest)
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String formattedDate = now.format(formatter);
            bufferedWriter.write("---------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write(formattedDate);
            bufferedWriter.newLine();
            bufferedWriter.write("---------------------------");
            bufferedWriter.newLine();
            for (Order o : Menus.orders) {
                //write order
                bufferedWriter.write("-Order Number: " + o.getOrderNumber() + ", Order Name: " +
                        o.getOrderName());
                bufferedWriter.newLine();
                for (Item i : o.getItems()) {
                    if (i instanceof Sandwich) {
                        Sandwich s = (Sandwich) i;
                        bufferedWriter.write("Item: Sandwich: " + s.getItemName() +
                                ", Size: " + s.getSize() + ", Base Price: $" + String.format("%.2f",
                                s.getBasePrice()));
                        bufferedWriter.newLine();
                        for (Topping t : s.getToppings()) {
                            if (t.isExtra()) {
                                bufferedWriter.write("\t\tExtra Topping: " + t.getToppingName() +
                                        ", Price: $" + String.format("%.2f", t.getToppingPrice()));
                            } else {
                                bufferedWriter.write("\tTopping: " + t.getToppingName() +
                                        ", Price: $" + String.format("%.2f", t.getToppingPrice()));
                            }
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.write("\tTotal Sandwich Price: $" + String.format("%.2f",
                                s.getTotalPrice()));
                        bufferedWriter.newLine();
                    } else if (i instanceof Chip) {
                        Chip c = (Chip) i;
                        bufferedWriter.write("Item: Chips: " + c.getItemName() + " Price: $" +
                                String.format("%.2f", c.getTotalPrice()));
                        bufferedWriter.newLine();
                    } else if (i instanceof Drink) {
                        Drink d = (Drink) i;
                        bufferedWriter.write("Item: Drink: " + d.getItemName() + " Size: " +
                                d.getSize() + " Price: $" + String.format("%.2f", d.getTotalPrice()));
                        bufferedWriter.newLine();
                    }
                }
                //write order total
                bufferedWriter.write("Total Order Price: $" + String.format("%.2f",
                        o.getTotalPrice()));
                bufferedWriter.newLine();
            }
            //closes automatically using try block syntax above,
            //no need for manual bufferedWriter.close() here
        } catch (IOException e) {
            System.out.println("-------------------");
            System.out.println("ERROR: Invalid file path " +
                    receiptsFilePath); //could not write to file, possible invalid filepath
            System.out.println("-------------------");
        }
    }
    public static double getTotalRevenuePreviousSessions() {
        double totalRevenue = 0.0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(receiptsFilePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //if line contains total order price "Total Order Price:"
                if (line.trim().startsWith("Total Order Price:")) {
                    try {
                        //get Total Order Price and add to totalrevenue
                        String priceStr = line.substring(line.indexOf('$') + 1).trim();
                        double price = Double.parseDouble(priceStr);
                        totalRevenue += price;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("-------------------");
            System.out.println("No existing recepts file at: " + receiptsFilePath);
            System.out.println("This might be your first time running this program, " +
                    "so that's ok!");
            System.out.println("-------------------");
        }
        return totalRevenue;
    }


}
