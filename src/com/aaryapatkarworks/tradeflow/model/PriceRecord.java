package com.aaryapatkarworks.tradeflow.model;

import java.time.LocalDateTime;

public class PriceRecord {

    private double price;
    private LocalDateTime timestamp;

    public PriceRecord(double price,
                       LocalDateTime timestamp) {

        this.price = price;
        this.timestamp = timestamp;
    }

    // ---------------- Getters ----------------

    public double getPrice() {

        return price;
    }

    public LocalDateTime getTimestamp() {

        return timestamp;
    }

    // ---------------- Display ----------------

    public void displayRecord() {

        System.out.printf(
                "₹%.2f | %s%n",
                price,
                timestamp
        );
    }
}