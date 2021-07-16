package com.paymybuddy.moneytransfert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
	
}
