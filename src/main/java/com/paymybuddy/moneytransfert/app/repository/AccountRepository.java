package com.paymybuddy.moneytransfert.app.repository;

import com.paymybuddy.moneytransfert.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.app.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Derived queries on accountId
    public Account findByAccountId(String accountId);
    //public List<Account> findByDuplicateClientMail(String clientMail);
    public List<Account> findByClientClientMail(String clientMail);
    public List<Account> findByClientClientMailNot(String clientMail);


}
