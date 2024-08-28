package org.example.junitinaction3.chapter08.web;

import java.io.InputStream;

public class MockConnectionFactory implements ConnectionFactoryCustom {

    private InputStream inputStream;

    @Override
    public InputStream getData() throws Exception {
        return inputStream;
    }

    public void setData(InputStream stream) {
        this.inputStream = stream;
    }
}
