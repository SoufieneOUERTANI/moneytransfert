package com.paymybuddy.moneytransfert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Transaction;
import com.paymybuddy.moneytransfert.repository.TransactionRepository;


public interface TransactionService {
	
	public Iterable<Transaction> gettransactions();
	
	public Optional<Transaction> gettransactionById(Integer id);
	
	public Transaction savetransaction(Transaction transaction);
	
	public void deletetransactionById(Integer id);
	
	public Page<Transaction> findPaginated(int pageNo, int page);

}
