package smartcontract;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.JavaX;

import com.user.DBconn;


public class TimeLookup {

	public static int timeflag=0;
	
	public static int timer5min(String EmailId, String Contract_Data) {
		try {
System.out.println("EmailId=>"+EmailId+"\tDuration=>"+Contract_Data);
			String endTimeStr = "", dbdate = "";
			Connection connection = DBconn.conn();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblmastersmartcontracts  where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "' order by S_ID desc");
			if (resultset.next()) {
				endTimeStr = resultset.getString("EndTime_Info");
				dbdate = resultset.getString("Current_Date_Info");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(endTimeStr);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 5);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Date working" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Date working");
					String startTimeStr = currenttime; // Getting start time

					//String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + endTimeStr);
					compareTimeJava8(startTimeStr, endTimeStr, EmailId, Contract_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "'";
					st001.executeUpdate(queryString001);
				}
			} else {

			}
		} catch (ParseException | SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	public static int timer10min(String EmailId,String Contract_Data) {
		try {
System.out.println("EmailId=>"+EmailId+"\tDuration=>"+Contract_Data);
			String StartTime = "", dbdate = "";
			Connection connection = DBconn.conn();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblmastersmartcontracts  where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("EndTime_Info");
				dbdate = resultset.getString("Current_Date_Info");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 10);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Date working" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Date working");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, EmailId, Contract_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "'";
					st001.executeUpdate(queryString001);
				}
			} else {

			}
		} catch (ParseException | SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	
	
	
	
	public static int timer15min(String EmailId,
			String Contract_Data) {
		try {
			System.out.println("EmailId=>"+EmailId+"\tDuration=>"+Contract_Data);
			String StartTime = "", dbdate = "";
			Connection connection = DBconn.conn();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblmastersmartcontracts  where EmailId='"
							+ EmailId
							+ "'and  Contract_Data='" + Contract_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("EndTime_Info");
				dbdate = resultset.getString("Current_Date_Info");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 15);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Current Date working=>" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("Start Time=>" + startTimeStr
							+ "\t End Time=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, EmailId, Contract_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "'";
					st001.executeUpdate(queryString001);
				}
			} else {

			}
		} catch (ParseException | SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	public static int timer20min(String EmailId,
			String Contract_Data) {
		try {
			JavaX.initTransaction();
			System.out.println("EmailId=>"+EmailId+"\tDuration=>"+Contract_Data);
			String StartTime = "", dbdate = "";
			Connection connection = DBconn.conn();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblmastersmartcontracts  where EmailId='"
							+ EmailId
							+ "'and  Contract_Data='" + Contract_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("EndTime_Info");
				dbdate = resultset.getString("Current_Date_Info");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 20);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Date working" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Date working");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, EmailId, Contract_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
							+ EmailId
							+ "' and  Contract_Data='" + Contract_Data + "'";
					st001.executeUpdate(queryString001);
				}
			} else {

			}
		} catch (ParseException | SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	public static int timer25min(String EmailId, 
			String Contract_Data) {
		try {
			// int flag=0;
			String StartTime = "", dbdate = "";
			Connection connection = DBconn.conn();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblmastersmartcontracts  where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("EndTime_Info");
				dbdate = resultset.getString("Current_Date_Info");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 25);
				dNow = cal.getTime();
				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Expiry");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					// String startTimeStr ="09" + ":" + "59" + ":"+ "30";
					// String endTimeStr = "09" + ":" + "31" + ":"+ "30";

					compareTimeJava8(startTimeStr, endTimeStr, EmailId, Contract_Data);

				}// if end

				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
							+ EmailId
							+ "' and  Contract_Data='" + Contract_Data + "'";
					st001.executeUpdate(queryString001);

				}
			} else {

			}
		} catch (ParseException | SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		}
		return timeflag;
	}
	public static int timer30min(String EmailId, 
			String Contract_Data) {
		try {
			// int flag=0;
			JavaX.initTransaction();
			String StartTime = "", dbdate = "";
			Connection connection = DBconn.conn();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblmastersmartcontracts  where EmailId='"
							+ EmailId
							+ "'and Contract_Data='" + Contract_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("EndTime_Info");
				dbdate = resultset.getString("Current_Date_Info");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 30);
				dNow = cal.getTime();
				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Expiry");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					// String startTimeStr ="09" + ":" + "59" + ":"+ "30";
					// String endTimeStr = "09" + ":" + "31" + ":"+ "30";

					compareTimeJava8(startTimeStr, endTimeStr, EmailId, Contract_Data);

				}// if end

				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
							+ EmailId
							+ "' and  Contract_Data='" + Contract_Data + "'";
					st001.executeUpdate(queryString001);

				}
			} else {

			}
		} catch (ParseException | SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		}
		return timeflag;
	}

	
	
	public static void compareTimeJava8(String startTimeStr, String endTimeStr,
			String EmailId,String Contract_Data) {

		LocalDate today = LocalDate.now();
		JavaX.initTransaction();
		String startTimeStrT = today + " " + startTimeStr;
		String endTimeStrT = today + " " + endTimeStr;

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");

		try {
			Connection connection = DBconn.conn();

			LocalDateTime startTime = LocalDateTime.parse(startTimeStrT,
					formatter);
			// System.out.println(startTime);
			LocalDateTime endTime = LocalDateTime.parse(endTimeStrT, formatter);

			Duration d = Duration.between(startTime, endTime);
			long minutes = TimeUnit.SECONDS.toMinutes(d.getSeconds());
			System.out.println("Second" + d.getSeconds() + "\tMinutes"
					+ minutes);
			if (d.getSeconds() == 0) {
				Statement st001 = connection.createStatement();
				String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
						+ EmailId
						+ "'  and Contract_Data='" + Contract_Data + "'";
				st001.executeUpdate(queryString001);
				System.out.println("Both Start time and End Time are equal");
			} else if (d.getSeconds() > 0) {
				System.out.println("Start time is less than end time");
				timeflag=0;
			} else {
				Statement st001 = connection.createStatement();
				String queryString001 = "update tblmastersmartcontracts set Status_U='Expiry' where EmailId='"
						+ EmailId
						+ "' and Contract_Data='" + Contract_Data + "'";
				st001.executeUpdate(queryString001);
				System.out.println("Start time is greater than end time");
				timeflag=1;
			}

		} catch (DateTimeParseException | SQLException | ClassNotFoundException e) {
			System.out.println("Invalid Input" + e.getMessage());

		}

	}

	public static void main(String args[]) {

	}
}
