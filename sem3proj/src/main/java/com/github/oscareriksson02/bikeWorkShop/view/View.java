package com.github.oscareriksson02.bikeWorkShop.view;

import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
/**
 * This class is responsible for displaying the user interface for the customer.
 * It will be used by the controller to display the user interface for the customer.
 */

public class View {
    private Controller contr;

    public View(Controller controller) {
        this.contr = controller;
    }
    
    /**
     * Searches customer via number and prints out full details
     * @param number
     */
    public void searchCustomer(String number)
    {
        CustomerDTO cust = contr.searchCustomer(number);
        if (cust != null) {
            System.out.println(cust);
        }
        else {
            System.out.println("Customer doesn't exist");
        }
        
    }



}
