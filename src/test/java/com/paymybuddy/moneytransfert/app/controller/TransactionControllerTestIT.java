package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.service.ITransactionService;
import com.paymybuddy.moneytransfert.app.service.impl.TransactionServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class TransactionControllerTestIT {

    private static final Logger logger = LogManager.getLogger("AccountControllerTestIT");

    @Value("${currentapplicationproperties}")
    private String currentproperties;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {

        logger.info("SOUE >>> currentproperties : " + currentproperties);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void homePage() {
    }

    @Test
    void viewHomePage() {
    }

    @Test
    @Sql(scripts = "/dumpingTestData.sql")
    @WithMockUser(username = "soufiene.mail_01@gmail.com", roles ={"EMPLOYEE"})
    void findPaginated() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("\n=====>>> authentication : "+authentication);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/transaction/page/1?sortField=transactionId&sortDir='desc'")
                        .with(SecurityMockMvcRequestPostProcessors.user("soufiene.mail_01@gmail.com").roles("EMPLOYEE"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transactions"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("currentPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalPages"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalItems"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sortField"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sortDir"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listMyTransactions"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listMyAccountsId"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOtherAccountsId"))
                .andExpect(MockMvcResultMatchers.model().attribute("currentPage", 1))
                .andExpect(MockMvcResultMatchers.model().attribute("totalPages", 1))
                .andExpect(MockMvcResultMatchers.model().attribute("totalItems", 3L))
                .andExpect(MockMvcResultMatchers.model().attribute("sortField", "transactionId"))
                .andExpect(MockMvcResultMatchers.model().attribute("sortDir", "'desc'"))
                .andExpect(MockMvcResultMatchers.model().attribute("listMyTransactions", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.model().attribute("listMyTransactions", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.<Transaction>hasProperty("transactionId", Matchers.is(6)),
                                Matchers.<Transaction>hasProperty("sourceLabbel", Matchers.is("50"))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("listMyTransactions", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.<Transaction>hasProperty("transactionId", Matchers.is(3)),
                                Matchers.<Transaction>hasProperty("sourceLabbel", Matchers.is("1"))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("listMyTransactions", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.<Transaction>hasProperty("transactionId", Matchers.is(1)),
                                Matchers.<Transaction>hasProperty("sourceLabbel", Matchers.is("4"))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("listMyAccountsId", Matchers.hasSize(5)))
                .andExpect(MockMvcResultMatchers.model().attribute("listMyAccountsId", Matchers.contains(1,2,3,4,6)))
                .andExpect(MockMvcResultMatchers.model().attribute("listOtherAccountsId", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("listOtherAccountsId", Matchers.contains(5,7)))
                .andReturn();

    }

    @Test
    void showNewTransactionForm() {
    }

    @Test
    void saveTransaction() {
    }

    @Test
    void deleteTransaction() {
    }

    @Test
    void showFormForUpdate() {
    }

    @Test
    void showTransactionsdetails() {
    }

    @Test
    void findPaginated_details() {
    }
}