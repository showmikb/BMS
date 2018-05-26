<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import=" com.util.Connections" %>
<%@ page import=" com.util.Dconnection" %>
<%@ page import=" com.controller.ControllerTeller" %>
<%@ page import=" com.dao.DaoTeller" %>
<%@ page import=" com.service.Service" %>
<html>
<head>
<script type="text/javascript">
function validation(){
	var custid=document.myFrm1.custid.value;
	var depositamt=document.myFrm1.depositamt.value;
	     var letters=/^[0-9]+$/;
	     var suffix=".00";
		if(custid!=null){
		   if(custid.length==9)
		{
		if(!letters.test(custid)){
			
		
	  
	        alert("please enter numeric character only for customer id");
			document.myFrm1.custid.focus();
	        return false;
	    }
		}
	    else{
	        	alert("please enter 9 digit customer id");
	            document.myFrm1.custid.focus();
			return false;
	    }
	    }
		else
			{
			alert("please enter customer id");
			document.myFrm1.custid.focus();
			return false;
			}
	  if(depositamt==null || depositamt.length<=0|| isNaN(depositamt)){
	   alert("please enter proper deposit amount");
			document.myFrm1.depositamt.focus();
			return false; 
	  }
	  else{
	   if (!depositamt.match(suffix+"$")){
	    alert("please enter proper deposit amount ends with '.00'");
			document.myFrm1.depositamt.focus();
	    return false; 
	  }
	  else
	  return true;
	  }
	        return false;
	}
</script>
<script type="text/javascript">
function validation1(){
	var custid=document.myFrm2.custid.value;
	var withdrawamt=document.myFrm2.withdrawamt.value;
	     var letters=/^[0-9]+$/;
	     var suffix=".00";
		if(custid!=null){
		   if(custid.length==9)
		{
		if(!letters.test(custid)){
			
		
	  
	        alert("please enter numeric character only for customer id");
			document.myFrm2.custid.focus();
	        return false;
	    }
		}
	    else{
	        	alert("please enter 9 digit customer id");
	            document.myFrm2.custid.focus();
			return false;
	    }
	    }
		else
			{
			alert("please enter customer id");
			document.myFrm2.custid.focus();
			return false;
			}
	  if(withdrawamt==null || withdrawamt.length<=0|| isNaN(withdrawamt)){
	   alert("please enter proper deposit amount");
			document.myFrm2.withdrawamt.focus();
			return false; 
	  }
	  else{
	   if (!withdrawamt.match(suffix+"$")){
	    alert("please enter proper deposit amount ends with '.00'");
			document.myFrm2.withdrawamt.focus();
	    return false; 
	  }
	  else
	  return true;
	  }
	        return false;
	}
</script>
<script type="text/javascript">
function validation2(){
	var custid=document.myFrm3.custid.value;
	var transferamt=document.myFrm3.transferamt.value;
	     var letters=/^[0-9]+$/;
	     var suffix=".00";
		if(custid!=null){
		   if(custid.length==9)
		{
		if(!letters.test(custid)){
			
		
	  
	        alert("please enter numeric character only for customer id");
			document.myFrm3.custid.focus();
	        return false;
	    }
		}
	    else{
	        	alert("please enter 9 digit customer id");
	            document.myFrm3.custid.focus();
			return false;
	    }
	    }
		else
			{
			alert("please enter customer id");
			document.myFrm3.custid.focus();
			return false;
			}
	  if(transferamt==null || transferamt.length<=0|| isNaN(transferamt)){
	   alert("please enter proper transfer amount");
			document.myFrm3.transferamt.focus();
			return false; 
	  }
	  else{
	   if (!transferamt.match(suffix+"$")){
	    alert("please enter proper transfer amount ends with '.00'");
			document.myFrm3.transferamt.focus();
	    return false; 
	  }
	  else
	  return true;
	  }
	        return false;
	}
</script>
	<meta charset="utf-8">
	<meta name="description" content="Miminium Admin Template v.1">
	<meta name="author" content="Isna Nur Azis">
	<meta name="keyword" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Miminium</title>

  <!-- start: Css -->
  <link rel="stylesheet" type="text/css" href="asset/css/bootstrap.min.css">

  <!-- plugins -->
  <link rel="stylesheet" type="text/css" href="asset/css/plugins/font-awesome.min.css"/>
  <link rel="stylesheet" type="text/css" href="asset/css/plugins/simple-line-icons.css"/>
  <link rel="stylesheet" type="text/css" href="asset/css/plugins/jquery.steps.css"/>
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
                     <a href="bankprofile.jsp"><span class="fa-home fa"></span>Home
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

