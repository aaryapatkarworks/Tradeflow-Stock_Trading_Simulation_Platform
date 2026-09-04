package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.Holding;
import com.aaryapatkarworks.tradeflow.model.Portfolio;

public class PortfolioPerformanceService {

    public double getPortfolioReturnPercentage(
            Portfolio portfolio) {

        if (portfolio.getTotalInvestment() == 0) {
            return 0;
        }

        return (portfolio.getProfitLoss()
                / portfolio.getTotalInvestment()) * 100;
    }

    public double getAverageMarketChange(
            StockMarket stockMarket) {

        if (stockMarket.getStocks().isEmpty()) {
            return 0;
        }

        double totalChange = 0;

        for (var stock : stockMarket.getStocks()) {
            totalChange += stock.getPriceChangePercentage();
        }

        return totalChange /
                stockMarket.getStocks().size();
    }

    public double getPerformanceDifference(
            Portfolio portfolio,
            StockMarket stockMarket) {

        double portfolioReturn =
                getPortfolioReturnPercentage(portfolio);

        double marketReturn =
                getAverageMarketChange(stockMarket);

        return portfolioReturn - marketReturn;
    }

    public String getPerformanceInsight(
            Portfolio portfolio,
            StockMarket stockMarket) {

        double difference =
                getPerformanceDifference(
                        portfolio,
                        stockMarket
                );

        if (difference >= 5) {
            return "Portfolio is significantly outperforming the market.";
        } else if (difference > 0) {
            return "Portfolio is outperforming the market.";
        } else if (difference < 0) {
            return "Portfolio is underperforming the market.";
        } else {
            return "Portfolio performance is in line with the market.";
        }
    }

    public String getPerformanceStatus(
            Portfolio portfolio,
            StockMarket stockMarket) {

        double difference =
                getPerformanceDifference(
                        portfolio,
                        stockMarket
                );

        if (difference >= 5) {
            return "STRONG OUTPERFORMANCE";
        } else if (difference > 0) {
            return "OUTPERFORMING";
        } else if (difference < 0) {
            return "UNDERPERFORMING";
        } else {
            return "MATCHING MARKET";
        }
    }

    public String getBenchmarkDescription() {
        return "Market Average (All Tracked Stocks)";
    }

    public void displayPerformance(
            Portfolio portfolio,
            StockMarket stockMarket) {

        System.out.println(
                "\n========== PORTFOLIO PERFORMANCE ==========\n"
        );

        if (portfolio.getHoldings().isEmpty()) {
            System.out.println("Portfolio is empty.");

            System.out.println(
                    "\n==========================================="
            );

            return;
        }

        double portfolioReturn =
                getPortfolioReturnPercentage(portfolio);

        double marketReturn =
                getAverageMarketChange(stockMarket);

        System.out.println(
                "Benchmark        : "
                        + getBenchmarkDescription()
        );

        double performanceDifference =
                getPerformanceDifference(
                        portfolio,
                        stockMarket
                );

        System.out.printf(
                "Portfolio Return : %+.2f%%%n",
                portfolioReturn
        );

        System.out.printf(
                "Market Return    : %+.2f%%%n",
                marketReturn
        );

        System.out.printf(
                "Outperformance   : %+.2f%%%n",
                performanceDifference
        );

        System.out.println(
                "Performance      : "
                        + getPerformanceStatus(
                        portfolio,
                        stockMarket
                )
        );

        System.out.println(
                "Insight           : "
                        + getPerformanceInsight(
                        portfolio,
                        stockMarket
                )
        );

        System.out.println(
                "\n==========================================="
        );
    }
}