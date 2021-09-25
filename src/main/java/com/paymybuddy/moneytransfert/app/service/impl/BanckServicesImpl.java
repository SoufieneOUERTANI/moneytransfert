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
import org.springframework.ui.Model;

//@Transactional
@Service
public class BanckServicesImpl implements IBanckServices {

    private static final Logger logger = LogManager.getLogger("BanckServicesImpl");

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Account consult(int accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new RuntimeException("Compte introuvale");
        }
        return account;
    }

    @Override
    public void verser(int accountId, int ammount, String labbel) {
        logger.info("SOUE >>> verser");

        if (ammount <= 0) {
            throw new RuntimeException("Le montant du versment doit être positif");
        }
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new RuntimeException("Vérifier le compte à créditer");
        }
        Versement versement = new Versement(account, (float) (ammount * 0.95), labbel);
        transactionRepository.save(versement);
        account.setBalance((float) (account.getBalance() + (ammount * 0.95)));
        accountRepository.save(account);
    }

    @Override
    public void retirer(Model model, int accountId, int ammount, String labbel) {
        logger.info("SOUE >>> retirer");

        Account account = null;
        if (ammount <= 0) {
            throw new RuntimeException("Le montant du versment doit être positif");
        }
        account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new RuntimeException("Vérifier le compte à débiter");
        }
        if (account.getBalance() < ammount * 1.05) {
            logger.info(">>> SOUE 'Solde insuffisant' : ");
            throw new RuntimeException("Solde insuffisant");
        }
        Retrait retrait = new Retrait(account, (float) (ammount * 1.05), labbel);
        transactionRepository.save(retrait);
        account.setBalance((float) (account.getBalance() - (ammount * 1.05)));
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void virer(Model model, int accountId1, int accountId2, int amount, String labbel) {

        if (accountId1 == accountId2) {
            throw new RuntimeException("Erreur virement : Compte source et déstination identiques");
        }
        logger.info("SOUE >>> virer");
        ///>>> Retirer >>>
        // retirer(model, accountId1, amount, labbel);
        logger.info("SOUE >>> retirer");

        Account account = null;
        if (amount <= 0) {
            throw new RuntimeException("Le montant du versment doit être positif");
        }
        account = accountRepository.findByAccountId(accountId1);
        if (account == null) {
            throw new RuntimeException("Vérifier le compte à débiter");
        }
        if (account.getBalance() < amount * 1.05) {
            logger.info(">>> SOUE \"Solde insuffisant\" : ");
            throw new RuntimeException("Solde insuffisant");
        }
        Retrait retrait = new Retrait(account, (float) (amount * 1.05), labbel);
        transactionRepository.save(retrait);
        account.setBalance((float) (account.getBalance() - (amount * 1.05)));
        accountRepository.save(account);

        ///>>> Verser  >>>
        // verser(accountId2, amount, labbel);
        logger.info("SOUE >>> verser");

        if (amount <= 0) {
            throw new RuntimeException("Le montant du versment doit être positif");
        }
        Account account1 = accountRepository.findByAccountId(accountId2);
        if (account1 == null) {
            throw new RuntimeException("Vérifier le compte à créditer");
        }
        Versement versement = new Versement(account1, (float) (amount * 0.95), labbel);
        transactionRepository.save(versement);
        account1.setBalance((float) (account1.getBalance() + (amount * 0.95)));
        accountRepository.save(account1);

    }

    @Override
    public Page<Transaction> listTransactions(int accountId, int page, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        return transactionRepository.listTransaction(accountId, PageRequest.of(page - 1, pageSize, sort));
    }
}
