package com.paymybuddy.moneytransfert.app.service;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public interface IBanckServices {
    //Account consult(int accountId);
    void verser(int accountId, int ammount, String labbel);
    void retirer(Model model,int accountId, int ammount, String labbel);
    void virer(Model model, int accountId1, int accountId2, int ammount, String labbel);
    //Page<Transaction> listTransactions(int accountId, int page, int pageSize, String sortField, String sortDirection);
}
