package com.github.oscareriksson02.bikeWorkShop.integration;

import com.github.oscareriksson02.bikeWorkShop.model.OrderBuilder;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// StubCustomerRegistry replaces dependancy 
// BeforeEach setUp() resets state before each test 
// Helper funtions minimize code repetition

class OrderRegistryTest {

    // -----------------------------------------------------------------------
    // Stub – replaces the real CustomerRegistry
    // -----------------------------------------------------------------------

    private static class StubCustomerRegistry extends CustomerRegistry {
        private final CustomerDTO customerToReturn;

        StubCustomerRegistry(CustomerDTO customer) {
            this.customerToReturn = customer;
        }

        @Override
        public CustomerDTO searchCustomer(String phoneNumber) {
            return customerToReturn;
        }
    }

    // -----------------------------------------------------------------------
    // Test fixtures
    // -----------------------------------------------------------------------

    private OrderRegistry orderRegistry;
    private CustomerDTO testCustomer;

    private static final String PHONE_A       = "0701234567";
    private static final String PHONE_B       = "0709876543";
    private static final String DESCRIPTION_A = "Flat tire";
    private static final String DESCRIPTION_B = "Broken brake";

    @BeforeEach
    void setUp() throws Exception {
        orderRegistry = new OrderRegistry();

        testCustomer = new CustomerDTO(
            "C001", "Test Person", "test@test.com", PHONE_A, null, null
        );

        Field cusRegField = OrderRegistry.class.getDeclaredField("cusReg");
        cusRegField.setAccessible(true);
        cusRegField.set(orderRegistry, new StubCustomerRegistry(testCustomer));
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private int createOrder(String phone, String description) {
        return orderRegistry.createNewRepairOrder(phone, description);
    }

    /**
     * Transitions an existing order to a new state using OrderBuilder.
     * Only the state field changes – all other fields are copied from the original.
     */
    private void transitionState(int orderId, OrderState newState) {
        OrderDTO original = orderRegistry.findOrderById(orderId);
        OrderDTO updated = new OrderBuilder.Builder(original)
            .state(newState)
            .build();
        orderRegistry.replaceOrderById(orderId, updated);
    }

    // -----------------------------------------------------------------------
    // createNewRepairOrder
    // -----------------------------------------------------------------------

    @Test
    void firstOrderReceivesIdOne() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        assertEquals(1, id,
            "The first order created should be assigned ID 1.");
    }

    @Test
    void secondOrderReceivesIdTwo() {
        createOrder(PHONE_A, DESCRIPTION_A);
        int id = createOrder(PHONE_B, DESCRIPTION_B);
        assertEquals(2, id,
            "The second order created should be assigned ID 2.");
    }

