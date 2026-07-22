package com.aaryapatkarworks.tradeflow;

public class Holding {

    Stock stock;
    int quantity;
    double averagePrice;

    Holding(Stock stock, int quantity, double averagePrice) {

        this.stock = stock;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    void displayHolding() {

        System.out.println("Stock      : " + stock.companyName);
        System.out.println("Symbol     : " + stock.stockSymbol);
        System.out.println("Quantity   : " + quantity);
        System.out.printf("Avg Price  : ₹%.2f%n", averagePrice);
    }
}