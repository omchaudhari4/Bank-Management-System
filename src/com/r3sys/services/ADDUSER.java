package com.r3sys.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.r3sys.dbcon.ConnectDB;


/**
 * Servlet implementation class ADDUSER
 */

public class ADDUSER extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADDUSER() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		int accNo = 0;
		String accName = request.getParameter("accName");
		String accMob = request.getParameter("accMob");
		float accBal  = Float.parseFloat(request.getParameter("accBal"));
		
		try{
			
			Connection con = ConnectDB.connect();
			PreparedStatement ps1 = con.prepareStatement("insert into bank_db values(?,?,?,?)");
			ps1.setInt(1, accNo);
			ps1.setString(2, accName);
			ps1.setString(3,accMob);
			ps1.setFloat(4, accBal);
			int i = ps1.executeUpdate();
			
			if(i>0){
				
				response.sendRedirect("success.html");
				
			}
			else {
				
				response.sendRedirect("failed.html");
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}



