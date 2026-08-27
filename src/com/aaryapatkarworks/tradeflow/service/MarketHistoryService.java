package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.PriceRecord;
import com.aaryapatkarworks.tradeflow.model.Stock;

import java.util.ArrayList;

public class MarketHistoryService {

        private final StockMarket stockMarket;

        public MarketHistoryService(StockMarket stockMarket) {

            this.stockMarket = stockMarket;
        }

        // ---------------- Display Price History ----------------

    // ---------------- Display Price History ----------------

    public void displayPriceHistory(String stockSymbol) {

        Stock stock =
                stockMarket.findStock(stockSymbol);

        if (stock == null) {

            System.out.println(
                    "Stock '" + stockSymbol
                            + "' not found."
            );

            return;
        }

        ArrayList<PriceRecord> history =
                stock.getPriceHistory();

        System.out.println(
                "\n========== PRICE HISTORY ==========\n"
        );

        System.out.println(
                "Stock  : "
                        + stock.getStockSymbol()
        );

        System.out.println(
                "Company : "
                        + stock.getCompanyName()
        );

        System.out.println();

        if (history.isEmpty()) {

            System.out.println(
                    "No price history available."
            );

            return;
        }

        for (PriceRecord record : history) {

            record.displayRecord();
        }

        System.out.printf(
                "\nHighest Price      : ₹%.2f%n",
                getHighestPrice(stockSymbol)
        );

        System.out.printf(
                "Lowest Price       : ₹%.2f%n",
                getLowestPrice(stockSymbol)
        );

        System.out.printf(
                "Historical Change  : ₹%+.2f%n",
                getHistoricalChange(stockSymbol)
        );

        System.out.printf(
                "Performance        : %+.2f%%%n",
                getHistoricalChangePercentage(stockSymbol)
        );

        System.out.println(
                "\n==================================="
        );
    }
        // ---------------- Highest Price ----------------

        public double getHighestPrice(
                String stockSymbol) {

            Stock stock =
                    stockMarket.findStock(stockSymbol);

            if (stock == null) {

                return 0;
            }

            double highestPrice =
                    Double.NEGATIVE_INFINITY;

            for (PriceRecord record :
                    stock.getPriceHistory()) {

                if (record.getPrice()
                        > highestPrice) {

                    highestPrice =
                            record.getPrice();
                }
            }

            return highestPrice;
        }

        // ---------------- Lowest Price ----------------

        public double getLowestPrice(
                String stockSymbol) {

            Stock stock =
                    stockMarket.findStock(stockSymbol);

            if (stock == null) {

                return 0;
            }

            double lowestPrice =
                    Double.POSITIVE_INFINITY;

            for (PriceRecord record :
                    stock.getPriceHistory()) {

                if (record.getPrice()
                        < lowestPrice) {

                    lowestPrice =
                            record.getPrice();
                }
            }

            return lowestPrice;
        }

    // ---------------- Historical Performance ----------------

    public double getHistoricalChangePercentage(
            String stockSymbol) {

        Stock stock =
                stockMarket.findStock(stockSymbol);

        if (stock == null ||
                stock.getPriceHistory().isEmpty()) {

            return 0;
        }

        ArrayList<PriceRecord> history =
                stock.getPriceHistory();

        double initialPrice =
                history.get(0).getPrice();

        double currentPrice =
                stock.getCurrentPrice();

        if (initialPrice == 0) {

            return 0;
        }

        return ((currentPrice - initialPrice)
                / initialPrice) * 100;
    }

    public double getHistoricalChange(
            String stockSymbol) {

        Stock stock =
                stockMarket.findStock(stockSymbol);

        if (stock == null ||
                stock.getPriceHistory().isEmpty()) {

            return 0;
        }

        ArrayList<PriceRecord> history =
                stock.getPriceHistory();

        double initialPrice =
                history.get(0).getPrice();

        return stock.getCurrentPrice()
                - initialPrice;
    }
}
