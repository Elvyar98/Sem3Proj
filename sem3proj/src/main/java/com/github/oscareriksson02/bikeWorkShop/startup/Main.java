package com.github.oscareriksson02.bikeWorkShop.startup;

import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.controller.Controller;
import com.github.oscareriksson02.bikeWorkShop.view.View;
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
        new View(contr);
    }

}
