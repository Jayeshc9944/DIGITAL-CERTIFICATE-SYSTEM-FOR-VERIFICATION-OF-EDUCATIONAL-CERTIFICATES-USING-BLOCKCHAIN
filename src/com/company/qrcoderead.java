package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smartcontract.TimeLookup;

import com.qrcode.Read_QR;
import com.user.DBconn;

@WebServlet("/qrcoderead")
public class qrcoderead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String tenfilepath = null;
	private static final int BUFFER_SIZE = 4096;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public qrcoderead() {
        super();
        // TODO Auto-generated constructor stub
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String emailid = request.getParameter("emailid");
			System.out.println(emailid);
			Connection con = (Connection) DBconn.conn();
			String sql1 = "SELECT * FROM tblqrupdateprofile where emailid = ?";
			PreparedStatement stt1 = con.prepareStatement(sql1);
			stt1.setString(1, emailid);
			String tenmsg = "10th.png";
			Blob blob;
			ResultSet result1 = stt1.executeQuery();
			if (result1.next()) {
				blob = result1.getBlob("file_data");
				InputStream inputStream = blob.getBinaryStream();
				// writes the file to the client
				String relativeWebPath = "/output/" + tenmsg;
				String absoluteDiskPath = getServletContext().getRealPath(
						relativeWebPath);
				File f = new File(absoluteDiskPath);
				tenfilepath = f.getAbsolutePath().toString();
				OutputStream outStream = new FileOutputStream(f);
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
				
				// smart contract check
				int timeflag=0;
				String Contract_Data="";
				String EndTime_Info="";
				String Current_Date_Info="";
				String sql = "SELECT * FROM tblmastersmartcontracts where EmailId = ?";
				PreparedStatement stt = con.prepareStatement(sql);
				stt.setString(1, emailid);
				ResultSet rs = stt.executeQuery();
				if (rs.next())
				{
					Contract_Data=rs.getString("Contract_Data");
					EndTime_Info=rs.getString("EndTime_Info");
					String[] contract=Contract_Data.split("#");
					int minu=Integer.valueOf(contract[0].toString());
					if(minu==5)
					{
						timeflag=TimeLookup.timer5min(emailid,Contract_Data);
					}
					if(minu==10)
					{
						timeflag=TimeLookup.timer10min(emailid,Contract_Data);
					}
					if(minu==15)
					{
						timeflag=TimeLookup.timer15min(emailid,Contract_Data);
					}
					else if(minu==20)
					{
						timeflag=TimeLookup.timer20min(emailid,Contract_Data);
					}
					else if(minu==25)
					{
						timeflag=TimeLookup.timer25min(emailid,Contract_Data);
					}
					else 
					{
						timeflag=TimeLookup.timer30min(emailid,Contract_Data);
					}
					
				}
				System.out.println("Time=>"+timeflag);
				if(timeflag==0)
				{
				String data=Read_QR.readQR(tenfilepath);
				f.delete();
		    	System.out.println(data);
		    	if(emailid.equalsIgnoreCase(data))
		    	{
		    		response.sendRedirect("StudentShowQR.jsp?emailid="+emailid);
		    	}
		    	else
		    	{
		    		response.sendRedirect("ShowQR.jsp");
		    	}
				}
				else
				{
					System.out.println("1");
					PrintWriter pw=response.getWriter();
					 pw.println("<script> alert('Smart Contract Expiry');</script>");
					 response.sendRedirect("ShowQR.jsp?msg=1");
				}
			} 
			

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
