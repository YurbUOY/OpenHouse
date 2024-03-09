package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String us = request.getParameter("Username");
        String em = request.getParameter("Email");
        String pw = request.getParameter("Password");

        DBConnection db = new DBConnection();
        Connection conn = null;

        try {
        	conn = db.getConnection();
            Statement stm = conn.createStatement();
            
            String sql = "INSERT INTO user (name, email, password) VALUES ('"+us+"', '"+em+"', '"+pw+"')";
            
            stm.executeUpdate(sql);
            
            response.setContentType("text/html");
            InputStream inputStream = getServletContext().getResourceAsStream("/html/Home.html");

            OutputStream outputStream = response.getOutputStream();

            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            inputStream.close();
            outputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        };
    }
}