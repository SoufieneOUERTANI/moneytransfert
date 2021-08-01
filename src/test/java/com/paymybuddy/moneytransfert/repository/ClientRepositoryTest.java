package com.paymybuddy.moneytransfert.repository;

import com.paymybuddy.moneytransfert.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest did not work instead of @SpringBootTest
@SpringBootTest
//application-test.properties
@ActiveProfiles("test")
class ClientRepositoryTest {

    Logger logger = Logger.getLogger("ClientRepositoryTest");

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @Sql("classpath:sqlTestFolder/findByClientMail.sql")
    void findByClientMail() {
        assertNotNull(clientRepository.findByClientMail("soufiene_Mail@gmail_2.com"));
    }

    @Test
    @Order(2)
    void save() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Client cl2 = clientRepository.save(new Client("soufiene_Mail@gmail_32.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),""));
        Client cl = new Client("soufiene_Mail@gmail_33.com", "OUERTANI","Soufiene", new Date(dateFormat.parse("13-01-2006").getTime()),"");
        Client cl3 = clientRepository.save(cl);
        assertNotNull(cl2);
        Assertions.assertThat(cl3).usingRecursiveComparison().ignoringFields("clientMail").isEqualTo(cl);
    }

    @Test
    @Order(3)
    @Sql("classpath:sqlTestFolder/deleteByClientMail.sql")
    void deleteByClientMail() {
        Client client = clientRepository.findByClientMail("soufiene_Mail@gmail_3.com");
        assertNotNull(client);
        clientRepository.delete(client);
        assertNull(clientRepository.findByClientMail("soufiene_Mail@gmail_3.com"));
    }

}