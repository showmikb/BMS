<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ControllerTeller" method="post">
<input type="hidden" name="page" value="Transfer">
Enter Amount To Transfer:<input type="text" name="transferamt"><br>
Source Account Type:<select name="sactype" id="sactype">
		            <option value="S">Savings</option>
		             <option value="C">Current</option>
		            </select><br>
Targeted Account Type:<select name="tactype" id="tactype">
		<option value="S">Savings</option>
		<option value="C">Current</option>
		</select><br>
Account ID:<input type="text" name="acid" >
CustomerId:<input type="text" name="custid" >
Account Balance:<input type="text" name="acbal" >
Last Transaction Date:<input type="text" name="lasttrdate">
<input type="Submit" value="Transfer">
</form>
</body>
</html>