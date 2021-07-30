package com.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddAdmin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if(email.equals("admin@gmail.com")&&password.equals("admin"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/AdminHome.jsp");
			rd.include(request, response); 
		}
		else
		{
			
			pw.println("<script> alert(' Wrong UserName and Password');</script>");
			RequestDispatcher rd = request.getRequestDispatcher("/Admin.jsp");
					rd.include(request, response);
		}
		
		}
	}
