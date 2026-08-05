package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.Holding;
import com.aaryapatkarworks.tradeflow.model.Portfolio;

import com.aaryapatkarworks.tradeflow.model.Stock;

public class PortfolioAnalyticsService {

    public double calculateTotalReturn(Portfolio portfolio) {

        return portfolio.getProfitLoss();
    }

    public double calculateReturnPercentage(
            Portfolio portfolio) {

        if (portfolio.getTotalInvestment() == 0) {

            return 0;
        }

        return (portfolio.getProfitLoss()
                / portfolio.getTotalInvestment()) * 100;
    }

    // ---------------- Best Performing Holding ----------------

    public Holding getBestHolding(
            Portfolio portfolio) {

        if (portfolio.getHoldings().isEmpty()) {

            return null;
        }

        Holding bestHolding =
                portfolio.getHoldings().get(0);

        for (Holding holding : portfolio.getHoldings()) {

            if (holding.getProfitLoss()
                    > bestHolding.getProfitLoss()) {

                bestHolding = holding;
            }
        }

        return bestHolding;
    }

    // ---------------- Worst Performing Holding ----------------

    public Holding getWorstHolding(
            Portfolio portfolio) {

        if (portfolio.getHoldings().isEmpty()) {

            return null;
        }

        Holding worstHolding =
                portfolio.getHoldings().get(0);

        for (Holding holding : portfolio.getHoldings()) {

            if (holding.getProfitLoss()
                    < worstHolding.getProfitLoss()) {

                worstHolding = holding;
            }
        }

        return worstHolding;
    }

    // ---------------- Display Analytics ----------------

    public void displayAnalytics(Portfolio portfolio) {

        System.out.println(
                "\n========== PORTFOLIO ANALYTICS ==========\n"
        );

        System.out.printf(
                "Total Return        : ₹%.2f%n",
                calculateTotalReturn(portfolio)
        );

        System.out.printf(
                "Return Percentage   : %.2f%%%n",
                calculateReturnPercentage(portfolio)
        );

        Holding bestHolding =
                getBestHolding(portfolio);

        Holding worstHolding =
                getWorstHolding(portfolio);

        if (bestHolding != null) {

            System.out.println();

            System.out.println(
                    "Best Performer      : "
                            + bestHolding.getStock().getStockSymbol()
            );

            System.out.printf(
                    "Profit              : ₹%.2f%n",
                    bestHolding.getProfitLoss()
            );
        }

        if (worstHolding != null) {

            System.out.println();

            System.out.println(
                    "Worst Performer     : "
                            + worstHolding.getStock().getStockSymbol()
            );

            System.out.printf(
                    "Profit              : ₹%.2f%n",
                    worstHolding.getProfitLoss()
            );
        }

        displayAllocation(portfolio);
        
    }

    // ---------------- Portfolio Allocation ----------------

    public void displayAllocation(
            Portfolio portfolio) {

        System.out.println(
                "\n========== PORTFOLIO ALLOCATION ==========\n"
        );

        double totalValue =
                portfolio.getCurrentValue();

        if (totalValue == 0) {

            System.out.println(
                    "Portfolio is empty."
            );

            return;
        }

        for (Holding holding : portfolio.getHoldings()) {

            double allocation =

                    (holding.getCurrentValue()
                            / totalValue)
                            * 100;

            System.out.printf(
                    "%-15s %6.2f%%%n",
                    holding.getStock()
                            .getStockSymbol(),
                    allocation
            );
        }
    }



}