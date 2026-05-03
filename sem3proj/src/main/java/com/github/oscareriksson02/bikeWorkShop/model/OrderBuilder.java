package com.github.oscareriksson02.bikeWorkShop.model;

import java.time.LocalDate;
import java.util.List;

import com.github.oscareriksson02.bikeWorkShop.integration.CustomerDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.integration.RepairTaskDTO;

/**
 * The class represents a repair order.
 * The nested class Builder is used to construct orders derivateve of a pre existing
 * orderDTO but overriding specific fields.
 */
public class OrderBuilder {
    private int orderID;
    private LocalDate dateOfCreation;
    private CustomerDTO customerDTO;
    private String problemDescription;
    private int totalCost;
    private OrderState state;
    private List<RepairTaskDTO> repairTasks;
    private String diagnosticReport;
    private String estimatedTimeOfCompletion;

    private OrderBuilder(Builder builder) {
        this.orderID = builder.orderID;
        this.dateOfCreation = builder.dateOfCreation;
        this.customerDTO = builder.customerDTO;
        this.problemDescription = builder.problemDescription;
        this.totalCost = builder.totalCost;
        this.state = builder.state;
        this.repairTasks = builder.repairTasks;
        this.diagnosticReport = builder.diagnosticReport;
        this.estimatedTimeOfCompletion = builder.estimatedTimeOfCompletion;
    }

    /**
     * Creates OrderDTO based on existing OrderDTO with option to override fields before building.
     */
    public static class Builder {
        private int orderID;
        private LocalDate dateOfCreation;
        private CustomerDTO customerDTO;
        private String problemDescription;
        private int totalCost;
        private OrderState state;
        private List<RepairTaskDTO> repairTasks;
        private String diagnosticReport;
        private String estimatedTimeOfCompletion;

        /**
         * Initialize builder buy copying all fields from given OrderDTO
         * @param dto source DTO 
         */
        public Builder(OrderDTO dto) {
            this.orderID = dto.getOrderID();
            this.dateOfCreation = dto.getDateOfCreation();
            this.customerDTO = dto.getCustomerDTO();
            this.problemDescription = dto.getProblemDescription();
            this.totalCost = dto.getTotalCost();
            this.state = dto.getState();
            this.repairTasks = dto.getRepairTasks();
            this.diagnosticReport = dto.getDiagnosticReport();
            this.estimatedTimeOfCompletion = dto.getEstimatedTimeOfCompletion();
        }

        /**
         * Overrides the state of the order.
         *
         * @param state the new OrderState.
         * @return this builder.
         */
        public Builder state(OrderState state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the diagnostic report and automatically transitions
         * the order state to READY_FOR_APPROVAL.
         *
         * @param diagnosticReport the diagnostic report text.
         * @return this builder.
         */
        public Builder diagnosticReport(String diagnosticReport) {
            this.diagnosticReport = diagnosticReport;
            state(OrderState.READY_FOR_APPROVAL);
            return this;
        }

        /**
         * Overrides the total cost of the order.
         *
         * @param totalCost the new total cost in SEK.
         * @return this builder.
         */
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

        /**
         * Overrides the estimated time of completion.
         *
         * @param eta the estimated completion date as a string (e.g. "2026-05-20").
         * @return this builder.
         */
        public Builder estimatedTimeOfCompletion(String eta) {
            this.estimatedTimeOfCompletion = eta;
            return this;
        }

        /**
         * Returns an OrderDTO object with updated fields
         * @return
         */
        public OrderDTO build() {
            return new OrderDTO(orderID,dateOfCreation, customerDTO, problemDescription, 
                totalCost, state, repairTasks, diagnosticReport, estimatedTimeOfCompletion);
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

    /**
     * Returns a string representation of the order, including all fields.
     *
     * @return a formatted string with order details.
     */
    @Override
    public String toString() {
    return "OrderID: " + orderID + 
           ", Date Of Creation: " + dateOfCreation +
           "\nCustomer: " + customerDTO +
           "\nProblem Description: " + problemDescription +
           ", State: " + state +
           ", Diagnostic Report: " + diagnosticReport +
           "\nRepair Tasks: " + repairTasks +
           ", Total Cost: " + totalCost +
           ", ETA: " + estimatedTimeOfCompletion;
    }
}
