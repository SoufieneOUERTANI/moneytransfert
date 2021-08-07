package com.paymybuddy.moneytransfert.app.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AllAspect {

	private static final Logger logger = LogManager.getLogger("AllAspect");


	@Before("execution(public Page<Account> findPaginated(..))")
	public void beforeAddAccountAdvice() {

		logger.info("\n=====>>> Executing @Before advice on addAccount()");
		
	}
}










