package com.aaryapatkarworks.tradeflow;

import java.util.Scanner;

import com.aaryapatkarworks.tradeflow.exception.InvalidQuantityException;
import com.aaryapatkarworks.tradeflow.exception.StockNotFoundException;

import com.aaryapatkarworks.tradeflow.model.User;
import com.aaryapatkarworks.tradeflow.service.StockMarket;
import com.aaryapatkarworks.tradeflow.service.TradingService;

public class Main {

    // ---------------- Objects ----------------

    private static final Scanner sc = new Scanner(System.in);

    private static final StockMarket market = new StockMarket();

    private static final TradingService tradingService =
            new TradingService(market);

    private static User user1;
    private static User user2;

    private static User currentUser;

    // ---------------- Main ----------------

    public static void main(String[] args) {

        initialize();

        startApplication();
    }

    // ---------------- Initialize ----------------

    private static void initialize() {

        user1 = new User(
                101,
                "Aarya Patkar",
                "aarya210@gmail.com",
                "password21",
                150000
        );

        user2 = new User(
                102,
                "Pranav More",
                "pranav007@gmail.com",
                "password10",
                100000
        );

        market.loadStocksFromCSV("stocks.csv");
    }

    // ---------------- Startup Menu ----------------

    private static void startApplication() {

        int choice;

        do {

            System.out.println("\n==========================================");
            System.out.println("        TRADEFLOW STOCK EXCHANGE");
            System.out.println("==========================================");

            System.out.println("1. Run Demo");
            System.out.println("2. Login");
            System.out.println("0. Exit");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    runDemo();

                    break;

                case 2:

                    login();

                    showMainMenu();

                    break;

                case 0:

                    System.out.println("\nThank you for using TradeFlow!");

                    break;

                default:

                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 0);
    }

    // ---------------- Login ----------------

    private static void login() {

        while (true) {

            System.out.println("\n========== LOGIN ==========");

            System.out.print("Email : ");
            String email = sc.nextLine();

            System.out.print("Password : ");
            String password = sc.nextLine();

            if (email.equalsIgnoreCase("aarya210@gmail.com")
                    && password.equals("password21")) {

                currentUser = user1;

                System.out.println("\nWelcome " +
                        currentUser.getFullName() + "!");

                break;
            }

            else if (email.equalsIgnoreCase("pranav007@gmail.com")
                    && password.equals("password10")) {

                currentUser = user2;

                System.out.println("\nWelcome " +
                        currentUser.getFullName() + "!");

                break;
            }

            else {

                System.out.println("\nInvalid Email or Password!");
            }
        }
    }

    // ---------------- Main Menu ----------------

    private static void showMainMenu() {

        int choice;

        do {

            System.out.println("\n==========================================");
            System.out.println("               MAIN MENU");
            System.out.println("==========================================");

            System.out.println("1. Display Market");
            System.out.println("2. Wallet");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("5. Portfolio");
            System.out.println("6. Dashboard");
            System.out.println("7. Watchlist");
            System.out.println("8. Transaction History");
            System.out.println("9. Trade Report");
            System.out.println("10. Admin Panel");
            System.out.println("0. Logout");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    tradingService.displayMarket();
                    break;

                case 2:

                    walletMenu();
                    break;

                case 3:

                    buyStockMenu();
                    break;

                case 4:

                    sellStockMenu();
                    break;

                case 5:

                    tradingService.displayPortfolio(currentUser);
                    break;

                case 6:

                    tradingService.displayDashboard(currentUser);
                    break;

                case 7:

                    watchlistMenu();
                    break;

                case 8:

                    tradingService.displayTransactions(currentUser);
                    break;

                case 9:

                    tradingService.generateTradeReport(currentUser);
                    break;

                case 10:

                    adminMenu();
                    break;

                case 0:

                    System.out.println("\nLogging out...");
                    break;

                default:

                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 0);
    }

    // ---------------- Wallet ----------------

    private static void walletMenu() {

        int choice;

        do {

            System.out.println("\n========== WALLET ==========");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("0. Back");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Amount : ₹");

                    double depositAmount = sc.nextDouble();

                    currentUser.deposit(depositAmount);

                    break;

                case 2:

                    System.out.print("Enter Amount : ₹");

                    double withdrawAmount = sc.nextDouble();

                    currentUser.withdraw(withdrawAmount);

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 0);
    }

    // ---------------- Buy Stock ----------------

