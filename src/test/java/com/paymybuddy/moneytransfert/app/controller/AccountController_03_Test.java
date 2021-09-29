package com.paymybuddy.moneytransfert.app.controller;

import com.paymybuddy.moneytransfert.MoneytransfertApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MoneytransfertApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountController_03_Test {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void viewHomePage() {
    }

    @Test
    void findPaginated() {
    }

    @Test
    void showNewAccountForm() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void showFormForUpdate() {
    }
}