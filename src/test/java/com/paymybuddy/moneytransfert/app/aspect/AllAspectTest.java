package com.paymybuddy.moneytransfert.app.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest
//@AutoConfigureMockMvc

@SpringBootTest
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class AllAspectTest {

    @Test
    void beforeFindPaginatedAccountService() {
    }
}