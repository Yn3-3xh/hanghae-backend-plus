package org.example.junitinaction3.chapter08.account;

public interface AccountManager {

    Account findAccountForUser(String userId);

    void updateAccount(Account account);
}
