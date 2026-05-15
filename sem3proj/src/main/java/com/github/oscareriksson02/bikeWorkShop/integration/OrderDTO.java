package com.github.oscareriksson02.bikeWorkShop.integration;

import com.github.oscareriksson02.bikeWorkShop.model.OrderState;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** 
 * This is a class for the Order Data Transfer Object (DTO).
 */
public class OrderDTO {
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
     * Constructor for an Order DTO object
     * @param orderID
     * @param customerDTO
     * @param problemDescription
     */
    public OrderDTO(int orderID, CustomerDTO customerDTO,
 String problemDescription) {
        this.orderID = orderID;
        this.dateOfCreation = LocalDate.now();
        this.customerDTO = customerDTO;
        this.problemDescription = problemDescription;
        this.totalCost = 0;
        this.state =  OrderState.NEWLY_CREATED;
        this.repairTasks = new ArrayList<>();
        this.diagnosticReport = " ";
        this.estimatedTimeOfCompletion = " ";
}

/**
 * Constructor when called from builder
 * @param orderID
 * @param dateOfCreation
 * @param customerDTO
 * @param problemDescription
 * @param totalCost
 * @param state
 * @param repairTasks
 * @param estimatedTimeOfCompletion
 */

public OrderDTO(int orderID, LocalDate dateOfCreation, CustomerDTO customerDTO,
 String problemDescription, int totalCost, OrderState state, List<RepairTaskDTO> repairTasks, String diagnosticReport,String estimatedTimeOfCompletion) {
        this.orderID = orderID;
        this.dateOfCreation = dateOfCreation;
        this.customerDTO = customerDTO;
        this.problemDescription = problemDescription;
        this.totalCost = totalCost;
        this.state =  state;
        this.repairTasks = repairTasks;
        this.diagnosticReport = diagnosticReport;
        this.estimatedTimeOfCompletion = estimatedTimeOfCompletion;
}



    @Override
    public String toString() {
    return "Order #" + orderID + " | " + dateOfCreation + " | State: " + state + "\n" +
           "Customer: " + customerDTO + "\n" +
           "Problem: " + problemDescription + "\n" +
           "Diagnostic: " + diagnosticReport + "\n" +
           "Tasks: " + repairTasks + "\n" +
           "Total: " + totalCost + " kr | ETC: " + estimatedTimeOfCompletion;
    }

    /**
     * Getters for the Order DTO Class
     * @return
     */

    public int getOrderID() {
        return orderID;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public OrderState getState() {
        return state;
    }

    public List<RepairTaskDTO> getRepairTasks() {
        return repairTasks;
    }

    public String getDiagnosticReport() {
        return diagnosticReport;
    }
    
    public String getEstimatedTimeOfCompletion() {
        return estimatedTimeOfCompletion;
    }

    public int getTotalCost() {
        return totalCost;
    }   


}

