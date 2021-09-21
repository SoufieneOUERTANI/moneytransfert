package com.paymybuddy.moneytransfert.login.service.impl;

import com.paymybuddy.moneytransfert.login.dao.RoleDao;
import com.paymybuddy.moneytransfert.login.entity.Role;
import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(value = "classpath:application-test.properties")
class UserServiceImplTest {

    private static final Logger logger = LogManager.getLogger("UserServiceImplTest");

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleDao roleDao;


    @BeforeEach
    void setUp() {
        Role role_employee = new Role("ROLE_EMPLOYEE");
        roleDao.save(role_employee);
        Role role_manager = new Role("ROLE_MANAGER");
        roleDao.save(role_manager);
        Role role_admin = new Role("ROLE_ADMIN");
        roleDao.save(role_admin);
        logger.info("roleDao.findAll() : "+ roleDao.findAll());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUserName() {
    }

    @Test
    void save_ValidNewUser_CreatedWithRole() {
        NewUser newUser = new NewUser("userName", "password", "password", "firstName","lastName", "email");
        User user = userService.save(newUser);
        assertNotNull(user);
        logger.info(user.toString());
        User tempUser = new User("userName", "password", "firstName","lastName", "email", Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
        logger.info(tempUser.toString());

        /*
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id","password")
                .build();
        Assertions.assertThat(user).usingRecursiveComparison(configuration).ignoringFields("id", "password").isEqualTo(tempUser);
        */

        Assertions.assertThat(user).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(tempUser);

    }

    @Test
    void save_UnValidNewUser_ThrowException() {
        NewUser newUser = new NewUser("userName", "password", "NotMatchingpassword", "firstName","lastName", "email");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.save(newUser);;
        });

        String expectedMessage = "La confirmation du mot de passe n'est pas identique";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void loadUserByUsername() {
    }


}