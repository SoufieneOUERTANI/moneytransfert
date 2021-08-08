package com.paymybuddy.moneytransfert;

import com.paymybuddy.moneytransfert.app.model.*;
import com.paymybuddy.moneytransfert.app.repository.AccountRepository;
import com.paymybuddy.moneytransfert.app.repository.ClientRepository;
import com.paymybuddy.moneytransfert.app.repository.TransactionRepository;
import com.paymybuddy.moneytransfert.app.service.IAccountService;
import com.paymybuddy.moneytransfert.app.service.IBanckServices;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import com.paymybuddy.moneytransfert.app.service.ITransactionService;
import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import com.paymybuddy.moneytransfert.login.validation.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class MoneytransfertApplication

		implements CommandLineRunner

{

	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


	@Autowired
	IUserService userService;

	@Autowired
	IClientService clientService;

	@Autowired
	IAccountService accountService;

	@Autowired
	IBanckServices banckServices;

	@Autowired
	ITransactionService transactionService;

	public static void main(String[] args)

			throws ParseException

	{
		ApplicationContext ctx =

				SpringApplication.run(MoneytransfertApplication.class, args);
/*
		ClientRepository clientRepository_2 =  ctx.getBean(ClientRepository.class);
		clientRepository_2.save(new Client("soufiene_Mail@gmail.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),""));
*/

	}

	@Override
	public void run(String... args) throws Exception {

		NewUser newUser2 = new NewUser("soufiene.mail_2@gmail.com","Sou2015","Sou2015", "Soufiene", "OUERTANI","soufiene.mail_2@gmail.com" );
		User theUser2 = userService.save(newUser2);
		Client client2 = new Client(theUser2.getEmail(),theUser2.getLastName(),theUser2.getFirstName());
		clientService.saveClient(client2);

		NewUser newUser3 = new NewUser("soufiene.mail_3@gmail.com","Sou2015","Sou2015", "Soufiene", "OUERTANI","soufiene.mail_3@gmail.com" );
		User theUser3 = userService.save(newUser3);
		Client client3 = new Client(theUser3.getEmail(),theUser3.getLastName(),theUser3.getFirstName());
		clientService.saveClient(client3);

		NewUser newUser4 = new NewUser("soufiene.mail_4@gmail.com","Sou2015","Sou2015", "Soufiene", "OUERTANI","soufiene.mail_4@gmail.com" );
		User theUser4 = userService.save(newUser4);
		Client client4 = new Client(theUser4.getEmail(),theUser4.getLastName(),theUser4.getFirstName());
		clientService.saveClient(client4);
		
		Account accout2 = accountService.saveAccount(new Account("soufiene_Mail@gmail_2.com_1",200, client2));
		Account accout3 = accountService.saveAccount(new Account("soufiene_Mail@gmail_3.com_1",300, client3));
		Account accout4 = accountService.saveAccount(new Account("soufiene_Mail@gmail_4.com_1",400, client4));

		transactionService.saveTransaction(new Versement(accout2,50,"Ver"));
		transactionService.saveTransaction(new Retrait(accout3,50,"Vir"));
		transactionService.saveTransaction(new Retrait(accout3,50,"Vir"));

		System.out.println(banckServices.consult("soufiene_Mail@gmail_4.com_1"));

		banckServices.verser("soufiene_Mail@gmail_2.com_1",250, "Versement initial");
		banckServices.retirer("soufiene_Mail@gmail_2.com_1",50, "Versement initial");

		banckServices.virer("soufiene_Mail@gmail_2.com_1", "soufiene_Mail@gmail_3.com_1", 50, "Versement initial");
		System.out.println("SOUE >>> : " + banckServices.listTransactions("soufiene_Mail@gmail_3.com_1", 1, 3,"transactionDate" ,"ASC"));


	}

}
