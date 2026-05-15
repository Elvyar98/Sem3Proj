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
        BikeDTO cykel = new BikeDTO("Centurion", "Super Le Mans", "1983");
        AdressDTO adress = new AdressDTO("Hittepåvägen 34", "12345", "Stockholm");
        CustomerDTO kalle = new CustomerDTO("1", "Kalle Jansson", "kalle@jansson", "0701234567", cykel, adress);

        customers.add(kalle);
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
