package com.github.oscareriksson02.bikeWorkShop.integration;

/**
 * This is a class for RepairOrderDTO
 */
public class RepairTaskDTO {
    private String problemDescription;
    private int cost;

    /**
     * Constructor for RepairTaskDTO
     * @param problemDescription
     * @param cost
     */
    public RepairTaskDTO (String problemDescription, int cost) {
        this.problemDescription = problemDescription;
        this.cost = cost;
    }

    /*
    Getters for RepairTaskDTO
     */

    @Override
    public String toString() {
    return problemDescription + " - " + cost + " kr";
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public int getCost() {
        return cost;
    }

}
