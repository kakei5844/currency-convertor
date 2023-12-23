package com.fdmgroup.converter;

/**
 * Exception class for when an unknown user is encountered.
 * <p>Thrown when attempting to retrieve a user that does not exist.
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class UnknownUserException extends Exception {
    /**
     * Constructs an UnknownUserException with the specified error message.
     * 
     * @param   message the error message
     */
    public UnknownUserException(String message) {
        super(message);
    }
}