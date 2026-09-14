package com.r3sys.dbcon;

import java.sql.*;

public class ConnectDB {
	
	static Connection con  = null;
	public static Connection connect(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_application","root","root");
			System.out.println("Connected...!"+con);
		}
		catch(Exception e){
			System.out.println("Failed to connect...!");
			e.printStackTrace();
		}
		
		return con;
	}
}
	
