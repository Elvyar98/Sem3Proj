package com.github.oscareriksson02.bikeWorkShop.controller;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.CustomerRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.DatabaseFailureException;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.Printer;
import com.github.oscareriksson02.bikeWorkShop.model.CustomerNotFoundException;
import com.github.oscareriksson02.bikeWorkShop.model.Order;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
import com.github.oscareriksson02.bikeWorkShop.model.SystemFailureException;

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
     * @param number
     * @throws CustomerNotFoundException
     */
    public CustomerDTO searchCustomer(String number) throws CustomerNotFoundException
    {
        try {
            CustomerDTO cust = customerRegistry.searchCustomer(number);

            if (cust != null) {
                return cust;
            } else {
                throw new CustomerNotFoundException(number, "Customer does not exist");
            }

        }
        catch (DatabaseFailureException e){
            throw new SystemFailureException("System failure\nContact Support", e);
        }
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
    public List<OrderDTO> findOrdersByState(OrderState state) {
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

    /**
     * Adds diagnostic report to order via order ID
     * @param orderId
     * @param diagnosticReport
     * @param estimatedTimeOfCompletion
     */

    public void addDiagnosticReport(int orderId, String diagnosticReport, String estimatedTimeOfCompletion) {
        Order order = new Order(orderId, orderRegistry);
        order.addDiagnosticReport(diagnosticReport);
        order.addEstimatedTimeOfCompletion(estimatedTimeOfCompletion);     

    }

    /**
     * Calls accept order in order via order id and calls printOrder function
     * @param orderId
     */

    public void acceptRepairOrder(int orderId) {
        Order order = new Order(orderId, orderRegistry);
        order.acceptRepairOrder();
        printOrder(orderId);

    }

    /**
     * Calls reject order function in order via order Id
     * @param orderId
     */

    public void rejectRepairOrder(int orderId) {
         Order order = new Order(orderId, orderRegistry);
        order.rejectRepairOrder();
        printOrder(orderId);
    }

    /*
    Funkar bra som det ska men annars kan man också bygga printer med tillgång till orderRegistry själv
    då behöver controller bara skicka order id och printer själv kan hitta rätt order sen skriva ut det */

    private void printOrder(int orderId) {
        OrderDTO orderDTO = orderRegistry.findOrderById(orderId);
        printer.printOrder(orderDTO);        
    }

}
