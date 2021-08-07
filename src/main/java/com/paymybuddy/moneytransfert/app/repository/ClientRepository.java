package com.paymybuddy.moneytransfert.app.repository;

import com.paymybuddy.moneytransfert.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    // Derived queries on clientMail
    public Client findByClientMail(String clientMail);
}
