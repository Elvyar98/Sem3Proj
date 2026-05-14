package com.github.oscareriksson02.bikeWorkShop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.github.oscareriksson02.bikeWorkShop.integration.FileLogger;
import com.github.oscareriksson02.bikeWorkShop.integration.Printer;
import com.github.oscareriksson02.bikeWorkShop.integration.RegistryCreator;
import com.github.oscareriksson02.bikeWorkShop.model.CustomerNotFoundException;


public class ControllerTest {
    private RegistryCreator creator;
   
    private Printer printer;
    private FileLogger fileLogger;
    private Controller controller;

    @BeforeEach 
    void setUp() {
        creator = new RegistryCreator();
        printer = new Printer();
        fileLogger = new FileLogger();
        controller = new Controller(creator, printer, fileLogger);
    }

@Test
void searchCustomer_exceptionsNotThrownDuringSuccessfulExecution() {
    assertDoesNotThrow(() -> controller.searchCustomer("0701234567"));
}

@Test
void searchCustomer_customerNotFoundExceptionThrownWithUnkownNUmber(){
    CustomerNotFoundException e = assertThrows(CustomerNotFoundException.class, 
    () -> controller.searchCustomer("0801234567"));
    assertEquals("Customer does not exist", e.getMessage()); 
}
}
