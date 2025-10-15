package com.pluralsight.capstone1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 * Represents a ledger for a command-line-driven accounting ledger program.
 * This ledger manages (stores & manipulates) transactions.
 * Transactions, which are of 2 categories: deposits (positive dollar amounts) and payments
 * (negative dollar amounts) in the ledger can be created, displayed (in multiple date ranges)
 * and written to a log file. Transactions written to the file include the current user's name
 * using the application, followed by their transactions. Transactions from previous users
 * will also be stored to the file to help keep a log between different executions of
 * the application. In order to reset the log file, it must be deleted (in this version of this
 * application, the log file is titled: "transactions-[username].csv").
 * A transaction is displayed in the following format:
 * date|time|description|vendor|amount
 * And stored in the following filepath/filename:
 * src/com/pluralsight/capstone1/transactions-[username].csv
 * which can be edited within the Menus class via the filePath variable.
 *
 * @author Ravi Spigner
 */
public class Ledger {
    private final ArrayList<Transaction> ledger;
    public Ledger() {
        this.ledger = new ArrayList<>();
    }
    public Ledger(ArrayList<Transaction> ledger) {
        this.ledger = ledger;
    }
    public void addTest1() {
        //older transactions ordered oldest to most recent
        makeTransaction(LocalDateTime.now().minusYears(1).minusMonths(11),
                "TestTransaction-Sandwich Purchase", "Subway", -11); //payment 4
        makeTransaction(LocalDateTime.now().minusYears(1).minusMonths(4),
                "TestTransaction-Caesar Salad", "Safeway", -6); //payment 3
        makeTransaction(LocalDateTime.now().minusYears(1).minusMonths(2),
                "TestTransaction-SnowPro Microphone-refund", "Amazon", 100); //deposit 6

        //newer transactions ordered oldest to most recent
        makeTransaction(LocalDateTime.now().minusYears(1),
                "TestTransaction-cash deposit", "Ravi", 80); //deposit 5
        makeTransaction(LocalDateTime.now().minusMonths(5), "TestTransaction-paycheck",
                "Generic Workplace", 400); //deposit 4
        makeTransaction(LocalDateTime.now().minusMonths(4), "TestTransaction-paycheck",
                "Generic Workplace", 400); //deposit 3
        makeTransaction(LocalDateTime.now().minusMonths(2).minusDays(14),
                "TestTransaction-Chicken Strips", "QFC", -10); //payment 2
        makeTransaction(LocalDateTime.now().minusMonths(2),
                "TestTransaction-cash deposit", "Joe", 100); //deposit 2
        makeTransaction(LocalDateTime.now().minusMonths(1).minusDays(14),
                "TestTransaction-Punching Bag", "Amazon", -100); //payment 1
        makeTransaction(LocalDateTime.now().minusMonths(1),
                "TestTransaction-cash deposit", "Joe", 100); //deposit 1
    }
    public void displayCustomTransactions(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                          String description, String vendorOrName,
                                          double upperAmount, double lowerAmount) {
        for(Transaction t : ledger) {
            //checks if transaction is not user login, description is same,
            //and vendorOrName is same, within upper and lower amount limits,
            //and within start and end date/time limits
            if (t.isTransaction() &&
                    //description same
                    (t.getDescription().equalsIgnoreCase(description) ||
                        t.getDescription().toLowerCase().contains(description.toLowerCase())) &&
                    //vendor or name same
                    (t.getDepositorOrVendorName().equalsIgnoreCase(vendorOrName) ||
                            t.getDepositorOrVendorName().toLowerCase().contains(
                            vendorOrName.toLowerCase())) &&
                    //amount within bounds
                    t.getAmount() <= upperAmount && t.getAmount() >= lowerAmount &&
                    //date & time within bounds
                    t.getDateTime().isAfter(startDateTime) &&
                    t.getDateTime().isBefore(endDateTime)) {
                System.out.println(t);
            }
        }
    }
    public void displayAllTransactions() {
        for(Transaction t : ledger) {
            if (t.isTransaction()) {
                System.out.println(t);
            }
        }
    }
    public void displayAllDeposits() {
        for(Transaction t : ledger) {
            if (t.isTransaction() && t.getAmount() > 0.0) { //deposits only have positive amounts
                System.out.println(t);
            }

        }
    }
    public void displayAllPayments() {
        for(Transaction t : ledger) {
            if (t.isTransaction() && t.getAmount() < 0.0) { //payments only have negative amounts
                System.out.println(t);
            }
        }
    }
    public void displayVendorTransactions(String vendorName) {
        for(Transaction t : ledger) {
            if(t.isTransaction() && t.getDepositorOrVendorName().equalsIgnoreCase(vendorName)) {
                System.out.println(t);
            }
        }
    }
    public ArrayList<Transaction> getTransactionsByDateRange(LocalDateTime start,
                                                              LocalDateTime end) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();
        for(Transaction t : ledger) {
            if(t.isTransaction() && t.getDateTime().isAfter(start) &&
                    t.getDateTime().isBefore(end)) {
                filteredTransactions.add(t);
            }
        }
        return filteredTransactions;
    }
    //transactions from the start of the current month until now
    public void displayTransactionsMonthToDate() {
        LocalDateTime now = LocalDateTime.now();
        //now -> first day of this month (at 12:00AM)
        LocalDateTime firstDayOfCurrentMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).
                withSecond(0).withNano(0);
        ArrayList<Transaction> tList =  getTransactionsByDateRange(firstDayOfCurrentMonth, now);
        for(Transaction t : tList) {
            if (t.isTransaction()) {
                System.out.println(t);
            }
        }
    }
    //transactions from the start to the end of the entire previous calendar month
    public void displayTransactionsPreviousMonth() {
        //now -> first day of this month (at 12:00AM) -> first day of last month
        LocalDateTime lastMonthStart = LocalDateTime.now().withDayOfMonth(1).withHour(0).
                withMinute(0).withSecond(0).withNano(0).minusMonths(1);
        //now -> first day of this month (at 12:00AM) -> last nanosecond of last month
        LocalDateTime lastMonthEnd = LocalDateTime.now().withDayOfMonth(1).withHour(0).
                withMinute(0).withSecond(0).withNano(0).minusNanos(1);
        ArrayList<Transaction> tList =  getTransactionsByDateRange(lastMonthStart, lastMonthEnd);
        for(Transaction t : tList) {
            if (t.isTransaction()) {
                System.out.println(t);
            }
        }
    }
    //transactions from the start of the current year until now
    public void displayTransactionsYearToDate() {
        LocalDateTime now = LocalDateTime.now();
        //now -> first day of this year (at 12:00AM)
        LocalDateTime firstDayOfCurrentYear = now.withDayOfYear(1).withHour(0).withMinute(0).
                withSecond(0).withNano(0);
        ArrayList<Transaction> tList =  getTransactionsByDateRange(firstDayOfCurrentYear, now);
        for(Transaction t : tList) {
            if (t.isTransaction()) {
                System.out.println(t);
            }
        }
    }
    //transactions from start to end of entire previous calendar year
    public void displayTransactionsPreviousYear() {
        //now -> first day of this year (at 12:00AM) -> first day of last year
        LocalDateTime lastYearStart = LocalDateTime.now().withDayOfYear(1).withHour(0).
                withMinute(0).withSecond(0).withNano(0).minusYears(1);
        //now -> first day of this year (at 12:00AM) -> last nanosecond of last year
        LocalDateTime lastYearEnd = LocalDateTime.now().withDayOfYear(1).withHour(0).
                withMinute(0).withSecond(0).withNano(0).minusNanos(1);
        ArrayList<Transaction> tList =  getTransactionsByDateRange(lastYearStart, lastYearEnd);
        for(Transaction t : tList) {
            if (t.isTransaction()) {
                System.out.println(t);
            }
        }
    }
    //uses current time for Transaction's dateTime
    //adds transaction to the top of (first element of the transaction list (ledger))
    public boolean makeTransaction(String description, String depositorOrVendorName,
                                   double amount) {
        try {
            ledger.add(0,new Transaction(LocalDateTime.now(), description, depositorOrVendorName,
                    amount));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //uses current time for Transaction's dateTime
    //adds transaction to the bottom of (last element of the transaction list (ledger))
    public boolean makeTransactionAtEnd(String description, String depositorOrVendorName,
                                        double amount) {
        try {
            ledger.add(new Transaction(LocalDateTime.now(), description, depositorOrVendorName,
                    amount));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //uses dateTime parameter for Transaction's dateTime
    //adds transaction to the top of (first element of the transaction list) ledger
    public boolean makeTransaction(LocalDateTime dateTime, String description,
                                   String depositorOrVendorName, double amount) {
        try {
            ledger.add(0, new Transaction(dateTime, description, depositorOrVendorName,
                    amount));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //uses dateTime parameter for Transaction's dateTime
    //adds transaction to the bottom of (last element of the transaction list) (ledger)
    public boolean makeTransactionAtEnd(LocalDateTime dateTime, String description,
                                        String depositorOrVendorName, double amount) {
        try {
            ledger.add(new Transaction(dateTime, description, depositorOrVendorName,
                    amount));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //uses dateTime parameter for Transaction's dateTime
    //adds user login as a transaction to the top of the transaction list (ledger)
    public boolean makeUserLogin(LocalDateTime dateTime, String username) {
        try {
            ledger.add(0, new Transaction(dateTime, "***new user login ("+username+")***",
                    "", 0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //uses dateTime parameter for Transaction's dateTime
    //adds user login as a transaction to the top of the transaction list (ledger)
    public boolean makeUserLoginAtEnd(LocalDateTime dateTime, String username) {
        try {
            ledger.add(new Transaction(dateTime, "***new user login ("+username+")***",
                    "", 0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input); //will return true for doubles/decimals and ints
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isDate(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static boolean isTime(String input) {
        try {
            LocalTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    //read through file (if it exists) and build ledger including all transactions, also include user logins
    public void buildLedgerFromFile(String filePath, String username) {
        String userFilenamePath = filePath+"transactions-"+ username +".csv";
        //read user file only if it exists
        if (Files.exists(Path.of(userFilenamePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFilenamePath))) {
                String line;
                String dateString;
                String timeString;
                String description;
                String vendorOrName;
                String amountString;
                String[] transactionData;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    transactionData = line.split("\\|");
                    if (transactionData.length != 5) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: Invalid line (format) found: " + line);
                        System.out.println("Must have 5 components separated by '|' character");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line and keep reading, may as well
                    }
                    dateString = transactionData[0];
                    if (!isDate(dateString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + dateString + " is not a valid date");
                        System.out.println("Proper Format: yyyy-MM-dd");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    LocalDate date = LocalDate.parse(transactionData[0]);
                    timeString = transactionData[1];
                    if (!isTime(timeString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + timeString + " is not a valid time");
                        System.out.println("Proper Format: HH:mm:ss");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    LocalTime time = LocalTime.parse(transactionData[1]);
                    LocalDateTime dateTime = LocalDateTime.of(date, time);
                    description = transactionData[2];
                    if (isNumber(description)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + description + " is a number, not a " +
                                "description (consisting of words)");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    //check if the line/transaction we're viewing is a user login
                    if (line.contains("***new user login")) {
                        makeUserLoginAtEnd(dateTime, username); //only dateTime & username needed
                                                             //to create a user login transaction
                        continue; //after creating new user login as transaction, skip rest of line
                    }
                    vendorOrName = transactionData[3];
                    if (isNumber(vendorOrName)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + vendorOrName + " is a number, not a " +
                                "name (consisting of words)");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    amountString = transactionData[4];
                    if (!isNumber(amountString)) {
                        System.out.println("-------------------");
                        System.out.println("ERROR: " + amountString + " is not a number");
                        System.out.println("File Line Number: " + lineNumber);
                        System.out.println("-------------------");
                        continue; //skip the line
                    }
                    double amount = Double.parseDouble(transactionData[4]);
                    makeTransactionAtEnd(dateTime, description, vendorOrName, amount);
                }
            } catch (IOException e) {
                System.out.println("No previous transactions log file to read for user.");
            }
            makeUserLogin(LocalDateTime.now(), username);
        } else {
            makeUserLogin(LocalDateTime.now(), username);
        }
    }
    public void writeToFileUsername(String filePath, String username) {
        String userFilenamePath = filePath+"transactions-"+ username +".csv";
            //this syntax for the try block
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(userFilenamePath))) {
                //write current session transactions (stored newest to oldest)
                for (Transaction t : ledger) {
                    if (t.isTestTransaction()) {
                        continue; //omit test transactions from log file
                    }
                    bufferedWriter.write(t.toString());
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

}
