package com.paymybuddy.moneytransfert.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.paymybuddy.moneytransfert.app.model.Client;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.service.IAccountService;

@Controller
public class AccountController {

    private static final Logger logger = LogManager.getLogger("AccountController");

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IClientService clientService;

    // display list of accounts
    @GetMapping("/account")
    public String viewHomePage(Model model) {
        return findPaginated(1, "creationDate", "desc", model);
    }

    // display list of accounts
    @GetMapping("/account/page/{pageNo}")
    public String findPaginated(@PathVariable (value="pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model ) {
        int pageSize = 3;
        Page<Account> page = accountService.findPaginatedAccountService(pageNo, pageSize, sortField, sortDir);
        List<Account> listAccounts = page.getContent();

        logger.info("SOUE >>> page.getContent() : "+ page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listAccounts", listAccounts);
        return "accounts";
    }

    @GetMapping("/account/showNewAccountForm")
    public String showNewAccountForm(Model model) {
        // create model attribute to bind form data
        Account account = new Account();

        model.addAttribute("account", account);

        List<Client> listClients = clientService.getClients();
        List<String> listClientsId = listClients.stream().map(x -> x.getClientMail()).collect(Collectors.toList());
        logger.info("Hello4 listClientsId : "+listClientsId);

        model.addAttribute("listClientsId", listClientsId);

        return "account_new";
    }

    @PostMapping("/account/createAccount")
    public String createAccount(
//            @ModelAttribute("account") Account account,
            @RequestParam(name = "accountId") String[] accountId_tab, @RequestParam(name = "accountMail") String accountMail) {
/*
        logger.info("accountId : "+accountId_tab[0]);
        logger.info("accountId : "+accountId_tab[1]);
*/
        logger.info("accountMail : "+accountMail);

        //int accountId = Integer.parseInt(accountId_tab[0]);
/*        if(accountId.substring(0,5).equals("null,"))
            accountId = accountId.substring(5);*/
        if(accountMail.substring(0,5).equals("null,"))
            accountMail = accountMail.substring(5);


/*        Account account = accountService.getAccountByAccountId(accountId);
        if (account == null){*/
            Client client = clientService.getClientByClientMail(accountMail);
            if(client == null){
                throw new RuntimeException("No client for this mail");
            }
            Account account=new Account(client);
            accountService.saveAccount(account);
            return "redirect:/account";
/*       }
        else
            throw new RuntimeException("Account already exists");*/
    }

    @PostMapping("/account/updateAccount")
    public String updateAccount(
            @ModelAttribute("account") Account account
//            //,
//            @RequestParam(name = "accountId") String accountId, @RequestParam(name = "accountMail") String accountMail
    ) {
/*        if(accountId.substring(0,5).equals("null,"))
            accountId = accountId.substring(5);
        if(accountMail.substring(0,5).equals("null,"))
            accountMail = accountMail.substring(5);*/

        Client client = clientService.getClientByClientMail(account.getClientMail());
        account = accountService.getAccountByAccountId(account.getAccountId());
        if(client == null){
            throw new RuntimeException("No client for this mail");
        }
//        Account account= accountService.getAccountByAccountId(accountId);
        account.setClient(client);
        accountService.saveAccount(account);
        return "redirect:/account";

    }


    @GetMapping("/account/deleteAccount/{id}")
    public String deleteAccount(@PathVariable (value = "id") int id) {

        // call delete account method
        this.accountService.deleteByAccountId(id);
        return "redirect:/account";
    }

    @GetMapping("/account/showFormForUpdate/{id}")
    public String showFormForUpdate(
            //@ModelAttribute("account") Account account,
            @PathVariable ( value = "id") int id, Model model) {

        // get account from the service
        Account account = accountService.getAccountByAccountId(id);
        account.setClientMail(account.getClient().getClientMail());

        // set account as a model attribute to pre-populate the form
        model.addAttribute("account", account);
        return "account_update";
    }
}
