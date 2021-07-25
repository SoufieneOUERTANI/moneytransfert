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

import com.paymybuddy.moneytransfert.model.Transaction;
import com.paymybuddy.moneytransfert.service.TransactionService;

@Controller
public class index {

	private static final Logger logger = LogManager.getLogger("index");

	@Autowired
	private TransactionService transactionService;
	
	// display list of transactions
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return(findPagineted(1, model));
//		model.addAttribute("listTransactions", transactionService.getTransactions());
//		return "index";
	}
	
	// display list of transactions
	@RequestMapping("/page/{pageNo}")
	public String findPagineted(@PathVariable (value="pageNo") int pageNo, Model model ) {
		int pageSize = 3;
		Page<Transaction> page = transactionService.findPaginated(pageNo, pageSize);
		List<Transaction> listTransactions = page.getContent();

		logger.info("SOUE >>> page.getContent() : "+ page.getContent());

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTransactions", listTransactions);
		return "transactions";
	}

	@GetMapping("/showNewTransactionForm")
	public String showNewTransactionForm(Model model) {
		// create model attribute to bind form data
		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);
		return "new_transaction";
	}

	@PostMapping("/saveTransaction")
	public String saveTransaction(@ModelAttribute("transaction") Transaction transaction) {
		// save transaction to database
		logger.info("SOUE >>> transaction : "+transaction);
		transactionService.saveTransaction(transaction);
		return "redirect:/";
	}


	@GetMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable (value = "id") int id) {

		// call delete transaction method
		this.transactionService.deleteTransactionById(id);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

		// get transaction from the service
		Optional<Transaction> transaction = transactionService.getTransactionById(id);

		// set transaction as a model attribute to pre-populate the form
		model.addAttribute("transaction", transaction);
		return "update_transaction";
	}
}
