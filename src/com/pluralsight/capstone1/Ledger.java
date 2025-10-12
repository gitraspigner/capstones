package com.pluralsight.capstone1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * *******Add program description here******
 * Alternate name for this would be transaction manager.
 *
 * @author Ravi Spigner
 */
public class Ledger {
    private final ArrayList<Transaction> ledger;

    public Ledger() {
        this.ledger = new ArrayList<Transaction>();
    }

    public Ledger(ArrayList<Transaction> ledger) {
        this.ledger = ledger;
    }

    public void displayAllTransactions() {
        //System.out.println("List of transactions: ");
        for(Transaction t : ledger) {
            System.out.println(t);
        }
    }

    public void displayAllDeposits() {
        //System.out.println("List of transactions: ");
        for(Transaction t : ledger) {
            if (t.getAmount() > 0.0) { //deposits only have positive amounts
                System.out.println(t);
            }
        }
    }

    public void displayAllPayments() {
        //System.out.println("List of transactions: ");
        for(Transaction t : ledger) {
            if (t.getAmount() < 0.0) { //payments only have negative amounts
                System.out.println(t);
            }
        }
    }

    public boolean makeTransaction(String description, String depositorOrVendorName,
                                   double amount) {
        return ledger.add(new Transaction(LocalDateTime.now(), description, depositorOrVendorName,
                amount));
    }

    public ArrayList<Transaction> getTransactionsByDateRange(LocalDateTime start,
                                                              LocalDateTime end) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();
        for(Transaction t : ledger) {
            if(t.getDateTime().isAfter(start) && t.getDateTime().isBefore(end)) {
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
            System.out.println(t);
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
            System.out.println(t);
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
            System.out.println(t);
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
            System.out.println(t);
        }
    }


    public void writeToFile(String filename) {
        //will need to use append always
        //this syntax for the try block
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write("new user at: " + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss")));
            bufferedWriter.newLine();

            for (Transaction t : ledger) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
            }
            //closes automatically using try block syntax above,
            //no need for manual bufferedWriter.close() here
        } catch (IOException e) {
            e.printStackTrace(); // Could not write to file
        }
    }
}
