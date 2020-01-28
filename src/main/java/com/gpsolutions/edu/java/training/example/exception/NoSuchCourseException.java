package com.gpsolutions.edu.java.training.example.exception;

/**
 * @author Wladimir Litvinov
 */
public class NoSuchCourseException extends Exception {

	public NoSuchCourseException() {
        super();
    }

    public NoSuchCourseException(final String message) {
        super(message);
    }
}
