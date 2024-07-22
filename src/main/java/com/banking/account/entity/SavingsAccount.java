package com.banking.account.entity;

import com.banking.account.enums.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="savings_account")
@DiscriminatorValue("SAVING")
public class SavingsAccount extends Account {
    public SavingsAccount() {}

    public SavingsAccount(Long userId) {

        super.setUserId(userId);
        super.setAccountType(AccountType.SAVINGS.toString());
    }
}
