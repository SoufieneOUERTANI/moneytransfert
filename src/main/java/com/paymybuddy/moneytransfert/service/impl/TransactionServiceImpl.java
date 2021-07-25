package com.paymybuddy.moneytransfert.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Transaction;
import com.paymybuddy.moneytransfert.repository.TransactionRepository;
import com.paymybuddy.moneytransfert.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Iterable<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}	
	
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	public void deleteTransactionById(Integer id) {
		transactionRepository.deleteById(id);
	}

	//Pagination

	@Override
	public Page<Transaction> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.transactionRepository.findAll(pageable);
	}

}
