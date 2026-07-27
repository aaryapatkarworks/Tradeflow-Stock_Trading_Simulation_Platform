package com.aaryapatkarworks.tradeflow;

public class TradingService {

    void buyStock(User user,
                  StockMarket market,
                  String symbol,
                  int quantity) {

        if (quantity <= 0) {

            System.out.println("Quantity must be greater than zero.");
            return;
        }

        Stock stock = market.findStock(symbol);

        if (stock == null) {

            System.out.println("Stock not found.");
            return;
        }

        user.buyStock(stock, quantity);
    }

    void sellStock(User user,
                   StockMarket market,
                   String symbol,
                   int quantity) {

        if (quantity <= 0) {

            System.out.println("Quantity must be greater than zero.");
            return;
        }

        Stock stock = market.findStock(symbol);

        if (stock == null) {

            System.out.println("Stock not found.");
            return;
        }

        user.sellStock(stock, quantity);
    }

    void addToWatchlist(User user,
                        StockMarket market,
                        String symbol) {

        Stock stock = market.findStock(symbol);

        if (stock == null) {

            System.out.println("Stock not found.");
            return;
        }

        user.addToWatchlist(stock);
    }

    void removeFromWatchlist(User user,
                             StockMarket market,
                             String symbol) {

        Stock stock = market.findStock(symbol);

        if (stock == null) {

            System.out.println("Stock not found.");
            return;
        }

        user.removeFromWatchlist(stock);
    }

    void updateMarketPrice(StockMarket market,
                           String symbol,
                           double newPrice) {

        if (newPrice <= 0) {

            System.out.println("Invalid stock price.");
            return;
        }

        market.updateStockPrice(
                symbol,
                newPrice
        );
    }
}