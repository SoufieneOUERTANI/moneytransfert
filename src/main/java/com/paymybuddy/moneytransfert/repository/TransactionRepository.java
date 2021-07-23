package com.paymybuddy.moneytransfert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
}
