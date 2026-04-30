package com.github.oscareriksson02.bikeWorkShop.integration;

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
    private String state;
    private List<RepairTaskDTO> repairTasks;
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
        this.state =  "Newly Created";
        this.repairTasks = new ArrayList<>();
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
 String problemDescription, int totalCost, String state, List<RepairTaskDTO> repairTasks, String estimatedTimeOfCompletion) {
        this.orderID = orderID;
        this.dateOfCreation = dateOfCreation;
        this.customerDTO = customerDTO;
        this.problemDescription = problemDescription;
        this.totalCost = totalCost;
        this.state =  state;
        this.repairTasks = repairTasks;
        this.estimatedTimeOfCompletion = estimatedTimeOfCompletion;
}



    @Override
    public String toString() {
        return "OrderID: " + orderID + ", Date Of Creation: " + dateOfCreation + 
        "\n" + "CustomerDTO: " + customerDTO+ 
        "\n" + "Problem description: " + problemDescription + ", Repair tasks: " + repairTasks + ", Estimated time of completion: " + estimatedTimeOfCompletion;  
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

    public String getState() {
        return state;
    }

    public List<RepairTaskDTO> getRepairTasks() {
        return repairTasks;
    }

    public String getEstimatedTimeOfCompletion() {
        return estimatedTimeOfCompletion;
    }

    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Add repair task to repairTasks list
     * @param repairTask
     */

    public void addRepairTask(RepairTaskDTO repairTask) {
        repairTasks.add(repairTask);
    }

    


}

