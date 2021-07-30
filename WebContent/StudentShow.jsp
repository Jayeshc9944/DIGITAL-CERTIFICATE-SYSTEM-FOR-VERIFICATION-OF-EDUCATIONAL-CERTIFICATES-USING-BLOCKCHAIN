<%@ page import="java.sql.*" %>
<%@page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.user.*" %>
<%@page import="javax.sql.*" %>
<%@page import=" java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@page import="javax.sql.*" %>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.math.BigInteger"%>
<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.*" %>
<%@page import=" java.io.PrintWriter" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<title>BlockChain</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Encode+Sans+Expanded:400,600,700" rel="stylesheet">
	<link href="plugin-frameworks/bootstrap.css" rel="stylesheet">
	<link href="fonts/ionicons.css" rel="stylesheet">
	<link href="common/styles.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="demo.css" />
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
    	$('#examples').DataTable( {
        "pagingType": "full_numbers"
	    } );
	} );
</script>
<style>
.rcorners3 { 
  border-radius: 15px 50px 30px;
  background: url(./img/paper.gif);
  padding: 20px; 
  width: 200px;
  height: 150px;
}
</style>
</head>
<body>
	<%
	if(request.getParameter("Update")!=null)
	{
	out.println("<script>alert('Status Update Successfully...!')</script>");	
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
				<li><a href="StudentShow.jsp">Student Information</a></li>		
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
				<table align="center">
					<tr>
						<td colspan="2"><b><%=session.getAttribute("Uusername")%>'s Profile Details</b></td>
					</tr>
					<tr>
						<td colspan="2"><div class="cleaner" style="height: 20%">&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
					</tr>
            	</table>
	
		        <%
					  try{
						  String emailid=(String)session.getAttribute("userid");
						  int i=1;
						  String query="select * from updateprofile where emailid='"+emailid+"'";
						  Connection conn=DBconn.conn();
						  Statement stmt=conn.createStatement();
						  ResultSet rs=stmt.executeQuery(query);
					    	while(rs.next()){
					    		{
					    		%>
						<table align="center" class="rcorners3" cellpadding="10" cellspacing="10">  	
					    	<tr>
						    	<td><b>First Name</b></td>
						    	<td><%=rs.getString(2) %></td>
						    	<td><b>Last Name</b></td>
							    <td><%=rs.getString(4) %></td>
							    <td rowspan="3" colspan="3"><img src="img/user-male.png" style="width:180px; height:185px"></td>
					    	</tr>
					    	<tr>
					    		<td><b>Address</b></td>
							   	<td><%=rs.getString(5) %></td>
					    		<td><b>Gender</b></td>
							    <td><%=rs.getString(6) %></td>
							</tr>
					    	<tr>
					    		<td><b>Mobile No</b></td>
							   	<td><%=rs.getString(7) %></td>
					    		<td><b>DOB</b></td>
							    <td><%=rs.getString(8) %></td>
							</tr>
							<tr>
								<td><b>10th</b></td>
						    	<td><b>Marksheet_No</b></td>
						    	<td><%=rs.getString(11) %></td>
						    	<td><b>Percentage</b></td>
						    	<td><%=rs.getString(10) %></td>
						    	<td><b>Passing-Year</b></td>
						    	<td><%=rs.getString(12) %></td>
							</tr>
							<tr>
								<td><b>12th</b></td>
						    	<td><b>Marksheet_No</b></td>
						    	<td><%=rs.getString(15) %></td>
						    	<td><b>Percentage</b></td>
						    	<td><%=rs.getString(14) %></td>
						    	<td><b>Passing-Year</b></td>
						    	<td><%=rs.getString(16) %></td>
							</tr>
							<tr>
								<td><b>BE</b></td>
						    	<td><b>Marksheet_No</b></td>
						    	<td><%=rs.getString(19) %></td>
						    	<td><b>Percentage</b></td>
						    	<td><%=rs.getString(18) %></td>
						    	<td><b>Passing-Year</b></td>
						    	<td><%=rs.getString(20) %></td>
							</tr>
					 
					    	<%}
					    		i++;
					    		
					    		} %>
					    	<% 
					    	}
					    	catch (Exception e) {
					    		e.printStackTrace();
					    	}%>
            
			        </tbody>
			    </table>                     
			  </div>
			</div>
				
				<div class="col-sm-12 col-md-4"><br><br>
					<img src="images/banner-1-600x450.jpg" alt="">
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