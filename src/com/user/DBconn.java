package com.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconn {

	public static String filepath="K:\\QRCode";
	public static String newfilepath="K:\\QRCode";
	public static String PrevHash1=null,PrevHash2=null,PrevHash3=null,PrevHash4=null;
	public static int blockchain1msg=0,blockchain2msg=0,blockchain3msg=0,blockchain4msg=0;

	public static Connection conn() throws ClassNotFoundException, SQLException
	{
		Connection con;
		 Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecertificateblockchain","root","");
		   

	return con;
	}

	public static Connection connblockchain(String databasename) throws SQLException, ClassNotFoundException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databasename, "root", "");

		return (con);

	}
    public static Connection conn1() throws SQLException, ClassNotFoundException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockchain1", "root", "");

		return (con);

	}
	public static Connection conn2() throws SQLException, ClassNotFoundException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockchain2", "root", "");

		return (con);

	}
	public static Connection conn3() throws SQLException, ClassNotFoundException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockchain3", "root", "");

		return (con);

	}
	public static Connection conn4() throws SQLException, ClassNotFoundException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blockchain4", "root", "");

		return (con);

	}

}
