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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</head>
<body>
	<header>		
		<div class="container">
			<a class="logo" href="index.html"><img src="images/logo-black.png" alt="Logo"></a>
			<a class="menu-nav-icon" data-menu="#main-menu" href="#"><i class="ion-navicon"></i></a>
			<ul class="main-menu" id="main-menu">
				<li><a href="CHome.jsp">Home</a></li>
				<li><a href="ShowQR.jsp" class="current">Show QR-Code</a></li>
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
						<td><img src="img/admin.jpg" width="10" height="240">
					</tr>
					<tr>
						<td colspan="2" align="center"><b>Home Page</b></td>
					</tr>
					<tr>
						<td align="center">(by Best Company)</td>
					</tr>
            	</table>			
								
						<% 
						   String msg=request.getParameter("msg");
						   int flag=0;
						   if(msg==null)
						   {
							   flag=0;   
						   }
						   else if(msg.equals("1"))
						   {
							   flag=1;
						   }
						   else{
							   flag=0;
						   }
						   if(flag==0)
						   {
						   String emailid="";
						   Connection con = DBconn.conn();
						   String imgName=null;
						   Statement st1 = (Statement) con.createStatement();
						   String str = "select * from tblqrupdateprofile ";
						   ResultSet rs = (st1).executeQuery(str);
						   while(rs.next()) 
						   {
						   	imgName=rs.getString("file_path");
						    emailid=rs.getString("emailid");
						   
						   try{
						   	    BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
						         ByteArrayOutputStream baos = new ByteArrayOutputStream();
						         BufferedImage result = new BufferedImage(
						         		bImage.getWidth(),
						         		bImage.getHeight(),
						                BufferedImage.TYPE_INT_RGB);
						         result.createGraphics().drawImage(bImage, 0, 0, Color.PINK, null);
						         ImageIO.write( result, "jpg", baos );
						         baos.flush();
						         byte[] imageInByteArray = baos.toByteArray();
						         baos.close();                                   
						         String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
						         %>
							
							<table align="center" border="3"  cellpadding="5" cellspacing="5">
								
								<tr align="center">
									<td><b>UserName: </b></td>
								</tr>
								<tr >
									<td><a href="qrcoderead?emailid=<%= emailid %>"><%= emailid %></a></td>
								</tr>
								<tr align="center">
									<td><b>QR Code: </b></td>
								</tr>
								<tr align="center">
									<td><a href="qrcoderead?emailid=<%= emailid %>"><img style="width: 150px; height: 150px" src="data:image/jpg;base64, <%=b64%>"/></a></td>
								</tr>
							<% 
							     }catch(IOException e){
							       System.out.println("Error: "+e);
							     } 
							   }
							   }
							   else
							   {
							%>
								   <script type="text/javascript">
									alert("Smart Contract Expiry");
								   </script>
							<%
							   } 
					     	%>	
						</table>
						
					</div>
				</div>
				
				<div class="col-sm-12 col-md-4">
					<img src="images/crypto-news-4-600x450.jpg" alt="">
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