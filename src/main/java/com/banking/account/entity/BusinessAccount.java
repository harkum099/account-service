package com.banking.account.entity;

import com.banking.account.enums.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="business_account")
@DiscriminatorValue("BUSINESS")
public class BusinessAccount extends Account {
    public BusinessAccount() {}

    public BusinessAccount(Long userId) {
        super.setUserId(userId);
        super.setAccountType(AccountType.BUSINESS.toString());
    }
}
