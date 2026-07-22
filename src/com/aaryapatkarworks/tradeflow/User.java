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
    }
}