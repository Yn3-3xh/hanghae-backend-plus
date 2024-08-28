package org.example.junitinaction3.chapter06.car;

public class VehicleDI {

    Driver d;
    boolean hasDriver = true;

    VehicleDI(Driver d) {
        this.d = d;
    }

    private void setHasDriver(boolean hasDriver) {
        this.hasDriver = hasDriver;
    }
}
