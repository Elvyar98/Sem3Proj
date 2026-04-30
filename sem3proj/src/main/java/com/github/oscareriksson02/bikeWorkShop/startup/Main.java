package com.github.oscareriksson02.bikeWorkShop.startup;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.model.OrderBuilder;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;

import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.view.View;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.Printer;

 /**
     * This is the main class of the application. 
     * It creates the necessary objects and starts the application by creating a new View.
     */
public class Main {
    /**
     * This is the main method that starts the application.
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        RegistryCreator creator = new RegistryCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(creator, printer);
        View view = new View(contr);

        view.searchCustomer("0701234567");
        view.createRepairOrder("0701234567", "Punkterat bakdäck");
        view.printOrdersByState(OrderState.NEWLY_CREATED);
        view.addRepairTask(1, "Byt däcktub", 400);
        view.addDiagnosticReport(1, "Vi kommer ta alla dina pengar", "2026-09-30");
        view.acceptRepairOrder(1);

        /*y orderRegistry = creator.getOrderRegistry();
        System.out.println("Test Order builder: ");
        List<OrderDTO> orders = orderRegistry.findOrdersByState("Newly Created"); 
        OrderDTO originalDTO = orders.get(0); // End detta och raden ovan sen detta är ett litet hack


        System.out.println("Original: " + originalDTO);

        // 3. Bygg en ny OrderBuilder med ett ändrat värde
        OrderDTO updatedOrder = new OrderBuilder.Builder(originalDTO)
        .state("In Progress")
        .build();

        System.out.println("Updated: " + updatedOrder);
        */
    }
}
