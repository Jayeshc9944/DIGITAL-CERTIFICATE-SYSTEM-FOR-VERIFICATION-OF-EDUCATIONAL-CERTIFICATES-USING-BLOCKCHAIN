package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.DBconn;


@WebServlet("/Reject")
public class Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Reject() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailid = request.getParameter("emailid");
		PrintWriter pw = response.getWriter();
		try {
			Connection con = (Connection) DBconn.conn();
			String msg="Reject Request";
			
			Statement st1 = (Statement) con.createStatement();
		    st1.executeUpdate("update accept set status='Reject Request' where emailid='"+emailid+"'");
		    Statement stRegister101=con.createStatement();
			String acc="2";
			stRegister101.executeUpdate("update updateprofile set Status_Info='"+acc+"' where emailid='" +emailid+ "'");
		
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		pw.println("<html><script>alert('Status Update Successfully');</script><body>");
		pw.println("");
		pw.println("</body></html>");
		RequestDispatcher rd = request.getRequestDispatcher("/DataShow.jsp");
		rd.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
