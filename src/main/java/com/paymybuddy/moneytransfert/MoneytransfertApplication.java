package com.paymybuddy.moneytransfert;

import com.paymybuddy.moneytransfert.model.*;
import com.paymybuddy.moneytransfert.repository.AccountRepository;
import com.paymybuddy.moneytransfert.repository.ClientRepository;
import com.paymybuddy.moneytransfert.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
	ClientRepository clientRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	public static void main(String[] args)

			throws ParseException

	{
		ApplicationContext ctx =

				SpringApplication.run(MoneytransfertApplication.class, args);

		ClientRepository clientRepository_2 =  ctx.getBean(ClientRepository.class);
		clientRepository_2.save(new Client("soufiene_Mail@gmail.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),""));

	}

	@Override
	public void run(String... args) throws Exception {
		Client cl2 = clientRepository.save(new Client("soufiene_Mail@gmail_2.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),""));
		Client cl3 = clientRepository.save(new Client("soufiene_Mail@gmail_3.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),""));
		Client cl4 = clientRepository.save(new Client("soufiene_Mail@gmail_4.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),""));

		Account accout2 = accountRepository.save(new Account("soufiene_Mail@gmail_2.com_1",200, cl2));
		Account accout3 = accountRepository.save(new Account("soufiene_Mail@gmail_3.com_1",300, cl3));
		Account accout4 = accountRepository.save(new Account("soufiene_Mail@gmail_4.com_1",400, cl4));
		Account accout5 = accountRepository.save(new Account("soufiene_Mail@gmail_5.com_1",400, cl4));

		Transaction transaction2 = transactionRepository.save(new Versement(accout2,50,"Ver"));
		Transaction transaction3 = transactionRepository.save(new Virement(accout3,50,"Vir"));
		Transaction transaction4 = transactionRepository.save(new Virement(accout3,50,"Vir"));
	}

}
