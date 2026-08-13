package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.Stock;

public class MarketAnalyticsService {

    private final StockMarket stockMarket;

    public MarketAnalyticsService(StockMarket stockMarket) {

        this.stockMarket = stockMarket;
    }

    // ---------------- Top Gainer ----------------

    public Stock getTopGainer() {

        Stock topGainer = null;

        double highestChange =
                Double.NEGATIVE_INFINITY;

        for (Stock stock : stockMarket.getStocks()) {

            if (stock.getPriceChangePercentage()
                    > highestChange) {

                highestChange =
                        stock.getPriceChangePercentage();

                topGainer = stock;
            }
        }

        return topGainer;
    }

    // ---------------- Top Loser ----------------

    public Stock getTopLoser() {

        Stock topLoser = null;

        double lowestChange =
                Double.POSITIVE_INFINITY;

        for (Stock stock : stockMarket.getStocks()) {

            if (stock.getPriceChangePercentage()
                    < lowestChange) {

                lowestChange =
                        stock.getPriceChangePercentage();

                topLoser = stock;
            }
        }

        return topLoser;
    }

    // ---------------- Advancing Stocks ----------------

    public int getAdvancingStocks() {

        int count = 0;

        for (Stock stock : stockMarket.getStocks()) {

            if (stock.getPriceChange() > 0) {

                count++;
            }
        }

        return count;
    }

    // ---------------- Declining Stocks ----------------

    public int getDecliningStocks() {

        int count = 0;

        for (Stock stock : stockMarket.getStocks()) {

            if (stock.getPriceChange() < 0) {

                count++;
            }
        }

        return count;
    }

    // ---------------- Unchanged Stocks ----------------

    public int getUnchangedStocks() {

        int count = 0;

        for (Stock stock : stockMarket.getStocks()) {

            if (stock.getPriceChange() == 0) {

                count++;
            }
        }

        return count;
    }

    // ---------------- Advance / Decline Ratio ----------------

    public double getAdvanceDeclineRatio() {

        int advancing = getAdvancingStocks();
        int declining = getDecliningStocks();

        if (declining == 0) {

            return advancing;
        }

        return (double) advancing / declining;
    }

    // ---------------- Display Analytics ----------------

    public void displayMarketAnalytics() {

        System.out.println(
                "\n========== MARKET ANALYTICS ==========\n"
        );

        System.out.println(
                "Stocks Tracked : "
                        + stockMarket.getStocks().size()
        );

        System.out.println(
                "Advancing      : "
                        + getAdvancingStocks()
        );

        System.out.println(
                "Declining      : "
                        + getDecliningStocks()
        );

        System.out.println(
                "Unchanged      : "
                        + getUnchangedStocks()
        );

        System.out.printf(
                "A/D Ratio      : %.2f%n",
                getAdvanceDeclineRatio()
        );

        Stock topGainer = getTopGainer();

        Stock topLoser = getTopLoser();

        if (topGainer != null) {

            System.out.println(
                    "\nTop Gainer"
            );

            System.out.println(
                    "-----------"
            );

            System.out.println(
                    "Stock : "
                            + topGainer.getStockSymbol()
            );

            System.out.printf(
                    "Change : +%.2f%%%n",
                    topGainer.getPriceChangePercentage()
            );
        }

        if (topLoser != null) {

            System.out.println(
                    "\nTop Loser"
            );

            System.out.println(
                    "-----------"
            );

            System.out.println(
                    "Stock : "
                            + topLoser.getStockSymbol()
            );

            System.out.printf(
                    "Change : %.2f%%%n",
                    topLoser.getPriceChangePercentage()
            );
        }

        System.out.println(
                "\n======================================"
        );
    }
}