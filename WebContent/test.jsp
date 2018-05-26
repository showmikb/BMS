<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Somwhere" method="post" name="example1">
		<input type="text" id="number" name="ssnid" required>
		<input type="submit" onclick="return check(document.example1.ssnid)"/>
		
	</form>
	<script>
	function check(inputtext)
	{
		var num=/^[0-9]{9}$/;
		if(inputtext.value.match(num))
			return true;
		else
			{
			alert("oops wrong ssnid")
			return false;}
	}
	</script>
	
</body>
</html>