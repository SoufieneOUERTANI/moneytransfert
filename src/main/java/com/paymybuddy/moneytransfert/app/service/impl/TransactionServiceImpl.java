package com.paymybuddy.moneytransfert.app.service.impl;

import java.util.Optional;

import com.paymybuddy.moneytransfert.app.service.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.repository.TransactionRepository;
import com.paymybuddy.moneytransfert.app.service.ITransactionService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private static final Logger logger = LogManager.getLogger("TransactionServiceImpl");

    @Autowired
    EntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private IAccountService accountService;

/*
    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Iterable<Transaction> getMyTransactions() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Session currentSession = entityManager.unwrap(Session.class);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("currentUserName-AccountServiceImpl : " + currentUserName);
            //filter.setParameter("userMail", currentUserName );
            //return accountRepository.findByDuplicateClientMail(currentUserName);
            return transactionRepository.findByAccountClientClientMail(currentUserName);
        }
        return null;
    }


    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }
*/
    /*

    public Transaction saveTransaction(Transaction transaction) {
        if (transaction.getAccount() == null && transaction.getAccountId() != 0) {
            transaction.setAccount(accountService.getAccountByAccountId(transaction.getAccountId()));
        }

        if (transaction.getAccount() != null && transaction.getAccountId() == 0) {
            transaction.setAccountId(transaction.getAccount().getAccountId());
        }

        logger.info(">>> SOUE >>> : " + transaction);
        logger.info(">>> SOUE >>> : " + transaction.getAccountId());
        logger.info(">>> SOUE >>> : " + accountService.getAccountByAccountId(transaction.getAccountId()));
        logger.info(">>> SOUE >>> : " + transaction);
        transaction.setAccount(accountService.getAccountByAccountId(transaction.getAccountId()));
        logger.info(">>> SOUE >>> : " + transaction);
        logger.info(">>> SOUE >>> : " + transaction.getAccount().getClient().getClientMail());
        transaction.setClientMail(transaction.getAccount().getClient().getClientMail());
        logger.info(">>> SOUE >>> : " + transaction.getClientMail());
        return transactionRepository.save(transaction);
    }
    */

    /*

    public void deleteTransactionById(Integer id) {
        transactionRepository.deleteById(id);
    }

    //Pagination

    @Override
    public Page<Transaction> findPaginatedTransactions(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.transactionRepository.findAll(pageable);
    }

    */


    public Page<Transaction> findMyPaginatedTransactions(int pageNo, int pageSize, String sortField, String sortDirection) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Session currentSession = entityManager.unwrap(Session.class);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("currentUserName-AccountServiceImpl : " + currentUserName);
            //filter.setParameter("userMail", currentUserName );
            //return accountRepository.findByDuplicateClientMail(currentUserName);
            Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();

            Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
            return this.transactionRepository.findAllByAccountClientClientMail(currentUserName, pageable);
        }
        return null;
    }
}
