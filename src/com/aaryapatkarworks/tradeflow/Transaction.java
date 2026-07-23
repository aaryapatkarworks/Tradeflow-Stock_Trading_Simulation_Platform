package com.aaryapatkarworks.tradeflow;

import java.time.LocalDateTime;

public class Transaction {

    String transactionType;
    Stock stock;
    int quantity;
    double price;
    LocalDateTime transactionTime;

    Transaction(String transactionType,
                Stock stock,
                int quantity,
                double price) {

        this.transactionType = transactionType;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.transactionTime = LocalDateTime.now();
    }

    void displayTransaction() {

        System.out.println("================================");
        System.out.println("Type      : " + transactionType);
        System.out.println("Stock     : " + stock.companyName);
        System.out.println("Symbol    : " + stock.stockSymbol);
        System.out.println("Quantity  : " + quantity);
        System.out.printf("Price     : ₹%.2f%n", price);
        System.out.println("Time      : " + transactionTime);
        System.out.println("================================");
    }
}