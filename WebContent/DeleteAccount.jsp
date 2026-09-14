<%@ page import="java.sql.*" %>
<%@ page import="com.r3sys.dbcon.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 
	try
	{
		String ano= request.getParameter("ano");
		Connection con = ConnectDB.connect();
		PreparedStatement ps = con.prepareStatement("delete from bank_db where accNo=?");
		ps.setString(1, ano);
		int i = ps.executeUpdate();
		if(i>0)
		{
			response.sendRedirect("success.html");
		}
		else
		{
			response.sendRedirect("failed.html");
		}	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>

</body>
</html>