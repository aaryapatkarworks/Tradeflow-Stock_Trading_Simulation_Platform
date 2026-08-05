package com.aaryapatkarworks.tradeflow;

import java.util.Scanner;

import com.aaryapatkarworks.tradeflow.exception.InvalidQuantityException;
import com.aaryapatkarworks.tradeflow.exception.StockNotFoundException;

import com.aaryapatkarworks.tradeflow.model.User;
import com.aaryapatkarworks.tradeflow.service.StockMarket;
import com.aaryapatkarworks.tradeflow.service.TradingService;
import com.aaryapatkarworks.tradeflow.service.UserService;
import com.aaryapatkarworks.tradeflow.service.OrderService;
import com.aaryapatkarworks.tradeflow.service.MarketSimulationService;
import com.aaryapatkarworks.tradeflow.service.PortfolioAnalyticsService;

public class Main {

    // ---------------- Objects ----------------

    private static final Scanner sc = new Scanner(System.in);

    private static final StockMarket market = new StockMarket();

    private static final TradingService tradingService =
            new TradingService(market);

    private static final OrderService orderService =
            new OrderService(tradingService);

    private static final UserService userService =
            new UserService();

    private static final MarketSimulationService
            marketSimulationService =
            new MarketSimulationService(market);

    private static final PortfolioAnalyticsService
            portfolioAnalyticsService =
            new PortfolioAnalyticsService();

    private static User currentUser;

    private static boolean loggedIn = false;

    private static long loginTime;

    // ---------------- Main ----------------

    public static void main(String[] args) {

        initialize();

        startApplication();
    }

    // ---------------- Initialize ----------------

    private static void initialize() {

        userService.registerUser(

                new User(
                        101,
                        "Aarya Patkar",
                        "aarya210@gmail.com",
                        "password21",
                        150000
                )
        );

        userService.registerUser(

                new User(
                        102,
                        "Pranav More",
                        "pranav007@gmail.com",
                        "password10",
                        100000
                )
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

            System.out.println("1. Register New User");
            System.out.println("2. Login");
            System.out.println("3. Run Demo");
            System.out.println("0. Exit");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    registerUser();

                    break;

                case 2:

                    login();

                    showMainMenu();

                    break;

                case 3:

                    runDemo();

                    break;

                default:

                    System.out.println("\nExit.");
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

            currentUser = userService.login(email, password);

            if (currentUser != null) {

                System.out.println(
                        "\nWelcome "
                                + currentUser.getFullName()
                                + "!"
                );

                break;
            }

            System.out.println(
                    "\nInvalid Email or Password!"
            );
        }
    }

    private static void registerUser() {

        System.out.println("\n========== REGISTER USER ==========\n");

        System.out.print("User ID : ");
        int id = sc.nextInt();
        sc.nextLine();
        if (userService.userIdExists(id)) {

            System.out.println("\nUser ID already exists.");

            return;
        }

        System.out.print("Full Name : ");
        String name = sc.nextLine();

        System.out.print("Email : ");
        String email = sc.nextLine();
        if (userService.emailExists(email)) {

            System.out.println("\nEmail already registered.");

            return;
        }

        System.out.print("Password : ");
        String password = sc.nextLine();

        System.out.print("Initial Wallet Balance : ₹");
        double wallet = sc.nextDouble();
        sc.nextLine();

        User user = new User(
                id,
                name,
                email,
                password,
                wallet
        );

        userService.registerUser(user);

        System.out.println("\nUser Registered Successfully!");
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
            System.out.println("9. Order History");
            System.out.println("10. Trade Report");
            System.out.println("11. Admin Panel");
            System.out.println("12. View Live Market");
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

                    orderService.displayOrders(currentUser);

                    break;

                case 10:

                    tradingService.generateTradeReport(currentUser);

                    portfolioAnalyticsService.displayAnalytics(
                            currentUser.getPortfolio()
                    );

                    break;

                case 11:

                    adminMenu();

                    break;

                case 12:

                    marketSimulationService.simulateMarket();

                    tradingService.displayMarket();

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

            orderService.placeBuyOrder(
                    currentUser,
                    market.findStock(symbol),
                    quantity
            );

            marketSimulationService.simulateMarket();

            System.out.println("\nUpdated Portfolio:");

            tradingService.displayPortfolio(currentUser);

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

            orderService.placeSellOrder(
                    currentUser,
                    market.findStock(symbol),
                    quantity
            );

            marketSimulationService.simulateMarket();

            System.out.println("\nUpdated Portfolio:");

            tradingService.displayPortfolio(currentUser);

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
            System.out.println("3. View All Users");
            System.out.println("4. Total Registered Users");
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

                case 3:

                    userService.displayUsers();

                    break;

                case 4:

                    System.out.println(
                            "\nTotal Registered Users : "
                                    + userService.getUsers().size()
                    );

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

        User user1 = userService.getUsers().get(0);

        User user2 = userService.getUsers().get(1);

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

            orderService.placeBuyOrder(
                    user1,
                    market.findStock("TCS"),
                    10
            );

            orderService.placeBuyOrder(
                    user1,
                    market.findStock("TCS"),
                    5
            );

            orderService.placeBuyOrder(
                    user2,
                    market.findStock("RELIANCE"),
                    20
            );

        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n========== SELL ORDERS ==========\n");

        try {

            orderService.placeSellOrder(
                    user1,
                    market.findStock("TCS"),
                    3
            );

            orderService.placeSellOrder(
                    user2,
                    market.findStock("RELIANCE"),
                    5
            );

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