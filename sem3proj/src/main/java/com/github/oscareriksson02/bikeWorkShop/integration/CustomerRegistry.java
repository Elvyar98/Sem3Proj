package com.github.oscareriksson02.bikeWorkShop.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for storing the customers in the system.
 * It will be used by the controller to add and retrieve customers from the system.
 */
public class CustomerRegistry {
    private List<CustomerDTO> customers = new ArrayList<>();
}
