package com.github.oscareriksson02.bikeWorkShop.model;

import com.github.oscareriksson02.bikeWorkShop.integration.OrderDTO;

public interface RepairOrderObserver {

    void onRepairOrderUpdate(OrderDTO repairOrderDTO);

}
