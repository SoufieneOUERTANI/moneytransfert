package com.paymybuddy.moneytransfert.login.controller;

import com.paymybuddy.moneytransfert.app.model.Client;
import com.paymybuddy.moneytransfert.app.model.Transaction;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import com.paymybuddy.moneytransfert.login.dao.UserDao;
import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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

    @Autowired
    UserDao userDao;

    //@Spy
    //@Autowired
    IClientService clientService;

    //@Spy
    //@Autowired
    IUserService userService;

    @Value("${currentapplicationproperties}")
    private String currentproperties;

    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSS");
    private Timestamp timestamp;
    private String testMail;
    NewUser newUser;

    @BeforeEach
    void setUp() {

        logger.info("SOUE >>> currentproperties : " + currentproperties);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initBinder() {
    }

    @Test
    void showMyLoginPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/register/showRegistrationForm"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("newUser"))
                .andReturn();
    }

    @Test
    void processRegistrationForm() throws Exception {

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

        this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/register/processRegistrationForm")
                    .flashAttr("newUser", newUser))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(userDao.findByUserName(newUser.getUserName()).getUserName(),newUser.getUserName());
    }
}