package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.Holding;
import com.aaryapatkarworks.tradeflow.model.Portfolio;

public class PortfolioRiskService {

    // ---------------- Largest Exposure ----------------

    public Holding getLargestExposure(
            Portfolio portfolio) {

        if (portfolio.getHoldings().isEmpty()) {

            return null;
        }

        Holding largestHolding =
                portfolio.getHoldings().get(0);

        for (Holding holding :
                portfolio.getHoldings()) {

            if (holding.getCurrentValue()
                    > largestHolding.getCurrentValue()) {

                largestHolding = holding;
            }
        }

        return largestHolding;
    }

    // ---------------- Concentration Percentage ----------------

    public double getLargestExposurePercentage(
            Portfolio portfolio) {

        if (portfolio.getCurrentValue() == 0) {

            return 0;
        }

        Holding largestHolding =
                getLargestExposure(portfolio);

        if (largestHolding == null) {

            return 0;
        }

        return (largestHolding.getCurrentValue()
                / portfolio.getCurrentValue()) * 100;
    }

    public String getRiskLevel(Portfolio portfolio) {

        double exposurePercentage =
                getLargestExposurePercentage(portfolio);

        if (exposurePercentage <= 40) {
            return "LOW";
        } else if (exposurePercentage <= 60) {
            return "MODERATE";
        } else {
            return "HIGH";
        }
    }

    public String getDiversificationStatus(Portfolio portfolio) {

        int holdingCount = portfolio.getHoldings().size();
        double largestExposure =
                getLargestExposurePercentage(portfolio);

        if (holdingCount <= 1) {
            return "POOR";
        }

        if (holdingCount <= 3 || largestExposure > 60) {
            return "MODERATE";
        }

        return "GOOD";
    }

    public String getRiskRecommendation(Portfolio portfolio) {

        String riskLevel = getRiskLevel(portfolio);

        switch (riskLevel) {

            case "LOW":
                return "Portfolio is well diversified.";

            case "MODERATE":
                return "Consider increasing diversification.";

            case "HIGH":
                return "High concentration detected. Consider reducing largest exposure.";

            default:
                return "Unable to determine recommendation.";
        }
    }

    // ---------------- Display Risk ----------------

    public void displayRiskAnalysis(
            Portfolio portfolio) {

        System.out.println(
                "\n========== PORTFOLIO RISK ANALYSIS ==========\n"
        );

        if (portfolio.getHoldings().isEmpty()) {

            System.out.println(
                    "Portfolio is empty."
            );

            System.out.println(
                    "\n============================================="
            );

            return;
        }

        Holding largestHolding =
                getLargestExposure(portfolio);

        System.out.println(
                "Largest Exposure : "
                        + largestHolding.getStock()
                        .getStockSymbol()
        );

        System.out.printf(
                "Exposure Value   : ₹%.2f%n",
                largestHolding.getCurrentValue()
        );

        System.out.printf(
                "Portfolio Share  : %.2f%%%n",
                getLargestExposurePercentage(portfolio)
        );

        System.out.println(
                "Risk Level       : "
                        + getRiskLevel(portfolio)
        );

        System.out.println(
                "Diversification  : "
                        + getDiversificationStatus(portfolio)
        );

        System.out.println(
                "Recommendation    : "
                        + getRiskRecommendation(portfolio)
        );

        System.out.println(
                "\n============================================="
        );
    }
}