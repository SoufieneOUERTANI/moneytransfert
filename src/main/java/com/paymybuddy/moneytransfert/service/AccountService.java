package com.paymybuddy.moneytransfert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Account;
import com.paymybuddy.moneytransfert.repository.AccountRepository;


public interface AccountService {

	public List<Account> getAccounts();

//	public Optional<Account> getAccountById(String id);

	public Account saveAccount(Account account);

//	public void deleteAccountById(Integer id);

	public Page<Account> findPaginated(int pageNo, int page, String sortField, String sortDirection);

	public Account getAccountByAccountId(String accountId);

	public void deleteByAccountId(String accountId);

}
