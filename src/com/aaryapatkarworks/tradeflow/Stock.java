package com.aaryapatkarworks.tradeflow;

public class Stock {

    int stockId;
    String companyName;
    String stockSymbol;
    double currentPrice;

    Stock(int stockId,
          String companyName,
          String stockSymbol,
          double currentPrice) {

        this.stockId = stockId;
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
    }

    void displayStock() {

        System.out.println("========== STOCK ==========");
        System.out.println("ID        : " + stockId);
        System.out.println("Company   : " + companyName);
        System.out.println("Symbol    : " + stockSymbol);
        System.out.printf("Price     : ₹%.2f%n", currentPrice);
        System.out.println("===========================");
    }
}