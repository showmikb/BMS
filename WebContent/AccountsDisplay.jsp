<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator" %>
<%@page import="com.bean.Transaction"%>
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
          <form id="example1" action="ControllerTeller"  method="post">
                           
                        
                          <input type="hidden" name="page" value="customeraccountdetails">
          <div id="content">
      <div class="btn-wrapper">
          <div class="panel box-shadow-none content-header">
            <div class="panel-body">
              <div class="col-md-12">
                  <h3 class="animated fadeInLeft">Accounts of Customer</h3>
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
                  
                          
 						This is Customer Accounts Profile
 						<div class="responsive-table">
<table id="datatables-example" class="table table-striped table-bordered" style="height:97px;" width="587" cellspacing="0">

<tbody>

<tr>
<% 
	Object aact1=request.getAttribute("act1");
	Object aact2=request.getAttribute("act2");
	
	String act1= aact1.toString();

	String act2= aact2.toString();

    if(act1!=null && act2!=null){
  	
	%>

	<tr>
	
		<td>
		<select name="accid">
		<% for(int i=0;i<2;i++){
		if(i==0){
  			if(!act1.equals("0")){%>
  				<option value="<%=act1 %>"><%=act1 %></option>
			<%} 
    	}else if(i==1){
    		if(!act2.equals("0")){%>
    			<option value="<%=act2 %>"><%=act2%></option>
    		<% }
		}
		}%>
    	</select>
		</td>
	</tr>
	<tr>
		<td>
			<input type="Submit" value="Get Details">
		</td>
	</tr>
<%} %>


</tbody>

</table>
</div>
                          
                         
                          
                        
 				
                      </div>
                  </div>
              </div>   
              
              
              
               
             
             </div>
         </div>

          
     </form>
</body>
</html>