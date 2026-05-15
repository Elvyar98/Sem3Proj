package com.github.oscareriksson02.bikeWorkShop.startup;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.integration.RepairOrderLogger;
import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.view.RepairOrderView;
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
        RepairOrderView repairOrderView = new RepairOrderView();
        RepairOrderLogger repairOrderLogger = new RepairOrderLogger();
        contr.addObserver(repairOrderLogger);
        contr.addObserver(repairOrderView);
        view.run();


       
    }
}
