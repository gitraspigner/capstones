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
    public static void writeToReceipts(String filePath) {
        String receiptsFilePath = Menus.filePath;
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
                // Write order details without extra headers
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
                    filePath); //could not write to file, possible invalid filepath
            System.out.println("-------------------");
        }
    }

//read through file (if it exists) and build total revenue
//    public void buildTotalRevenueFromFile(String filePath, String username) {
//        String userFilenamePath = filePath+"receipts-"+ username +".csv";
//        //read user file only if it exists
//        if (Files.exists(Path.of(userFilenamePath))) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(userFilenamePath))) {
//                String line;
//                String dateString;
//                String timeString;
//                String description;
//                String vendorOrName;
//                String amountString;
//                String[] transactionData;
//                int lineNumber = 0;
//                while ((line = reader.readLine()) != null) {
//                    lineNumber++;
//                    transactionData = line.split("\\|");
//                    if (transactionData.length != 5) {
//                        System.out.println("-------------------");
//                        System.out.println("ERROR: Invalid line (format) found: " + line);
//                        System.out.println("Must have 5 components separated by '|' character");
//                        System.out.println("File Line Number: " + lineNumber);
//                        System.out.println("-------------------");
//                        continue; //skip the line and keep reading, may as well
//                    }
//                    dateString = transactionData[0];
//                    if (!isDate(dateString)) {
//                        System.out.println("-------------------");
//                        System.out.println("ERROR: " + dateString + " is not a valid date");
//                        System.out.println("Proper Format: yyyy-MM-dd");
//                        System.out.println("File Line Number: " + lineNumber);
//                        System.out.println("-------------------");
//                        continue; //skip the line
//                    }
//                    LocalDate date = LocalDate.parse(transactionData[0]);
//                    timeString = transactionData[1];
//                    if (!isTime(timeString)) {
//                        System.out.println("-------------------");
//                        System.out.println("ERROR: " + timeString + " is not a valid time");
//                        System.out.println("Proper Format: HH:mm:ss");
//                        System.out.println("File Line Number: " + lineNumber);
//                        System.out.println("-------------------");
//                        continue; //skip the line
//                    }
//                    LocalTime time = LocalTime.parse(transactionData[1]);
//                    LocalDateTime dateTime = LocalDateTime.of(date, time);
//                    description = transactionData[2];
//                    if (isNumber(description)) {
//                        System.out.println("-------------------");
//                        System.out.println("ERROR: " + description + " is a number, not a " +
//                                "description (consisting of words)");
//                        System.out.println("File Line Number: " + lineNumber);
//                        System.out.println("-------------------");
//                        continue; //skip the line
//                    }
//                    //check if the line/transaction we're viewing is a user login
//                    if (line.contains("***new user login")) {
//                        makeUserLoginAtEnd(dateTime, username); //only dateTime & username needed
//                        //to create a user login transaction
//                        continue; //after creating new user login as transaction, skip rest of line
//                    }
//                    vendorOrName = transactionData[3];
//                    if (isNumber(vendorOrName)) {
//                        System.out.println("-------------------");
//                        System.out.println("ERROR: " + vendorOrName + " is a number, not a " +
//                                "name (consisting of words)");
//                        System.out.println("File Line Number: " + lineNumber);
//                        System.out.println("-------------------");
//                        continue; //skip the line
//                    }
//                    amountString = transactionData[4];
//                    if (!isNumber(amountString)) {
//                        System.out.println("-------------------");
//                        System.out.println("ERROR: " + amountString + " is not a number");
//                        System.out.println("File Line Number: " + lineNumber);
//                        System.out.println("-------------------");
//                        continue; //skip the line
//                    }
//                    double amount = Double.parseDouble(transactionData[4]);
//                    makeTransactionAtEnd(dateTime, description, vendorOrName, amount);
//                }
//            } catch (IOException e) {
//                System.out.println("Error reading file (file may be unreadable or restricted).");
//            }
//            makeUserLogin(LocalDateTime.now(), username);
//        } else {
//            makeUserLogin(LocalDateTime.now(), username);
//        }
//    }
}
