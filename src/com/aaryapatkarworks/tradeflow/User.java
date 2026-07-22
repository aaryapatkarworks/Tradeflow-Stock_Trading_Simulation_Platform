package com.aaryapatkarworks.tradeflow;

public class User {

    int userId;
    String fullName;
    String email;
    String password;
    double walletBalance;

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

            System.out.println("\nOrder Status : SUCCESS");
            System.out.printf("Remaining Wallet : ₹%.2f%n", walletBalance);

        } else {

            System.out.println("\nOrder Status : FAILED");
            System.out.println("Reason : Insufficient Wallet Balance");
        }

        System.out.println("===============================");
    }
}