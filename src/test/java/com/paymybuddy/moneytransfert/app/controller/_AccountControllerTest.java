package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.app.service.IAccountService;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
//@ContextConfiguration(classes = IUserService.class, loader = AnnotationConfigContextLoader.class)
@WebMvcTest(controllers = AccountController.class)
//@SpringBootTest
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class _AccountControllerTest {

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
    IUserService userService;*/

    @Autowired
    private MockMvc accountControllerMockMvc;

    @Mock
    private IAccountService accountService;

    @BeforeEach
    void setUp() throws Exception {

/*        //
        logger.info("SOUE >>> currentproperties : " + currentproperties);

        // Create the mail for the new user
        timestamp = new Timestamp(System.currentTimeMillis());
        testMail = "mail"+sdf2.format(timestamp)+"@mail.com";
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
                        new BasicNameValuePair("username", testMail),
                        new BasicNameValuePair("password", "Tes+2015")
                )))))
                .andExpect(status().isFound())
                .andReturn();*/
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void viewHomePage() {
        /*
        Model model = null;
        accountController.viewHomePage(model);
        verify(accountController,
                Mockito.times(1)).findPaginated(any(Integer.class), any(String.class), any(String.class), null);
        */
    }

    @Test
    //@WithMockUser(value = "spring")

    void findPaginated() {
        int pageNo = 1;
        int pageSize = 1;

        //when(accountService.findPaginatedAccountService(pageNo, pageSize, "sortField", "sortDir")).thenReturn(null);
        when(accountService.findPaginatedAccountService(any(Integer.class), any(Integer.class), any(String.class), any(String.class))).thenReturn(null);

        accountController.findPaginated(pageNo,"sortField", "sortDir", null);
        verify(accountService, Mockito.times(1)).findPaginatedAccountService(pageNo, pageSize, "sortField", "sortDir");

    }

    @Test
    void showNewAccountForm() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void showFormForUpdate() {
    }
}