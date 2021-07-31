package com.paymybuddy.moneytransfert.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Account;
import com.paymybuddy.moneytransfert.repository.AccountRepository;
import com.paymybuddy.moneytransfert.service.AccountService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

//    public Optional<Account> getAccountById(String id) {
//        return accountRepository.findById(id);
//    }

    public Account getAccountByAccountEmailId(String accountEmailId){
        return accountRepository.findByAccountEmailId(accountEmailId);
    }


    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

//    public void deleteAccountById(Integer id) {
//        accountRepository.deleteById(id);
//    }

    public void deleteByAccountEmailId(String accountEmailId) {
        accountRepository.deleteById(accountEmailId);
    }

    //Pagination

    @Override
    public Page<Account> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.accountRepository.findAll(pageable);
    }

}
