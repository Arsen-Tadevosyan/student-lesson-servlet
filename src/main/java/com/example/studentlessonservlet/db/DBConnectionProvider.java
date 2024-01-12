package com.example.studentlessonservlet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private static DBConnectionProvider dbConnectionProvider = null;

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student-lesson";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "rout";

    private DBConnectionProvider()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static DBConnectionProvider getInstance() {
        if (dbConnectionProvider == null) {
            dbConnectionProvider = new DBConnectionProvider();
        }
        return dbConnectionProvider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