    private static void buyStockMenu() {

        tradingService.displayMarket();

        System.out.print("\nEnter Stock Symbol : ");

        String symbol = sc.next().toUpperCase();

        System.out.print("Enter Quantity : ");

        int quantity = sc.nextInt();

        try {

            tradingService.buyStock(
                    currentUser,
                    symbol,
                    quantity
            );

        }

        catch (StockNotFoundException |
               InvalidQuantityException e) {

            System.out.println(e.getMessage());
        }
    }

    // ---------------- Sell Stock ----------------

    private static void sellStockMenu() {

        tradingService.displayPortfolio(currentUser);

        System.out.print("\nEnter Stock Symbol : ");

        String symbol = sc.next().toUpperCase();

        System.out.print("Enter Quantity : ");

        int quantity = sc.nextInt();

        try {

            tradingService.sellStock(
                    currentUser,
                    symbol,
                    quantity
            );

        }

        catch (StockNotFoundException |
               InvalidQuantityException e) {

            System.out.println(e.getMessage());
        }
    }

    // ---------------- Watchlist ----------------

    private static void watchlistMenu() {

        int choice;

        do {

            System.out.println("\n========== WATCHLIST ==========");

            System.out.println("1. View Watchlist");
            System.out.println("2. Add Stock");
            System.out.println("3. Remove Stock");
            System.out.println("0. Back");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    tradingService.displayWatchlist(currentUser);

                    break;

                case 2:

                    System.out.print("Enter Stock Symbol : ");

                    String addSymbol = sc.next().toUpperCase();

                    try {

                        tradingService.addToWatchlist(
                                currentUser,
                                addSymbol
                        );

                    }

                    catch (StockNotFoundException e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    System.out.print("Enter Stock Symbol : ");

                    String removeSymbol = sc.next().toUpperCase();

                    try {

                        tradingService.removeFromWatchlist(
                                currentUser,
                                removeSymbol
                        );

                    }

                    catch (StockNotFoundException e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 0);
    }

    // ---------------- Admin ----------------

    private static void adminMenu() {

        int choice;

        do {

            System.out.println("\n========== ADMIN PANEL ==========");

            System.out.println("1. Display Market");
            System.out.println("2. Update Stock Price");
            System.out.println("0. Back");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    tradingService.displayMarket();

                    break;

                case 2:

                    System.out.print("Enter Stock Symbol : ");

                    String symbol = sc.next().toUpperCase();

                    System.out.print("Enter New Price : ₹");

                    double price = sc.nextDouble();

                    try {

                        tradingService.updateMarketPrice(
                                symbol,
                                price
                        );

                        System.out.println(
                                "\nPrice Updated Successfully."
                        );

                    }

                    catch (StockNotFoundException e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 0);
    }

    // ---------------- Demo ----------------

    private static void runDemo() {

        System.out.println("\n========== USERS ==========\n");

        user1.displayUser();

        System.out.println();

        user2.displayUser();

        System.out.println("\n========== MARKET ==========\n");

        tradingService.displayMarket();

        System.out.println("\n========== WALLET ==========\n");

        user2.deposit(90000);

        user1.withdraw(10500);

        System.out.println("\n========== WATCHLIST ==========\n");

        try {

            tradingService.addToWatchlist(user1, "TCS");
            tradingService.addToWatchlist(user1, "INFY");
            tradingService.addToWatchlist(user2, "RELIANCE");

        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n========== BUY ORDERS ==========\n");

        try {

            tradingService.buyStock(user1, "TCS", 10);

            tradingService.buyStock(user1, "TCS", 5);

            tradingService.buyStock(user2, "RELIANCE", 20);

        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n========== SELL ORDERS ==========\n");

        try {

            tradingService.sellStock(user1, "TCS", 3);

            tradingService.sellStock(user2, "RELIANCE", 5);

        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n========== MARKET UPDATE ==========\n");

        try {

            tradingService.updateMarketPrice("TCS", 3650.75);

            tradingService.updateMarketPrice("RELIANCE", 1555.20);

        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n========== DASHBOARD ==========\n");

        tradingService.displayDashboard(user1);

        System.out.println();

        tradingService.displayDashboard(user2);

        System.out.println("\n========== WATCHLIST ==========\n");

        tradingService.displayWatchlist(user1);

        System.out.println();

        tradingService.displayWatchlist(user2);

        System.out.println("\n========== TRADE REPORT ==========\n");

        tradingService.generateTradeReport(user1);

        System.out.println();

        tradingService.generateTradeReport(user2);
    }

}