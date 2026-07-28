package com.aaryapatkarworks.tradeflow.model;

public class Holding {

    private Stock stock;
    private int quantity;
    private double averagePrice;

    public Holding(Stock stock,
                   int quantity,
                   double averagePrice) {

        this.stock = stock;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    // ---------------- Getters ----------------

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    // ---------------- Business Methods ----------------

    public void addQuantity(int newQuantity,
                            double purchasePrice) {

        double totalInvestment =
                (quantity * averagePrice)
                        + (newQuantity * purchasePrice);

        quantity += newQuantity;

        averagePrice = totalInvestment / quantity;
    }

    public void removeQuantity(int sellQuantity) {

        quantity -= sellQuantity;
    }

    public boolean isEmpty() {

        return quantity == 0;
    }

    // ---------------- Portfolio Calculations ----------------

    public double getInvestedAmount() {

        return quantity * averagePrice;
    }

    public double getCurrentValue() {

        return quantity * stock.getCurrentPrice();
    }

    public double getProfitLoss() {

        return getCurrentValue() - getInvestedAmount();
    }

    // ---------------- Display ----------------

    public void displayHolding() {

        System.out.println("------------------------------");
        System.out.println("Company        : " + stock.getCompanyName());
        System.out.println("Symbol         : " + stock.getStockSymbol());
        System.out.println("Quantity       : " + quantity);
        System.out.printf("Average Price  : ₹%.2f%n", averagePrice);
        System.out.printf("Current Price  : ₹%.2f%n", stock.getCurrentPrice());
        System.out.printf("Current Value  : ₹%.2f%n", getCurrentValue());
        System.out.printf("Profit / Loss  : ₹%.2f%n", getProfitLoss());
    }
}