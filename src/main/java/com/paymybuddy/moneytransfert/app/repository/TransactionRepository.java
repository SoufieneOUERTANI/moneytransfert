package com.paymybuddy.moneytransfert.app.repository;

import com.paymybuddy.moneytransfert.app.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.app.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("select tr from Transaction tr where tr.account.accountId=:x")
    Page<Transaction> listTransaction(@Param("x") int AccountId, Pageable pageable);
    public List<Transaction> findByAccountClientClientMail(String clientMail);
    public Page<Transaction> findAllByAccountClientClientMail(String clientMail, Pageable pageable);


}
