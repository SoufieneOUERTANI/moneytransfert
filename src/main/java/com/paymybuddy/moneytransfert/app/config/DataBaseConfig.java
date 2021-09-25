package com.paymybuddy.moneytransfert.app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    @Value("${spring.datasource.driver-class-name}")
    private String springDatasourceDriverClassName;

    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @Value("${spring.datasource.username}")
    private String springDatasourceUsername;

    @Value("${spring.datasource.password}")
    private String springDatasourcePassword;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        logger.info("Create DB connection");

        Class.forName(springDatasourceDriverClassName);
        return DriverManager.getConnection(
                springDatasourceUrl,springDatasourceUsername,springDatasourcePassword);

    }

    /*

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }

    */

}
