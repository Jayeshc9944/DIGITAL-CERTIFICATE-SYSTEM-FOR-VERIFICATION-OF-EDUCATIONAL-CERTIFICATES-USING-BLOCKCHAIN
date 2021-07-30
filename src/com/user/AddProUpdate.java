package com.user;

//import java.io.File;
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
//import javax.servlet.http.HttpSession;
//
//import com.qrcode.Create_QR;

@WebServlet("/AddProUpdate")
public class AddProUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProUpdate() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String emailid = request.getParameter("emailid");
		String firstname = request.getParameter("firstname");
		String middlename = request.getParameter("middlename");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String mobileno = request.getParameter("mobileno");
		String DOB = request.getParameter("DOB");
		//HttpSession session = request.getSession(false);
		try {
			Connection con = DBconn.conn();
			Statement st = (Statement) con.createStatement();
			Statement st1 = (Statement) con.createStatement();
			Statement st2 = (Statement) con.createStatement();
			ResultSet rs;
			String str = "select * from updateprofile where emailid='"
					+ emailid + "'";
			rs = ((java.sql.Statement) st1).executeQuery(str);
			if (rs.next()) {

				st2.executeUpdate("update updateprofile set  address='"
						+ address + "'  where emailid='"
						+ emailid + "' and mobileno='" + mobileno + "' ");

			} else {
				String tenmsg="10th";
				String twelmsg="12th";
				String bemsg="BE";
				String msg="0";
				st.executeUpdate("insert into updateprofile(emailid, firstname, middlename, lastname,address,gender,mobileno,DOB,TenEducation,tenpercentage,tenMarksheet_No,tenPassingYear,TwelEducation,twelpercentage,twelMarksheet_No,twelPassingYear,BEEducation,bepercentage,beMarksheet_No,bePassingYear,tenfile,twelfile,befile,Status_Info) values "
						+ "('"
						+ emailid
						+ "','"
						+ firstname
						+ "','"
						+ middlename
						+ "','"
						+ lastname
						+ "','"
						+ address
						+ "','"
						+ gender
						+ "','"
						+ mobileno
						+ "','"
						+ DOB + "','"+tenmsg+"','"+msg+"','"+msg+"','"+msg+"','"+twelmsg+"','"+msg+"','"+msg+"','"+msg+"','"+bemsg+"','"+msg+"','"+msg+"','"+msg+"','"+msg+"','"+msg+"','"+msg+"','"+msg+"')");

			}
		
			//	String Username = (String) session.getAttribute("Uusername");
			//	File finalpath = new File(DBconn.filepath, Username);
			//	finalpath.mkdir();
			//	String path=DBconn.filepath+"\\"+Username+"\\"+Username+".png";
			//	System.out.println(path);
			
			PrintWriter pw=response.getWriter();
			 pw.println("<script> alert(' Update Profile Successfuly');</script>");
		} catch (Exception e) {
			System.out.println(e);
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/Educational_details.jsp");
		//rd.include(request, response);
		response.sendRedirect("Educational_details.jsp?update");
	}
}
