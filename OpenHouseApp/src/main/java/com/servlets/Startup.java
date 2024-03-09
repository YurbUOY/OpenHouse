package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/GetStarted")
public class Startup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Startup() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        InputStream inputStream = getServletContext().getResourceAsStream("/html/StartPage.html");

        OutputStream outputStream = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }

        inputStream.close();
        outputStream.close();
	}
}
