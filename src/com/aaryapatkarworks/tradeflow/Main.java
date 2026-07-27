package com.aaryapatkarworks.tradeflow;

public class Main {

    public static void main(String[] args) {

        // Create Users
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

        // Create Stock Market
        StockMarket market = new StockMarket();

        // Create Trading Service
        TradingService tradingService = new TradingService();

        // Add Stocks to Market
        market.addStock(new Stock(
                1,
                "Tata Consultancy Services",
                "TCS",
                3520.50
        ));

        market.addStock(new Stock(
                2,
                "Reliance Industries",
                "RELIANCE",
                1498.75
        ));

        market.addStock(new Stock(
                3,
                "Infosys",
                "INFY",
                1624.80
        ));

        // Display Users
        System.out.println("========== USERS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();

        // Display Stock Market
        market.displayMarket();

        // Wallet Operations
        System.out.println("\n========== WALLET OPERATIONS ==========\n");

        user2.deposit(90000);
        user1.withdraw(10500);

        System.out.println("\n========== WATCHLIST ==========\n");

        tradingService.addToWatchlist(
                user1,
                market,
                "TCS"
        );

        tradingService.addToWatchlist(
                user1,
                market,
                "INFY"
        );

        tradingService.addToWatchlist(
                user2,
                market,
                "RELIANCE"
        );

        // Buy Orders
        System.out.println("\n========== BUY ORDERS ==========\n");

        tradingService.buyStock(
                user1,
                market,
                "TCS",
                10
        );

        tradingService.buyStock(
                user1,
                market,
                "TCS",
                5
        );

        tradingService.buyStock(
                user2,
                market,
                "RELIANCE",
                80
        );

        // Sell Orders
        System.out.println("\n========== SELL ORDERS ==========\n");

        tradingService.sellStock(
                user1,
                market,
                "TCS",
                3
        );

        tradingService.sellStock(
                user2,
                market,
                "RELIANCE",
                10
        );

        System.out.println("\n========== MARKET UPDATE ==========\n");

        tradingService.updateMarketPrice(
                market,
                "TCS",
                3650.75
        );

        tradingService.updateMarketPrice(
                market,
                "RELIANCE",
                1555.20
        );

        tradingService.updateMarketPrice(
                market,
                "INFY",
                1688.40
        );

        // Final User Details
        System.out.println("\n========== FINAL USER DETAILS ==========\n");

        user1.displayUser();
        System.out.println();

        user2.displayUser();

        // Dashboards
        System.out.println("\n========== USER 1 DASHBOARD ==========");

        user1.displayDashboard();

        System.out.println("\n========== USER 2 DASHBOARD ==========");

        user2.displayDashboard();

        user1.displayWatchlist();

        user2.displayWatchlist();
    }
}