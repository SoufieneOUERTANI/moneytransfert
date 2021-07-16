package com.paymybuddy.moneytransfert.config;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataBaseConfigTest {

	DataBaseConfig dataBaseConfig;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataBaseConfig = new DataBaseConfig();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void ConnexionDoesNotFail() throws ClassNotFoundException, SQLException {
        Connection con = null;
		con = dataBaseConfig.getConnection();
		assertNotEquals(null, con);
	}

}
