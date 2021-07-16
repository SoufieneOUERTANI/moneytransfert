package com.paymybuddy.moneytransfert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytransfert.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
}
