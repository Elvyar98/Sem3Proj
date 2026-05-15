package com.github.oscareriksson02.bikeWorkShop.model;

import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;

public class LoyaltyDiscount implements DiscountStrategy {
    private String phoneNumber;
    private OrderRegistry orderRegistry;

    public LoyaltyDiscount(String phoneNumber, OrderRegistry orderRegistry)  {
        this.phoneNumber = phoneNumber;
        this.orderRegistry = orderRegistry;
    }

    /**
     * Applies LoyaltyDiscount to totalCost for 
     * every third accepted order. 
     */
    public int applyDiscount(int totalCost) {
        int amount = getTimesCustomerRepairedBike();

        if (amount > 0 && amount % 3 == 0){
            totalCost = (int) (totalCost * 0.9);
        }

        return totalCost;
        
    }

    /**
     * Calculates how many times a customer has repaired bike
     * in shop. 
     * @return timesCustomerHasRepairedBike 
     */
    private int getTimesCustomerRepairedBike () {

        int timesCustomerHasRepairedBike = 0;
        for (OrderDTO order : orderRegistry.getOrderList()){
            CustomerDTO cust = order.getCustomerDTO();
            if (cust.getPhoneNumber().equals(phoneNumber) && 
            order.getState() == OrderState.ACCEPTED){
                timesCustomerHasRepairedBike++;
            };
        }
        return timesCustomerHasRepairedBike;
    }
}

