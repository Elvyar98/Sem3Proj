package com.github.oscareriksson02.bikeWorkShop.integration;

public class RegistryCreator {

    /**
     * This class is responsible for creating the registries for the customers and orders.
     */
    public RegistryCreator() {
    }
    
    private CustomerRegistry customerRegistry = new CustomerRegistry();
    private OrderRegistry orderRegistry = new OrderRegistry();

    public CustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

     public OrderRegistry getOrderRegistry() {
        return orderRegistry;
    }
}
