package org.example.junitinaction3.chapter08.account;

import org.example.junitinaction3.chapter08.configurations.MockConfiguration;
import org.junit.jupiter.api.Test;

public class TestDefaultAccountManager {

    @Test
    public void testFindAccountByUser() {
        MockLog logger = new MockLog();
        MockConfiguration configuration = new MockConfiguration();
        configuration.setSQL("SELECT * FROM TEST");

        DefaultAccountManager2 am = new DefaultAccountManager2(logger, configuration);
        Account account = am.findAccountForUser("1234");
    }
}
