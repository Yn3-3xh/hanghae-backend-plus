package org.example.junitinaction3.chapter08.web;

import java.io.InputStream;

public interface ConnectionFactoryCustom {

    InputStream getData() throws Exception;
}
