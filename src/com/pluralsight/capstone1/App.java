package com.pluralsight.capstone1;
/**
 * Simulates an Accounting Ledger application (ran from a command line interface).
 * A transaction is displayed in the following format:
 * date|time|description|vendor|amount
 * And are stored in the following filepath/filename:
 * src/com/pluralsight/capstone1/transactions-[username].csv
 * which can be edited within the Menus class via the filePath variable.
 *
 * @author Ravi Spigner
 */
public class App {
    public static void main(String[] args) {
        Menus.mainMenu();
    }
}
