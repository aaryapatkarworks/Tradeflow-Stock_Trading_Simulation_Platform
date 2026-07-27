package com.aaryapatkarworks.tradeflow.exceptions;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException() {

        super("Quantity must be greater than zero.");
    }
}