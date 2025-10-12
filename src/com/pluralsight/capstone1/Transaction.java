package com.pluralsight.capstone1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class Transaction {
    //private LocalDate date;
    private LocalDateTime dateTime;
    private String description;
    private String depositorOrVendorName;
    private double amount;
    //private boolean isPayment; //negative amounts are payments

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
