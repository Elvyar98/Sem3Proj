package com.github.oscareriksson02.bikeWorkShop.model;

import java.time.LocalDate;
import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.RepairTaskDTO;

public class OrderBuilder {
    private int orderID;
    private LocalDate dateOfCreation;
    private CustomerDTO customerDTO;
    private String problemDescription;
    private int totalCost;
    private String state;
    private List<RepairTaskDTO> repairTasks;
    private String estimatedTimeOfCompletion;


    private OrderBuilder(Builder builder) {
        this.orderID = builder.orderID;
        this.dateOfCreation = builder.dateOfCreation;
        this.customerDTO = builder.customerDTO;
        this.problemDescription = builder.problemDescription;
        this.totalCost = builder.totalCost;
        this.state = builder.state;
        this.repairTasks = builder.repairTasks;
        this.estimatedTimeOfCompletion = builder.estimatedTimeOfCompletion;
    }

    public static class Builder {
        // Copy all fields from DTO as defaults
        private int orderID;
        private LocalDate dateOfCreation;
        private CustomerDTO customerDTO;
        private String problemDescription;
        private int totalCost;
        private String state;
        private List<RepairTaskDTO> repairTasks;
        private String estimatedTimeOfCompletion;

        // Initialize builder from DTO
        public Builder(OrderDTO dto) {
            this.orderID = dto.getOrderID();
            this.dateOfCreation = dto.getDateOfCreation();
            this.customerDTO = dto.getCustomerDTO();
            this.problemDescription = dto.getProblemDescription();
            this.totalCost = dto.getTotalCost();
            this.state = dto.getState();
            this.repairTasks = dto.getRepairTasks();
            this.estimatedTimeOfCompletion = dto.getEstimatedTimeOfCompletion();
        }

        // One method per field you might want to override
        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder totalCost(int totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        /**
         * Adds repair tasks and calls calculate totalcost
         * @param repairTask
         * @return
         */
        public Builder repairTasks(RepairTaskDTO repairTask) {
            this.repairTasks.add(repairTask);
            totalCost(calculateTotalCost());
            return this;
        }

        public Builder estimatedTimeOfCompletion(String eta) {
            this.estimatedTimeOfCompletion = eta;
            return this;
        }

        // Add the rest of the fields the same way...
        /**
         * Returns an OrderDTO object with updated fields
         * @return
         */
        public OrderDTO build() {
            return new OrderDTO(orderID,dateOfCreation, customerDTO, problemDescription, 
                totalCost, state, repairTasks, estimatedTimeOfCompletion);
        }

        /**
         * Calculate total cost from repairTasks and returns it as an int
         * @return int updatedCost
         */
        private int calculateTotalCost() {
            int updatedCost = 0;
            for (RepairTaskDTO repairTaskDTO : repairTasks){
                updatedCost += repairTaskDTO.getCost();
            }

            return updatedCost;
    }

        
    }

    @Override
    public String toString() {
    return "OrderID: " + orderID + 
           ", Date Of Creation: " + dateOfCreation +
           "\nCustomer: " + customerDTO +
           "\nProblem Description: " + problemDescription +
           ", State: " + state +
           "\nRepair Tasks: " + repairTasks +
           ", Total Cost: " + totalCost +
           ", ETA: " + estimatedTimeOfCompletion;
    }
}
