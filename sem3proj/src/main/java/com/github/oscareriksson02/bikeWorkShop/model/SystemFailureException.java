package com.github.oscareriksson02.bikeWorkShop.model;

/**
 * A general system failure exception class that increases the
 * hides infrastructure failures from view. 
 */
public class SystemFailureException extends RuntimeException {

/**
 * Constructor for the SystemFailureException class
 * @param message
 * @param cause
 */

public SystemFailureException (String message, Throwable cause) {
    super(message, cause);

}
}
