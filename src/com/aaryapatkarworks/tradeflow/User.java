package com.aaryapatkarworks.tradeflow;
import java.util.ArrayList;

public class User {

    int userId;
    String fullName;
    String email;
    String password;
    double walletBalance;

    Portfolio portfolio;

    ArrayList<Transaction> transactions;

    User(int userId,
         String fullName,
         String email,
         String password,
         double walletBalance) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.walletBalance = walletBalance;
        this.portfolio = new Portfolio();
        transactions = new ArrayList<>();
    }

    void displayUser() {

        System.out.println("========== USER ==========");
        System.out.println("ID      : " + userId);
        System.out.println("Name    : " + fullName);
        System.out.println("Email   : " + email);
        System.out.printf("Wallet  : ₹%.2f%n", walletBalance);
        System.out.println("==========================");
    }

    void deposit(double amount) {

        walletBalance += amount;

        System.out.printf("₹%.2f deposited into %s's wallet.%n",
                amount, fullName);
    }

    void withdraw(double amount) {

        if (amount <= walletBalance) {

            walletBalance -= amount;

            System.out.printf("₹%.2f withdrawn from %s's wallet.%n",
                    amount, fullName);

        } else {

            System.out.println("Insufficient Wallet Balance.");
        }
    }

    void buyStock(Stock stock, int quantity) {

        double totalCost = stock.currentPrice * quantity;

        System.out.println("\n========== BUY ORDER ==========");

        System.out.println("User      : " + fullName);
        System.out.println("Stock     : " + stock.companyName);
        System.out.println("Symbol    : " + stock.stockSymbol);
        System.out.println("Quantity  : " + quantity);

        System.out.printf("Price     : ₹%.2f%n", stock.currentPrice);
        System.out.printf("Total     : ₹%.2f%n", totalCost);

        if (walletBalance >= totalCost) {

            walletBalance -= totalCost;

            portfolio.addHolding(stock, quantity);

            transactions.add(
                    new Transaction(
                            "BUY",
                            stock,
                            quantity,
                            stock.currentPrice
                    )
            );

            System.out.println("\nOrder Status : SUCCESS");
            System.out.printf("Remaining Wallet : ₹%.2f%n", walletBalance);

        } else {

            System.out.println("\nOrder Status : FAILED");
            System.out.println("Reason : Insufficient Wallet Balance");
        }

        System.out.println("===============================");
    }

    void sellStock(Stock stock, int quantity) {

        Holding holding = portfolio.getHolding(stock);

        System.out.println("\n========== SELL ORDER ==========");

        if (holding == null) {

            System.out.println("You do not own this stock.");

            return;
        }

        if (!holding.removeQuantity(quantity)) {

            System.out.println("Insufficient quantity available.");

            return;
        }

        double totalAmount = stock.currentPrice * quantity;

        walletBalance += totalAmount;

        transactions.add(
                new Transaction(
                        "SELL",
                        stock,
                        quantity,
                        stock.currentPrice
                )
        );

        if (holding.isEmpty()) {

            portfolio.removeHolding(holding);
        }

        System.out.println("Order Status : SUCCESS");

        System.out.printf("Amount Credited : ₹%.2f%n", totalAmount);

        System.out.printf("Wallet Balance : ₹%.2f%n", walletBalance);
    }

    void displayPortfolio() {

        System.out.println("\nPortfolio of " + fullName);

        portfolio.displayPortfolio();
    }

    void displayTransactionHistory() {

        System.out.println("\n========== TRANSACTION HISTORY ==========\n");

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {

            transaction.displayTransaction();
        }
    }
}