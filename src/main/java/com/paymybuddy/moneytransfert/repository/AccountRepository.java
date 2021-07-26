package com.paymybuddy.moneytransfert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Derived queries on accountEmailId
    public Account findByAccountEmailId(String accountEmailId);
    public void deleteByAccountEmailId(String accountEmailId);
}
