package com.github.oscareriksson02.bikeWorkShop.model;

/**
 * Discount Strategy interface
 */
public interface DiscountStrategy {

    /**
     * Method to apply discount
     * @param totalCost
     * @return
     */
    int applyDiscount(int totalCost);

}
