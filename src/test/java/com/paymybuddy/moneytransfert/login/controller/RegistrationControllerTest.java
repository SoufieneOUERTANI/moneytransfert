package com.paymybuddy.moneytransfert.login.controller;

import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class RegistrationControllerTest {

    private static final Logger logger = LogManager.getLogger("AccountControllerTestIT");

    @Autowired
    private MockMvc mockMvc;

    @Value("${currentapplicationproperties}")
    private String currentproperties;

    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSS");
    private Timestamp timestamp;
    private String testMail;
    NewUser newUser;

    @BeforeEach
    void setUp() {

        logger.info("SOUE >>> currentproperties : " + currentproperties);

        // Create the mail for the new user
        timestamp = new Timestamp(System.currentTimeMillis());
        testMail = ("mail"+sdf2.format(timestamp)+"@mail.com").toLowerCase();
        logger.info("testMail : "+testMail);

        // Create the new user
        newUser = new NewUser(testMail, "Tes+2015", "Tes+2015", "firstName", "lastName", testMail);
        logger.info("newUser : "+newUser);

        // Register the new user
        //logger.info("SOUE >>> registrationController : ");
        //registrationController.processRegistrationForm(newUser, null, null);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initBinder() {
    }

    @Test
    void showMyLoginPage() {
    }

    @Test
    @Sql(scripts = "/dumpingTestData.sql")
    void processRegistrationForm() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/processRegistrationForm")
                .requestAttr("newUser", newUser)
                        /*
                        .with(SecurityMockMvcRequestPostProcessors.user("soufiene.mail_01@gmail.com").roles("EMPLOYEE"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())

                         */
        )
                .andExpect(status().isOk())
                /*
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

                 */
                .andReturn();



    }
}