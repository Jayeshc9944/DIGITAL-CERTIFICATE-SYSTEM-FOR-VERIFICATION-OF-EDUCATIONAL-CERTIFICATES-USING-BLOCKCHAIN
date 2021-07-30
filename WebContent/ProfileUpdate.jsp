<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.user.*"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<title>Blockchain</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Encode+Sans+Expanded:400,600,700" rel="stylesheet">
	<link href="plugin-frameworks/bootstrap.css" rel="stylesheet">
	<link href="fonts/ionicons.css" rel="stylesheet">
	<link href="common/styles.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="demo.css" />
<style>
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}
.button1 {
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
  border-radius: 12px;
}
.button1:hover {
  background-color: #4CAF50;
  color: white;
}
.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
  border-radius: 12px;
}
.button2:hover {
  background-color: #008CBA;
  color: white;
}
</style>
</head>
<body>
	<%
	if(request.getParameter("succ")!=null)
	{
	out.println("<script>alert('Congratulations....Login Successfull...!')</script>");	
	}
   %>
	<header>		
		<div class="container">
			<a class="logo" href="index.html"><img src="images/logo-black.png" alt="Logo"></a>
			<a class="menu-nav-icon" data-menu="#main-menu" href="#"><i class="ion-navicon"></i></a>
			<ul class="main-menu" id="main-menu">
				<li><a href="Home.jsp" >Home</a></li>
				<li><a href="ProfileUpdate.jsp" class="current">Profile Update</a></li>
				<li><a href="QRCode_details.jsp">QRCode Upload</a></li>		
				<!-- <li><a href="AddLogin" class="current-demo">Logout</a></li> -->
				<li><a href="index.jsp?logout">Logout</a></li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</header>

	<section class="ptb-0">
	<div class="mb-30 brdr-ash-1 opacty-5"></div>
		<div class="container"><br><br><br>
		 <div class="row">
		  <div class="col-sm-12 col-md-8">
			<div class="row">			
			
			<form action="AddProUpdate" method="post" class="form-block form-bold form-mb-20 form-h-35 form-brdr-b-grey pr-50 pr-sm-0">
			<%
                 String emailid=(String)session.getAttribute("userid");
                 String mobileno=(String)session.getAttribute("mobile"); 
                 String First_Name="";
                 String Middle_Name="";
                 String Last_Name="";
                 String Address="";
                 String Gender="";
                 String DOB="";
                 try{
                	 System.out.println("Email=>"+emailid);
       				int i=1;
       				int flag=0;
       				String query="select * from updateprofile where emailid='"+emailid+"'";
       				Connection conn=DBconn.conn();
       				Statement stmt=conn.createStatement();
       				ResultSet rs=stmt.executeQuery(query);
       				if(rs.next())
       				{
       					flag=1;
       					First_Name=rs.getString(2);
                        Middle_Name=rs.getString(3);
                        Last_Name=rs.getString(4);
                        Address=rs.getString(5);
                        Gender=rs.getString(6);
                        DOB=rs.getString(8);
                        
       				}
       				else
       				{
       					flag=0;	
       				}
       				System.out.println("Flag=>"+flag);
                        if(flag==1)
                    {%>
			
				<table align="center" border="1"  cellpadding="5" cellspacing="5">
					 <tr>
						<td><b>Email Id</b></td>
						<td><input type="text" name="emailid" value=<%=emailid %> required="required" readonly="readonly" placeholder="myusername or mymail@mail.com"/></td>
						<td><b>First Name</b></td>
						<td><input type="text" name="firstname" value="<%=First_Name %>" readonly="readonly" required /></td>
					</tr>
					<tr>
						<td><b>Middle Name</b></td>
						<td><input type="text" name="middlename" value="<%=Middle_Name %>" readonly="readonly" placeholder="eg. Abc" required /></td>
						<td><b>Last Name</b></td>
						<td><input type="text" name="lastname" value="<%=Last_Name %>" readonly="readonly" required /></td>
					</tr>
					<tr>
						<td><b>Address</b></td>
						<td><input type="text" name="address" value="<%=Address %>" readonly="readonly" placeholder="Enter Address" required /></td>
						<td><b>Gender</b></td>
						<td><input type="text" name="gender" value="<%=Gender %>" readonly="readonly" required /></td>
					</tr>
					<tr>
						<td><b>Mobile Number</b></td>
						<td><input type="text" name="mobileno" value=<%=mobileno %> required="required" placeholder="eg.1234567890"/></td>
						<td><b>DOB</b></td>
						<td><input type="text" name="DOB" required value="<%=DOB %>" readonly="readonly"/></td>
					</tr>
					<tr>
				     	<td colspan="2" align="center"><input type="submit" value="Next" style="height:50px;width:105px" class="button button1" /></td>
				    	<td colspan="2" align="center"><input type="reset" value="Reset" style="height:50px;width:105px" class="button button2" /></td>
				    </tr>
				</table>
				   <%
                       }   
                        else
                        {
                    %>
                        	  	
                    <table align="center" border="1"  cellpadding="5" cellspacing="5">
					 <tr>
						<td><b>Email Id</b></td>
						<td><input type="text" name="emailid" value=<%=emailid %> required="required" readonly="readonly" placeholder="myusername or mymail@mail.com"/></td>
						<td><b>First Name</b></td>
						<td><input type="text" name="firstname"  pattern="[A-Za-z].{1,}" title="Minimum Two  Characters  are Required For First Name" required /></td>
					</tr>
					<tr>
						<td><b>Middle Name</b></td>
						<td><input type="text" name="middlename"  pattern="[A-Za-z].{1,}" title="Minimum Two  Characters  are Required For First Name"  placeholder="eg. Abc" required /></td>
						<td><b>Last Name</b></td>
						<td><input type="text" name="lastname" pattern="[A-Za-z].{1,}" title="Minimum Two  Characters  are Required For First Name"  required /></td>
					</tr>
					<tr>
						<td><b>Address</b></td>
						<td><input type="text" name="address" placeholder="Enter Address" required /></td>
						<td><b>Gender</b></td>
						<td>
						<select style="width: 100%; height: 30px"
										id="gender" name="gender" required="required">
											<option selected="selected">Select Gender</option>
											<option value="Male">Male</option>
											<option value="Female">Female</option>
											<!-- <option value="Insurance">Insurance</option> -->
									</select>
						</td>
					</tr>
					<tr>
						<td><b>Mobile Number</b></td>
						<td><input type="text" name="mobileno" value=<%=mobileno %> readonly="readonly" required="required" placeholder="eg.1234567890"/></td>
						<td><b>DOB</b></td>
						<td><input type="date" name="DOB" required  /></td>
					</tr>
					<tr>
				     	<td colspan="2" align="center"><input type="submit" value="Next" style="background: yellow;color: black;" class="button"/></td>
				    	<td colspan="2" align="center"><input type="reset" value="Reset" style="background: yellow;color: black;" class="button"/></td>
				    </tr>
				</table>
                        <%
                        }
                      }
					  catch (Exception e) {
					   e.printStackTrace();
					 }
                   %>
			</form>	
			</div>
		</div>
				
		<div class="col-sm-12 col-md-4">
				<h3 align="center"><b>Profile Update</b></h3>
				<h4 align="center">(by Best Student)</h4>
					<img src="img/users.jpg" alt="">
				</div>
			</div>
		</div>
	</section><br><br><br><br><br><br>
	
	<footer class="bg-191 color-ccc">
			<div class="brdr-ash-1 opacty-2"></div>
			<div class="oflow-hidden color-ash font-9 text-sm-center ptb-sm-5"><br>
			<p class="color-ash">
				Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="ion-heart" aria-hidden="true"></i> by <a href="#" target="_blank">Digital Certificate on Blockchain</a>
			</p><br>
			</div>
	</footer>
	<script src="plugin-frameworks/jquery-3.2.1.min.js"></script>	
	<script src="plugin-frameworks/tether.min.js"></script>	
	<script src="plugin-frameworks/bootstrap.js"></script>	
	<script src="common/scripts.js"></script>	
</body>
</html>
     