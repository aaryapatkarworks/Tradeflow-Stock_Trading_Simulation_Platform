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

        System.out.printf("₹%.2f deposited successfully into %s's wallet.%n",
                amount, fullName);
    }

    void withdraw(double amount) {

        if (amount <= walletBalance) {

            walletBalance -= amount;

            System.out.printf("₹%.2f withdrawn successfully from %s's wallet.%n",
                    amount, fullName);

        } else {

            System.out.printf("Withdrawal failed! %s has insufficient wallet balance.%n",
                    fullName);
        }
    }
}