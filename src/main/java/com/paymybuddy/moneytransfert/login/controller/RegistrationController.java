package com.paymybuddy.moneytransfert.login.controller;

import com.paymybuddy.moneytransfert.app.model.Client;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Transactional
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
	
    @Autowired
    private IUserService userService;

	@Autowired
	private IClientService clientService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("newUser", new NewUser());
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("newUser") NewUser theNewUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theNewUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult != null && theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("newUser", new NewUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        
        // create user account        						
        userService.save(theNewUser);
        Client client = new Client(theNewUser.getEmail(),theNewUser.getLastName(),theNewUser.getFirstName());
		clientService.saveClient(client);

		logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
