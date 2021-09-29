package com.paymybuddy.moneytransfert.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.moneytransfert.MoneytransfertApplication;
import com.paymybuddy.moneytransfert.app.model.Account;
import com.paymybuddy.moneytransfert.app.repository.AccountRepository;
import com.paymybuddy.moneytransfert.login.controller.RegistrationController;
import com.paymybuddy.moneytransfert.login.dao.RoleDao;
import com.paymybuddy.moneytransfert.login.dao.RoleDaoImpl;
import com.paymybuddy.moneytransfert.login.entity.Role;
import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// You can run this test in your IDE or on the command line (by running ./mvnw test or ./gradlew test)

/*
JUnit 5 defines an extension interface through which classes can integrate with the JUnit test.
We can enable this extension by adding the @ExtendWith annotation to our test classes and specifying the extension class to load.
To run the Spring test, we use SpringExtension.class
--
@ExtendWith annotation to tell JUnit 5 to enable Spring support.
As of Spring Boot 2.1, we no longer need to load the SpringExtension because it's included as a meta annotation in the Spring Boot test annotations like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
*/
//@ExtendWith(SpringExtension.class)

/*
@SpringBootTest : useful when we need to bootstrap the entire container.
The annotation works by creating the ApplicationContext that will be utilized in our tests
--
@SpringBootTest by default starts searching in the current package of the test class and then searches upwards through the package structure, looking for a class annotated with @SpringBootConfiguration from which it then reads the configuration to create an application context.
This class is usually our main application class since the @SpringBootApplication annotation includes the @SpringBootConfiguration annotation.
It then creates an application context very similar to the one that would be started in a production environment.
We can customize this application context in many different ways
--
We can turn a lot of knobs to customize the application context created by @SpringBootTest
Here are some other useful ones from the documentation:

@AutoConfigureWebTestClient: Adds WebTestClient to the test application context. It allows us to test server endpoints.
@AutoConfigureTestDatabase: This allows us to run the test against a real database instead of the embedded one.
@RestClientTest: It comes in handy when we want to test our RestTemplates. It autoconfigures the required components plus a MockRestServiceServer object which helps us mock responses for the requests coming from the RestTemplate calls.
@JsonTest: Autoconfigures JSON mappers and classes such as JacksonTester or GsonTester. Using these we can verify whether our JSON serialization/deserialization is working properly or not
__
in tests it’s necessary to set some configuration properties to a value that’s different from the value in a production setting
@SpringBootTest(properties = "foo=bar")
class SpringBootPropertiesTest {

  @Value("${foo}")
  String foo;

  @Test
  void test(){
    assertThat(foo).isEqualTo("bar");
  }
}

*/
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = MoneytransfertApplication.class)
@SpringBootTest


//Another useful approach is to not start the server at all but to test only the layer below that, where Spring handles the incoming HTTP request and hands it off to your controller.
// That way, almost of the full stack is used, and your code will be called in exactly the same way as if it were processing a real HTTP request but without the cost of starting the server.
// To do that, use Spring’s MockMvc and ask for that to be injected for you by using the @AutoConfigureMockMvc annotation on the test case
// --
// We use this MockMvc object to perform a POST request to our application and to verify that it responds as expected.
@AutoConfigureMockMvc

//If many of our tests need the same set of properties, we can create a configuration file application-<profile>.properties or application-<profile>.yml
//@ActiveProfiles("test")

// define a new configuration source and override the value of that property
//@TestPropertySource(locations = "classpath:_application-test.properties")
//@PropertySource(value = "classpath:_application-test.properties", ignoreResourceNotFound = true)

@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class AccountControllerTestIT {

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
    RoleDao roleDao;

    @Autowired
    IUserService userService;

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegistrationController registrationController;

    @Autowired
    private MockMvc accountControllerMockMvc;

    @Autowired
    EntityManager entityManager;

    @BeforeAll
    //@Sql(scripts = "classpath:sqlTestFolder/_insertRoles.sql", statements = "delete from role", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
    }

    @BeforeEach
    //SOUE
    @Async
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
        logger.info("SOUE >>> registrationController : ");
        registrationController.processRegistrationForm(newUser, null, null);

