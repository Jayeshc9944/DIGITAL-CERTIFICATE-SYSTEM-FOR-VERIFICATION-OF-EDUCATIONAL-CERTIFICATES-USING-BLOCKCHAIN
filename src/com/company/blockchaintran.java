package com.company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Blockchain.ChainConsensus;

import com.user.DBconn;

@WebServlet("/blockchaintran")
public class blockchaintran extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public blockchaintran() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session=request.getSession();
		try {

			String fname="",mname="",lname="",mobileno="",DOB="";
			String cmail=(String)session.getAttribute("Cuserid");
			String cmobile=(String)session.getAttribute("Cmobile");
			String Cusername=(String)session.getAttribute("Cusername");
			String candidateid = request.getParameter("emailid");
			
			Connection con = (Connection) DBconn.conn();
			String sql1 = "SELECT * FROM updateprofile where emailid = ?";
			PreparedStatement stt1 = con.prepareStatement(sql1);
			stt1.setString(1, candidateid);

			ResultSet result1 = stt1.executeQuery();
			if (result1.next()) {
				fname=result1.getString("firstname");
				mname=result1.getString("middlename");
				lname=result1.getString("lastname");
				mobileno=result1.getString("mobileno");
				DOB=result1.getString("DOB");
			}
			String finaldata=fname+mname+lname+mobileno+DOB+cmail+Cusername+cmobile;
			System.out.println(finaldata);
			ChainConsensus.ProofOfWork(finaldata);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().print("SQL Error: " + ex.getMessage());
		}
		
		response.sendRedirect("ShowQR.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
