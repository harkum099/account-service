package com.banking.account.service;

import com.banking.account.client.GreetingClient;
import com.banking.account.entity.Account;
import com.banking.account.enums.AccountType;
import com.banking.account.exception.AccountAlreadyExistsException;
import com.banking.account.exception.AccountNotFoundException;
import com.banking.account.exception.UserNotFoundException;
import com.banking.account.factory.AccountFactory;
import com.banking.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Autowired
    GreetingClient greetingClient;

    private static final String USER_SERVICE_URL = "http://user-service/users/";

    public Account createAccount(AccountType type, String email, Double amount) {
        log.info(" Acc Type {} and email {}",type, email);
        User user= greetingClient.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found, So can't create a/c. Create an user first.");
        }

        Long userId = user.getId();

        // Check if the user already has an account of the requested type
        Optional<Account> existingAccount = accountRepository.findByUserIdAndAccountType(userId, type.toString());
        if (existingAccount.isPresent()) {
            throw new AccountAlreadyExistsException("User already has an account of type: " + type);
        }
        Account account = AccountFactory.createAccount(type, userId);
        log.info("ac {}",account);
        account.setBalance(amount);
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public Account updateAccount(Long id, Account accountDetails) {
        Account account = getAccountById(id);
        account.setBalance(accountDetails.getBalance());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }

    public ResponseEntity<String> getVersion() {
        return greetingClient.test();
    }

    public List<Account> getAccounts(Long id) {
        return accountRepository.findAllByUserId(id);
    }

    public static class User {
        private Long id;
        private String name;
        private String email;

        public Long getId() {
            return id;
        }

        // Setter for id
        public void setId(Long id) {
            this.id = id;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Setter for name
        public void setName(String name) {
            this.name = name;
        }

        // Getter for email
        public String getEmail() {
            return email;
        }

        // Setter for email
        public void setEmail(String email) {
            this.email = email;
        }

    }
}
