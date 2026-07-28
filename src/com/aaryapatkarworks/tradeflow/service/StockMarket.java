package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StockMarket {

    private ArrayList<Stock> stocks;

    public StockMarket() {

        stocks = new ArrayList<>();
    }

    // ---------------- Add Stock ----------------

    public void addStock(Stock stock) {

        stocks.add(stock);
    }

    // ---------------- Display Market ----------------

    public void displayMarket() {

        System.out.println("\n========== STOCK MARKET ==========\n");

        if (stocks.isEmpty()) {

            System.out.println("No stocks available.");
            return;
        }

        for (Stock stock : stocks) {

            stock.displayStock();
            System.out.println();
        }
    }

    // ---------------- Search ----------------

    public Stock findStock(String stockSymbol) {

        for (Stock stock : stocks) {

            if (stock.getStockSymbol()
                    .equalsIgnoreCase(stockSymbol)) {

                return stock;
            }
        }

        return null;
    }

    // ---------------- Update Price ----------------

    public void updateStockPrice(String stockSymbol,
                                 double newPrice) {

        Stock stock = findStock(stockSymbol);

        if (stock == null) {

            System.out.println("Stock not found.");
            return;
        }

        stock.updatePrice(newPrice);
    }

    // ---------------- Load CSV ----------------

    public void loadStocksFromCSV(String filePath) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            // Skip Header
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length != 4) {
                    continue;
                }

                int stockId = Integer.parseInt(data[0].trim());

                String companyName = data[1].trim();

                String stockSymbol = data[2].trim();

                double currentPrice = Double.parseDouble(data[3].trim());

                addStock(
                        new Stock(
                                stockId,
                                companyName,
                                stockSymbol,
                                currentPrice
                        )
                );
            }

            System.out.println("Stocks loaded successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ---------------- Getter ----------------

    public ArrayList<Stock> getStocks() {

        return stocks;
    }
}