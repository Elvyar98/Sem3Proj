package com.github.oscareriksson02.bikeWorkShop.integration;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for storing the customers in the system.
 * It will be used by the controller to add and retrieve customers from the system.
 */
public class CustomerRegistry {
    private List<CustomerDTO> customers = new ArrayList<>();
    private static CustomerRegistry instance;
   
    /**
     * Constructor that adds a customer for testing
     * 
     */
    private CustomerRegistry() {
        BikeDTO cykel1 = new BikeDTO("Centurion", "Super Le Mans", "1983");
        AdressDTO adress1 = new AdressDTO("Hittepåvägen 34", "12345", "Stockholm");
        CustomerDTO kalle = new CustomerDTO("1", "Kalle Jansson", "kalle@jansson", "0701234567", cykel1, adress1);

        BikeDTO cykel2 = new BikeDTO("Trek", "FX3", "2021");
        AdressDTO adress2 = new AdressDTO("Storgatan 12", "54321", "Göteborg");
        CustomerDTO lisa = new CustomerDTO("2", "Lisa Svensson", "lisa@svensson", "0709876543", cykel2, adress2);

        BikeDTO cykel3 = new BikeDTO("Giant", "Escape 3", "2019");
        AdressDTO adress3 = new AdressDTO("Lillgatan 5", "67890", "Malmö");
        CustomerDTO erik = new CustomerDTO("3", "Erik Lindqvist", "erik@lindqvist", "0731234567", cykel3, adress3);

        customers.add(kalle);
        customers.add(lisa);
        customers.add(erik);
    }

      /**
     * Return an instance of the CustomerRegistry.
     * If instance == null it creates a new one.
     * @return instance
     */

    public static CustomerRegistry getInstance() {
        if (instance == null) {
            instance = new CustomerRegistry();
        }

        return instance;
    }

     /**
     * Method specifically for ressetting instance for tests
     */
    static void resetInstance() {
        instance = null;
    }
    
    /**
     * This methood returns CustomerDTO with the given number. if none is found it returns null.
     * Throws a DatabaseFailureException when "1234567" is searched
     * @param number
     * @return customerDTO
     */
    public CustomerDTO searchCustomer(String number) {
        if (number.equals("1234567")){
            throw new DatabaseFailureException("Unable to reach database server");
        }


        for (CustomerDTO customerDTO : customers) {
            if(customerDTO.getPhoneNumber().equals(number))
                return customerDTO;
        }
        return null;
    }    
}
