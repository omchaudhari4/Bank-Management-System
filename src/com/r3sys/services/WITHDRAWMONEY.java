package com.r3sys.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.r3sys.dbcon.ConnectDB;

/**
 * Servlet implementation class WITHDRAWMONEY
 */
public class WITHDRAWMONEY extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WITHDRAWMONEY() {
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
		//doGet(request, response);
			
		

		int accNo = Integer.parseInt(request.getParameter("accNo"));
		float amt = Float.parseFloat(request.getParameter("amt"));
		float accBal;
		
		try {
			
			if(amt > 0){
				
				Connection con = ConnectDB.connect();
				PreparedStatement ps1 = con.prepareStatement("select * from bank_db where accNo = ?");
				ps1.setInt(1,accNo);
				ResultSet r1 = ps1.executeQuery();
				
				if(r1.next()){
					
					accBal = r1.getFloat("accBal");
					
					if(accBal >= amt){
					accBal = accBal - amt;
					PreparedStatement ps2 = con.prepareStatement("update bank_db set accBal = ? where accNo = ?");
					ps2.setFloat(1, accBal);
					ps2.setInt(2, accNo);
					int i = ps2.executeUpdate();
					if(i > 0){
						
						response.sendRedirect("withdraw_success.html");
						
					}
					else {
						response.sendRedirect("withdraw_failed.html");

					}
			     }
					else {
						
						response.sendRedirect("withdraw_failed.html");
					}
				}
				
				else {
					response.sendRedirect("withdraw_failed.html");
				}
			}
			else{
				
				response.sendRedirect("withdraw_failed.html");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

