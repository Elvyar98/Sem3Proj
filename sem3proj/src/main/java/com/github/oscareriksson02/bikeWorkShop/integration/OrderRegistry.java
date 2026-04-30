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


    private int generateOrderId() {
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
        int orderID = generateOrderId();

        CustomerDTO customer = cusReg.searchCustomer(phoneNumber);
        OrderDTO repairOrder = new OrderDTO(orderID, customer, problemDescription);
        orders.add(repairOrder);
        counter++;

        return repairOrder.getOrderID();

    }

    /**
     * Finds an order in the registry by orderId
     * @param orderId
     * @return
     */

    public OrderDTO findOrderById(int orderId){
        for (OrderDTO orderDTO : orders) {
            if (orderDTO.getOrderID() == orderId) {
                return orderDTO;
            } 
        }

        System.out.println("Order: " + orderId + " does not exist.");
        return null;
        
    }

    public void replaceOrderById(int orderId, OrderDTO order) {
       int index = 0;

        for (OrderDTO orderDTO : orders) {
            if (orderDTO.getOrderID() == orderId) {
                orders.set(index, order);
                break;
            }

            index++;
        }
    }



}
