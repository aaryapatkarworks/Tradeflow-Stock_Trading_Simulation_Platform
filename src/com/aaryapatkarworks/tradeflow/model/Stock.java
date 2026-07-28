package com.aaryapatkarworks.tradeflow.model;

public class Stock {

    private int stockId;
    private String companyName;
    private String stockSymbol;
    private double currentPrice;

    public Stock(int stockId,
                 String companyName,
                 String stockSymbol,
                 double currentPrice) {

        this.stockId = stockId;
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
    }

    // ---------------- Getters ----------------

    public int getStockId() {
        return stockId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    // ---------------- Business Methods ----------------

    public void updatePrice(double newPrice) {

        System.out.printf(
                "\n%s price updated from ₹%.2f to ₹%.2f%n",
                stockSymbol,
                currentPrice,
                newPrice
        );

        currentPrice = newPrice;
    }

    public void displayStock() {

        System.out.println("Stock ID      : " + stockId);
        System.out.println("Company       : " + companyName);
        System.out.println("Symbol        : " + stockSymbol);
        System.out.printf("Current Price : ₹%.2f%n", currentPrice);
    }
}