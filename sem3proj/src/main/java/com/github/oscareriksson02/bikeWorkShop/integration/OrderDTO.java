package com.github.oscareriksson02.bikeWorkShop.integration;

import com.github.oscareriksson02.bikeWorkShop.integration.RepairTaskDTO;
import java.time.LocalDate;
import java.util.ArrayList;

/** 
 * This is a class for the Order Data Transfer Object (DTO).
 */
public class OrderDTO {
    private int orderID;
    private LocalDate dateOfCreation;
    private CustomerDTO customerDTO;
    private String problemDescription;
    private String state;
    private List<RepairTaskDTO> repairTasks = new ArrayList<>();
    private String estimatedTimeOfCompletion;


    public OrderDTO(int orderID, CustomerDTO customerDTO,
 String problemDescription) {
        this.orderID = orderID;
        this.dateOfCreation = LocalDate.now();
        this.customerDTO = customerDTO;
        this.problemDescription = problemDescription;
        this.state =  "Newly Created";
}

    @Override
    public String toString() {
        return "OrderID: " + orderID + ", Date Of Creation: " + dateOfCreation + 
        "\n" + "CustomerDTO: " + customerDTO+ 
        "\n" + "Problem description: " + problemDescription + ", State: " + state + ", Repair tasks: " + repairTasks + ", Estimated time of completion: " + estimatedTimeOfCompletion;  
    }

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
}


