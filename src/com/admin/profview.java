package com.admin;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.user.DBconn;

@WebServlet("/profview")
public class profview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String tenfilepath = null, twelfilepath = "",
			befilepath = "";
	private static final int BUFFER_SIZE = 4096;

	public profview() {
		super();

	}

	public void tenimage(HttpServletRequest request,
			HttpServletResponse response, ResultSet result1) {
		String tenmsg = "10th.png";
		Blob blob;
		try {
			blob = result1.getBlob("tenfile");
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
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void twelimage(HttpServletRequest request1,
			HttpServletResponse response1, ResultSet result1) {
		String tenmsg = "12th.png";
		Blob blob1;
		try {
			blob1 = result1.getBlob("twelfile");
			InputStream inputStream1 = blob1.getBinaryStream();
			// writes the file to the client
			String relativeWebPath = "/output/" + tenmsg;
			String absoluteDiskPath = getServletContext().getRealPath(
					relativeWebPath);
			File f = new File(absoluteDiskPath);
			twelfilepath = f.getAbsolutePath().toString();
			// File f=new File(p);
			OutputStream outStream = new FileOutputStream(f);
			byte[] buffer1 = new byte[BUFFER_SIZE];
			int bytesRead1 = -1;

			while ((bytesRead1 = inputStream1.read(buffer1)) != -1) {
				outStream.write(buffer1, 0, bytesRead1);
			}

			inputStream1.close();
			outStream.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void beimage(HttpServletRequest request,
			HttpServletResponse response, ResultSet result1) {
		String tenmsg = "BE.png";
		Blob blob;
		try {
			blob = result1.getBlob("befile");
			InputStream inputStream = blob.getBinaryStream();
			// writes the file to the client
			String relativeWebPath = "/output/" + tenmsg;
			String absoluteDiskPath = getServletContext().getRealPath(
					relativeWebPath);
			File f = new File(absoluteDiskPath);
			befilepath = f.getAbsolutePath().toString();
			OutputStream outStream = new FileOutputStream(f);
			;// response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			String candidateid = request.getParameter("emailid");
			Connection con = (Connection) DBconn.conn();
			String sql1 = "SELECT * FROM updateprofile where emailid = ?";
			PreparedStatement stt1 = con.prepareStatement(sql1);
			stt1.setString(1, candidateid);

			ResultSet result1 = stt1.executeQuery();
			if (result1.next()) {
				tenimage(request, response, result1);
				twelimage(request, response, result1);
				beimage(request, response, result1);
			} else {
			}
			response.setContentType("multipart/x-mixed-replace;boundary=END");

			// Set the content type based on the file type you need to download
			String contentType = "Content-type: text/rtf";
			File file1 = new File(tenfilepath);
			File file2 = new File(twelfilepath);
			File file3 = new File(befilepath);
			// List of files to be downloaded
			ArrayList<String> files = new ArrayList<String>();
			System.out.println(file1.getAbsolutePath().toString());
			files.add(file1.getAbsolutePath().toString());
			files.add(file2.getAbsolutePath().toString());
			files.add(file3.getAbsolutePath().toString());

			ServletOutputStream out = response.getOutputStream();

			// Print the boundary string
			out.println();
			out.println("--END");

			for (String file : files) {
				File f = new File(file);
				// Get the file
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);

				} catch (FileNotFoundException fnfe) {
					// If the file does not exists, continue with the next file
					System.out.println("Couldfind file " + f.getAbsolutePath());
					continue;
				}

				BufferedInputStream fif = new BufferedInputStream(fis);

				// Print the content type
				out.println(contentType);
				out.println("Content-Disposition: attachment; filename="
						+ f.getName());
				out.println();

				System.out.println("Sending " + f.getName());

				// Write the contents of the file
				int data = 0;
				while ((data = fif.read()) != -1) {
					out.write(data);
				}
				fif.close();

				// Print the boundary string
				out.println();
				out.println("--END");
				out.flush();
				System.out.println("Finisheding file " + f.getName());
			}

			// Print the ending boundary string
			out.println("--END--");
			out.flush();
			out.close();

//			file3.delete();
//			file1.delete();
//			file2.delete();

		} catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().print("SQL Error: " + ex.getMessage());
		}
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}