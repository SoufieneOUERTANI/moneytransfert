package com.paymybuddy.moneytransfert.app.service;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public interface IBanckServices {
    Account consult(String accountId);
    void verser(String accountId, int ammount, String labbel);
    void retirer(Model model,String accountId, int ammount, String labbel);
    void virer(Model model, String accountId1, String accountId2, int ammount, String labbel);
    Page<Transaction> listTransactions(String accountId, int page, int pageSize, String sortField, String sortDirection);
}
