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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Block Chain</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!--  Free CSS Templates from www.templatemo.com -->
	<div id="templatemo_container">
		<div id="templatemo_menu">
			<ul>
				<li><a href="CHome.jsp" class="current" >Home</a></li>
				<li><a href="ShowQR.jsp" >Show QR-Code</a></li>
				
				<li><a href="AddLogin" class="current-demo">Logout</a></li>
			</ul>
		</div>
		<!-- end of menu -->

		<div id="templatemo_header">

			<div id="templatemo_special_offers">
				
			</div>


			<div id="templatemo_new_books">
				
			</div>
		</div>
		<!-- end of header -->

		<div id="templatemo_content">

						<!-- end of content left -->

			<div id="templatemo_content_right" style="width: 100%">
				<div class="templatemo_product_box" style="width: 92%;height: 100%">
					<h1>
						Student ID Proof  Show</span>
					</h1>

                           <table id="examples" class="displays" style="background-color: black;width: 100%">
        
        <tbody>
        <%
					  try{
						  String emailid=request.getParameter("emailid");
						  Connection conn=DBconn.conn();
						  String imgName=null;
						  String b64="";
						   Statement st1 = (Statement) conn.createStatement();
						   String str = "select * from tblidproof where emailid='"+emailid+"' ";
						   ResultSet rs1 = (st1).executeQuery(str);
						   if(rs1.next()) 
						   {
						   	imgName=rs1.getString("file_path");
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
					          b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
						  
						   }
						  
						 {
					    		{
					    		%>
					    	
					    	<tr>
					    	<td >EmailId: </td>
					    	<td style="background-color: black;"><%=emailid %></td>
  </tr>
  <tr>
  <td >ID Proof: </td>
   <td><img style="width: 200px; height: 200px"  src="data:image/jpg;base64, <%=b64%>"/></td>
   
            </tr>
					    	<%}
					    		
					    		
					    		} %>
					    	<% 
					    	}
					    	catch (Exception e) {
					    		e.printStackTrace();
					    	}%>
            
           
        </tbody>
    </table>                        
                           
                             </div>
					<div class="cleaner">&nbsp;</div>
				

				<div class="cleaner_with_width">&nbsp;</div>


				<div class="cleaner_with_height">&nbsp;</div>


				<div class="cleaner_with_width">&nbsp;</div>


				<div class="cleaner_with_height">&nbsp;</div>


			</div>
			<!-- end of content right -->

			<div class="cleaner_with_height">&nbsp;</div>
		</div>
		<!-- end of content -->

		<div id="templatemo_footer">

			<a href="subpage.html">Home</a> | <a href="subpage.html">Search</a> |
			<a href="subpage.html">Books</a> | <a href="#">New Releases</a> | <a
				href="#">FAQs</a> | <a href="#">Contact Us</a><br /> </a>
			<!-- Credit: www.templatemo.com -->
		</div>
		<!-- end of footer -->
		<!--  Free CSS Template www.templatemo.com -->
	</div>
	<!-- end of container -->
	<!-- templatemo 086 book store -->
	<!-- 
Book Store Template 
http://www.templatemo.com/preview/templatemo_086_book_store 
-->
</body>
</html>