</nav>
        <!-- start: Content -->
          <div id="content">
             <div class="panel box-shadow-none content-header">
                  <div class="panel-body">
                    <div class="col-md-12">
                        <h3 class="animated fadeInLeft">Account Details</h3>
                                            </div>
                  </div>
              </div>
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading"></div>
                    <div class="panel-body">
 <form id="example1" action="ControllerTeller" method="post">
                            <h3>Fetched Account</h3>
                       <div>
                            	<input type="hidden" name="page" value="AccountDetails">	
                             	 <% 
								    String acid=(String)request.getAttribute("acid");
									 String acctype= (String)request.getAttribute("actype");
									 String acbal= (String)request.getAttribute("acbal");
									 String lasttrdate=(String)request.getAttribute("lasttrdate");
							     %> 
							        <table class="table table-striped table-bordered" width="100%" cellspacing="0">
					                    <thead>
					                      <tr>
					                       
					                        <th>AccountID</th>
					                        <th>Account Type</th>
					                        <th>Balance</th>
					                        <th>Last Transaction Date</th>
					                                          
					                      </tr>
					                    </thead>
					                    <tbody>
					            	             
					  			          <TR>
					            	    <TD> <%=acid %></td>
					                	<TD> <%= acctype %></TD>
					                	<TD> <%= acbal %></TD>
					                	<TD> <%= lasttrdate %></TD>
					                
					            			</TR>
					          			 </tbody>
					                 	 </table>
					        </div>
					                 
            
</form><br><br><br>
 
<form name="myFrm1" onsubmit="return validation()" action="ControllerTeller" method="post">
<fieldset align=center>
                                <legend>Enter The Following Information To Deposit</legend>
                                
<input type="hidden" name="page" value="deposite">
	 
<input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
<input type="hidden" name="actype" value="<%=request.getAttribute("actype") %>">
<input type="hidden" name="acbal" value="<%=request.getAttribute("acbal") %>">
<input type="hidden" name="lasttrdate" value="<%=request.getAttribute("lasttrdate") %>">
	
<label for="userName-2" >Customer ID*</label>
   <input id="userName-2" name="custid" type="text"  class="required" required>
             <p>(*) Mandatory</p><br>    
  <label for="userName-2" >Enter Amount To Deposit</label>
   <input id="userName-2" name="depositamt" type="text"  class="required" required>
   <br><br>
                                                                  
                   
                              <button type="submit" class="btn-flip btn btn-raised btn-info" >
                                <div class="flip">
                                  <div class="side">
                                   <center>Deposit</center>
                                  </div>
                                 </div>
                                <span class="icon"></span>
                              </button>
                              
                       </fieldset>
 </form><br><br><br><br>
 
 <form name="myFrm2" onsubmit="return validation1()" action="ControllerTeller" method="post">
<fieldset align=center>
                                <legend>Enter The Following Information To Withdraw</legend>
                                
<input type="hidden" name="page" value="withdraw">
	
<input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
<input type="hidden" name="actype" value="<%=request.getAttribute("actype") %>">
<input type="hidden" name="acbal" value="<%=request.getAttribute("acbal") %>">
<input type="hidden" name="lasttrdate" value="<%=request.getAttribute("lasttrdate") %>">
	
<label for="userName-2" >Customer ID*</label>
   <input id="userName-2" name="custid" type="text"  class="required" required>
            <p>(*) Mandatory</p><br>       
  <label for="userName-2" >Enter Amount To Withdraw</label>
   <input id="userName-2" name="withdrawamt" type="text"  class="required" required>
                          <br>  <br>                                      
                          
                              <button type="submit" class="btn-flip btn btn-raised btn-info">
                                <div class="flip">
                                  <div class="side">
                                   <center>Withdraw</center>
                                  </div>
                                 </div>
                                <span class="icon"></span>
                              </button>
                           </fieldset>
                      
 </form><br><br><br><br>

<form name="myFrm3" onsubmit="return validation2()" action="ControllerTeller" method="post">
<fieldset align=center>
                                <legend>Enter The Following Information To Transfer</legend>
                                
<input type="hidden" name="page" value="transfer">
<input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
<input type="hidden" name="actype" value="<%=request.getAttribute("actype") %>">
<input type="hidden" name="acbal" value="<%=request.getAttribute("acbal") %>">
<input type="hidden" name="lasttrdate" value="<%=request.getAttribute("lasttrdate") %>">
<label for="userName-2" >Customer ID*</label>
   <input id="userName-2" name="custid" type="text"  class="required" required>
        <p>(*) Mandatory</p><br>
  <label for="userName-2" >Enter Amount To Transfer:</label>
   <input id="userName-2" name="transferamt" type="text" required><br><br>
 <label for="userName-2" >Source Account Type:</label>
