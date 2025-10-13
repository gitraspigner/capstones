package com.pluralsight.capstone1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a transaction for a command-line-driven accounting ledger program.
 * Transactions are of 2 categories: deposits (positive dollar amounts) and payments
 * (negative dollar amounts).
 * A transaction contains the following information: the date and time of its processing, a
 * description of the type of deposit or (for payments) the item purchased, the name of the
 * depositor or vendor, and the dollar amount deposited or paid.
 * A transaction displayed is in the following format:
 * date|time|description|vendor|amount
 *
 * @author Ravi Spigner
 */
public class Transaction {
    private LocalDateTime dateTime;
    private String description;
    private String depositorOrVendorName;
    private double amount;

    public Transaction() {
        this.dateTime = LocalDateTime.now();
        this.description = "";
        this.depositorOrVendorName = "";
        this.amount = 0.0;
    }

    public Transaction(LocalDateTime dateTime, String description, String depositorOrVendorName, double amount) {
        this.dateTime = dateTime;
        this.description = description;
        this.depositorOrVendorName = depositorOrVendorName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        return  formattedDate + "|"
                + formattedTime + "|"
                + description + "|"
                + depositorOrVendorName + "|"
                + amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepositorOrVendorName() {
        return depositorOrVendorName;
    }

    public void setDepositorOrVendorName(String depositorOrVendorName) {
        this.depositorOrVendorName = depositorOrVendorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
