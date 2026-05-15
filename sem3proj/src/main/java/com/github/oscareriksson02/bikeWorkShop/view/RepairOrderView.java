package com.github.oscareriksson02.bikeWorkShop.view;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.model.RepairOrderObserver;

/**
 * Repair Order View class that prints out repair order conten
 * when state change.
 */
public class RepairOrderView implements RepairOrderObserver {

/**
 * Method prints out changed repair order
 */
   public void onRepairOrderUpdate(OrderDTO repaiOrder) {
        System.out.print(repaiOrder);
    }

}
