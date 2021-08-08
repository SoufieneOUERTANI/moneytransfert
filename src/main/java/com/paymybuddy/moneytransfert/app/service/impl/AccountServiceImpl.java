package com.paymybuddy.moneytransfert.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountServiceImpl implements com.paymybuddy.moneytransfert.app.service.IAccountService {

    private static final Logger logger = LogManager.getLogger("AccountServiceImpl");


    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByAccountId(String accountId){ return accountRepository.findByAccountId(accountId); }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteByAccountId(String accountId) {
        accountRepository.deleteById(accountId);
    }

    //Pagination

    @Override
    public Page<Account> findPaginatedAccountService(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.accountRepository.findAll(pageable);
    }

}
