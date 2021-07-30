package com.profiledata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.qrcode.Create_QR;
import com.user.DBconn;

/**
 * Servlet implementation class QRCodeupload
 */
@WebServlet("/QRCodeupload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class QRCodeupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRCodeupload() {
        super();
        // TODO Auto-generated constructor stub
    }
    String getFileName(Part filePart) {

		for (String cd : filePart.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1);
				// return cd.substring(cd.indexOf('=') + 1).trim().replace("\"",
				// "") ;
			}

		}

		return null;

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String emailid=(String)session.getAttribute("userid");
		Part tenfile=request.getPart("qrcodefile");
		String tenfilename=getFileName(tenfile);
		 InputStream teninputStream = null;
		 try {
				Connection con = DBconn.conn();
				Statement st = (Statement) con.createStatement();
				ResultSet rs;
				String Username=(String)session.getAttribute("Uusername");
				File finalpath = new File(DBconn.filepath, Username);
				finalpath.mkdir();
				String filepath=DBconn.newfilepath+"/"+Username+"/"+Username+".png";
				Create_QR.CreateQR(emailid,filepath);
					teninputStream = tenfile.getInputStream();
					Statement st3=con.createStatement();
					Statement st2=con.createStatement();
					
					ResultSet rs1;
					String str1 = "select * from tblqrupdateprofile where emailid='"
							+ emailid + "'";
					rs1 = ((java.sql.Statement) st3).executeQuery(str1);
					if (rs1.next()) {

						String updateSQL = "update tblqrupdateprofile set file_path=? where emailid=?";
						PreparedStatement pstmt = con.prepareStatement(updateSQL);
						pstmt.setString(2, emailid);
						pstmt.setString(1, filepath);
						
						
						pstmt.executeUpdate();
						

					} else {
					
						String updateSQL = "insert into tblqrupdateprofile(emailid,file_path,file_data) values(?,?,?)";
						PreparedStatement pstmt = con.prepareStatement(updateSQL);
						pstmt.setString(1, emailid);
						pstmt.setString(2, filepath);
						pstmt.setBlob(3, teninputStream);
						
						pstmt.executeUpdate();
							}
					 
					
		
		} catch (Exception e) {
			System.out.println(e);
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
		//rd.include(request, response);
		 response.sendRedirect("Home.jsp?Sent");
	}

}
