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

        Stock stock1 = new Stock(
                1,
                "Tata Consultancy Services",
                "TCS",
                3520.50
        );

        Stock stock2 = new Stock(
                2,
                "Reliance Industries",
                "RELIANCE",
                1498.75
        );

        Stock stock3 = new Stock(
                3,
                "Infosys",
                "INFY",
                1624.80
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

        System.out.println("\n========== AVAILABLE STOCKS ==========\n");

        stock1.displayStock();
        System.out.println();

        stock2.displayStock();
        System.out.println();

        stock3.displayStock();

        System.out.println("\n========== BUY STOCK ==========\n");

        user1.buyStock(stock1, 10);

        System.out.println();

        user2.buyStock(stock2, 80);

        System.out.println();

        System.out.println("========== FINAL USER DETAILS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();

    }
}