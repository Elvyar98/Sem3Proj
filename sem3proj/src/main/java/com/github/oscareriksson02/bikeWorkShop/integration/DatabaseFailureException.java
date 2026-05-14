package com.github.oscareriksson02.bikeWorkShop.integration;

/**
 * DatabaseFailureException class is thrown when
 * the database is unreacble for any reason
 */


public class DatabaseFailureException extends RuntimeException {
    /**
     * Constructor for DatabaseFailureException.
     * @param message
     * @param cause
     */

    public DatabaseFailureException(String message, Throwable cause) {
        super(message, cause);

    }

      /**
     * Constructor for stimulated DatabaseFailureException.
     * @param message
     */

    public DatabaseFailureException(String message) {
        super(message);

    }


}
