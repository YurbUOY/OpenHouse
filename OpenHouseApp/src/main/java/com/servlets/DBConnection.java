package com.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    private String password = "&JUst3663&";
    private String username = "root";
    private String url = "jdbc:mysql://localhost:3306/OpenHouse";

    public Connection getConnection() throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection != null) {
            System.out.println("Database connection successful");
        } else {
            System.out.println("Failed to make database connection");
        }
        return connection;
    }
}
