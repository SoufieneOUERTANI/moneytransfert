package com.paymybuddy.moneytransfert.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.app.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("select tr from Transaction tr where tr.account.accountId=:x")
    Page<Transaction> listTransaction(@Param("x") String AccountId, Pageable pageable);
	
}