    @Test
    void createdOrderIsStoredInRegistry() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        assertNotNull(orderRegistry.findOrderById(id),
            "A just-created order must be retrievable from the registry.");
    }

    @Test
    void createdOrderHasStateNewlyCreated() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        assertEquals(OrderState.NEWLY_CREATED, orderRegistry.findOrderById(id).getState(),
            "A freshly created order must have state NEWLY_CREATED.");
    }

    @Test
    void createdOrderStoresProblemDescription() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        assertEquals(DESCRIPTION_A, orderRegistry.findOrderById(id).getProblemDescription(),
            "The stored order must contain the problem description that was supplied.");
    }

    // -----------------------------------------------------------------------
    // findOrderById
    // -----------------------------------------------------------------------

    @Test
    void findOrderById_existingId_returnsOrderWithCorrectId() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO result = orderRegistry.findOrderById(id);
        assertNotNull(result,
            "findOrderById should return a non-null DTO for an existing ID.");
        assertEquals(id, result.getOrderID(),
            "The returned DTO must carry the same ID that was searched for.");
    }

    @Test
    void findOrderById_nonExistentId_returnsNull() {
        OrderDTO result = orderRegistry.findOrderById(999);
        assertNull(result,
            "findOrderById should return null when the ID does not exist in the registry.");
    }

    @Test
    void findOrderById_afterMultipleCreations_returnsCorrectOrder() {
        int idA = createOrder(PHONE_A, DESCRIPTION_A);
        int idB = createOrder(PHONE_B, DESCRIPTION_B);

        assertEquals(DESCRIPTION_A, orderRegistry.findOrderById(idA).getProblemDescription(),
            "findOrderById should return order A when queried with A's ID.");
        assertEquals(DESCRIPTION_B, orderRegistry.findOrderById(idB).getProblemDescription(),
            "findOrderById should return order B when queried with B's ID.");
    }

    // -----------------------------------------------------------------------
    // findOrdersByState
    // -----------------------------------------------------------------------

    @Test
    void findOrdersByState_emptyRegistry_returnsEmptyList() {
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED);
        assertTrue(result.isEmpty(),
            "findOrdersByState should return an empty list when the registry contains no orders.");
    }

    @Test
    void findOrdersByState_noOrdersMatchState_returnsEmptyList() {
        createOrder(PHONE_A, DESCRIPTION_A);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.ACCEPTED);
        assertTrue(result.isEmpty(),
            "findOrdersByState should return an empty list when no orders have the requested state.");
    }

    @Test
    void findOrdersByState_oneOrderMatchesState_returnsListWithOneElement() {
        createOrder(PHONE_A, DESCRIPTION_A);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED);
        assertEquals(1, result.size(),
            "findOrdersByState should return exactly one element when one order matches.");
    }

    @Test
    void findOrdersByState_allOrdersMatchState_returnsAllOrders() {
        createOrder(PHONE_A, DESCRIPTION_A);
        createOrder(PHONE_B, DESCRIPTION_B);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED);
        assertEquals(2, result.size(),
            "findOrdersByState should return all orders when every order matches the requested state.");
    }

    @Test
    void findOrdersByState_mixedStates_returnsOnlyMatchingOrders() {
        int idA = createOrder(PHONE_A, DESCRIPTION_A);
        createOrder(PHONE_B, DESCRIPTION_B);

        transitionState(idA, OrderState.ACCEPTED);

        assertEquals(1, orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED).size(),
            "There should be exactly one NEWLY_CREATED order after promoting one to ACCEPTED.");
        assertEquals(1, orderRegistry.findOrdersByState(OrderState.ACCEPTED).size(),
            "There should be exactly one ACCEPTED order after the state transition.");
    }

    @Test
    void findOrdersByState_rejectedOrder_isReturnedCorrectly() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        transitionState(id, OrderState.REJECTED);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.REJECTED);
        assertEquals(1, result.size(),
            "findOrdersByState should return the order once it has been set to REJECTED.");
    }

    @Test
    void findOrdersByState_readyForApprovalOrder_isReturnedCorrectly() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        transitionState(id, OrderState.READY_FOR_APPROVAL);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.READY_FOR_APPROVAL);
        assertEquals(1, result.size(),
            "findOrdersByState should return the order once it has been set to READY_FOR_APPROVAL.");
    }

    // -----------------------------------------------------------------------
    // replaceOrderById
    // -----------------------------------------------------------------------

    @Test
    void replaceOrderById_existingId_orderIsReplaced() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO original = orderRegistry.findOrderById(id);

        OrderDTO replacement = new OrderBuilder.Builder(original)
            .state(OrderState.ACCEPTED)
            .build();
        orderRegistry.replaceOrderById(id, replacement);

        OrderDTO found = orderRegistry.findOrderById(id);
        assertEquals(OrderState.ACCEPTED, found.getState(),
            "After replacement the order should carry the new state ACCEPTED.");
    }

    @Test
    void replaceOrderById_nonExistentId_doesNotThrow() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO original = orderRegistry.findOrderById(id);

        OrderDTO ghost = new OrderBuilder.Builder(original)
            .state(OrderState.ACCEPTED)
            .build();

        assertDoesNotThrow(() -> orderRegistry.replaceOrderById(999, ghost),
            "Calling replaceOrderById with a non-existent ID should not throw any exception.");
    }

    @Test
    void replaceOrderById_nonExistentId_leavesExistingOrderUnchanged() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO original = orderRegistry.findOrderById(id);

        OrderDTO ghost = new OrderBuilder.Builder(original)
            .state(OrderState.ACCEPTED)
            .build();
        orderRegistry.replaceOrderById(999, ghost);

        assertEquals(OrderState.NEWLY_CREATED, orderRegistry.findOrderById(id).getState(),
            "An existing order must not be affected when replacing a different, non-existent ID.");
    }

    @Test
    void replaceOrderById_onlyTargetOrderIsReplaced() {
        int idA = createOrder(PHONE_A, DESCRIPTION_A);
        int idB = createOrder(PHONE_B, DESCRIPTION_B);

        transitionState(idA, OrderState.REJECTED);

        assertEquals(OrderState.NEWLY_CREATED, orderRegistry.findOrderById(idB).getState(),
            "Replacing order A must not affect the state of the unrelated order B.");
    }
}