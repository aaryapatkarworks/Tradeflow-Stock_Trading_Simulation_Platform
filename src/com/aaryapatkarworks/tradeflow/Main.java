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

        System.out.println("========== USERS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();

        System.out.println("\n========== AVAILABLE STOCKS ==========\n");

        stock1.displayStock();
        System.out.println();

        stock2.displayStock();
        System.out.println();

        stock3.displayStock();

        System.out.println("\n========== WALLET OPERATIONS ==========\n");

        user2.deposit(90000);
        user1.withdraw(10500);

        System.out.println("\n========== BUY ORDERS ==========");

        user1.buyStock(stock1, 10);
        user1.buyStock(stock1,5);

        System.out.println();

        user2.buyStock(stock2, 80);

        System.out.println("\n========== SELL ORDERS ==========\n");

        user1.sellStock(stock1, 3);

        user2.sellStock(stock2, 10);

        System.out.println("\n========== FINAL USER DETAILS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();

        System.out.println("\n========== PORTFOLIOS ==========");

        user1.displayPortfolio();

        System.out.println();

        user2.displayPortfolio();
    }
}