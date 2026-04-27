package com.github.oscareriksson02.bikeWorkShop.integration;

import com.github.oscareriksson02.bikeWorkShop.integration.BikeDTO;

/**
 * This is a class for the Customer Data Transfer Object (DTO). .
 */
public class CustomerDTO {
    private String customerID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private BikeDTO bikeDTO;
    private AdressDTO adressDTO;

    /**
     * This is a constructor for the CustomerDTO class.
     */
    public CustomerDTO(String customerID, String fullName, String email, 
                   String phoneNumber, BikeDTO bikeDTO, AdressDTO adressDTO) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeDTO = bikeDTO;
        this.adressDTO = adressDTO;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
    return phoneNumber;
}
    public BikeDTO getBikeDTO() {
        return bikeDTO;
    }

    public AddressDTO getAdressDTO() {
        return adressDTO;
    }
}