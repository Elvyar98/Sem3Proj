package com.github.oscareriksson02.bikeWorkShop.integration;

/**
 * A DTO for customer bikes
 */
public class BikeDTO {
    private String bikeBrand;
    private String bikeModel;
    private String bikeSerialNumber;

    /**
     * Constructor for BikeDTO class
     * 
     * @param bikeBrand
     * @param bikeModel
     * @param bikeSerialNumber
     */
    public BikeDTO (String bikeBrand, String bikeModel, String bikeSerialNumber) {
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
    }

    /**
     * Getters for BikeDTO Class
     * @return bikeBrand
     * @return bikeModel
     * @return bikeSerialNumber
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

     public String getBikeModel() {
        return bikeModel;
    }

     public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }
}
