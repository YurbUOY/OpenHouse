package com.servlets;

import java.io.IOException;
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

@WebServlet("/Paperwork")
public class DisplayPaperwork extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayPaperwork() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection db = new DBConnection();
        Connection conn = null;

        try {
        	conn = db.getConnection();
            Statement stm = conn.createStatement();
            
            String sql = "select Name, Description, PaperworkLink, Finished from Paperwork;";
            
            ResultSet results = stm.executeQuery(sql);
            
            response.setContentType("text/html");
            String htmlContent = "<!DOCTYPE html>"
            		+ "<html lang=\"en\">"
            		+ "<head>"
            		+ "    <meta charset=\"UTF-8\">"
            		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
            		+ "    <title>Paperwork Details</title>"
            		+ "    <style>"
            		+ "        body {"
            		+ "            font-family: Arial, sans-serif;"
            		+ "            margin: 0;"
            		+ "            padding: 0;"
            		+ "        }"
            		+ ""
            		+ ""
            		+ "        .navbar {"
            		+ "            background-color: black;"
            		+ "            color: white;"
            		+ "            padding: 10px;"
            		+ "            text-align: center;"
            		+ "        }"
            		+ ""
            		+ "        .navbar a {"
            		+ "            text-decoration: none;"
            		+ "            color: white;"
            		+ "        }"
            		+ ""
            		+ "        h2 {"
            		+ "            text-align: center;"
            		+ "            margin-top: 20px;"
            		+ "            color: #333;"
            		+ "            float: left;"
            		+ "            margin-left: 60px;"
            		+ "        }"
            		+ "        table {"
            		+ "            width: 80%;"
            		+ "            margin: 20px auto;"
            		+ "            border-collapse: collapse;"
            		+ "        }"
            		+ "        th, td {"
            		+ "            border: 1px solid #ddd;"
            		+ "            padding: 8px;"
            		+ "            text-align: left;"
            		+ "        }"
            		+ "        th {"
            		+ "            background-color: #f2f2f2;"
            		+ "            color: #333;"
            		+ "        }"
            		+ "        tr:nth-child(even) {"
            		+ "            background-color: #f9f9f9;"
            		+ "        }"
            		+ "        tr:hover {"
            		+ "            background-color: #f2f2f2;"
            		+ "        }"
            		+ "        a {"
            		+ "            color: blue;"
            		+ "            text-decoration: none;"
            		+ "        }"
            		+ "        a:hover {"
            		+ "            text-decoration: underline;"
            		+ "        }"
            		+ "#addPaperwork {float: right; margin-right: 60px; margin-top: 20px;}"
            		+ "    </style>"
            		+ "</head>"
            		+ "<body>"
            		+ "    <div class=\"navbar\">"
            		+ "        <a href=\"Home\">"
            		+ "            <h1>Open House</h1>"
            		+ "        </a>"
            		+ "    </div>"
            		+ "    <a id=\"addPaperwork\"href=\"/OpenHouseApp/html/AddPaperwork.html\">Add Paperwork</a>"
            		+ "    <h2>Paperwork Details</h2>"
            		+ "    <table>"
            		+ "        <tr>"
            		+ "            <th>Name</th>"
            		+ "            <th>Description</th>"
            		+ "            <th>Finished</th>"
            		+ "        </tr>";
            
            String ending = "</table>"
            		+ "</body>"
            		+ "</html>";
            
			while(results.next()){
				String finished = "No";
				if (results.getBoolean("Finished")) {
					finished = "Yes";
				}
				htmlContent += "<tr>"+
				"<td><a href=\""+results.getString("PaperworkLink")+"\">"+results.getString("Name")+"</a></td>"+
	            "<td>"+results.getString("Description")+"</td>"+
	            "<td>"+finished+"</td></tr>";
			}
			
			htmlContent += ending;
			
			PrintWriter writer = response.getWriter();
			writer.write(htmlContent);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        };
    }
}