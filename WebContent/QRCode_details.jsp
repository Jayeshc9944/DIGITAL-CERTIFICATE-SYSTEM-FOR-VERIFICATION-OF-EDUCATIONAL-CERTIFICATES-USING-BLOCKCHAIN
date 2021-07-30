<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  padding: 10px 10px;
  font-size: 20px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #898988f;
  background-color: yellow;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
  width: 50px;
  height: 50px;
}
.button:hover {background-color: #3e8e41}
.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
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
	if(request.getParameter("Done")!=null)
	{
	out.println("<script>alert('Congratulations....Profle Added Successfully ...!')</script>");	
	}
	if(request.getParameter("invalid")!=null)
	{
	out.println("<script>alert('Invalid Login Credential ...!')</script>");	
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
						<td><img src="img/admin.jpg" width="10" height="240">
					</tr>
					<tr>
						<td colspan="2" align="center"><b>QR Code</b></td>
					</tr>
					<tr>
						<td colspan="2" align="center">(by Best Student)</td>
					</tr>
            	</table>	
            		<%
			            String emailid=(String)session.getAttribute("userid");
			        %>		
						<form class="post_section" action="QRCodeupload" method="post" enctype="multipart/form-data">
							<table align="center" class="rcorners3" cellpadding="10px" cellspacing="10px">
								
								<tr>
									<td colspan="2" align="center"><h3><b>Upload QRCode</b></h3></td>
								</tr>
								
								<tr>
									<td><b>Email_ID:</b></td>
									<td><input type="text" name="Marksheet_No" placeholder="mymail@mail.com" value="<%=emailid%>" style="width:170px" required /></td>
								</tr>
								<tr>
									<td colspan="2"><div class="cleaner" style="height: 10%"></div></td>
								</tr>
								<tr>
									<td><b>Upload QRCode:</b></td>
									<td><input type="file" name="qrcodefile" placeholder="Select Document" required /></td>
								</tr>
								<tr>
							     	<td colspan="2" align="center">
							     		<input type="submit" value="Update" class="button" style="width:112px;height:45px" /></td>
				    			</tr>
							</table>
						</form>	
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