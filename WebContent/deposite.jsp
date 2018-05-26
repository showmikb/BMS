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

<input type="hidden" name="page" value="deposite">
<%=request.getAttribute("acid") %>
<%=request.getAttribute("actype") %>
<%=request.getAttribute("acbal") %>
<%=request.getAttribute("lasttrdate") %>

<input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
<input type="hidden" name="actype" value="<%=request.getAttribute("actype") %>">
<input type="hidden" name="acbal" value="<%=request.getAttribute("acbal") %>">
<input type="hidden" name="lasttrdate" value="<%=request.getAttribute("lasttrdate") %>">
Customer ID:<input type="text" name="custid">
Enter Amount To Deposit/Withdraw:<input type="text" name="depositamt">

<input type="Submit" value="Deposite">
</form>

<form action="ControllerTeller" method="post">
<input type="hidden" name="page" value="withdraw">
<%=request.getAttribute("acid") %>
<%=request.getAttribute("actype") %>
<%=request.getAttribute("acbal") %>
<%=request.getAttribute("lasttrdate") %>


<input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
<input type="hidden" name="actype" value="<%=request.getAttribute("actype") %>">
<input type="hidden" name="acbal" value="<%=request.getAttribute("acbal") %>">
<input type="hidden" name="lasttrdate" value="<%=request.getAttribute("lasttrdate") %>">
Customer ID:<input type="text" name="custid">
Enter Amount To Deposit/Withdraw:<input type="text" name="withdrawamt">


<input type="Submit" value="Withdraw">
</form>
<form action="ControllerTeller" method="post">
<input type="hidden" name="page" value="transfer">
<%=request.getAttribute("acid") %>
<%=request.getAttribute("actype") %>
<%=request.getAttribute("acbal") %>
<%=request.getAttribute("lasttrdate") %>


Enter Amount To Transfer:<input type="text" name="transferamt"><br>
Source Account Type:<select name="sactype" id="sactype">
		<option value="S">Savings</option>
		<option value="C">Current</option>
		</select><br>
Targeted Account Type:<select name="tactype" id="tactype">
		<option value="S">Savings</option>
		<option value="C">Current</option>
		</select><br>
		Customer ID:<input type="text" name="custid">
<input type="hidden" name="acid" value="<%=request.getAttribute("acid") %>">
<input type="hidden" name="acbal" value="<%=request.getAttribute("acbal") %>">
<input type="hidden" name="lasttrdate" value="<%=request.getAttribute("lasttrdate") %>">


<input type="Submit" value="Transfer">
</form>
</body>
</html>