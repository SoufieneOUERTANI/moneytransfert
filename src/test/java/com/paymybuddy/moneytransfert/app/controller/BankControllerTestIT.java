package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.app.service.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@WebMvcTest
//@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class BankControllerTestIT {

    // logger
    private static final Logger logger = LogManager.getLogger("BankControllerTestIT");

    // application.properties file
    @Value("${currentapplicationproperties}")
    private String currentproperties;

    @Autowired
    BankController bankController;

    @Autowired
    IAccountService accountService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    // @WithMockUser needs to add the spring-security-test dependency
    // @WithMockUser(username="soufiene.mail_01@gmail.com",password = "Sou2015", roles={"EMPLOYEE"})
    @Sql(statements = "update moneytransfert.account set balance = 50.25 where account_id = '2'")
    void bankOperation_Vers_SoldeAugmente_5PercentCommision() {
        int versement = 100;
        int account_id = 2;
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());
        float newBalance = (float) (accountService.getAccountByAccountId(account_id).getBalance() + (versement * 0.95));
        logger.info("newBalance : "+ newBalance);
        bankController.bankOperation(null, String.valueOf(account_id),"" ,versement , "100 versement", "Vers");
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());
        assertEquals(newBalance, accountService.getAccountByAccountId(account_id).getBalance());
    }

    @Test
    // @WithMockUser needs to add the spring-security-test dependency
    // @WithMockUser(username="soufiene.mail_01@gmail.com",password = "Sou2015", roles={"EMPLOYEE"})
    @Sql(statements = "update moneytransfert.account set balance = 50.25 where account_id = '2'")
    void bankOperation_Retr_SoldeDiminue_5PercentCommision() {
        int retrait = 40;
        int account_id = 2;
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());
        float newBalance = (float) (accountService.getAccountByAccountId(account_id).getBalance() - (retrait * 1.05));
        logger.info("newBalance : "+ newBalance);
        bankController.bankOperation(null, String.valueOf(account_id),"" ,retrait , "40 retrait", "Retr");
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());
        assertEquals(newBalance, accountService.getAccountByAccountId(account_id).getBalance());
    }

    @Test
    // @WithMockUser needs to add the spring-security-test dependency
    // @WithMockUser(username="soufiene.mail_01@gmail.com",password = "Sou2015", roles={"EMPLOYEE"})
    @Sql(statements = "update moneytransfert.account set balance = 50.25 where account_id = '2'")
    void bankOperation_Retr_SoldeInsuffisant_MemeSolde() {
        int retrait = 51;
        int account_id = 2;
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());
        float newBalance = (float) (accountService.getAccountByAccountId(account_id).getBalance() - (retrait * 1.05));
        logger.info("newBalance : "+ newBalance);
        bankController.bankOperation(null, String.valueOf(account_id),"" ,retrait , "51 retrait", "Retr");
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());
        assertEquals(50.25, accountService.getAccountByAccountId(account_id).getBalance());
    }

    @Test
    // @WithMockUser needs to add the spring-security-test dependency
    // @WithMockUser(username="soufiene.mail_01@gmail.com",password = "Sou2015", roles={"EMPLOYEE"})
    @Sql(scripts = "/dumpingTestData.sql")
    void bankOperation_Vire_DeuxComptesImpact() {

        int virement = 30;
        int myAccount_id = 2;
        int account_id = 5;

        logger.info("accountService.getAccountByAccountId("+myAccount_id+").getBalance() : "+ accountService.getAccountByAccountId(myAccount_id).getBalance());
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());

        float MyNewBalance = (float) (accountService.getAccountByAccountId(myAccount_id).getBalance() - (virement * 1.05));
        logger.info("MyNewBalance : "+ MyNewBalance);
        float newBalance = (float) (accountService.getAccountByAccountId(account_id).getBalance() + (virement * 0.95));
        logger.info("newBalance : "+ newBalance);

        bankController.bankOperation(null, String.valueOf(myAccount_id),String.valueOf(account_id) ,virement , "30 virement 2-5", "Vire");

        logger.info("accountService.getAccountByAccountId("+myAccount_id+").getBalance() : "+ accountService.getAccountByAccountId(myAccount_id).getBalance());
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());

        assertEquals(MyNewBalance, accountService.getAccountByAccountId(myAccount_id).getBalance());
        assertEquals(newBalance, accountService.getAccountByAccountId(account_id).getBalance());

    }

    @Test
    // @WithMockUser needs to add the spring-security-test dependency
    // @WithMockUser(username="soufiene.mail_01@gmail.com",password = "Sou2015", roles={"EMPLOYEE"})
    @Sql(scripts = "/dumpingTestData.sql")
    void bankOperation_Vire_SoleInsuffisant_SoldesInchanges() {

        int virement = 100;
        int myAccount_id = 2;
        int account_id = 5;

        float myOldBalance = accountService.getAccountByAccountId(myAccount_id).getBalance();
        float oldBalance = accountService.getAccountByAccountId(account_id).getBalance();

        logger.info("accountService.getAccountByAccountId("+myAccount_id+").getBalance() : "+ myOldBalance);
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ oldBalance);

        float MyNewBalance = (float) (accountService.getAccountByAccountId(myAccount_id).getBalance() - (virement * 1.05));
        logger.info("MyNewBalance : "+ MyNewBalance);
        float newBalance = (float) (accountService.getAccountByAccountId(account_id).getBalance() + (virement * 0.95));
        logger.info("newBalance : "+ newBalance);

        bankController.bankOperation(null, String.valueOf(myAccount_id),String.valueOf(account_id) ,virement , "30 virement 2-5", "Vire");

        logger.info("accountService.getAccountByAccountId("+myAccount_id+").getBalance() : "+ accountService.getAccountByAccountId(myAccount_id).getBalance());
        logger.info("accountService.getAccountByAccountId("+account_id+").getBalance() : "+ accountService.getAccountByAccountId(account_id).getBalance());

        assertEquals(myOldBalance, accountService.getAccountByAccountId(myAccount_id).getBalance());
        assertEquals(oldBalance, accountService.getAccountByAccountId(account_id).getBalance());

    }

}