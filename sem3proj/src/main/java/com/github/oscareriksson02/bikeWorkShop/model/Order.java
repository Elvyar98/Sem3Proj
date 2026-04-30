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

    /**
     * Function adds a diagnostic report to an existing orderDTO by creating a new
     * one and replacing the one in order registry with it. 
     * @param diagnosticReport
     */

    public void addDiagnosticReport(String diagnosticReport) {
        OrderDTO updateOrderDTO = new OrderBuilder.Builder(orderDTO)
        .diagnosticReport(diagnosticReport)
        .build();

        updateOrderDTO(orderDTO.getOrderID(), updateOrderDTO);

    }

    /**
     * Updates estimated time of completion by creating a new DTO and replacing the old one in order registry
     * @param estimatedTimeOfCompletion
     */

    public void addEstimatedTimeOfCompletion(String estimatedTimeOfCompletion) {
        OrderDTO updateOrderDTO = new OrderBuilder.Builder(orderDTO)
        .estimatedTimeOfCompletion(estimatedTimeOfCompletion)
        .build();

        updateOrderDTO(orderDTO.getOrderID(), updateOrderDTO);
    }

    /**
     * Updates orderstate to ACCEPTED by creating a new DTO and replacing the old one in order registry
     */
    public void acceptRepairOrder(){
        OrderDTO updateOrderDTO = new OrderBuilder.Builder(orderDTO)
        .state(OrderState.ACCEPTED)
        .build();

        updateOrderDTO(orderDTO.getOrderID(), updateOrderDTO);
    }

    public void rejectRepairOrder(){
        OrderDTO updateOrderDTO = new OrderBuilder.Builder(orderDTO)
        .state(OrderState.REJECTED)
        .build();

        updateOrderDTO(orderDTO.getOrderID(), updateOrderDTO);
    }

    /**
     * Function replaces orderDTO in registry with updatedDTO
     * It also change the reference in the order oobject to the updated one
     * @param orderId
     * @param orderDTO
     */

    private void updateOrderDTO(int orderId, OrderDTO orderDTO) {
        orderRegistry.replaceOrderById(orderId, orderDTO);
        this.orderDTO = orderDTO;
    }
    


}
