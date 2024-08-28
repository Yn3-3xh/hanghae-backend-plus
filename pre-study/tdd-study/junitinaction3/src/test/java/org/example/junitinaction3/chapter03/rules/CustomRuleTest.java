package org.example.junitinaction3.chapter03.rules;

import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTest {

    @Rule
    public CustomRule myRule = new CustomRule();

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
