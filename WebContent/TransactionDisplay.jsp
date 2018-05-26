<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>    
<%@ page import="com.bean.*" %>    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	
	<meta charset="utf-8">
	<meta name="description" content="Miminium Admin Template v.1">
	<meta name="author" content="Isna Nur Azis">
	<meta name="keyword" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <title>BankofQcity</title>

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

              <ul class="nav navbar-nav search-nav">
                <li>
                   <div class="search">
                    <span class="fa fa-search icon-search" style="font-size:23px;"></span>
                    <div class="form-group form-animate-text">
                      <input type="text" class="form-text" required>
                      <span class="bar"></span>
                      <label class="label-search">Type anywhere to <b>Search</b> </label>
                    </div>
                  </div>
                </li>
              </ul>

         
            </div>
          </div>
        </nav>
      <!-- end: Header -->

  
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
                      <a class="tree-toggle nav-header" href="customerprofile.jsp"><span class="fa-home fa"></span> Home 
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                     
                    </li>
                     <li class="active ripple">
                      <a class="tree-toggle nav-header" href="logout.jsp"><span class="fa-home fa"></span> Logout
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                     
                    </li>
                   
               
                   
                  
                    
                     
                    <li><a href="changepassword.jsp">Change Password</a></li>
                  </ul>
                </div>
            </div>
          <!-- end: Left Menu -->

          <!-- start: Content -->
          
         <%--  <% ArrayList<String> arlist = ( ArrayList<String>)request.getAttribute("trlist"); %> --%>
          
          <div id="content">
      <div class="btn-wrapper">
          <div class="panel box-shadow-none content-header">
            <div class="panel-body">
              <div class="col-md-12">
                  <h3 class="animated fadeInLeft">Last N Transactions</h3>
                  <p class="animated fadeInDown">
                   
                  </p>
              </div>
            </div>
          </div>
         

           <div class="col-md-12">
                  <div class="panel">
                      <div class="panel-heading">
                        <h4>Status</h4>
                      </div>
                      <div class="panel-body">
                       <!-- start:   <div class="col-md-3"> Content -->
                  
                          
 						Statement For The Account
 						<div class="responsive-table">
<table id="datatables-example" class="table table-striped table-bordered" style="height:97px;" width="587" cellspacing="0">
<thead>
<tr><th>
<p align="JUSTIFY"></p>TRANSACTION ID
</th><th>TYPE OF TRANSACTION</th><th>AMOUNT</th><th>TRANSACTION DATE</th></tr>
</thead>
<tbody>
<% Transaction t = new Transaction();
//creating arraylist object of type category class
ArrayList<Transaction> list = new ArrayList<Transaction>();
//storing passed value from jsp
list =( ArrayList<Transaction>)request.getAttribute("trlist");

for(int i = 0; i < list.size(); i++) {

t = list.get(i);

%>
<tr>



<td>&nbsp;<%out.println( t.getTrans_id());%></td>
<td>&nbsp;<%out.println(t.getOp_type());%></td>
<td>&nbsp;<%out.println(t.getAmount() ); %></td>
<td>&nbsp;<%out.println(t.getTrans_date() ); %></td>
<%} %>
</tbody>

</table>

</div>
                          
                         
                          
                        
 				
                      </div>
                  </div>
              </div>   
              
              
              
               
             
             </div>
         </div>
<center>  <button onclick="history.go(-1);return false;">Back</button>      </center> 
          
<%} %>     
</body>
</html>