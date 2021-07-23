package com.paymybuddy.moneytransfert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.model.Account;
import com.paymybuddy.moneytransfert.repository.AccountRepository;

public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Iterable<Account> getAccounts() {
		return accountRepository.findAll();
	}

	public Optional<Account> getAccountById(Integer id) {
		return accountRepository.findById(id);
	}	

	public Account saveAccount(Account Account) {
		return accountRepository.save(Account);
	}

	public void deleteAccountById(Integer id) {
		accountRepository.deleteById(id);
	}

}
