package com.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mail.SendMail;
import com.qrcode.Create_QR;
import com.user.DBconn;

/**
 * Servlet implementation class Accept
 */
@WebServlet("/Accept")
public class Accept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accept() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailid = request.getParameter("emailid");
		PrintWriter pw = response.getWriter();
		try {
			Connection con = (Connection) DBconn.conn();
			int flag=0;
			String Username = "";
			String msg="Accept Request";
			Statement stRegister12=(Statement) con.createStatement();
			 ResultSet rsLogin12=stRegister12.executeQuery("select * from updateprofile where emailid='" +emailid+ "'");
			 if(rsLogin12.next())
			 {
				 Username=rsLogin12.getString("firstname");
			 }
			Statement stRegister1=(Statement) con.createStatement();
			 ResultSet rsLogin1=stRegister1.executeQuery("select * from accept where emailid='" +emailid+ "'");
			 if(rsLogin1.next())
			 {
				 flag=1;
			 }
			 System.out.println("Flag=>"+flag);
			 if(flag==1)
			 {
				 Statement stRegister11=con.createStatement();
				stRegister11.executeUpdate("update accept set status='"+msg+"' where emailid='" +emailid+ "'");
				Statement stRegister101=con.createStatement();
				String acc="1";
				stRegister101.executeUpdate("update updateprofile set Status_Info='"+acc+"' where emailid='" +emailid+ "'");
			 }
			 else{
		    String sql="insert into accept(emailid,status) values(?,?)";
			PreparedStatement p=(PreparedStatement) con.prepareStatement(sql);
			p.setString(1,emailid);
			p.setString(2, msg);
			
			p.executeUpdate();
			Statement stRegister101=con.createStatement();
			String acc="1";
			stRegister101.executeUpdate("update updateprofile set Status_Info='"+acc+"' where emailid='" +emailid+ "'");
			
			
			
			 }
			 File finalpath = new File(DBconn.filepath, Username);
				finalpath.mkdir();
				String path=DBconn.filepath+"\\"+Username+"\\"+Username+".png";
				System.out.println(path);
				//creating QR code
				
				Create_QR.CreateQR(emailid,path);
				SendMail.SendImage(emailid,path);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		pw.println("<html><script>alert('Status Update Successfully');</script><body>");
		pw.println("");
		pw.println("</body></html>");
		//RequestDispatcher rd = request.getRequestDispatcher("/DataShow.jsp");
		//rd.include(request, response);
		response.sendRedirect("StudentDataShow.jsp?Done");
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
