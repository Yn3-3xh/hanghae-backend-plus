package org.example.junitinaction3.chapter02.hamcrest;

import org.example.junitinaction3.chapter02.hamcrest.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final Customer customer = new Customer(FIRST_NAME, LAST_NAME);

    @Test
    @DisplayName("Hamcrest is, anyOf, allOf 매처를 사용한 테스트")
    public void testHamcrestIs() {
        int price1 = 1, price2 = 1, price3 = 2;

        assertThat(1, is(price1));
        assertThat(1, anyOf(is(price2), is(price3)));
        assertThat(1, allOf(is(price1), is(price2)));
    }

    @Test
    @DisplayName("nullValue 매처를 사용한 테스트")
    void testNull() {
        assertThat(null, nullValue());
    }

    @Test
    @DisplayName("notNullValue 매처를 사용한 테스트")
    void testNotNull() {
        assertThat(customer, notNullValue());
    }

    @Test
    @DisplayName("hasProperty 매처를 사용한 테스트")
    void checkCorrectCustomerProperties() {
        assertThat(customer, allOf(
                hasProperty("firstName", is(FIRST_NAME)),
                hasProperty("lastName", is(LAST_NAME))
        ));
    }
}
/*
JUnit은 테스트 메서드의 <span style="color:#FF3333">격리성</span>을 보장
단언문에서 <span style="color:#FF3333">람다식</span>을 파라미터로 사용할 때의 이점은 <span style="color:#FF3333">지연 전달 덕분에 성능이 향상</span>된다는 데 있다.
assertThrows 메서드를 사용하면 시스템에서 할당된 Job 객체가 없을 때 예외를 던지는지 확인할 수 있으므로 <span style="color:#FF3333">테스트 가독성</span>이 좋아진다.
 */