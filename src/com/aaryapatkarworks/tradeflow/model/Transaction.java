package com.aaryapatkarworks.tradeflow.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String transactionType;
    private Stock stock;
    private int quantity;
    private double price;
    private LocalDateTime transactionTime;

    public Transaction(String transactionType,
                       Stock stock,
                       int quantity,
                       double price) {

        this.transactionType = transactionType;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.transactionTime = LocalDateTime.now();
    }

    // ---------------- Getters ----------------

    public String getTransactionType() {
        return transactionType;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    // ---------------- Display ----------------

    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("-------------------------------------");
        System.out.println("Transaction Type : " + transactionType);
        System.out.println("Company          : " + stock.getCompanyName());
        System.out.println("Symbol           : " + stock.getStockSymbol());
        System.out.println("Quantity         : " + quantity);
        System.out.printf("Price            : ₹%.2f%n", price);
        System.out.printf("Total Value      : ₹%.2f%n", price * quantity);
        System.out.println("Date & Time      : " + transactionTime.format(formatter));
    }
}