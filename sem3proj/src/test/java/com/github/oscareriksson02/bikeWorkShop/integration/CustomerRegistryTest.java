package com.github.oscareriksson02.bikeWorkShop.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CustomerRegistryTest {

    private CustomerRegistry customerRegistry;

    @BeforeEach
    void setUp() {
        customerRegistry = new CustomerRegistry();
    }

    @Test
    void searchCustomer_existingPhoneNumber_returnsCorrectCustomer() {
        CustomerDTO result = customerRegistry.searchCustomer("0701234567");

        assertNotNull(result, "Customer should be found and not be null");
        assertEquals("kalle@jansson", result.getEmail());
        assertEquals("0701234567", result.getPhoneNumber());
    }

    @Test
    void searchCustomer_nonExistingPhoneNumber_returnsNull() {
        CustomerDTO result = customerRegistry.searchCustomer("0000000000");
        assertNull(result, "Should return null when customer does not exist");
    }

    @Test
    void searchCustomer_emptyString_returnsNull() {
        CustomerDTO result = customerRegistry.searchCustomer("");
        assertNull(result, "Should return null for an empty phone number");
    }

    @Test
    void searchCustomer_partialPhoneNumber_returnsNull() {
        CustomerDTO result = customerRegistry.searchCustomer("070123");
        assertNull(result, "A partial phone number should not match");
    }

    @Test
    void searchCustomer_hardCodedDatabaseFailure() {
        DatabaseFailureException e = assertThrows(DatabaseFailureException.class, 
    () -> customerRegistry.searchCustomer("1234567"));
        assertEquals("Unable to reach database server", e.getMessage());
    }
}

