package com.gpsolutions.edu.java.training.example.exception;

/**
 * @author Wladimir Litvinov
 */
public class BadCourseOperationException extends Exception {
    public BadCourseOperationException() {
        super();
    }

    public BadCourseOperationException(final String message) {
        super(message);
    }
}
