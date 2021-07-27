package com.paymybuddy.moneytransfert.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.paymybuddy.moneytransfert.model.Account;
import com.paymybuddy.moneytransfert.service.AccountService;

@Controller
public class AccountController {

    private static final Logger logger = LogManager.getLogger("index");

    @Autowired
    private AccountService accountService;

    // display list of accounts
    @GetMapping("/account")
    public String viewHomePage(Model model) {
        return(findPagineted(1, "accountEmailId", "desc", model));
    }

    // display list of accounts
    @GetMapping("/account/page/{pageNo}")
    public String findPagineted(@PathVariable (value="pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model ) {
        int pageSize = 3;
        Page<Account> page = accountService.findPaginated(pageNo, pageSize, sortField, sortDir);
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
        return "account_new";
    }

    @PostMapping("/account/saveAccount")
    public String saveAccount(@ModelAttribute("account") Account account) {
        // save account to database
        // logger.info("SOUE >>> account : "+account);
        accountService.saveAccount(account);
        return "redirect:/account";
    }


    @GetMapping("/account/deleteAccount/{id}")
    public String deleteAccount(@PathVariable (value = "id") String id) {

        // call delete account method
        this.accountService.deleteByAccountEmailId(id);
        return "redirect:/account";
    }

    @GetMapping("/account/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model) {

        // get account from the service
        Account account = accountService.getAccountByAccountEmailId(id);

        // set account as a model attribute to pre-populate the form
        model.addAttribute("account", account);
        return "account_update";
    }
}
