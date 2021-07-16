package com.paymybuddy.moneytransfert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Transaction;
import com.paymybuddy.moneytransfert.repository.TransactionRepository;


@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Iterable<Transaction> gettransactions() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> gettransactionById(Integer id) {
		return transactionRepository.findById(id);
	}	
	
	public Transaction savetransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	public void deletetransactionById(Integer id) {
		transactionRepository.deleteById(id);
	}

}
