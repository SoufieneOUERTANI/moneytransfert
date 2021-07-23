package com.paymybuddy.moneytransfert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.moneytransfert.model.Transaction;
import com.paymybuddy.moneytransfert.service.TransactionService;

@Controller
public class index {

	@Autowired
	private TransactionService transactionService;
	
	// display list of employees
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		//return(findPagineted(1, model));
		model.addAttribute("listTransactions", transactionService.gettransactions());
		return "index";
	}
	
	// display list of employees
	@RequestMapping("/page/{pageNo}")
	public String findPagineted(@PathVariable (value="pageNo") int pageNo, Model model ) {
		int pageSize = 5;
		Page<Transaction> page = transactionService.findPaginated(pageNo, pageSize);
		List<Transaction> listTransactions = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("is at sufficient level.", listTransactions);
		return "transactions";
	}
	
}
