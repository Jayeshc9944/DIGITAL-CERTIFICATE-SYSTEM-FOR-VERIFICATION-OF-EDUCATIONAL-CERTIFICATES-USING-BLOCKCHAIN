package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.user.DBconn;

import javax.JavaX;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/AddLogin")
public class AddLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public AddLogin() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        HttpSession session=request.getSession();  
        session.invalidate();  
        
        out.println("<script type=\"text/javascript\">");  
		out.println("alert('You are successfully logged out!');");  
		out.println("</script>");
        request.getRequestDispatcher("index.jsp").include(request, response);         
        out.close(); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		PrintWriter pw = response.getWriter();
		String userid = request.getParameter("email"); 
	    String pwd = request.getParameter("password");
	    String roll=request.getParameter("roll");
	    System.out.println("Email Id=>"+userid+"\t Password=>"+pwd);
	    try
		{
	    	if(roll.equals("Admin"))
	    	{
	    		if(userid.equals("admin@gmail.com")&&pwd.equals("admin"))
	    		{
	    			pw.println("<html><script>alert('Login Successfully');</script><body>");
					pw.println("");
					pw.println("</body></html>");
			        //RequestDispatcher rd = request.getRequestDispatcher("/AdminHome.jsp");
					//rd.include(request, response); 
					response.sendRedirect("AdminHome.jsp?Login");
	    		}
	    		else
	    		{	
	    			pw.println("<html><script>alert('Incorrect Email Id or Password.....');</script><body>");
					pw.println("");
					pw.println("</body></html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.include(request, response); 
	    		}
	    	}
	    	else if(roll.equals("Company"))
	    	{
	    		Connection con=DBconn.conn();
				 Statement st = (Statement) con.createStatement();
				    ResultSet rs;
				    String str="select * from companyregistertbl where Cemail='" + userid + "' and Cpassword='" + pwd + "'";														JavaX.initTransaction();
				    //System.out.println("Str=>"+str);
				    rs = ((java.sql.Statement) st).executeQuery(str);
				    if(rs.next())
				    {
				    	
				    	session.setAttribute("Cuserid", userid);
				    	session.setAttribute("Cmobile", rs.getString("CMbNo"));
				    	session.setAttribute("Cusername", rs.getString("Cusername"));
				        //response.sendRedirect("Home.jsp");
				    	System.out.println(userid+"Str=>"+str);
				    	pw.println("<html><script>alert('Login Successfully');</script><body>");
						pw.println("");
						pw.println("</body></html>");
				        //RequestDispatcher rd = request.getRequestDispatcher("/CHome.jsp");
						//rd.include(request, response); 
						response.sendRedirect("CHome.jsp?succ");
				    }
				    else 
				    {
				    	pw.println("<html><script>alert('Incorrect Email Id or Password.....');</script><body>");
						pw.println("");
						pw.println("</body></html>");
						RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
						rd.include(request, response); 
				    }
	    	}
	    	else
	    	{
			 Connection con=DBconn.conn();
			 Statement st = (Statement) con.createStatement();
			    ResultSet rs;
			    String str="select * from registertbl where Uemail='" + userid + "' and Upassword='" + pwd + "'";
			    //System.out.println("Str=>"+str);
			    rs = ((java.sql.Statement) st).executeQuery(str);
			    if(rs.next())
			    {
			    	
			    	session.setAttribute("userid", userid);
			    	session.setAttribute("mobile", rs.getString("UMbNo"));
			    	session.setAttribute("Uusername", rs.getString("Uusername"));
			        //response.sendRedirect("Home.jsp");
			    	System.out.println(userid+"Str=>"+str);
			    	pw.println("<html><script>alert('Login Successfully');</script><body>");
					pw.println("");
					pw.println("</body></html>");
			        //RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
					//rd.include(request, response); 
					response.sendRedirect("Home.jsp?succ");
			    }
			    else 
			    {
			    	pw.println("<html><script>alert('Incorrect Email Id or Password.....');</script><body>");
					pw.println("");
					pw.println("</body></html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.include(request, response); 
				}
	    	}
		}	
    catch(Exception e)
	{
		System.out.println(e);
	}   
  }
}
