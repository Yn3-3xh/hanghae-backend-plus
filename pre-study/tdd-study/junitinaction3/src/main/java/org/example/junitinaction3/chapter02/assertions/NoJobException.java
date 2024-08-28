package org.example.junitinaction3.chapter02.assertions;

public class NoJobException extends RuntimeException {
    NoJobException(String message) {
        super(message);
    }
}