package com.banking.account.controller;


import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;
import com.banking.account.enums.AccountType;
import com.banking.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody AccountDTO accountDTO) {
        System.out.println(" Hi there "+accountDTO.getAccountType());
        log.info(" type --> {}",accountDTO.getAccountType());
        return accountService.createAccount(AccountType.valueOf(
                accountDTO.getAccountType().toUpperCase()),
                accountDTO.getEmail(), accountDTO.getAmount());
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getVersion() {
        return accountService.getVersion();
    }

    @GetMapping("/get-ac/{id}")
    public List<Account> getAccounts(@PathVariable Long id) {
        return accountService.getAccounts(id);
    }
}
