package com.github.oscareriksson02.bikeWorkShop.controller;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.Printer;

/**
 * This class is responsible for controlling the flow of the application.
 * It will be used by the view to interact with the model and the integration layer.
 */
public class Controller {
    private CustomerRegistry customerRegistry;
    private OrderRegistry orderRegistry;
    private Printer printer;

    /**
     * This is the constructor for the Controller class. It takes a RegistryCreator and a Printer as parameters and initializes the customerRegistry, orderRegistry and printer fields.
     * @param creator
     * @param printer
     */
    public Controller(RegistryCreator creator, Printer printer) {
        this.customerRegistry = creator.getCustomerRegistry();
        this.orderRegistry = creator.getOrderRegistry();
        this.printer = printer;
    }

    /**
     * Searches customer via number and returns CustomerDTO
     * @param number
     * @return CustomerDTO
     */

    public CustomerDTO searchCustomer(String number)
    {
        CustomerDTO cust = customerRegistry.searchCustomer(number);
        return cust;
    }

}
