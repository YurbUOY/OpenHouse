package com.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/ValidateLogin")
public class ValidateLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ValidateLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parName = request.getParameter("username");
        String parPw = request.getParameter("password");
    	
    	DBConnection db = new DBConnection();
        Connection conn = null;

        try {
        	conn = db.getConnection();
            Statement stm = conn.createStatement();
            
            String sql = "select name, password from user where name = '"+parName+"' and password = '"+parPw+"';";
            
            ResultSet results = stm.executeQuery(sql);
            
            PrintWriter writer = response.getWriter();
            
			if (results.next()) {
				writer.write("true");
			} else {
				writer.write("false");
			}
			
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        };
    }
}