package com.github.oscareriksson02.bikeWorkShop.integration;

/** 
 * This is a class for the Order Data Transfer Object (DTO).
 */
public class OrderDTO {
    private int orderID;
    private String dateOfCreation;
    private CustomerDTO customerDTO;
    private String problemDescription;
    private int state;
    private String repairTasks;
    private String estimatedTimeOfCompletion;

    public OrderDTO(int orderID, String dateOfCreation, CustomerDTO customerDTO,
                String problemDescription, int state, String repairTasks,
                String estimatedTimeOfCompletion) {
        this.orderID = orderID;
        this.dateOfCreation = dateOfCreation;
        this.customerDTO = customerDTO;
        this.problemDescription = problemDescription;
        this.state = state;
        this.repairTasks = repairTasks;
        this.estimatedTimeOfCompletion = estimatedTimeOfCompletion;
}

    public int getOrderID() {
        return orderID;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public int getState() {
        return state;
    }

    public String getRepairTasks() {
        return repairTasks;
    }

    public String getEstimatedTimeOfCompletion() {
        return estimatedTimeOfCompletion;
    }
}


