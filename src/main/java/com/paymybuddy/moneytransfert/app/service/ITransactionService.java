package com.paymybuddy.moneytransfert.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.paymybuddy.moneytransfert.app.model.Transaction;


public interface ITransactionService {
	
	//public Iterable<Transaction> getTransactions();

	//public Iterable<Transaction> getMyTransactions();

	//public Optional<Transaction> getTransactionById(Integer id);
	
	//public Transaction saveTransaction(Transaction transaction);
	
	//public void deleteTransactionById(Integer id);
	
	//public Page<Transaction> findPaginatedTransactions(int pageNo, int page, String sortField, String sortDirection);

	public Page<Transaction> findMyPaginatedTransactions(int pageNo, int page, String sortField, String sortDirection);


}
