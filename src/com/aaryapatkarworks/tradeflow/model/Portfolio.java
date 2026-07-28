package com.aaryapatkarworks.tradeflow.model;

import java.util.ArrayList;

public class Portfolio {

    private ArrayList<Holding> holdings;

    public Portfolio() {

        holdings = new ArrayList<>();
    }

    // ---------------- Portfolio Operations ----------------

    public void addHolding(Stock stock,
                           int quantity) {

        Holding existingHolding = getHolding(stock);

        if (existingHolding != null) {

            existingHolding.addQuantity(
                    quantity,
                    stock.getCurrentPrice()
            );
        }

        else {

            holdings.add(
                    new Holding(
                            stock,
                            quantity,
                            stock.getCurrentPrice()
                    )
            );
        }
    }

    public Holding getHolding(Stock stock) {

        for (Holding holding : holdings) {

            if (holding.getStock().getStockSymbol()
                    .equalsIgnoreCase(stock.getStockSymbol())) {

                return holding;
            }
        }

        return null;
    }

    public void removeHolding(Holding holding) {

        holdings.remove(holding);
    }

    // ---------------- Portfolio Statistics ----------------

    public int getTotalHoldings() {

        return holdings.size();
    }

    public double getTotalInvestment() {

        double total = 0;

        for (Holding holding : holdings) {

            total += holding.getInvestedAmount();
        }

        return total;
    }

    public double getCurrentValue() {

        double total = 0;

        for (Holding holding : holdings) {

            total += holding.getCurrentValue();
        }

        return total;
    }

    public double getProfitLoss() {

        return getCurrentValue() - getTotalInvestment();
    }

    // ---------------- Display ----------------

    public void displayPortfolio() {

        System.out.println("\n========== PORTFOLIO ==========\n");

        if (holdings.isEmpty()) {

            System.out.println("Portfolio is empty.");
            return;
        }

        for (Holding holding : holdings) {

            holding.displayHolding();
            System.out.println();
        }
    }
}