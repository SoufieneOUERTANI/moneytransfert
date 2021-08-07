package com.paymybuddy.moneytransfert.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.app.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Derived queries on accountId
    public Account findByAccountId(String accountId);
}
