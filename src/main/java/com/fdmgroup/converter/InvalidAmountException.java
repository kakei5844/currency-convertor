package com.fdmgroup.converter;

/**
 * Exception class for when an invalid amount is encountered.
 * <p>Thrown when attempting to use an invalid amount in a transaction.
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class InvalidAmountException extends Exception {
    /**
     * Constructs an InvalidAmountException with the specified error message.
     * 
     * @param message the error message
     */
    public InvalidAmountException(String message) {
        super(message);
    }
}