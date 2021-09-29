package com.paymybuddy.moneytransfert.app.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@WebMvcTest
//@AutoConfigureMockMvc
@TestPropertySource(value = "classpath:application-test-MYSQL.properties")
class DataBaseConfigTest {

    @Autowired
    DataBaseConfig dataBaseConfig;

    @Test
    void getConnection() throws SQLException, ClassNotFoundException {
        Connection con;
        con = dataBaseConfig.getConnection();
        assertNotEquals(null, con);
    }

/*    @Test
    void closeConnection() {
    }

    @Test
    void closePreparedStatement() {
    }

    @Test
    void closeResultSet() {
    }*/
}