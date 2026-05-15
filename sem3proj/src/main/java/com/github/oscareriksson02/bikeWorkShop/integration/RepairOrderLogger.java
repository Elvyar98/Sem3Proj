package com.github.oscareriksson02.bikeWorkShop.integration;
import com.github.oscareriksson02.bikeWorkShop.model.RepairOrderObserver;

public class RepairOrderLogger implements RepairOrderObserver {
private FileLogger fileLogger;

public RepairOrderLogger() {
    this.fileLogger = new FileLogger();
}

public void onRepairOrderUpdate(OrderDTO repairOrder) {
    fileLogger.log(repairOrder);
}
}
