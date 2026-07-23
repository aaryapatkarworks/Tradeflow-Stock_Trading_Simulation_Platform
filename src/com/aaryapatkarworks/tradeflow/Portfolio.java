package com.aaryapatkarworks.tradeflow;

import java.util.ArrayList;

public class Portfolio {

    ArrayList<Holding> holdings;

    Portfolio() {

        holdings = new ArrayList<>();
    }

    void addHolding(Stock stock, int quantity) {

        for (Holding holding : holdings) {

            if (holding.stock.stockSymbol.equals(stock.stockSymbol)) {

                holding.addQuantity(quantity, stock.currentPrice);

                System.out.println("Existing holding updated.");

                return;
            }
        }

        holdings.add(
                new Holding(
                        stock,
                        quantity,
                        stock.currentPrice
                )
        );

        System.out.println("New holding added to portfolio.");
    }

    Holding getHolding(Stock stock) {

        for (Holding holding : holdings) {

            if (holding.stock.stockSymbol.equals(stock.stockSymbol)) {

                return holding;
            }
        }

        return null;
    }

    void removeHolding(Holding holding) {

        holdings.remove(holding);
    }

    void displayPortfolio() {

        System.out.println("\n========== PORTFOLIO ==========\n");

        if (holdings.isEmpty()) {

            System.out.println("Portfolio is empty.");

            return;
        }

        for (Holding holding : holdings) {

            holding.displayHolding();

            System.out.println("----------------------------");
        }
    }
}