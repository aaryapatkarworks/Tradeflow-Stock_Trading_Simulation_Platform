package com.aaryapatkarworks.tradeflow.service;
import com.aaryapatkarworks.tradeflow.model.Stock;
import java.util.Random;

public class MarketSimulationService {

    private final StockMarket stockMarket;

    private final Random random;

    public MarketSimulationService(
            StockMarket stockMarket) {

        this.stockMarket = stockMarket;

        random = new Random();
    }

    public void simulateMarket() {

        System.out.println(
                "Simulation Time : "
                        + java.time.LocalTime.now()
        );

        System.out.println();

        System.out.println(
                "\n========== MARKET SIMULATION ==========\n"
        );

        for (Stock stock : stockMarket.getStocks()) {

            double oldPrice = stock.getCurrentPrice();

            double volatility =
                    getVolatility(stock);

            double percentage =
                    (random.nextDouble() * volatility * 2)
                            - volatility;

            double newPrice =
                    oldPrice +
                            (oldPrice * percentage / 100);

            if (newPrice < 1) {

                newPrice = 1;
            }

            stock.updatePrice(newPrice);

            double difference =
                    newPrice - oldPrice;

            System.out.printf(
                    "%-12s ₹%.2f -> ₹%.2f (%+.2f | %+.2f%%)%n",
                    stock.getStockSymbol(),
                    oldPrice,
                    newPrice,
                    difference,
                    percentage
            );
        }

        System.out.println();

        System.out.println(
                "Market simulation completed successfully."
        );

        System.out.println(
                "\n======================================"
        );
    }

    private double getVolatility(Stock stock) {

        String symbol = stock.getStockSymbol();

        switch (symbol) {

            case "ITC":
            case "HDFCBANK":
            case "SBIN":
                return 1.0;

            case "TCS":
            case "INFY":
            case "RELIANCE":
                return 2.0;

            case "TECHM":
            case "BAJFINANCE":
            case "M&M":
                return 4.0;

            default:
                return 2.0;
        }
    }
}