package com.github.oscareriksson02.bikeWorkShop.view;

import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.model.CustomerNotFoundException;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
import com.github.oscareriksson02.bikeWorkShop.model.SystemFailureException;
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
     * Method is responsible for stimulating the repair task flow
     */
    public void run() {
        if (!searchCustomer("0701234567")) return;
        int orderId = createRepairOrder("0701234567", "Punkterat bakdäck och en gnällig kedja");
        //printOrdersByState(OrderState.NEWLY_CREATED);
        addRepairTask(orderId, "Byt däcktub", 400);
        addRepairTask(orderId, "Byt kedja", 450);
        addRepairTask(orderId, "Smörj kedja", 100);
        addDiagnosticReport(orderId, "Vi kommer ta alla dina pengar", "2026-09-30");
        acceptRepairOrder(orderId);
    }
    
    /**
     * Searches customer via number and prints out full details
     * @param number
     */
   public boolean searchCustomer(String number) {
        try {
            System.out.println("\n========== CUSTOMER DETAILS ==========");
            System.out.println(contr.searchCustomer(number));
            System.out.println("======================================");
            return true;
        } catch (CustomerNotFoundException | SystemFailureException e) {
            System.out.println("\n========== ERROR ==========");
            System.out.println(e.getMessage());
            System.out.println("===========================");
            return false;
        }
    }

    /**
     * The function prints out an orderId after creating a new repair order
     * @param phoneNumber
     * @param problemDescription
     */
    
    public int createRepairOrder(String phoneNumber, String problemDescription) {
        int orderId = contr.createNewRepairOrder(phoneNumber, problemDescription);
        System.out.println("\n========== REPAIR ORDER CREATED ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("==========================================");
        return orderId;
    }

    
    /**
    * Prints all ordersDTO:s in order registry with matching state value.
    */
    public void printOrdersByState(OrderState state) {
        List<OrderDTO> orders = contr.findOrdersByState(state);
        System.out.println("\n========== ORDERS WITH STATE: " + state + " ==========");
        for (OrderDTO orderDTO : orders) {
            System.out.println(orderDTO);
            System.out.println("------------------------------------------");
        }
        System.out.println("=============================================");
    }

    /**
     * Adds repair task description and cost to order via orderId
     * @param orderId
     * @param repairTaskDescription
     * @param cost
     */

    public void addRepairTask(int orderId, String repairTaskDescription, int cost) {
        contr.addRepairTask(orderId,repairTaskDescription,cost);
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

    /**
     * Function writes text on screen and calls accept repair order function in controller
     * @param orderId
     */

  public void acceptRepairOrder(int orderId) {
        System.out.println("\n========== ORDER ACCEPTED ==========");
        contr.acceptRepairOrder(orderId);
        System.out.println("====================================");
    }

    /**
     * Prints out text and calls reject order function in controller
     * @param orderId
     */

    public void rejectRepairOrder(int orderId) {
         System.out.println("\n-----------------------------------------");
        System.out.println("Order rejected, bye bye.");
        System.out.println("-----------------------------------------");
        contr.rejectRepairOrder(orderId);
    }



}
