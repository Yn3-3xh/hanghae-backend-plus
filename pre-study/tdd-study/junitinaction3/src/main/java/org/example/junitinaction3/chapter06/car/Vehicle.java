package org.example.junitinaction3.chapter06.car;

public class Vehicle {

    Driver d = new Driver();
    boolean hasDriver = true;

    private void setHasDriver(boolean hasDriver) {
        this.hasDriver = hasDriver;
    }
}
