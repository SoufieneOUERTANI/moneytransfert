package com.paymybuddy.moneytransfert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Transaction;
import com.paymybuddy.moneytransfert.repository.TransactionRepository;


public interface TransactionService {
	
	public Iterable<Transaction> getTransactions();
	
	public Optional<Transaction> getTransactionById(Integer id);
	
	public Transaction saveTransaction(Transaction transaction);
	
	public void deleteTransactionById(Integer id);
	
	public Page<Transaction> findPaginated(int pageNo, int page, String sortField, String sortDirection);

}
