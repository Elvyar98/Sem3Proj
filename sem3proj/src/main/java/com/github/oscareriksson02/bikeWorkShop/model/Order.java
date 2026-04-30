package com.github.oscareriksson02.bikeWorkShop.model;

import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.RepairTaskDTO;

public class Order {
   private OrderDTO orderDTO;
   private OrderRegistry orderRegistry;

  

    /**
     * Constructor for the order class
     * @param orderID
     * @param orderRegistry
     */
    public Order(int orderID, OrderRegistry orderRegistry) {
        this.orderDTO = orderRegistry.findOrderById(orderID);
        this.orderRegistry = orderRegistry;
    }


    /**
     * adds a repair task to the DTO
     * @param repairTask
     */

    public void addRepairTask(String repairTaskDescription, int cost) {
        RepairTaskDTO repairTask = new RepairTaskDTO(repairTaskDescription, cost);
        
        OrderDTO updateOrderDTO = new OrderBuilder.Builder(orderDTO)
        .repairTasks(repairTask)
        .build();

        updateOrderDTO(orderDTO.getOrderID(), updateOrderDTO);
        
    }

    private void updateOrderDTO(int orderId, OrderDTO orderDTO) {
        orderRegistry.replaceOrderById(orderId, orderDTO);
        this.orderDTO = orderDTO;
    }
    


}
