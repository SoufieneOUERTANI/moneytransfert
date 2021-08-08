package com.paymybuddy.moneytransfert.app.aspect;

import com.paymybuddy.moneytransfert.app.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
public class AllAspect {

	private static final Logger logger = LogManager.getLogger("AllAspect");

	@Autowired
	EntityManager entityManager;

	@Before("execution(* findPaginatedAccountService(..))")
	public void beforeFindPaginatedAccountService() {

		logger.info("\n=====>>> Executing @Before advice on addAccount()");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Session currentSession = entityManager.unwrap(Session.class);

		Filter filter = currentSession.enableFilter("accountFilter");

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			logger.info("currentUserName-AccountServiceImpl : "+currentUserName);
			//filter.setParameter("userMail", currentUserName );
			filter.setParameter("userMail", "soufiene_Mail@gmail_4.com_1" );
		}
		//return this.accountRepository.findAll(pageable);
		else{
			filter.setParameter("userMail", null );
		}
	}
}










