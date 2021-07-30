<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ page import="java.sql.*" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.user.*"%>
<%@page import="java.sql.Connection"%>
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
  background: url(./img/Paper11.jpg);
  padding: 20px; 
  width: 200px;
  height: 150px;
}
</style>
</head>
<body>
	<%
	if(request.getParameter("Done")!=null)
	{
	out.println("<script>alert('Status Update Successfully...!')</script>");	
	}
   %>
	<header>		
		<div class="container">
			<a class="logo" href="index.html"><img src="images/logo-black.png" alt="Logo"></a>
			<a class="menu-nav-icon" data-menu="#main-menu" href="#"><i class="ion-navicon"></i></a>
			<ul class="main-menu" id="main-menu">
				<li><a href="AdminHome.jsp" class="current">Home</a></li>
				<li><a href="NDataShow.jsp" class="current-demo">Data Show</a></li>
				<li><a href="StudentDataShow.jsp" class="current-demo">Student Data Show</a></li>
				<li><a href="index.jsp?logout">Logout</a></li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</header>

	<section class="ptb-0">
	<div class="mb-30 brdr-ash-1 opacty-5"></div>
		<div class="container" >
		<h3 align="center"><b>View Information:</b></h3>
		 <div >
		  <div  >
			<div >
			
				<div  ><br>
				
				<table align="center" class="rcorners3" cellpadding="10" cellspacing="10"> 	
				<tr>
					<td><b>EmailId:</b></td>
					<td><b>10th (%)</b></td>
					<td><b>Seat No(10th)</b></td>
					<td><b>12th(%)</b></td>
					<td><b>SeatNo(12th)</b></td>
					<td><b>BE (%)</b></td>
					<td><b>Seat No(BE)</b></td>
					<td><b>Action</b></td>
				</tr>
				<%
					  try{
						  int i=1;
						  String action="";
						  String query="select * from updateprofile";
						  Connection conn=DBconn.conn();
						  Statement stmt=conn.createStatement();
						  ResultSet rs=stmt.executeQuery(query);
					    	while(rs.next()){
					    	{
					    		String msg=rs.getString("Status_Info");
					    		if(msg.contains("1"))
					    		{
					    			action="Accept";
					    			
					    		}
					    		else
					    		{
					    			action="Reject";
					    		}
					    		
			%>
			
				 	
				<tr>
					
					<td><%=rs.getString(1) %></td>
				
					
					<td><%=rs.getString(10) %>%</td> 
				
					
					<td><%=rs.getString(11) %></td> 
				
					
					 <td><%=rs.getString(14) %>%</td>
				
					
					<td><%=rs.getString(15) %></td>
				
					
					<td><%=rs.getString(18) %>%</td>
				
					
					<td><%=rs.getString(19) %></td>
				
					<td> <%=action %> </td>
				</tr>
					   
				<%}
					i++;	
				} %>
				<% 
					}
					catch (Exception e) {
					 e.printStackTrace();
				}%>	           
    			</table>
				</div>
			</div>
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