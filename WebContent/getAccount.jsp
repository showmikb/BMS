<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/ loose.dtd">
<html>
<head>
<script type="text/javascript">
function validate()
{
	var acctid=document.myFrm.actid.value;
     var letters=/^[0-9]+$/;
	if(acctid!=null){
	   if(acctid.length==9)
	{
	if(letters.test(acctid)){
		
		return true;
	}
    else{
        alert("please enter numeric character only for account id");
		document.myFrm.actid.focus();
        return false;
    }
	}
    else{
        	alert("please enter 9 digit account id");
            document.myFrm.actid.focus();
		return false;
    }
    }
	else
		{
		alert("please enter account id");
		document.myFrm.actid.focus();
		return false;
		}
        return false;
	}


</script>
<script type="text/javascript">
function validate1()
{
	var custid=document.myFrm1.custid.value;
     var letters=/^[0-9]+$/;
	if(custid!=null){
	   if(custid.length==9)
	{
	if(letters.test(custid)){
		
		return true;
	}
    else{
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
        return false;
	}


</script>
<meta charset="utf-8">
<meta name="description" content="Miminium Admin Template v.1">
<meta name="author" content="Isna Nur Azis">
<meta name="keyword" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Q City Bank</title>

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


        <!-- start: Content -->
          <div id="content">
             <div class="panel box-shadow-none content-header">
                  <div class="panel-body">
                    <div class="col-md-12">
                        <h3 class="animated fadeInLeft">Account</h3>
                        <p class="animated fadeInDown">
                         
                        </p>
                    </div>
                  </div>
              </div>
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading"></div>
                    <div class="panel-body">
                         <form name="myFrm" onsubmit="return validate()" id="example1" action="ControllerTeller"  method="post">
                           
                            <fieldset>
                                <legend>Enter The Following Information To Get Account Details</legend>
                          <input type="hidden" name="page" value="AccountDetails">
                                <label for="userName-2" >ACCOUNT ID*</label>
                                <input id="userName-2" name="actid" type="text" class= "required" required>
                                        
                                <p>(*) Mandatory</p>
                            </fieldset>
                      <div class="col-md-3">
                              <button type="submit" class="btn-flip btn btn-raised btn-info" >
                                <div class="flip">
                                  <div class="side">
                                   <center>Get Account Details</center>
                                  </div>
                                 
                                </div>
                                <span class="icon"></span>
                              </button>
                          </div>
</form><br><br><br><br>
                        <form name="myFrm1" onsubmit="return validate1()" id="example1" action="ControllerTeller"  method="post" >
                           
                            <fieldset>
                                <legend>Enter The Following Information To Get Accounts of Customer</legend>
                          <input type="hidden" name="page" value="getaccountsforcustomer">
                                <label for="userName-2" >CUSTOMER ID*</label>
                                <input id="userName-2" name="custid" type="text" class="required" required>
                                        
                                <p>(*) Mandatory</p>
                            </fieldset>
                      <div class="col-md-3">
                              <button  type="submit" class="btn-flip btn btn-raised btn-info">
                                <div class="flip">
                                  <div class="side">
                                   <center>Get Accounts</center>
                                  </div>
                                 
                                </div>
                                <span class="icon"></span>
                              </button>
                          </div>
</form><br><br><br><br>


                    </div>
                </div>
                
            </div>
          </div>
        <!-- end: content -->


    
          <!-- start: right menu -->
                      <!-- end: right menu -->
          
      </div>

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
                   </ul>
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
</body></html>