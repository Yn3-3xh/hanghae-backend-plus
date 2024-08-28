package org.example.junitinaction3.chapter04.rules;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CustomExtension.class)
public class JUnit5CustomExtensionTest {

    @Test
    void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
