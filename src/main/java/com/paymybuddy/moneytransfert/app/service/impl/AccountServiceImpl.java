package com.paymybuddy.moneytransfert.app.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.paymybuddy.moneytransfert.app.service.IAccountService;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

//SOUETransactional
@Service
public class AccountServiceImpl implements IAccountService {

    private static final Logger logger = LogManager.getLogger("AccountServiceImpl");

    @Autowired
    EntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IClientService clientService;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getMyAccounts() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Session currentSession = entityManager.unwrap(Session.class);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("currentUserName-AccountServiceImpl : "+currentUserName);
            //filter.setParameter("userMail", currentUserName );
            //return accountRepository.findByDuplicateClientMail(currentUserName);
            return accountRepository.findByClientClientMail(currentUserName);
        }
        return null;
    }

    @Override
    public List<Account> getOtherAccounts() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Session currentSession = entityManager.unwrap(Session.class);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("currentUserName-AccountServiceImpl : "+currentUserName);
            //filter.setParameter("userMail", currentUserName );
            //return accountRepository.findByDuplicateClientMail(currentUserName);
            return accountRepository.findByClientClientMailNot(currentUserName);
        }
        return null;
    }

    public Account getAccountByAccountId(int accountId){ return accountRepository.findByAccountId(accountId); }

    @Transactional
    public Account saveAccount(Account account) {
        account.setClientMail(account.getClient().getClientMail());

/*        List<String> AccountIdList = accountRepository.findAll().stream().map(o->String.valueOf(o.getAccountId())).collect(Collectors.toList());
        long max = AccountIdList.stream().map(o->o.split("-")[1])
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L) + 1;
        String stringMax = "FR-"+String.format("%010d", max);
        //account.setAccountId(stringMax+"-"+account.getClient().getClientMail());
        */

        return accountRepository.save(account);
    }

    public void deleteByAccountId(int accountId) {
        accountRepository.deleteById(accountId);
    }

    //Pagination

    @Override
    public Page<Account> findPaginatedAccountService(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.accountRepository.findAll(pageable);
    }

}