/*        // connect with the new user
        logger.info("\nSOUE >>> accountControllerMockMvc");
        MvcResult mvcResult = accountControllerMockMvc.perform(MockMvcRequestBuilders.post("/authenticateTheUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", testMail),
                        new BasicNameValuePair("password", "Tes+2015")
                )))))
                .andExpect(status().isFound())
                .andReturn();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("\n=====>>> authentication : "+authentication);*/

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createAccount_CreateAccountExistingClient_isCreated() throws Exception
    {
        // create account
        List <Account> tempListAccount;
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),0);
        logger.info("testMail : "+testMail);
        accountController.createAccount(null, testMail);
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),1);
        Account currentAccount = accountRepository.findByClientClientMail(testMail)
                .stream()
                .findFirst()
                .orElse(null);

        logger.info("currentAccount.getClient().getClientMail() : "+currentAccount.getClient().getClientMail());

        assertEquals(testMail.toString(),currentAccount.getClient().getClientMail().toString());
    }

    @Test
    void createAccount_CreateAccountForNonExistingClient_Error() throws Exception
    {
        //
        List <Account> tempListAccount;
        tempListAccount = accountRepository.findByClientClientMail("soufiene.mail_test@gmail.com");
        assertEquals(tempListAccount.size(),0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            accountController.createAccount(null, "soufiene.mail_testNonExisting@gmail.com");
        });

        String expectedMessage = "No client for this mail";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        tempListAccount = accountRepository.findByClientClientMail("soufiene.mail_test@gmail.com");
        assertEquals(tempListAccount.size(),0);
    }


    @Test
    void createAccount_CreateManyAccountForTheSameExistingClient_isCreated() throws Exception
    {
        //
        List <Account> tempListAccount;
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),0);
        accountController.createAccount(null, testMail);
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),1);
        accountController.createAccount(null, testMail);
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),2);

    }

    @Test
    @Sql(scripts = "/dumpingTestData.sql")
    void deleteAccount() {
        List <Account> tempListAccount;
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),0);
        accountController.createAccount(null, testMail);
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),1);

        Account currentAccount = accountRepository.findByClientClientMail(testMail)
                .stream()
                .findFirst()
                .orElse(null);

        accountController.deleteAccount(currentAccount.getAccountId());
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),0);

    }

    @Test
    //SOUE
    @Async
    //@Sql(scripts = "/dumpingTestData.sql")

    @WithMockUser(username = "soufiene.mail_01@gmail.com", roles ={"EMPLOYEE"})
    void findPaginated() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("\n=====>>> authentication : "+authentication);

/*        MvcResult mvcResult = accountControllerMockMvc.perform(MockMvcRequestBuilders.post("/authenticateTheUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", testMail),
                        new BasicNameValuePair("password", "Tes+2015")
                )))))
                .andExpect(status().isFound())
                .andReturn();*/

        authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("\n=====>>> authentication : "+authentication);

        Session currentSession = entityManager.unwrap(Session.class);

        Filter filter = currentSession.enableFilter("accountFilter");

        /*

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("currentUserName-AccountServiceImpl : "+currentUserName);
            //filter.setParameter("userMail", currentUserName );
            filter.setParameter("userMail", currentUserName );
        }
        //return this.accountRepository.findAll(pageable);
        else{
            filter.setParameter("userMail", "" );
            logger.info("\n=====>>> Fail identifying userMail");
        }
*/

        List <Account> tempListAccount;
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),0);
        accountController.createAccount(null, testMail);
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),1);
        accountController.createAccount(null, testMail);
        tempListAccount = accountRepository.findByClientClientMail(testMail);
        assertEquals(tempListAccount.size(),2);

        this.accountControllerMockMvc.perform(MockMvcRequestBuilders.get("/account/page/1?sortField=accountId&sortDir='desc'")
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


}