<select name="sactype" id="sactype"  required>
		<option value="S">Savings</option>
		<option value="C">Current</option>
</select><br><br>
<label for="userName-2" >Targeted Account Type:</label>
<select name="tactype" id="tactype"  required>
		<option value="S">Savings</option>
		<option value="C">Current</option>
		</select><br><br>
					
                              <button type="submit" class="btn-flip btn btn-raised btn-info">
                                <div class="flip">
                                  <div class="side">
                                   <center>Transfer</center>
                                  </div>
                                 </div>
                                <span class="icon"></span>
                              </button>
                        </fieldset>
                      
</form><br><br><br><br>

              <form name="myFrm4" onsubmit="return validation3()" action="ControllerTeller" method="post">
                           
                            <fieldset align=center>
                                <legend>Enter The Following Information To Get Last Statement</legend>
                          <input type="hidden" name="page" value="getlaststmt">
                          <input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
                                <label for="userName-2" >Enter No of Transactions*</label>
                                <input id="userName-2" name="numberofTrns" type="text" class="required" required>
                                <p>(*) Mandatory</p><br>
                            
                     
                      <br>
                             <button type="submit" class="btn-flip btn btn-raised btn-info" >
                                <div class="flip">
                                  <div class="side">
                                   <center>Get Statement</center>
                                  </div>
                                 
                                </div>
                                <span class="icon"></span>
                              </button>
                        </fieldset>
</form>
                 
                
            
         
			 <!-- start: Mobile -->
      <div id="mimin-mobile" class="reverse">
        <div class="mimin-mobile-menu-list">
            <div class="col-md-12 sub-mimin-mobile-menu-list animated fadeInLeft">
                <ul class="nav nav-list">
                    <li class="active ripple">
                      <a class="tree-toggle nav-header">
                        <span class="fa-home fa"></span>Dashboard 
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      </ul>
                      <ul class="nav nav-list tree">
                          <li><a href="dashboard-v1.html">Dashboard v.1</a></li>
                          <li><a href="dashboard-v2.html">Dashboard v.2</a></li>
                      </ul>
                    </li>
                   
            </div>
        </div>       
      </div>
      <button id="mimin-mobile-menu-opener" class="animated rubberBand btn btn-circle btn-danger">
        <span class="fa fa-bars"></span>
      </button>
       <!-- end: Mobile -->

<!-- start: Javascript -->
<script src="asset/js/jquery.min.js"></script>
<script src="asset/js/jquery.ui.min.js"></script>
<script src="asset/js/bootstrap.min.js"></script>


<!-- plugins -->
<script src="asset/js/plugins/jquery.steps.min.js"></script>
<script src="asset/js/plugins/moment.min.js"></script>
<script src="asset/js/plugins/jquery.validate.min.js"></script>
<script src="asset/js/plugins/jquery.nicescroll.js"></script>


<!-- custom -->
<script src="asset/js/main.js"></script>
<script type="text/javascript">
  $(document).ready(function(){



    var form = $("#example1").show();
     
    form.steps({
        headerTag: "h3",
        bodyTag: "fieldset",
        transitionEffect: "slideLeft",
        onStepChanging: function (event, currentIndex, newIndex)
        {
            // Allways allow previous action even if the current form is not valid!
            if (currentIndex > newIndex)
            {
                return true;
            }
            // Forbid next action on "Warning" step if the user is to young
            if (newIndex === 3 && Number($("#age-2").val()) < 18)
            {
                return false;
            }
            // Needed in some cases if the user went back (clean up)
            if (currentIndex < newIndex)
            {
                // To remove error styles
                form.find(".body:eq(" + newIndex + ") label.error").remove();
                form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
            }
            form.validate().settings.ignore = ":disabled,:hidden";
            return form.valid();
        },
        onStepChanged: function (event, currentIndex, priorIndex)
        {
            // Used to skip the "Warning" step if the user is old enough.
            if (currentIndex === 2 && Number($("#age-2").val()) >= 18)
            {
                form.steps("next");
            }
            // Used to skip the "Warning" step if the user is old enough and wants to the previous step.
            if (currentIndex === 2 && priorIndex === 3)
            {
                form.steps("previous");
            }
        },
        onFinishing: function (event, currentIndex)
        {
            form.validate().settings.ignore = ":disabled";
            return form.valid();
        },
        onFinished: function (event, currentIndex)
        {
            document.getElementById("example1").submit();
            
        }
    }).validate({
        errorPlacement: function errorPlacement(error, element) { element.before(error); },
        rules: {
            confirm: {
                equalTo: "#password-2"
            }
        }
    });




  });
</script>
<!-- end: Javascript -->
							     
					    

</body>
</html>