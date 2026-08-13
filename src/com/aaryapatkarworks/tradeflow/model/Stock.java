package com.aaryapatkarworks.tradeflow.model;

public class Stock {

    private int stockId;
    private String companyName;
    private String stockSymbol;
    private double currentPrice;
    private double previousPrice;
    private double priceChange;
    private double priceChangePercentage;

    public Stock(int stockId,
                 String companyName,
                 String stockSymbol,
                 double currentPrice) {

        this.stockId = stockId;
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
        this.previousPrice = currentPrice;
        this.priceChange = 0;
        this.priceChangePercentage = 0;
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

    public double getPreviousPrice() {

        return previousPrice;
    }

    public double getPriceChange() {

        return priceChange;
    }

    public double getPriceChangePercentage() {

        return priceChangePercentage;
    }

    // ---------------- Business Methods ----------------

    public void updatePrice(double newPrice) {

        previousPrice = currentPrice;

        currentPrice = newPrice;

        priceChange =
                currentPrice - previousPrice;

        if (previousPrice != 0) {

            priceChangePercentage =
                    (priceChange / previousPrice) * 100;

        } else {

            priceChangePercentage = 0;
        }

        System.out.printf(
                "\n%s price updated from ₹%.2f to ₹%.2f%n",
                stockSymbol,
                previousPrice,
                currentPrice
        );
    }

    public void displayStock() {

        System.out.println("Stock ID      : " + stockId);
        System.out.println("Company       : " + companyName);
        System.out.println("Symbol        : " + stockSymbol);
        System.out.printf("Current Price : ₹%.2f%n", currentPrice);
    }
}