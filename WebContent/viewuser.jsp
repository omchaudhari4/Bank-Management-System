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
	<table>
		<tr>
			<th>Account No.</th>
			<th>Customer Name</th>
			<th>Mobile No.</th>
			<th>Balance.</th>
			<th>Action.</th>
		</tr>
	<%
		try
		{	
			Connection con=ConnectDB.connect();
			PreparedStatement ps1 = con.prepareStatement("select *from bank_db");
			ResultSet rs = ps1.executeQuery();
			while(rs.next()){
	%>
				<tr>
					<td><%=rs.getInt(1)%></td>
					<td><%=rs.getString(2)%></td>
					<td><%=rs.getString(3)%></td>
					<td><%=rs.getFloat(4)%></td>
<td>
    <a href="DeleteAccount.jsp?ano=<%=rs.getInt(1)%>">Delete</a>
</td>				</tr>
	<%			
			}
		}
		catch(Exception e)
		{	
			String error = "Something Went Wrong,check console...!!!!";
	%>
		<h3><%=error%></h3>
	<% 	
		e.printStackTrace();
		}
	%>
	
		<a href = "dashboard.html">Go To dashboard</a>
	</table>
</body>
</html>