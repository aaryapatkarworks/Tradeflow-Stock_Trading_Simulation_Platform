package com.aaryapatkarworks.tradeflow;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    void updateStockPrice(String symbol,
                          double newPrice) {

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println("Stock not found.");

            return;
        }

        stock.updatePrice(newPrice);
    }

    void loadStocksFromCSV(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            // Skip header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int stockId = Integer.parseInt(data[0]);
                String companyName = data[1];
                String stockSymbol = data[2];
                double currentPrice = Double.parseDouble(data[3]);

                addStock(new Stock(
                        stockId,
                        companyName,
                        stockSymbol,
                        currentPrice
                ));
            }

            System.out.println("Stocks loaded successfully.");

        }

        catch (IOException e) {

            System.out.println("Unable to load stock data.");
        }

    }
}