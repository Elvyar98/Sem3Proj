package com.github.oscareriksson02.bikeWorkShop.view;

import java.util.ArrayList;
import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
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

<<<<<<< HEAD
    private void findOrdersByState(int state) {
        List<OrderDTO> orders = Controller.findOrdersByState(state);
        
    }
=======


>>>>>>> a88f5aa63541c275859d8b76739a50787e6074b0
}
