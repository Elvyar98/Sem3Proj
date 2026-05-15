package com.github.oscareriksson02.bikeWorkShop.model;


import com.github.oscareriksson02.bikeWorkShop.integration.CustomerRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for LoyaltyDiscount using Erik Lindqvist (0731234567).
// Singletons are reset before each test to ensure clean state.
class LoyaltyDiscountTest {

    // Erik's phone number matches hardcoded customer in CustomerRegistry
    private static final String ERIK_PHONE = "0731234567";
    private static final int TOTAL_COST = 1000;

    private OrderRegistry orderRegistry ;
    private LoyaltyDiscount loyaltyDiscount;

    @BeforeEach
    void setUp() {
        // Reset singletons for clean state
        CustomerRegistry.resetInstance();
        OrderRegistry.resetInstance();
        orderRegistry = OrderRegistry.getInstance();
        loyaltyDiscount = new LoyaltyDiscount(ERIK_PHONE, orderRegistry);
    }

    // Helper - creates and accepts an order for Erik
    private void createAcceptedOrder() {
        int orderId = orderRegistry.createNewRepairOrder(ERIK_PHONE, "Test repair");
        // Transition to ACCEPTED using OrderBuilder
        com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO original = 
            orderRegistry.findOrderById(orderId);
        com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO accepted = 
            new OrderBuilder.Builder(original)
                .state(OrderState.ACCEPTED)
                .build();
        orderRegistry.replaceOrderById(orderId, accepted);
    }

    // -----------------------------------------------------------------------
    // No discount cases
    // -----------------------------------------------------------------------

    @Test
    void applyDiscount_noOrders_returnsUnchangedCost() {
        int result = loyaltyDiscount.applyDiscount(TOTAL_COST);
        assertEquals(TOTAL_COST, result,
            "No discount should apply when customer has no completed orders.");
    }

    @Test
    void applyDiscount_oneAcceptedOrder_returnsUnchangedCost() {
        createAcceptedOrder();
        int result = loyaltyDiscount.applyDiscount(TOTAL_COST);
        assertEquals(TOTAL_COST, result,
            "No discount should apply when customer has only one completed order.");
    }

    @Test
    void applyDiscount_twoAcceptedOrders_returnsUnchangedCost() {
        createAcceptedOrder();
        createAcceptedOrder();
        int result = loyaltyDiscount.applyDiscount(TOTAL_COST);
        assertEquals(TOTAL_COST, result,
            "No discount should apply when customer has only two completed orders.");
    }

    // -----------------------------------------------------------------------
    // Discount cases
    // -----------------------------------------------------------------------

    @Test
    void applyDiscount_threeAcceptedOrders_returnsDiscountedCost() {
        createAcceptedOrder();
        createAcceptedOrder();
        createAcceptedOrder();
        int result = loyaltyDiscount.applyDiscount(TOTAL_COST);
        assertEquals(900, result,
            "10% discount should apply when customer has exactly three completed orders.");
    }

    @Test
    void applyDiscount_sixAcceptedOrders_returnsDiscountedCost() {
        for (int i = 0; i < 6; i++) createAcceptedOrder();
        int result = loyaltyDiscount.applyDiscount(TOTAL_COST);
        assertEquals(900, result,
            "10% discount should apply when customer has six completed orders.");
    }

    // -----------------------------------------------------------------------
    // Other customer not affected
    // -----------------------------------------------------------------------

    @Test
    void applyDiscount_threeOrdersForOtherCustomer_returnsUnchangedCost() {
        // Kalle has 3 orders, not Erik
        for (int i = 0; i < 3; i++) {
            orderRegistry.createNewRepairOrder("0701234567", "Test repair");
        }
        int result = loyaltyDiscount.applyDiscount(TOTAL_COST);
        assertEquals(TOTAL_COST, result,
            "Discount for another customer should not affect Erik's discount.");
    }
}