package org.example.junitinaction3.chapter03.runners;

import org.example.junitinaction3.chapter03.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(CustomTestRunner.class)
public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 20);
        assertEquals(30, result, 0);
    }
}
