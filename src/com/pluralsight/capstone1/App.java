package com.pluralsight.capstone1;

import java.time.LocalDateTime;

/**
 * Simulates an Accounting Ledger application (ran from a command line interface).
 * Includes tests (test1() method) that can be run in order to include transactions from
 * earlier in the year, last year, and over a year ago (from the time of running the application).
 *
 * @author Ravi Spigner
 */
public class App {
    public static void main(String[] args) {
        Menus.displayWelcome();
        //---Test Inputs (prior to learning JUnit for this course) for more
        //transactions before current ones given by running a command line (App) session---
        test1(); //uncomment this entire line to run tests
        Menus.mainMenu();
    }

    public static void test1() {
        Ledger testInputsLedgerReference = Menus.getLedger();
        //makeTransaction() args order: String description, String depositorOrVendorName, double amount
        //recent transactions, this current year or last year
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusMonths(1), "cash deposit",
                "Joe", 100); //deposit 1
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusMonths(1).minusDays(14),
                "Punching Bag", "Amazon", -100); //payment 1
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusMonths(2), "cash deposit",
                "Joe", 100); //deposit 2
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusMonths(2).minusDays(14),
                "Chicken Strips", "QFC", -10); //payment 2
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusMonths(4), "paycheck",
                "Generic Workplace", 400); //deposit 3
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusMonths(5), "paycheck",
                "Generic Workplace", 400); //deposit 4

        //older transactions, from last year or older
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusYears(1),
                "cash deposit", "Ravi", 80); //deposit 5
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusYears(1).minusMonths(2),
                "SnowPro Microphone-refund", "Amazon", 100); //deposit 6
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusYears(1).minusMonths(4),
                "Caesar Salad", "Safeway", -6); //payment 3
        testInputsLedgerReference.makeTransaction(LocalDateTime.now().minusYears(1).minusMonths(11),
                "Sandwich Purchase", "Subway", -11); //payment 4
    }
}
