<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	
	<meta charset="utf-8">
	<meta name="description" content="Miminium Admin Template v.1">
	<meta name="author" content="Isna Nur Azis">
	<meta name="keyword" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Q</title>

  <!-- start: Css -->
  <link rel="stylesheet" type="text/css" href="asset/css/bootstrap.min.css">

  <!-- plugins -->
  <link rel="stylesheet" type="text/css" href="asset/css/plugins/font-awesome.min.css"/>
  <link rel="stylesheet" type="text/css" href="asset/css/plugins/simple-line-icons.css"/>
  <link rel="stylesheet" type="text/css" href="asset/css/plugins/animate.min.css"/>
  <link href="asset/css/style.css" rel="stylesheet">
  <!-- end: Css -->

  <link rel="shortcut icon" href="asset/img/logomi.png">
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
</head>

<body id="mimin" class="dashboard">
<% session=request.getSession(false);
if((session.getAttribute("ssnid")==null)||(session==null))
{
	
		response.sendRedirect("login.jsp");
}
else  if(!(session.getAttribute("role").equals("customer")))
	response.sendRedirect("logout.jsp");
else
{		 System.out.println("jhere :: "+session.getAttribute("role")); %>
      <!-- start: Header -->
        <nav class="navbar navbar-default header navbar-fixed-top">
          <div class="col-md-12 nav-wrapper">
            <div class="navbar-header" style="width:100%;">
              <div class="opener-left-menu is-open">
                <span class="top"></span>
                <span class="middle"></span>
                <span class="bottom"></span>
              </div>
           
               <a href="index.html" class="navbar-brand"> 
                 <b>Q</b>
                </a>
				<h6 class="atomic-symbol">City </h6>
              

              
            </div>
          </div>
        </nav>
      <!-- end: Header -->

      <div class="container-fluid mimin-wrapper">
  
          <!-- start:Left Menu -->
           <div id="left-menu">
              <div class="sub-left-menu scroll">
                <ul class="nav nav-list">
                    <li><div class="left-bg"></div></li>
                    <li class="time">
                      <h1 class="animated fadeInLeft">21:00</h1>
                      <p class="animated fadeInRight">Sat,October 1st 2029</p>
                    </li>
                    
                    <li class="active ripple"> 
                     <a href="customerprofile.jsp"><span class="fa-home fa"></span>Home
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                     
                    </li>
                     <li class="active ripple">
                      <a  href="logout.jsp"><span class="fa-home fa"></span> Logout
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                     
                    </li>
                   
               
                   
                  
                    
                     
                    <li><a href="credits.html">Credits</a></li>
                  </ul>
                </div>
            </div>
          <!-- end: Left Menu -->
          <!-- start: Content -->
          <div id="content">
      <div class="btn-wrapper">
          <div class="panel box-shadow-none content-header">
            <div class="panel-body">
              <div class="col-md-12">
                  <h3 class="animated fadeInLeft">Hi <%= session.getAttribute("ssnid")%></h3>
                  <p class="animated fadeInDown">
                    Welcome to your Dashboard
                  </p>
              </div>
            </div>
          </div>
         

           <div class="col-md-12">
                  <div class="panel">
                      <div class="panel-heading">
                        <h4>Customer credentials</h4>
                      </div>
                      <div class="panel-body">
                   
                          <div class="col-md-3">
                           <a href="customergetprofile.jsp">
                              <button class="btn-flip btn btn-round btn-default">
                                <div class="flip">
                                  <div class="side">
                                     Profile
                                  </div>
                                  <div class="side back">
                                    Click to View
                                  </div>
                                </div>
                                <span class="icon"></span>
                              </button></a>
                          </div>
 						<div class="col-md-3">
 						 <a href="customeraccountdetails.jsp">
                              <button class="btn-flip btn btn-round btn-default">
                                <div class="flip">
                                  <div class="side">
                                    Get Account
                                  </div>
                                  <div class="side back">
                                  	Account info
                                  </div>
                                </div>
                                <span class="icon"></span>
                              </button></a>
                          </div>
                          
                          <div class="col-md-3">
 						 <a href="customergetaccountstatement.jsp">
                              <button class="btn-flip btn btn-round btn-default">
                                <div class="flip">
                                  <div class="side">
                                    Account Statement
                                  </div>
                                  <div class="side back">
                                  	View Statement
                                  </div>
                                </div>
                                <span class="icon"></span>
                              </button></a>
                          </div>
                          
                          <div class="col-md-3">
 						 <a href="customerministatement.jsp">
                              <button class="btn-flip btn btn-round btn-default">
                                <div class="flip">
                                  <div class="side">
                                    Mini Statement
                                  </div>
                                  <div class="side back">
                                  	view Statement
                                  </div>
                                </div>
                                <span class="icon"></span>
                              </button></a>
                          </div>
 				
                      </div>
                  </div>
              </div>   
              
              
              
               
             
             </div>
         </div>

          
<%}%>    
</body>
</html>