package org.example.junitinaction3.chapter08.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.junitinaction3.chapter08.configurations.Configuration;
import org.example.junitinaction3.chapter08.configurations.DefaultConfiguration;

public class DefaultAccountManager2 implements AccountManager {

    private Log logger;

    private Configuration configuration;

    public DefaultAccountManager2() {
        this(LogFactory.getLog(DefaultAccountManager2.class), new DefaultConfiguration("technical"));
    }

    public DefaultAccountManager2(Log logger, Configuration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

    public Account findAccountForUser(String userId) {
        this.logger.debug("Getting account for user [" + userId + "]");
        this.configuration.getSQL("FIND_ACCOUNT_FOR_USER");

        // JDBC를 사용하여 유저의 계좌 정보를 가져오는 비즈니스 로직
        return null;
    }

    public void updateAccount(Account account) {
    }
}