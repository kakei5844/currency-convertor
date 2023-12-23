package com.fdmgroup.converter;

/**
 * Exception class for when the same currency is used in a transaction.
 * <p>Thrown when attempting to perform a transaction with the same currency.
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class SameCurrencyException extends Exception {
    /**
     * Constructs a SameCurrencyException with the specified error message.
     * 
     * @param   message the error message
     */
    public SameCurrencyException(String message) {
        super(message);
    }
}
