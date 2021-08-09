package com.paymybuddy.moneytransfert.app.service.impl;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.model.Retrait;
import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.model.Versement;
import com.paymybuddy.moneytransfert.app.repository.AccountRepository;
import com.paymybuddy.moneytransfert.app.repository.TransactionRepository;
import com.paymybuddy.moneytransfert.app.service.IBanckServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BanckServicesImpl implements IBanckServices {

    private static final Logger logger = LogManager.getLogger("BanckServicesImpl");

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Account consult(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if(account==null){
            throw new RuntimeException("Compte introuvale");
        }
        return account;
    }

    @Override
    public void verser(String accountId, int ammount, String labbel) {
        logger.info("SOUE >>> verser");

        if(ammount<=0){
            throw new RuntimeException("Le montant du versment doit être positif");
        }
        Account account = accountRepository.findByAccountId(accountId);
        if(account==null){
            throw new RuntimeException("Compte inexistant");
        }
        Versement versement = new Versement(account, (float) (ammount*0.95), labbel);
        transactionRepository.save(versement);
        account.setBalance((float) (account.getBalance()+(ammount*0.95)));
        accountRepository.save(account);
    }

    @Override
    public void retirer(String accountId, int ammount, String labbel) {
        logger.info("SOUE >>> retirer");

        if(ammount<=0){
            throw new RuntimeException("Le montant du versment doit être positif");
        }
        Account account = accountRepository.findByAccountId(accountId);
        if(account==null){
            throw new RuntimeException("Compte inexistant");
        }
        if(account.getBalance()< ammount*1.05)
        {
            throw new RuntimeException("Solde insuffisant");
        }
        Retrait retrait = new Retrait(account, (float) (ammount*1.05), labbel);
        transactionRepository.save(retrait);
        account.setBalance((float) (account.getBalance()-(ammount*1.05)));
        accountRepository.save(account);
    }

    @Override
    public void virer(String accountId1, String accountId2, int ammount, String labbel) {
        if(accountId1 == accountId2){
            throw new RuntimeException("Erreur virement : Compte source et déstination identiques");
        }
        logger.info("SOUE >>> virer");
        retirer(accountId1, ammount, labbel);
        verser(accountId2, ammount, labbel);
    }

    @Override
    public Page<Transaction> listTransactions(String accountId, int page, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        return transactionRepository.listTransaction(accountId, PageRequest.of(page - 1, pageSize, sort));
    }

}
