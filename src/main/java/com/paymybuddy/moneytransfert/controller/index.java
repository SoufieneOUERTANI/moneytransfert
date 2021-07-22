package com.paymybuddy.moneytransfert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.moneytransfert.service.TransactionService;

@Controller
public class index {

	@Autowired
	private TransactionService transactionService;
	
	// display list of employees
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listTransactions", transactionService.gettransactions());
		return "index";
	}
	

}
