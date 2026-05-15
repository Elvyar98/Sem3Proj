package com.github.oscareriksson02.bikeWorkShop.integration;
import com.github.oscareriksson02.bikeWorkShop.model.RepairOrderObserver;



/**
 * This class files orders when they change State
 */
public class RepairOrderLogger implements RepairOrderObserver {
private FileLogger fileLogger;

public RepairOrderLogger() {
    this.fileLogger = new FileLogger();
}

public void onRepairOrderUpdate(OrderDTO repairOrder) {
    fileLogger.log(repairOrder.toString());
}
}
