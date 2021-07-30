<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.user.*"%>
<%@page import="java.sql.Connection"%>
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
				<li><a href="Home.jsp" >Home</a></li>
				<li><a href="ProfileUpdate.jsp" class="current">Profile Update</a>
</li>
<li><a href="QRCode_details.jsp" >QRCode Upload</a></li>
				
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
						Profile Update <span>(by Best Student)</span>
					</h1>

					<div class="product_info" style="width: 100%;height: 100%">
                           <form class="post_section" action="smartcontractinfo" method="post">
                             <table  width="100%">
								
                                <tr>
                               
                                 <td> 
                                    <label style="font-size: 15px; color: #cbc750;">Select Minutes</label>
                                    </td>
                                    <td>
                                    <select id="gender" name="minutes" required="required" onblur="this.form.submit();" >
											<option selected="selected">Select Minutes</option>
											<option value="5#Min">5 Minutes</option>
											<option value="10#Min">10 Minutes</option>
											<option value="15#Min">15 Minutes</option>
											<option value="20#Min">20 Minutes</option>
											<option value="25#Min">25 Minutes</option>
											<option value="30#Min">30 Minutes</option>
									</select>
                                     <input type="submit" value="Search"  style="background-image: url(images/templatemo_h1_bg.jpg);color: white" />
                                </td>
                                
                                 </tr>
                                 
                                
								
							</table>
                                
                 </form>
                
              </div>
					<div class="cleaner">&nbsp;</div>
				</div>

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