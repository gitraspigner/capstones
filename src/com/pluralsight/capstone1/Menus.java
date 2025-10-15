package com.pluralsight.capstone1;

import java.util.Scanner;

/**
 * Displays menus and gathers user input to simulate an Accounting Ledger application.
 * A non-empty username is required to display the menus and for the user to give input
 * to the Accounting Ledger application.
 *
 * @author Ravi Spigner
 */
public class Menus {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Ledger ledger = new Ledger();
    private static final String filePath = "src/com/pluralsight/capstone1/";

    //ask to include test data (ranging from this year to a year ago)
    public static void askForTest1() {
        String input;

        while (true) {
            System.out.println("---Test Inputs?---");
            System.out.println("Would you like to include some test transactions that range " +
                    "from the current year to a little over a year ago?");
            System.out.print("Please enter an option (Y-Yes, N-No): ");
            input = scanner.nextLine().toUpperCase().trim();

            if (input.equals("Y")) {
                ledger.addTest1();
                break;
            } else if (input.equals("N")) {
                break;
            } else {
                System.out.println("-------------------");
                System.out.println("ERROR: Invalid Menu Option: " + input);
                System.out.println("Only a single letter (Y, N) entered is " +
                        "acceptable for your menu input");
                System.out.println("-------------------");
                continue;
            }
        }
    }

    public static void mainMenu() {
        String username;
        String input;

        displayWelcome();

        while (true) {
            System.out.print("Please enter your username: ");
            username = scanner.nextLine().trim();
            if (!username.isEmpty()) {
                //username (and transactions are) written to the file at
                //the end of a user's session
                break;
            } else {
                System.out.println("-------------------");
                System.out.println("ERROR: Empty Username Entered");
                System.out.println("-------------------");
                continue;
            }
        }
        //populate the ledger with existing file data of transactions, if any
        ledger.buildLedgerFromFile(filePath, username);
        //ask to include test data (ranging from this year to a year ago)
        askForTest1();


        //begin main menu
        while (true) {
            System.out.println("---Main Menu---");
            System.out.print("Please enter an option (D-Add Deposit, P-Make Payment (Debit), " +
                    "L-Ledger, X-Exit Application): ");
            input = scanner.nextLine().toUpperCase().trim();

            if (input.equals("D")) {
                //Make deposit
                addDeposit();
            } else if (input.equals("P")) {
                //Make payment
                makePayment();
            } else if (input.equals("L")) {
                //Display ledger screen
                ledger();
            } else if (input.equals("X")) {
                //Exit
                writeToFileUsername(filePath, username);
                displayGoodbye();
                break;
            }else {
                System.out.println("-------------------");
                System.out.println("ERROR: Invalid Menu Option: " + input);
                System.out.println("Only a single letter (D, P, L, or X) entered is " +
                        "acceptable for your menu input");
                System.out.println("-------------------");
                continue;
            }
        }
    }


    public static void writeToFileUsername(String filePath, String username) {
        ledger.writeToFileUsername(filePath, username);
    }

    public static void ledger() {
        String input;
        while (true) {
            System.out.println("---Ledger---");
            System.out.print("Please enter an option (A-Display All Entries, " +
                    "D-Display Deposits, P-Display Payments, R-Reports, H-Go Back To The Home " +
                    "Screen): ");
            input = scanner.nextLine().toUpperCase().trim();

            if (input.equals("A")) {
                //Display all entries (payments & transactions)
                displayAllEntries();
            } else if (input.equals("D")) {
                //Display all transactions
                displayAllDeposits();
            } else if (input.equals("P")) {
                //Display all payments
                displayAllPayments();
            } else if (input.equals("R")) {
                //Display reports screen
                reports();
            } else if (input.equals("H")) {
                //Exit back to the home screen
                break;
            }else {
                System.out.println("-------------------");
                System.out.println("ERROR: Invalid Menu Option: " + input);
                System.out.println("Only a single letter (A, D, P, R, or H) entered is " +
                        "acceptable for your menu input");
                System.out.println("-------------------");
                continue;
            }
        }
    }

    public static void reports() {
        String input;
        while (true) {
            System.out.println("---Reports---");
                System.out.print("Please enter an option (1-Display Month-To-Date Transactions, " +
                    "2-Display Previous Month Transactions, 3-Display Year-To-Date Transactions,\n" +
                        " 4-Display Previous Year Transactions, 5-Search By Vendor, 6-Go Back To " +
                        "The Ledger Page): ");
            input = scanner.nextLine().toUpperCase().trim();

            if (input.equals("1")) {
                //Display Month-To-Date Transactions
                monthToDateTransactions();
            } else if (input.equals("2")) {
                //Display Previous Month Transactions
                previousMonthTransactions();
            } else if (input.equals("3")) {
                //Display Year-To-Date Transactions
                previousYearToDateTransactions();
            } else if (input.equals("4")) {
                //Display Previous Year Transactions
                previousYearTransactions();
            } else if (input.equals("5")) {
                //Search By Vendor
                searchVendor();
            } else if (input.equals("6")) {
                //Exit back to the home screen
                break;
            }else {
                System.out.println("-------------------");
                System.out.println("ERROR: Invalid Menu Option: " + input);
                System.out.println("Only a single number (1, 2, 3, 4, 5, or 6) entered is " +
                        "acceptable for your reports menu input");
                System.out.println("-------------------");
                continue;
            }
        }
    }

    public static void searchVendor() {
            System.out.println("---Vendor Search---");
            System.out.print("Please enter the name of the vendor to search: ");
            String input = scanner.nextLine().trim();
            ledger.displayVendorTransactions(input);
    }

