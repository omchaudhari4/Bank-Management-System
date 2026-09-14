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
 * Servlet implementation class TRANSACTION
 */

public class TRANSACTION extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TRANSACTION() {
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
		
		int SrcAccno = Integer.parseInt(request.getParameter("SrcAccno"));
		int DestAccno = Integer.parseInt(request.getParameter("DestAccno"));
		float amt = Float.parseFloat(request.getParameter("amt"));
		float SrcBal= 0;
		float DestBal = 0;
		
		
		try{
			Connection con = ConnectDB.connect();
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM bank_db WHERE accNo = ?");			ps1.setInt(1, SrcAccno);
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next()){
				
				 SrcBal = rs.getFloat("accBal");
				
					if(SrcBal >= amt){ 
						PreparedStatement ps2 = con.prepareStatement("select * from bank_db where accNo = ?");
						ps2.setInt(1,DestAccno);
						ResultSet rs1 = ps2.executeQuery();
						
						if(rs1.next()){
							DestBal = rs1.getFloat("accBal");
							
							float newSrcBal = SrcBal - amt;
							float newDestBal = DestBal + amt;
							
							PreparedStatement ps3 = con.prepareStatement("update bank_db set accBal = ? where accNo = ?");
							ps3.setFloat(1, newSrcBal);
							ps3.setInt(2,SrcAccno );
							int i = ps3.executeUpdate();
							
							
							PreparedStatement ps4 = con.prepareStatement("update bank_db set accBal = ? where accNo = ?");
							ps4.setFloat(1, newDestBal);
							ps4.setInt(2,DestAccno );
							int j = ps4.executeUpdate();
							
							if(i > 0 && j > 0){
								
								response.sendRedirect("t_success.html");
								
							}
							else {
								response.sendRedirect("t_failed.html");
							}
							
						}
						else {
							response.sendRedirect("t_failed.html");
						}
					}
						
					
					else {
						response.sendRedirect("t_failed.html");
					}
				
			}
			else{
				response.sendRedirect("t_failed.html");
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
	}

}


