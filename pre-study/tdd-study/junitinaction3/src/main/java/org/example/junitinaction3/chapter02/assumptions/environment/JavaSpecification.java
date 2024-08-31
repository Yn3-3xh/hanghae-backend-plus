package org.example.junitinaction3.chapter02.assumptions.environment;

public class JavaSpecification {
    private final String version;

    public JavaSpecification(String version) {
        this.version = version;
    }

    String getVersion() {
        return version;
    }
}