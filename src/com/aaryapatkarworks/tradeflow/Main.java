package com.aaryapatkarworks.tradeflow;

public class Main {

    public static void main(String[] args) {

        User user1 = new User(
                101,
                "Aarya Patkar",
                "aarya210@gmail.com",
                "password21",
                150000
        );

        User user2 = new User(
                102,
                "Pranav More",
                "pranav007@gmail.com",
                "password10",
                100000
        );

        System.out.println("========== INITIAL USER DETAILS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();

        System.out.println("\n========== WALLET OPERATIONS ==========\n");

        user1.deposit(25700);
        user2.withdraw(15200);
        user1.withdraw(3090);

        System.out.println("\n========== UPDATED USER DETAILS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();
    }
}