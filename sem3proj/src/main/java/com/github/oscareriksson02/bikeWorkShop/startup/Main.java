package com.github.oscareriksson02.bikeWorkShop.startup;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.view.View;
import com.github.oscareriksson02.bikeWorkShop.integration.Printer;
import com.github.oscareriksson02.bikeWorkShop.integration.FileLogger;

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
        FileLogger filelogger = new FileLogger();
        Controller contr = new Controller(creator, printer, filelogger);
        View view = new View(contr);

        view.searchCustomer("0701234567");

       view.createRepairOrder("0701234567", "Punkterat bakdäck och en gnällig kedja");

        view.printOrdersByState(OrderState.NEWLY_CREATED);

        view.addRepairTask(1, "Byt däcktub", 400);
        view.addRepairTask(1, "Byt kedja", 450);
        view.addRepairTask(1, "Smörj kedja", 100);

        view.addDiagnosticReport(1, "Vi kommer ta alla dina pengar", "2026-09-30");

        //view.rejectRepairOrder(1);
        view.acceptRepairOrder(1);

       
    }
}
