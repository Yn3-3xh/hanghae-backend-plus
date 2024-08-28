package org.example.junitinaction3.chapter08.configurations;

public class MockConfiguration implements Configuration {

    @Override
    public String getSQL(String sqlString) {
        return null;
    }

    public void setSQL(String sqlString) {
    }
}
