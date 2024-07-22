package com.banking.account.entity;

import com.banking.account.enums.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="checking_account")
@DiscriminatorValue("CHECKING")
public class CheckingAccount extends Account {
    public CheckingAccount() {}

    public CheckingAccount(Long userId) {

        super.setUserId(userId);
        super.setAccountType(AccountType.CHECKING.toString());
    }
}
