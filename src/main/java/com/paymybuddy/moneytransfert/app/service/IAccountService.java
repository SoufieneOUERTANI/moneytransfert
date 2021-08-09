package com.paymybuddy.moneytransfert.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.paymybuddy.moneytransfert.app.model.Account;


public interface IAccountService {

	public List<Account> getAccounts();

	public List<Account> getMyAccounts();

	public List<Account> getOtherAccounts();

	public Account getAccountByAccountId(String accountId);

	public Account saveAccount(Account account);

	public void deleteByAccountId(String accountId);

	public Page<Account> findPaginatedAccountService(int pageNo, int pageSize, String sortField, String sortDirection);

}
