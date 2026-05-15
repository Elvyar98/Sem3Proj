package com.github.oscareriksson02.bikeWorkShop.integration;

import com.github.oscareriksson02.bikeWorkShop.model.OrderBuilder;
import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRegistryTest {

    private static final String PHONE_A       = "0701234567";
    private static final String PHONE_B       = "0709876543";
    private static final String PHONE_C       = "0731234567";
    private static final String DESCRIPTION_A = "Flat tire";
    private static final String DESCRIPTION_B = "Broken brake";
    private static final String DESCRIPTION_C = "Battery issue";

    private OrderRegistry orderRegistry;

    @BeforeEach
    void setUp() {
        CustomerRegistry.resetInstance();
        OrderRegistry.resetInstance();
        orderRegistry = OrderRegistry.getInstance();
    }

    private int createOrder(String phone, String description) {
        return orderRegistry.createNewRepairOrder(phone, description);
    }

    private void transitionState(int orderId, OrderState newState) {
        OrderDTO original = orderRegistry.findOrderById(orderId);
        OrderDTO updated = new OrderBuilder.Builder(original)
            .state(newState)
            .build();
        orderRegistry.replaceOrderById(orderId, updated);
    }

    @Test
    void firstOrderReceivesIdOne() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        assertEquals(1, id, "The first order created should be assigned ID 1.");
    }

    @Test
    void secondOrderReceivesIdTwo() {
        createOrder(PHONE_A, DESCRIPTION_A);
        int id = createOrder(PHONE_B, DESCRIPTION_B);
        assertEquals(2, id, "The second order created should be assigned ID 2.");
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

    @Test
    void findOrderById_existingId_returnsOrderWithCorrectId() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO result = orderRegistry.findOrderById(id);
        assertNotNull(result, "findOrderById should return a non-null DTO for an existing ID.");
        assertEquals(id, result.getOrderID(),
            "The returned DTO must carry the same ID that was searched for.");
    }

    @Test
    void findOrderById_nonExistentId_returnsNull() {
        OrderDTO result = orderRegistry.findOrderById(999);
        assertNull(result, "findOrderById should return null when the ID does not exist.");
    }

    @Test
    void findOrderById_afterMultipleCreations_returnsCorrectOrder() {
        int idA = createOrder(PHONE_A, DESCRIPTION_A);
        int idB = createOrder(PHONE_B, DESCRIPTION_B);
        assertEquals(DESCRIPTION_A, orderRegistry.findOrderById(idA).getProblemDescription());
        assertEquals(DESCRIPTION_B, orderRegistry.findOrderById(idB).getProblemDescription());
    }

    @Test
    void findOrdersByState_emptyRegistry_returnsEmptyList() {
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED);
        assertTrue(result.isEmpty(),
            "findOrdersByState should return an empty list when registry contains no orders.");
    }

    @Test
    void findOrdersByState_noOrdersMatchState_returnsEmptyList() {
        createOrder(PHONE_A, DESCRIPTION_A);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.ACCEPTED);
        assertTrue(result.isEmpty(),
            "findOrdersByState should return empty list when no orders match the state.");
    }

    @Test
    void findOrdersByState_oneOrderMatchesState_returnsListWithOneElement() {
        createOrder(PHONE_A, DESCRIPTION_A);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED);
        assertEquals(1, result.size());
    }

    @Test
    void findOrdersByState_allOrdersMatchState_returnsAllOrders() {
        createOrder(PHONE_A, DESCRIPTION_A);
        createOrder(PHONE_B, DESCRIPTION_B);
        createOrder(PHONE_C, DESCRIPTION_C);
        List<OrderDTO> result = orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED);
        assertEquals(3, result.size());
    }

    @Test
    void findOrdersByState_mixedStates_returnsOnlyMatchingOrders() {
        int idA = createOrder(PHONE_A, DESCRIPTION_A);
        createOrder(PHONE_B, DESCRIPTION_B);
        transitionState(idA, OrderState.ACCEPTED);
        assertEquals(1, orderRegistry.findOrdersByState(OrderState.NEWLY_CREATED).size());
        assertEquals(1, orderRegistry.findOrdersByState(OrderState.ACCEPTED).size());
    }

    @Test
    void findOrdersByState_rejectedOrder_isReturnedCorrectly() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        transitionState(id, OrderState.REJECTED);
        assertEquals(1, orderRegistry.findOrdersByState(OrderState.REJECTED).size());
    }

    @Test
    void findOrdersByState_readyForApprovalOrder_isReturnedCorrectly() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        transitionState(id, OrderState.READY_FOR_APPROVAL);
        assertEquals(1, orderRegistry.findOrdersByState(OrderState.READY_FOR_APPROVAL).size());
    }

    @Test
    void replaceOrderById_existingId_orderIsReplaced() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO original = orderRegistry.findOrderById(id);
        OrderDTO replacement = new OrderBuilder.Builder(original).state(OrderState.ACCEPTED).build();
        orderRegistry.replaceOrderById(id, replacement);
        assertEquals(OrderState.ACCEPTED, orderRegistry.findOrderById(id).getState());
    }

    @Test
    void replaceOrderById_nonExistentId_doesNotThrow() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO original = orderRegistry.findOrderById(id);
        OrderDTO ghost = new OrderBuilder.Builder(original).state(OrderState.ACCEPTED).build();
        assertDoesNotThrow(() -> orderRegistry.replaceOrderById(999, ghost));
    }

    @Test
    void replaceOrderById_nonExistentId_leavesExistingOrderUnchanged() {
        int id = createOrder(PHONE_A, DESCRIPTION_A);
        OrderDTO original = orderRegistry.findOrderById(id);
        OrderDTO ghost = new OrderBuilder.Builder(original).state(OrderState.ACCEPTED).build();
        orderRegistry.replaceOrderById(999, ghost);
        assertEquals(OrderState.NEWLY_CREATED, orderRegistry.findOrderById(id).getState());
    }

    @Test
    void replaceOrderById_onlyTargetOrderIsReplaced() {
        int idA = createOrder(PHONE_A, DESCRIPTION_A);
        int idB = createOrder(PHONE_B, DESCRIPTION_B);
        transitionState(idA, OrderState.REJECTED);
        assertEquals(OrderState.NEWLY_CREATED, orderRegistry.findOrderById(idB).getState());
    }
}