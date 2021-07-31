package com.paymybuddy.moneytransfert.repository;

import com.paymybuddy.moneytransfert.model.Account;
import com.paymybuddy.moneytransfert.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    // Derived queries on clientMail
    public Account findByClientMail(String clientMail);
    public void deleteByClientMail(String clientMail);
}
