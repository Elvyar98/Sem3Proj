package com.github.oscareriksson02.bikeWorkShop.model;


/**
 * The CustomerNotFoundException class gets thrown
 * when a customer is not found in the database,
 * The class extends the Exception class
 */
public class CustomerNotFoundException extends Exception {
    private String phoneNumber;

    /**
     * Constructor for the CustomerNotFoundException class
     * @param phoneNumber
     * @param message
     */

    public CustomerNotFoundException(String phoneNumber, String message) {
        super(message);
        this.phoneNumber = phoneNumber;
    }
    

    public String getPhoneNumber(){
        return phoneNumber;
    }

}
