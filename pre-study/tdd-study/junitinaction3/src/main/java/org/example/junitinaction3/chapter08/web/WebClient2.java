package org.example.junitinaction3.chapter08.web;

import java.io.InputStream;

public class WebClient2 {

    public String getContent(ConnectionFactoryCustom connectionFactoryCustom) {
        String workingContent;

        StringBuffer content = new StringBuffer();
        try (InputStream is = connectionFactoryCustom.getData()) {
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }

            workingContent = content.toString();
        } catch (Exception e) {
            workingContent = null;
        }

        return workingContent;
    }
}
