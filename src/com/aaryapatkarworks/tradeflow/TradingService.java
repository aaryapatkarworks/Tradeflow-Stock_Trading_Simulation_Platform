package com.aaryapatkarworks.tradeflow;

public class TradingService {

    void buyStock(User user,
                  StockMarket market,
                  String symbol,
                  int quantity) {

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

        market.updateStockPrice(
                symbol,
                newPrice
        );
    }
}