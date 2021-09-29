package com.paymybuddy.moneytransfert.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.model.Versement;
import com.paymybuddy.moneytransfert.app.service.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.service.ITransactionService;

import javax.persistence.EntityManager;


@Controller
public class TransactionController {

    private static final Logger logger = LogManager.getLogger("TransactionController");

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IAccountService accountService;

    // display list of transactions
    @GetMapping("/")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("currentUserName : "+currentUserName);
        }
        return "redirect:/transaction";
    }

    // display list of transactions
    @GetMapping("/transaction")
    public String viewHomePage(Model model, @ModelAttribute("transaction") Transaction transaction) {
//        public String viewHomePage(Model model) {

            logger.info("Hello-1");

        return(findPaginated(1, "transactionId", "desc", model, transaction));
//        return(findPaginated(1, "transactionId", "desc", model));
    }

    // display list of transactions
    @GetMapping("/transaction/page/{pageNo}")
    public String findPaginated(@PathVariable (value="pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model
            ,
                                @ModelAttribute("transaction") Transaction transaction
    ) {

        logger.info("Hello1");

        int pageSize = 3;
        Page<Transaction> page = transactionService.findPaginatedTransactions(pageNo, pageSize, sortField, sortDir);
        logger.info("Hello2");

        List<Transaction> listTransactions = page.getContent();

        Page<Transaction> myPage = transactionService.findMyPaginatedTransactions(pageNo, pageSize, sortField, sortDir);
        logger.info("Hello2");

        List<Transaction> listMyTransactions = myPage.getContent();
        model.addAttribute("listMyTransactions", listMyTransactions);
        logger.info("Hello3");

        List<Account> listAccounts = accountService.getAccounts();
        List<Integer> listAccountsId = listAccounts.stream().map(x -> x.getAccountId()).collect(Collectors.toList());
        logger.info("Hello4 listAccountsId : "+listAccountsId);

        //logger.info("SOUE >>> page.getContent() : "+ page.getContent());

        model.addAttribute("currentPage", pageNo);
        logger.info("Hello5");

        model.addAttribute("totalPages", myPage.getTotalPages());
        logger.info("Hello6");

        model.addAttribute("totalItems", myPage.getTotalElements());
        logger.info("Hello7");


        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        logger.info("Hello8");

        model.addAttribute("listTransactions", listTransactions);
        logger.info("Hello9");

        model.addAttribute("listAccountsId", listAccountsId);

        logger.info("Hello10");


        List<Integer> listMyAccountsId =accountService.getMyAccounts().stream().map(x-> x.getAccountId()).collect(Collectors.toList());

        logger.info("listMyAccountsId : "+listMyAccountsId);

        model.addAttribute("listMyAccountsId", listMyAccountsId);

        List<Integer> listOtherAccountsId =accountService.getOtherAccounts().stream().map(x-> x.getAccountId()).collect(Collectors.toList());

        logger.info("listOtherAccountsId : "+listOtherAccountsId);

        model.addAttribute("listOtherAccountsId", listOtherAccountsId);

        return "transactions";
    }

    @GetMapping("/transaction/showNewTransactionForm")
    public String showNewTransactionForm(Model model) {
        // create model attribute to bind form data
        // SOUE >>>
        Transaction transaction = new Versement();
        model.addAttribute("transaction", transaction);
        return "transaction_new";
    }


    @PostMapping("/transaction/saveTransaction")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction){
        logger.info("SOUE2 >>> transaction : " + transaction);
        transactionService.saveTransaction(transaction);
        return "redirect:/transaction";
    }

    @GetMapping("/transaction/deleteTransaction/{id}")
    public String deleteTransaction(@PathVariable (value = "id") int id) {

        // call delete transaction method
        this.transactionService.deleteTransactionById(id);
        return "redirect:/transaction";
    }

    @GetMapping("/transaction/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

        // get transaction from the service
        Optional<Transaction> transaction = transactionService.getTransactionById(id);

        // set transaction as a model attribute to pre-populate the form
        model.addAttribute("transaction", transaction);
        return "transaction_update";
    }

    @GetMapping("/transaction-details")
    public String showTransactionsdetails( Model model) {
        return(findPaginated_details(1, "transactionId", "desc", model));
    }

    // display list of transactions
    @GetMapping("/transaction-details/page/{pageNo}")
    public String findPaginated_details(@PathVariable (value="pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model ) {
        int pageSize = 3;
        Page<Transaction> page = transactionService.findPaginatedTransactions(pageNo, pageSize, sortField, sortDir);
        List<Transaction> listTransactions = page.getContent();

        List<Account> listAccounts = accountService.getAccounts();

        //logger.info("SOUE >>> page.getContent() : "+ page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTransactions", listTransactions);
        model.addAttribute("listAccounts", listAccounts);

        return "transaction-details";
    }
}
