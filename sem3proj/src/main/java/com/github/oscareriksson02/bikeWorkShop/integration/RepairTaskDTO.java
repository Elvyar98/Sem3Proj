package com.github.oscareriksson02.bikeWorkShop.integration;

public class RepairTaskDTO {
    private String problemDescription;
    private int cost;

    public RepairTaskDTO (String problemDescription, int cost) {
        this.problemDescription = problemDescription;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Problem description: " + problemDescription + ", cost: " + 
        cost;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public int getCost() {
        return cost;
    }

}
