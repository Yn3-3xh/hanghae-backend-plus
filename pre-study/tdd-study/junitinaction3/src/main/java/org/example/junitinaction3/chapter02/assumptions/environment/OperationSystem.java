package org.example.junitinaction3.chapter02.assumptions.environment;

import lombok.Getter;

@Getter
public class OperationSystem {
    private final String name;
    private final String architecture;

    public OperationSystem(String name, String architecture) {
        this.name = name;
        this.architecture = architecture;
    }

}