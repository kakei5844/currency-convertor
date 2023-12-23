package com.fdmgroup.converter;

/**
 * Exception class for when an invalid currency is encountered.
 * <p>Thrown when attempting to use an unsupported or invalid currency.
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class InvalidCurrencyException extends Exception {
    /**
     * Constructs an InvalidCurrencyException with the specified error message.
     * 
     * @param   message the error message
     */
    public InvalidCurrencyException(String message) {
        super(message);
    }
}
