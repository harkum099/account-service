package com.banking.account.service;

import com.banking.account.entity.Account;
import com.banking.account.enums.AccountType;
import com.banking.account.exception.AccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    void testCreateAccount() {
        Account account = accountService.createAccount(AccountType.SAVINGS, 1L);

        assertNotNull(account);
        assertEquals(AccountType.SAVINGS, account.getAccountType());
    }

    @Test
    void testGetAccountById() {
        Account account = accountService.createAccount(AccountType.SAVINGS, 1L);
        Account foundAccount = accountService.getAccountById(account.getId());

        assertNotNull(foundAccount);
        assertEquals(account.getId(), foundAccount.getId());
    }

    @Test
    void testUpdateAccount() {
        Account account = accountService.createAccount(AccountType.SAVINGS, 1L);
        account.setBalance(1000.0);

        Account updatedAccount = accountService.updateAccount(account.getId(), account);

        assertNotNull(updatedAccount);
        assertEquals(1000.0, updatedAccount.getBalance());
    }

    @Test
    void testDeleteAccount() {
        Account account = accountService.createAccount(AccountType.SAVINGS, 1L);
        accountService.deleteAccount(account.getId());

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(account.getId()));
    }
}
