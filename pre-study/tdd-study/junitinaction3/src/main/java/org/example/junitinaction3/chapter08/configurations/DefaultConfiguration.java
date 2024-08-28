package org.example.junitinaction3.chapter08.configurations;

public class DefaultConfiguration implements Configuration {

    public DefaultConfiguration(String configurationName) {
    }

    public String getSQL(String sqlString) {
        return null;
    }
}