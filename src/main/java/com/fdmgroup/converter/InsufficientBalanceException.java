package com.fdmgroup.converter;

/**
 * Exception class for when there is insufficient balance in an account.
 * <p>Thrown when attempting to perform a transaction with insufficient balance.
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class InsufficientBalanceException extends Exception {
    /**
     * Constructs an InsufficientBalanceException with the specified error message.
     * 
     * @param   message the error message
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
