package com.github.oscareriksson02.bikeWorkShop.view;

import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
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

    /**
     * The function prints out an orderId after creating a new repair order
     * @param phoneNumber
     * @param problemDescription
     */
    public void createRepairOrder(String phoneNumber, String problemDescription) {
        int orderId = contr.createNewRepairOrder(phoneNumber, problemDescription);
        System.out.println("Customers Order Id: " + orderId);
    }
    
    /**
    * Prints all ordersDTO:s in order registry with matching state value.
    */
    public void printOrdersByState(OrderState state) {
        List<OrderDTO> orders = contr.findOrdersByState(state);
        
        for (OrderDTO orderDTO : orders) {
            System.out.println(orderDTO);
        }
    }

    /**
     * Adds repair task description and cost to order via orderId
     * @param orderId
     * @param repairTaskDescription
     * @param cost
     */

    public void addRepairTask(int orderId, String repairTaskDescription, int cost) {
        contr.addRepairTask(1,"Byt däcktub", 400);
    }

    /**
     * Function calls add diagnostic report function in controller
     * @param orderId
     * @param diagnosticReport
     * @param estimatedTimeOfCompletion
     */

    public void addDiagnosticReport(int orderId, String diagnosticReport, String estimatedTimeOfCompletion) {
        contr.addDiagnosticReport(orderId, diagnosticReport, estimatedTimeOfCompletion);

    }

    public void acceptRepairOrder(int orderId) {
        System.out.println(contr.acceptRepairOrder(orderId));
    }


}
