package com.paymybuddy.moneytransfert.login.controller;

import com.paymybuddy.moneytransfert.app.service.IAccountService;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import com.paymybuddy.moneytransfert.login.config.CustomAuthenticationSuccessHandler;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {LoginController.class})
@AutoConfigureMockMvc
class LoginControllerTest {

    private static final Logger logger = LogManager.getLogger("AccountControllerTestIT");

    @MockBean
    IUserService userService;

    @MockBean
    CustomAuthenticationSuccessHandler CustomAuthenticationSuccessHandler;

/*    @MockBean
    IAccountService accountService;

    @MockBean
    IClientService clientService;*/


    @Value("${currentapplicationproperties}")
    private String currentproperties;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showMyLoginPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/showMyLoginPage"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andReturn();
    }

    @Test
    void showAccessDenied() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/access-denied"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("access-denied"))
                .andReturn();
    }
}