    public static void monthToDateTransactions() {
        System.out.println("---Month To Date Transactions---");
        ledger.displayTransactionsMonthToDate();
    }

    public static void previousMonthTransactions() {
        System.out.println("---Month To Date Transactions---");
        ledger.displayTransactionsPreviousMonth();
    }

    public static void previousYearToDateTransactions() {
        System.out.println("---Month To Date Transactions---");
        ledger.displayTransactionsYearToDate();
    }

    public static void previousYearTransactions() {
        System.out.println("---Month To Date Transactions---");
        ledger.displayTransactionsPreviousYear();
    }

    public static void displayAllEntries() {
        System.out.println("---All Payments & Deposits---");
        ledger.displayAllTransactions();
    }

    public static void displayAllDeposits() {
        System.out.println("---All Deposits---");
        ledger.displayAllDeposits();
    }

    public static void displayAllPayments() {
        System.out.println("---All Payments---");
        ledger.displayAllPayments();
    }

    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input); //will return true for doubles/decimals and ints
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //ask user for deposit input, then call Ledger's makeTransaction()
    public static void addDeposit() {
        String depositorName;
        String description;
        String depositAmountString;
        boolean depositSuccessful = false;
        while (!depositSuccessful) {
            System.out.println("---Deposit Entry---");
            System.out.print("Please enter the depositor's name: ");
            depositorName = scanner.nextLine().trim();
            if (isNumber(depositorName)) {
                System.out.println("-------------------");
                System.out.println("ERROR: " + depositorName + " is a number, not a name");
                System.out.println("-------------------");
                break;
            }
            System.out.print("Please enter a description of your Deposit (i.e. Invoice or " +
                    "Cash Deposit): ");
            description = scanner.nextLine().trim();
            if (isNumber(description)) {
                System.out.println("-------------------");
                System.out.println("ERROR: " + description + " is a number, not a name");
                System.out.println("-------------------");
                break;
            }
            System.out.print("Please enter your deposit amount in Dollars (i.e. 150.00 or 150): ");
            depositAmountString = scanner.nextLine().trim();
            double depositAmountDouble;
            if(depositorName.contains("*") || description.contains("*")) {
                System.out.println("-------------------");
                System.out.println("ERROR: Depositor Name or Description cannot contain '*'");
                System.out.println("-------------------");
                break;
            }
            try {
                depositAmountDouble = Double.parseDouble(depositAmountString);
                //if deposit entered has negative amount, means it is a payment
                if(depositAmountDouble < 0.0) {
                    System.out.println("-------------------");
                    System.out.println("ERROR: Invalid Menu Deposit: " + depositAmountString
                    + " must be greater than $0.00");
                    System.out.println("-------------------");
                    break;
                }
            } catch (Exception e) {
                System.out.println("-------------------");
                System.out.println("ERROR: Invalid Menu Option: " + depositAmountString);
                System.out.println("-------------------");
                break;
            }
            depositSuccessful = ledger.makeTransaction(description, depositorName,
                    depositAmountDouble);
            if(depositSuccessful) {
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Deposit unsuccessful.");
                break;
            }
        }
    }

    //ask user for payment input, then call Ledger's makeTransaction()
    public static void makePayment() {
        String vendorName;
        String description;
        String paymentAmountString;
        boolean paymentSuccessful = false;
        while (!paymentSuccessful) {
            System.out.println("---Payment Entry---");
            System.out.print("Please enter the Vendor/Payee's name (i.e. Amazon): ");
            vendorName = scanner.nextLine().trim();
            if (isNumber(vendorName)) {
                System.out.println("-------------------");
                System.out.println("ERROR: " + vendorName + " is a number, not a name");
                System.out.println("-------------------");
                break;
            }
            System.out.print("Please enter a description what your payment was for (i.e. " +
                    "Headphones): ");
            description = scanner.nextLine().trim();
            if (isNumber(description)) {
                System.out.println("-------------------");
                System.out.println("ERROR: " + description + " is a number, not a name");
                System.out.println("-------------------");
                break;
            }
            System.out.print("Please enter your payment amount in Dollars (i.e. 150.00): ");
            paymentAmountString = scanner.nextLine().trim();
            double paymentAmountDouble;
            if(vendorName.contains("*") || description.contains("*")) {
                System.out.println("-------------------");
                System.out.println("ERROR: Vendor Name or Description cannot contain '*'");
                System.out.println("-------------------");
                break;
            }
            try {
                paymentAmountDouble = Double.parseDouble(paymentAmountString);
                //if payment entered has positive amount, means it is a deposit
                if(paymentAmountDouble < 0.0) {
                    System.out.println("-------------------");
                    System.out.println("ERROR: Invalid Menu Payment: " + paymentAmountString
                            + " must be greater than $0.00");
                    System.out.println("-------------------");
                    break;
                }
            } catch (Exception e) {
                System.out.println("-------------------");
                System.out.println("ERROR: Invalid Menu Option: " + paymentAmountString);
                System.out.println("-------------------");
                break;
            }
            paymentSuccessful = ledger.makeTransaction(description, vendorName,
                    -1.00 * paymentAmountDouble);
            if(paymentSuccessful) {
                System.out.println("Payment successful!");
            } else {
                System.out.println("Payment unsuccessful.");
                break;
            }
        }
    }

    public static void displayWelcome() {
        System.out.println("---Welcome To The Accounting Ledger App!---");
    }

    public static void displayGoodbye() {
        System.out.println("---Thank you!---");
        System.out.println("---See you next time!---");
    }
}
