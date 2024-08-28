package org.example.junitinaction3.chapter02.tags;

import lombok.Getter;

@Getter
public class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
