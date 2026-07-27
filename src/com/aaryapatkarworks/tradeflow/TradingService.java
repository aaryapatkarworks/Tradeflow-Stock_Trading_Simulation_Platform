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

}