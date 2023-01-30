package com.management.hotel.factory;

import com.management.hotel.constant.ConfigInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnectFactory {
    private static MySqlConnectFactory ourInstance;

    public static MySqlConnectFactory getInstance() {
        if (ourInstance == null) {
            synchronized (MySqlConnectFactory.class) {
                if (ourInstance == null) {
                    ourInstance = new MySqlConnectFactory();
                }
            }
        }
        return ourInstance;
    }

    private static HikariDataSource ds;

    private MySqlConnectFactory() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("Hikari-MySQL-Pool");
        config.setJdbcUrl(ConfigInfo.MYSQL_JDBC_URL);
        config.setUsername(ConfigInfo.MYSQL_USERNAME);
        config.setPassword(ConfigInfo.MYSQL_PASSWORD);
        config.setMaximumPoolSize(ConfigInfo.MYSQL_MAXIMUM_POOL);
        config.setMinimumIdle(ConfigInfo.MYSQL_MINIMUM_IDLE_SIZE);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setAutoCommit(true);

        ds = new HikariDataSource(config);
    }

    public Connection getMySQLConnection() throws SQLException {
        return ds.getConnection();
    }
}
