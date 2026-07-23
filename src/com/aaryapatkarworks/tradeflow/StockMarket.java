package com.aaryapatkarworks.tradeflow;

import java.util.ArrayList;

public class StockMarket {

    ArrayList<Stock> stocks;

    StockMarket() {

        stocks = new ArrayList<>();
    }

    void addStock(Stock stock) {

        stocks.add(stock);
    }

    void displayMarket() {

        System.out.println("\n========== STOCK MARKET ==========\n");

        for (Stock stock : stocks) {

            stock.displayStock();
            System.out.println();
        }
    }

    Stock findStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.stockSymbol.equalsIgnoreCase(symbol)) {

                return stock;
            }
        }

        return null;
    }
}