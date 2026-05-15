package com.github.oscareriksson02.bikeWorkShop.view;
import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;
import com.github.oscareriksson02.bikeWorkShop.model.RepairOrderObserver;

public class RepairOrderView implements RepairOrderObserver {

    
   public void onRepairOrderUpdate(OrderDTO repaiOrder) {
        System.out.print(repaiOrder);
    }

}
