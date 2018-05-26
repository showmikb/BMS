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
<input type="hidden" name="page" value="withdraw">
CustomerId:<input type="text" name="custid" >
Account ID:<input type="text" name="acid" >
<input type="text" name="actype" >
Account Balance:<input type="text" name="acbal" >
Last Transaction Date:<input type="text" name="lasttrdate" >
Enter Amount To Deposit/Withdraw:<input type="text" name="withdrawamt">


<input type="Submit" value="Withdraw">
</form>
</body>
</html>