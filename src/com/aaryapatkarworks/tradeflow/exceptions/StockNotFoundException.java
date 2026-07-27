package com.aaryapatkarworks.tradeflow.exceptions;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String symbol) {

        super("Stock with symbol '" + symbol + "' was not found.");
    }
}