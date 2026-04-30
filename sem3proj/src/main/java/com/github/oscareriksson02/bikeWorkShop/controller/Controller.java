package com.github.oscareriksson02.bikeWorkShop.controller;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.Printer;
import com.github.oscareriksson02.bikeWorkShop.model.Order;

import java.util.List;

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
     * Returns customer with given number from customerRegistry.
     */
    public CustomerDTO searchCustomer(String number)
    {
        return customerRegistry.searchCustomer(number);
    }

    /**
     * Returns orderId from newly created repairOrder
     * @param phoneNumber
     * @param problemDescription
     * @return orderId
     */
    public int createNewRepairOrder(String phoneNumber, String problemDescription) {
        return orderRegistry.createNewRepairOrder(phoneNumber, problemDescription);
    }

    /**
     * Returns all ordersDTO:s in order registry with matching state value as list.
     * @param state State of oreder completion
     * @return List of matching order DTO:s
     */
    public List<OrderDTO> findOrdersByState(String state) {
        return orderRegistry.findOrdersByState(state);
    }

    /**
     * Adds repair task to a specific order by using order id
     * @param orderId
     * @param repairTaskDescription
     * @param cost
     */

    public void addRepairTask(int orderId, String repairTaskDescription, int cost) {
        Order order = new Order(orderId, orderRegistry);
        order.addRepairTask(repairTaskDescription, cost);
    }

    public void addDiagnosticReport(int orderId, String diagnosticReport, String estimatedTimeOfCompletion) {
        Order order = new Order(orderId, orderRegistry);
        order.addDiagnosticReport(diagnosticReport);
        order.addEstimatedTimeOfCompletion(estimatedTimeOfCompletion);
        

    }


}
