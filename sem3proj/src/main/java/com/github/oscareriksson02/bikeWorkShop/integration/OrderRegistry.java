package com.github.oscareriksson02.bikeWorkShop.integration;

import java.util.ArrayList;
import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.model.OrderState;


/**
 * This class is responsible for storing the orders in the system.
 * It will be used by the controller to add and retrieve orders from the system.
 */
public class OrderRegistry {
    private List<OrderDTO> orders;
    private CustomerRegistry cusReg;
    private int counter;
    private static OrderRegistry instance;
    
    /**
     * Constructor for the order class
     */

    private OrderRegistry() {
       cusReg =  CustomerRegistry.getInstance();
       orders = new ArrayList<>();
       counter = 0;
    }

    /**
     * Return an instance of the OrderRegistry.
     * If instance == null it creates a new one.
     * @return instance
     */

    public static OrderRegistry getInstance() {
        if (instance == null) {
            instance = new OrderRegistry();
        }
        return instance;
    }

    /**
     * Method specifically for ressetting instance for tests
     */
    public static void resetInstance() {
        instance = null;
    }

    public List<OrderDTO> getOrderList() {
        return orders;
    }

    /**
     * Returns all ordersDTO:s in order registry with matching state value as list.
     * @param state State of oreder completion
     * @return List of matching order DTO:s
     */
    public List<OrderDTO> findOrdersByState(OrderState state) {

        List<OrderDTO> orderStateMatches = new ArrayList<>();

        for(OrderDTO orderDTO : orders) {
            if(orderDTO.getState() == state) {
                orderStateMatches.add(orderDTO);
            }
        }
        return orderStateMatches;
    }

    /**
     * Creates new repair order and adds it to the list of orders.
     * @param phoneNumber
     * @param problemDescription
     * @return
     */
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

    /**
     * Replaces a existing order of specified orderid in the list with a given order
     * @param orderId id of order to be replaced
     * @param order the new order
     */
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

     private int generateOrderId() {
        return counter + 1;
    }
}
