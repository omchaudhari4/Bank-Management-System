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

	Search Account <br>

<form action="SearchAccount.jsp">
    <input type="text" name="accNo" placeholder="Enter account number">
    <input type="submit">
</form>

<br>

		<%
String accNo = request.getParameter("accNo");

if (accNo!=null)
{
    try
    {
        Connection con = ConnectDB.connect();
		PreparedStatement ps = con.prepareStatement("select * from Bank_db where accNo=?");
		ps.setString(1, accNo);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
		%>
			Account Number : <%=rs.getString(1) %> <br>
			Customer Name  : <%=rs.getString(2) %> <br>
			Mobile Number  : <%=rs.getString(3) %> <br>
			Account Number : <%=rs.getString(4) %> <br>	
		<% 	
		}
		else
		{
		%>
			<h3 style="color:red">Invalid Account Number...!!!</h3>
			<br>
		<% 		
		}
	}
    catch(Exception e)
    {
    		e.printStackTrace();
    }
}
%>

	<br>
	<a href="dashboard.html">Go to dashboard</a>
</body>
</html>