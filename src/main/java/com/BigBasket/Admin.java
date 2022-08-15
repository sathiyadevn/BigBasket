package com.BigBasket;

import static java.lang.System.in;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Admin {
	
	private static Logger log =Logger.getLogger(Admin.class);
	static String[] str=new String[]{"fruits","graints","cosmetics","cleaning","desserts"};
	
	
	
	public static void aSwitch() throws ClassNotFoundException, SQLException {
		out.println("\t\t\t****************************");		
		out.println("				ADMIN PAGE");
		out.println("\t\t\t****************************\n");
		
		out.println("1 - Add Staff");	
		out.println("2 - Check Stocks");	
//		out.println("3 - Update Stocks");
		out.println("0 - Exit");
		
		
		Scanner sc=new Scanner(in);
		out.println("Enter your Choice (1-2): ");
		try {
			int i=sc.nextInt();
			switch(i) {
			case 1:
				String name="staff";
				Cus.newUser(name);
				break;
			case 2:
				out.println("1 - Low Stocks");	
				out.println("2 - Expiry Stock");
				out.println("0 - Exit");
				out.println("Enter your Choice (1-2): ");
				try {
					int j=sc.nextInt();
					switch(j) {
					case 1:
						lowStock();
						break;
					case 2:
						expiryStock();
						break;
					case 0:
						aSwitch();
						break;
					default:
						log.error("value exist given limit");
						out.println("Enter value within the range of (1-2)");
						break;
					}
					
				} catch (Exception e) {
					log.warn(e);
					out.println("Enter valid input as Integer");
					aSwitch();
				}				
				break;
			default:
				log.error("value exist given limit");
				out.println("Enter value in between (1-2)");
				break;					
			}		
			
		} catch (Exception e) {
			log.warn(e);	
			out.println("Enter valid input as Integer");
			aSwitch();
			}
	
	}
	private static void expiryStock() throws ClassNotFoundException, SQLException {
		
		out.printf("%-15s","catagory");
		out.printf("%-4s","id");
		out.printf("%-20s","Items");
		out.printf("%4s","Price");
		out.printf("%20s","M_date");
		out.printf("%20s","Exp_date");
		out.printf("%10s","quantity");			
		out.printf("%10s","Tax");
		out.println("\n");
		
		for(int i=0;i<5;i++) {
			out.println();
			
			String tname=str[i];
			out.printf("%-15s",tname);
			expiryStockItems(tname);
		}
		
	}
	public static void expiryStockItems(String tname) throws ClassNotFoundException, SQLException {
		Connection con=getCon();		
		
		log.info("List of Expiry Items on next month");

		String s="select * from "+tname+" where month(exp_date)=month(m_date)+1";	

		PreparedStatement ps=con.prepareStatement(s);		
							
		ResultSet rs=ps.executeQuery();										
		log.debug(ps);
		
		while(rs.next()) {				
			
			out.printf("%-4d",rs.getInt("id"));
			out.printf("%-20s",rs.getString("Items"));
			out.printf("%4s",rs.getInt("Price"));
			out.printf("%20s",rs.getString("M_date"));
			out.printf("%20s",rs.getString("Exp_date"));
			out.printf("%10s",rs.getInt("quantity"));			
			out.printf("%10s",rs.getInt("Tax"));			
			
		}
		
		
	}
	public static void lowStock() throws ClassNotFoundException, SQLException {
		out.printf("%-15s","catagory");
		out.printf("%-4s","id");
		out.printf("%-20s","Items");
		out.printf("%4s","Price");
		out.printf("%20s","M_date");
		out.printf("%20s","Exp_date");
		out.printf("%10s","quantity");			
		out.printf("%10s","Tax");
		out.println("\n");
		
		for(int i=0;i<5;i++) {
			out.println();
			
			String tname=str[i];
			out.printf("%-15s",tname);
			lowStockItems(tname);
		}
		
	}

	public static void lowStockItems(String tname) throws ClassNotFoundException, SQLException {
				
		Connection con=getCon();		
//		Statement stmt=con.createStatement();	
		log.info("List of Low Stock Items");

		String s="select * from "+tname+" where quantity<5";		
		PreparedStatement ps=con.prepareStatement(s);		
//		ps.executeUpdate();								// after deletion we have to update our database
		ResultSet rs=ps.executeQuery();														
		log.debug(ps);
		
		while(rs.next()) {					
			
			out.printf("%-4d",rs.getInt("id"));
			out.printf("%-20s",rs.getString("Items"));
			out.printf("%4s",rs.getInt("Price"));
			out.printf("%20s",rs.getString("M_date"));
			out.printf("%20s",rs.getString("Exp_date"));
			out.printf("%10s",rs.getInt("quantity"));			
			out.printf("%10s",rs.getInt("Tax"));			
			
		}
		
		
	}
	
	
	
	public static Connection getCon() throws ClassNotFoundException, SQLException{
			
			String driver="com.mysql.cj.jdbc.Driver";				// --> 1
			
			String url="jdbc:mysql://localhost:3306/proj";			// --> 2
			String username="root";
			String password="DeV43";
			
			Class.forName(driver);									// --> 3		
			
			Connection con=DriverManager.getConnection(url, username, password);	// --> 4
			
//			System.out.println(con);
//			com.mysql.cj.jdbc.ConnectionImpl@4d6025c5
			
			return con;
		}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		PropertyConfigurator.configure("log4j");			// Store logging info into a file
		
		aSwitch();
	}

}
