package com.paymybuddy.moneytransfert.app.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.repository.TransactionRepository;
import com.paymybuddy.moneytransfert.app.service.ITransactionService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TransactionServiceImpl implements ITransactionService {

	private static final Logger logger = LogManager.getLogger("TransactionServiceImpl");

	@Autowired
	private TransactionRepository transactionRepository;
	
	public Iterable<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}	
	
	public Transaction saveTransaction(Transaction transaction) {
		logger.info(">>> SOUE >>> : "+ transaction);
		logger.info(">>> SOUE >>> : "+ transaction.getAccount().getClient().getClientMail());
		transaction.setClientMail(transaction.getAccount().getClient().getClientMail());
		logger.info(">>> SOUE >>> : "+ transaction.getClientMail());
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
