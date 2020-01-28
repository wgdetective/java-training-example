package com.gpsolutions.edu.java.training.example.exception;

/**
 * @author Wladimir Litvinov
 */
public class SuchUserAlreadyExistException extends Exception {

    public SuchUserAlreadyExistException() {
        super();
    }

    public SuchUserAlreadyExistException(final String message) {
        super(message);
    }
}
