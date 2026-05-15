package com.github.oscareriksson02.bikeWorkShop.integration;


/**
 * AdressDTO class that stores a customers adress information
 */
public class AdressDTO {
    private String streetAdress;
    private String zipCode;
    private String city;

/**
 * Constructor for AdressDTO
 * @param streetAdress
 * @param zipCode
 * @param city
 */
    public AdressDTO (String streetAdress, String zipCode, String city) {
        this.streetAdress = streetAdress;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Override
    public String toString() {
    return streetAdress + ", " + zipCode + " " + city;
    }

    /**
     * Getters for AdressDTO
     * @return streetAdress;
     * @return zipCode;
     * @return city
     */

    public String getStreetAdress() {
        return streetAdress;
    }

     public String getZipCode() {
        return zipCode;
    }

     public String getCity() {
        return city;
    }

}
