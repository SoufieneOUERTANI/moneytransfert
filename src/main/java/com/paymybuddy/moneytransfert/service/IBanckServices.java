package com.paymybuddy.moneytransfert.service;

import com.paymybuddy.moneytransfert.model.Account;
import com.paymybuddy.moneytransfert.model.Transaction;
import org.springframework.data.domain.Page;

public interface IBanckServices {
    Account consult(String accountId);
    void verser(String accountId, int ammount, String labbel);
    void retirer(String accountId, int ammount, String labbel);
    void virer(String accountId1, String accountId2, int ammount, String labbel);
    Page<Transaction> listTransactions(String accountId, int page, int pageSize, String sortField, String sortDirection);
}
