package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.DBconn;

@WebServlet("/AddRegister")
public class AddRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String MbNo = request.getParameter("MbNo");
		String role=request.getParameter("roll");

		try {
			Connection con = DBconn.conn();
			
			if(role.equalsIgnoreCase("Company"))
			{
				Statement st1 = con.createStatement();
				String str = "select * from companyregistertbl where Cemail='" + email + "'";
				ResultSet rs1 = ((java.sql.Statement) st1).executeQuery(str);
				if (rs1.next()) {
					pw.println("<script> alert('AllReady Email-ID Existing');</script>");
				} else {
				
				Statement st = (Statement) con.createStatement();
				((java.sql.Statement) st)
						.executeUpdate("insert into companyregistertbl(Cusername, Cemail, Cpassword, CMbNo) values ('"
								+ username
								+ "','"
								+ email
								+ "','"
								+ password
								+ "','" + MbNo + "')");
				
				pw.println("<script> alert(' Register Successfuly');</script>");
				}
				
			}
			else{
			
			Statement st1 = con.createStatement();
			String str = "select * from registertbl where Uemail='" + email + "'";
			ResultSet rs1 = ((java.sql.Statement) st1).executeQuery(str);
			if (rs1.next()) {
				pw.println("<script> alert('AllReady Email-ID Existing');</script>");
			} else {
			
			Statement st = (Statement) con.createStatement();
			((java.sql.Statement) st)
					.executeUpdate("insert into registertbl(Uusername, Uemail, Upassword, UMbNo) values ('"
							+ username
							+ "','"
							+ email
							+ "','"
							+ password
							+ "','" + MbNo + "')");
			String amount="1000";
			Statement stt = (Statement) con.createStatement();
			((java.sql.Statement) stt).executeUpdate("insert into tblsmartrs(emailId, amount) values ('"
							+ email
							+ "','"
							+ amount
							+ "')");
			pw.println("<script> alert(' Register Successfuly');</script>");
			}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		//rd.include(request, response);
		response.sendRedirect("LoginPage.jsp?Done");
	}
}
