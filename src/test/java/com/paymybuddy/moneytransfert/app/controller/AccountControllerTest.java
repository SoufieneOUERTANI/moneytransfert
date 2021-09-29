package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.app.service.IAccountService;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import com.paymybuddy.moneytransfert.login.controller.RegistrationController;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = {AccountController.class})
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class AccountControllerTest {

    private static final Logger logger = LogManager.getLogger("AccountControllerTestIT");

    /*
    @LocalServerPort
    private int port;
    */

    @Value("${currentapplicationproperties}")
    private String currentproperties;

    //private int NumberTest = 0;
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSS");
    private Timestamp timestamp;
    private String testMail;
    NewUser newUser;

    @Autowired
    RegistrationController registrationController;

    @Autowired
    AccountController accountController;

/*    @Autowired
    IAccountService accountService;*/

    @Autowired
    private MockMvc accountControllerMockMvc;

    @MockBean
    private IAccountService accountService;

    @MockBean
    private IUserService userService;

    @MockBean
    private IClientService clientService;

    @BeforeEach
    void setUp() throws Exception {

        //
        logger.info("SOUE >>> currentproperties : " + currentproperties);

        // Create the mail for the new user
        timestamp = new Timestamp(System.currentTimeMillis());
        testMail = ("mail"+sdf2.format(timestamp)+"@mail.com").toLowerCase();
        logger.info("testMail : "+testMail);

        // Create the new user
        newUser = new NewUser(testMail, "Tes+2015", "Tes+2015", "firstName", "lastName", testMail);
        logger.info("newUser : "+newUser);

        // Register the new user
        registrationController.processRegistrationForm(newUser, null, null);

        // connect with the new user
        MvcResult mvcResult = accountControllerMockMvc.perform(MockMvcRequestBuilders.post("/authenticateTheUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
/*                        new BasicNameValuePair("username", testMail),
                        new BasicNameValuePair("password", "Tes+2015")*/
                        new BasicNameValuePair("username", "soufiene.mail_01@gmail.com"),
                        new BasicNameValuePair("password", "Sou2015")
                )))))
                .andExpect(status().isFound())
                .andReturn();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void viewHomePage() {
    }

    @Test
    // @WithMockUser needs to add the spring-security-test dependency
    // @WithMockUser(username="soufiene.mail_01@gmail.com",password = "$2a$10$fCFe3vct4X14KXAGGQ0gUueNXOvnjLpegXjR5gaykjkGq9ji3bXS6", roles={"EMPLOYEE"})
    @Sql(scripts = "/dumpingTestData.sql")
    void findPaginated() throws Exception {
        this.accountControllerMockMvc.perform(MockMvcRequestBuilders.get("/account/page/2?sortField=''&sortDir=''")
        /*
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", testMail),
                        new BasicNameValuePair("password", "Tes+2015")
                ))))
        */
        )
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    void showNewAccountForm() {
    }

    @Test
    void showFormForUpdate() {
    }
}