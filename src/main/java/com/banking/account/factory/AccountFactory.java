package com.banking.account.factory;

import com.banking.account.entity.Account;
import com.banking.account.entity.BusinessAccount;
import com.banking.account.entity.CheckingAccount;
import com.banking.account.entity.SavingsAccount;
import com.banking.account.enums.AccountType;

public class AccountFactory {
    public static Account createAccount(AccountType type, Long userId) {
        switch (type) {
            case SAVINGS:
                return new SavingsAccount(userId);
            case CHECKING:
                return new CheckingAccount(userId);
            case BUSINESS:
                return new BusinessAccount(userId);
            default:
                throw new IllegalArgumentException("Unknown account type");
        }
    }
}

