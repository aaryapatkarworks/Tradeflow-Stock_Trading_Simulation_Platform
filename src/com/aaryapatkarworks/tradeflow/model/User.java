package com.aaryapatkarworks.tradeflow.model;

import java.util.ArrayList;

public class User {

    private int userId;
    private String fullName;
    private String email;
    private String password;
    private double walletBalance;

    private Portfolio portfolio;

    private ArrayList<Transaction> transactions;
    private ArrayList<Stock> watchlist;

    public User(int userId,
                String fullName,
                String email,
                String password,
                double walletBalance) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.walletBalance = walletBalance;

        portfolio = new Portfolio();
        transactions = new ArrayList<>();
        watchlist = new ArrayList<>();
    }

    // ---------------- Getters ----------------

    public String getFullName() {
        return fullName;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    // ---------------- User Details ----------------

    public void displayUser() {

        System.out.println("========== USER ==========");
        System.out.println("ID      : " + userId);
        System.out.println("Name    : " + fullName);
        System.out.println("Email   : " + email);
        System.out.printf("Wallet  : ₹%.2f%n", walletBalance);
        System.out.println("==========================");
    }

    // ---------------- Wallet ----------------

    public void deposit(double amount) {

        walletBalance += amount;

        System.out.printf("₹%.2f deposited into %s's wallet.%n",
                amount,
                fullName);
    }

    public void withdraw(double amount) {

        if (amount > walletBalance) {

            System.out.println("Insufficient Wallet Balance.");
            return;
        }

        walletBalance -= amount;

        System.out.printf("₹%.2f withdrawn from %s's wallet.%n",
                amount,
                fullName);
    }

    // ---------------- Buy Stock ----------------

    public void buyStock(Stock stock,
                         int quantity) {

        double totalCost =
                stock.getCurrentPrice() * quantity;

        System.out.println("\n========== BUY ORDER ==========");

        System.out.println("User      : " + fullName);
        System.out.println("Stock     : " + stock.getCompanyName());
        System.out.println("Symbol    : " + stock.getStockSymbol());
        System.out.println("Quantity  : " + quantity);

        System.out.printf("Price     : ₹%.2f%n",
                stock.getCurrentPrice());

        System.out.printf("Total     : ₹%.2f%n",
                totalCost);

        if (walletBalance < totalCost) {

            System.out.println("\nOrder Status : FAILED");
            System.out.println("Reason : Insufficient Wallet Balance");
            return;
        }

        walletBalance -= totalCost;

        portfolio.addHolding(stock, quantity);

        transactions.add(
                new Transaction(
                        "BUY",
                        stock,
                        quantity,
                        stock.getCurrentPrice()
                )
        );

        System.out.println("\nOrder Status : SUCCESS");
        System.out.printf("Remaining Wallet : ₹%.2f%n",
                walletBalance);

        System.out.println("===============================");
    }

    // ---------------- Sell Stock ----------------

    public void sellStock(Stock stock,
                          int quantity) {

        Holding holding =
                portfolio.getHolding(stock);

        System.out.println("\n========== SELL ORDER ==========");

        if (holding == null) {

            System.out.println("You do not own this stock.");
            return;
        }

        if (holding.getQuantity() < quantity) {

            System.out.println("Insufficient quantity available.");
            return;
        }

        holding.removeQuantity(quantity);

        double totalAmount =
                stock.getCurrentPrice() * quantity;

        walletBalance += totalAmount;

        transactions.add(
                new Transaction(
                        "SELL",
                        stock,
                        quantity,
                        stock.getCurrentPrice()
                )
        );

        if (holding.isEmpty()) {

            portfolio.removeHolding(holding);
        }

        System.out.println("Order Status : SUCCESS");

        System.out.printf("Amount Credited : ₹%.2f%n",
                totalAmount);

        System.out.printf("Wallet Balance : ₹%.2f%n",
                walletBalance);
    }

    // ---------------- Portfolio ----------------

    public void displayPortfolio() {

        System.out.println("\nPortfolio of " + fullName);

        portfolio.displayPortfolio();
    }

    // ---------------- Transactions ----------------

    public void displayTransactionHistory() {

        System.out.println("\n========== TRANSACTION HISTORY ==========\n");

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {

            transaction.displayTransaction();
        }
    }

    // ---------------- Dashboard ----------------

    public void displayDashboard() {

        System.out.println("\n=========================================");
        System.out.println("          TRADEFLOW DASHBOARD");
        System.out.println("=========================================");

        System.out.println("User            : " + fullName);

        System.out.printf("Wallet Balance  : ₹%.2f%n",
                walletBalance);

        System.out.println("\nPortfolio Summary");
        System.out.println("-----------------");

        System.out.println("Total Holdings  : "
                + portfolio.getTotalHoldings());

        portfolio.displayPortfolio();

        System.out.println("\n========== PORTFOLIO SUMMARY ==========");

        System.out.printf("Invested Amount : ₹%.2f%n",
                portfolio.getTotalInvestment());

        System.out.printf("Current Value   : ₹%.2f%n",
                portfolio.getCurrentValue());

        System.out.printf("Profit / Loss   : ₹%.2f%n",
                portfolio.getProfitLoss());

        System.out.println("\nRecent Transactions");

        displayTransactionHistory();

        System.out.println("=========================================");
    }

    // ---------------- Trade Report ----------------

    public void generateTradeReport() {

        System.out.println("\n=================================");
        System.out.println("        TRADE REPORT");
        System.out.println("=================================");

        System.out.println("User : " + fullName);

        System.out.printf("Wallet Balance : ₹%.2f%n",
                walletBalance);

        System.out.println("\nPortfolio Summary");

        System.out.println("Total Holdings : "
                + portfolio.getTotalHoldings());

        System.out.println("Transactions : "
                + getTransactionCount());

        System.out.println("Watchlist Stocks : "
                + getWatchlistCount());

        System.out.printf("Invested Amount : ₹%.2f%n",
                portfolio.getTotalInvestment());

        System.out.printf("Current Value : ₹%.2f%n",
                portfolio.getCurrentValue());

        System.out.printf("Profit / Loss : ₹%.2f%n",
                portfolio.getProfitLoss());

        double netWorth =
                walletBalance +
                        portfolio.getCurrentValue();

        System.out.printf("Net Worth : ₹%.2f%n",
                netWorth);

        System.out.println("=================================");
    }

    // ---------------- Watchlist ----------------

    public void addToWatchlist(Stock stock) {

        if (watchlist.contains(stock)) {

            System.out.println(
                    stock.getStockSymbol()
                            + " is already in your watchlist."
            );

            return;
        }

        watchlist.add(stock);

        System.out.println(
                stock.getStockSymbol()
                        + " added to watchlist."
        );
    }

    public void removeFromWatchlist(Stock stock) {

        if (watchlist.remove(stock)) {

            System.out.println(
                    stock.getStockSymbol()
                            + " removed from watchlist."
            );
        }

        else {

            System.out.println(
                    stock.getStockSymbol()
                            + " is not in your watchlist."
            );
        }
    }

    public void displayWatchlist() {

        System.out.println("\n========== WATCHLIST ==========\n");

        if (watchlist.isEmpty()) {

            System.out.println("Watchlist is empty.");
            return;
        }

        for (Stock stock : watchlist) {

            stock.displayStock();
            System.out.println();
        }
    }

    // ---------------- Statistics ----------------

    public int getTransactionCount() {

        return transactions.size();
    }

    public int getWatchlistCount() {

        return watchlist.size();
    }
}