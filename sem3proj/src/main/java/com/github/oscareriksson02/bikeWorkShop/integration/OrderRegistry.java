package com.github.oscareriksson02.bikeWorkShop.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for storing the orders in the system.
 * It will be used by the controller to add and retrieve orders from the system.
 */
public class OrderRegistry {
    private List<OrderDTO> orders;
    private CustomerRegistry cusReg;
    private int counter;


    private int generateOrderId(int counter) {
        return counter + 1;
    }

    public OrderRegistry() {
       cusReg = new CustomerRegistry();
       orders = new ArrayList<>();
       counter = 0;
    }


    public List<OrderDTO> findOrdersByState(String state) {

        List<OrderDTO> orderStateMatches = new ArrayList<>();

        for(OrderDTO orderDTO : orders) {
            if(orderDTO.getState() == state) {
                orderStateMatches.add(orderDTO);
            }
        }
        return orderStateMatches;
    }

    public int createNewRepairOrder(String phoneNumber, String problemDescription) {
        int orderID = generateOrderId(counter);

        CustomerDTO customer = cusReg.searchCustomer(phoneNumber);
        OrderDTO repairOrder = new OrderDTO(orderID, customer, problemDescription);
        orders.add(repairOrder);
        counter++;

        return repairOrder.getOrderID();

    }

}
