<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import=" org.json.*;" %>
<!DOCTYPE html>
<html lang="en">
<head>
	
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
<%-- <% session=request.getSession(false);
if((session.getAttribute("ssnid")==null)||(session==null))
{

		response.sendRedirect("login.jsp");
}
else  if(!(session.getAttribute("role").equals("customer")))
	response.sendRedirect("logout.jsp");
else
{		 System.out.println("jhere :: "+session.getAttribute("role")); %> --%>
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
                     <a onclick="history.go(-2); return false;"><span class="fa-home fa"></span>Home
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
       
          <!-- end: Left Menu -->


        <!-- start: Content -->
          <div id="content">
             <div class="panel box-shadow-none content-header">
                  <div class="panel-body">
                    <div class="col-md-12">
                        <h3 class="animated fadeInLeft">Details of Customer</h3>
                        <p class="animated fadeInDown">
                         
                        </p>
                    </div>
                  </div>
              </div>
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading"></div>
                    <div class="panel-body">
                        <form id="example1" action="Customer" method="post">
                            <h3>Fetch Customer</h3>
                            
                            <fieldset>
                            	<input type="hidden" name="page" value="update">	
                             	 <% 
							       String json = (String) request.getAttribute("json");
							       JSONObject j = (JSONObject) new JSONTokener(json).nextValue(); 
							       JSONObject j1=j.getJSONObject("Envelope");
							       JSONObject j2=j1.getJSONObject("Body");
							       JSONObject j3=j2.getJSONObject("TF000101OperationResponse"); 
							   
							       String custid=(String)j3.get("ws_id");
							       String ssnid=(String)j3.get("ws_cust_ssn");
							       String name=(String)j3.get("ws_cust_name");
								   String address = (String)j3.get("ws_cust_adrs");
								   String age = (String)j3.get("ws_cust_age"); 
								   
								   request.setAttribute("json",json);
							     %> 
					                    <table class="table table-striped table-bordered" width="100%" cellspacing="0">
					                    <thead>
					                      <tr>
					                       
					                        <th>Customer Id</th>
					                        <th>SSNID</th>
					                        <th>Name</th>
					                        <th>Address</th>
					                        <th>Age</th>
					                       
					                      </tr>
					                    </thead>
					                    <tbody>
					                     
					                       
					             
					            <TR>
					                <TD> <%= custid %></td>
					                <TD> <%= ssnid %></TD>
					                <TD> <%= name %></TD>
					                <TD> <%= address %></TD>
					                <TD> <%= age %></TD>
					            </TR>
					           </tbody>
					                  </table>
					                  </div>
					                  <div class="col-md-6" style="padding-top:20px;">
					                    <span>showing 0-10 of 30 items</span>
					                  </div>
                            </fieldset>
                     

                        </form>


                    </div>
                </div>
            </div>
          </div>
       <%-- <% 
       String json = (String) request.getAttribute("json");
       JSONObject j = (JSONObject) new JSONTokener(json).nextValue(); 
       JSONObject j1=j.getJSONObject("Envelope");
       JSONObject j2=j1.getJSONObject("Body");
       JSONObject j3=j2.getJSONObject("TF000101OperationResponse"); 
   
       String custid=(String)j3.get("ws_id");
       String ssnid=(String)j3.get("ws_cust_ssn");
       String name=(String)j3.get("ws_cust_name");
	   String address = (String)j3.get("ws_cust_adrs");
	   String age = (String)j3.get("ws_cust_age"); 
        %> 
                    <table class="table table-striped table-bordered" width="100%" cellspacing="0">
                    <thead>
                      <tr>
                       
                        <th>ssnid</th>
                        <th>cuname</th>
                        <th>cuaddress</th>
                        <th>cuage</th>
                        <th>password</th>
                       
                      </tr>
                    </thead>
                    <tbody>
                     
                       
             
            <TR>
                <TD> <%= custid %></td>
                <TD> <%= ssnid %></TD>
                <TD> <%= name %></TD>
                <TD> <%= address %></TD>
                <TD> <%= age %></TD>
            </TR>
           </tbody>
                  </table>
                  </div>
                  <div class="col-md-6" style="padding-top:20px;">
                    <span>showing 0-10 of 30 items</span>
                  </div>
                </div>
              </div>
            </div>  
          </div>
          </div> --%>
        <!-- end: content -->


    
          <!-- start: right menu -->
                      <!-- end: right menu -->
          
     

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
            document.location.href="/BMS/customerStatus.jsp";
            
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
<%-- <%} %> --%>
</body>
</html>