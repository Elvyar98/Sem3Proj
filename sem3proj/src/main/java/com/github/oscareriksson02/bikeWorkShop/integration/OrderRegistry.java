package com.github.oscareriksson02.bikeWorkShop.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for storing the orders in the system.
 * It will be used by the controller to add and retrieve orders from the system.
 */
public class OrderRegistry {
    private List<OrderDTO> orders = new ArrayList<>();

    public OrderRegistry() {
        OrderDTO kallesOrder= new OrderDTO(1, "dagens datum", null, "Punkterat bakdäck", 1, "Byt innertub backdäck", "datum här");
        orders.add(kallesOrder);
    }

    /**
     * Returns all ordersDTO:s in order registry with matching state value as list.
     * 
     * @param state order state represent progress of the order.
     * @return List of matched orders.
     */
    public List<OrderDTO> findOrdersByState(int state) {

        List<OrderDTO> orderStateMatches = new ArrayList<>();

        for(OrderDTO orderDTO : orders) {
            if(orderDTO.getState() == state) {
                orderStateMatches.add(orderDTO);
            }
        }
        return orderStateMatches;
    }

}
