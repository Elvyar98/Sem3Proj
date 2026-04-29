package com.github.oscareriksson02.bikeWorkShop.model;

import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderRegistry;
import com.github.oscareriksson02.bikeWorkShop.integration.RepairTaskDTO;

public class Order {
   private OrderDTO orderDTO;
  

    /**
     * Constructor for the order class
     * @param orderID
     * @param orderRegistry
     */
    public Order(int orderID, OrderRegistry orderRegistry) {
        this.orderDTO = orderRegistry.findOrderById(orderID);
    }


    /**
     * adds a repair task to the DTO
     * @param repairTask
     */

    public void addRepairTask(RepairTaskDTO repairTask) {
        orderDTO.addRepairTask(repairTask);
        calculateTotalCost();
    }


    private int calculateTotalCost() {
        int totalCost = 0;
        for(RepairTaskDTO repairTaskDTO : orderDTO.getRepairTasks()) {
            totalCost += repairTaskDTO.getCost();
        }

        return totalCost;
    }

    public void acceptRepairOrder() {
        this.state = "Order Accepted";
    }



}
