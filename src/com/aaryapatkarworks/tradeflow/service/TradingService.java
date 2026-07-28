package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.exception.InvalidQuantityException;
import com.aaryapatkarworks.tradeflow.exception.StockNotFoundException;
import com.aaryapatkarworks.tradeflow.model.Stock;
import com.aaryapatkarworks.tradeflow.model.User;

public class TradingService {

    private StockMarket stockMarket;

    public TradingService(StockMarket stockMarket) {

        this.stockMarket = stockMarket;
    }

    // ---------------- Buy Stock ----------------

    public void buyStock(User user,
                         String stockSymbol,
                         int quantity)
            throws StockNotFoundException,
            InvalidQuantityException {

        if (quantity <= 0) {

            throw new InvalidQuantityException(
                    "Quantity must be greater than zero."
            );
        }

        Stock stock = stockMarket.findStock(stockSymbol);

        if (stock == null) {

            throw new StockNotFoundException(
                    "Stock '" + stockSymbol + "' not found."
            );
        }

        user.buyStock(stock, quantity);
    }

    // ---------------- Sell Stock ----------------

    public void sellStock(User user,
                          String stockSymbol,
                          int quantity)
            throws StockNotFoundException,
            InvalidQuantityException {

        if (quantity <= 0) {

            throw new InvalidQuantityException(
                    "Quantity must be greater than zero."
            );
        }

        Stock stock = stockMarket.findStock(stockSymbol);

        if (stock == null) {

            throw new StockNotFoundException(
                    "Stock '" + stockSymbol + "' not found."
            );
        }

        user.sellStock(stock, quantity);
    }

    // ---------------- Watchlist ----------------

    public void addToWatchlist(User user,
                               String stockSymbol)
            throws StockNotFoundException {

        Stock stock = stockMarket.findStock(stockSymbol);

        if (stock == null) {

            throw new StockNotFoundException(
                    "Stock '" + stockSymbol + "' not found."
            );
        }

        user.addToWatchlist(stock);
    }

    public void removeFromWatchlist(User user,
                                    String stockSymbol)
            throws StockNotFoundException {

        Stock stock = stockMarket.findStock(stockSymbol);

        if (stock == null) {

            throw new StockNotFoundException(
                    "Stock '" + stockSymbol + "' not found."
            );
        }

        user.removeFromWatchlist(stock);
    }

    // ---------------- Market ----------------

    public void displayMarket() {

        stockMarket.displayMarket();
    }

    public void updateMarketPrice(String stockSymbol,
                                  double newPrice)
            throws StockNotFoundException {

        Stock stock = stockMarket.findStock(stockSymbol);

        if (stock == null) {

            throw new StockNotFoundException(
                    "Stock '" + stockSymbol + "' not found."
            );
        }

        stock.updatePrice(newPrice);
    }

    // ---------------- Reports ----------------

    public void displayPortfolio(User user) {

        user.displayPortfolio();
    }

    public void displayDashboard(User user) {

        user.displayDashboard();
    }

    public void displayTransactions(User user) {

        user.displayTransactionHistory();
    }

    public void displayWatchlist(User user) {

        user.displayWatchlist();
    }

    public void generateTradeReport(User user) {

        user.generateTradeReport();
    }
}