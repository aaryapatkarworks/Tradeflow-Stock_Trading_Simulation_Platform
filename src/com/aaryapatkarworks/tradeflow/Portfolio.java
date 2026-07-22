package com.aaryapatkarworks.tradeflow;

import java.util.ArrayList;

public class Portfolio {

    ArrayList<Holding> holdings;

    Portfolio() {

        holdings = new ArrayList<>();
    }

    void addHolding(Holding holding) {

        holdings.add(holding);

        System.out.println("Holding added to Portfolio.");
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