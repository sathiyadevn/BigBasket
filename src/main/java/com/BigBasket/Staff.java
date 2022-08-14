package com.BigBasket;

import static java.lang.System.in;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Staff {
	
	private static Logger log =Logger.getLogger(Staff.class);

	public static void sSwitch() throws ClassNotFoundException, SQLException {
		
		//out.println("ok")
		out.println("\t\t\t****************************");		
		out.println("				STAFF PAGE");
		out.println("\t\t\t****************************\n");
		
		out.println("1 - View basket");	
		out.println("2 - Generate bill");
		out.println("0 - Exit");
		Scanner sc=new Scanner(in);
		out.println("Enter your Choice (1-2): ");
		try {
			int i=sc.nextInt();
			switch(i) {
			case 1:				
				viewBasket();
				break;
			case 2:
				out.println("1 - pay by Cash");	
				out.println("2 - pay by UPI(2% discount)");	
				out.println("3 - pay by Credit Card (4% discount)");	
				out.println("Enter your Choice (1-3): ");
				try {
					int j=sc.nextInt();
					switch(j) {
					case 1:						
						total(j);
						break;					
					case 2:						
						total(j);
						break;				
					case 3:						
						total(j);				
						break;
					case 0:
						sSwitch();
						break;
					default:
						out.println("Enter value inbetween (1-3)");
						log.error("Value Exit given limit");
					}
				} catch (Exception e) {
					log.warn(e);
					out.print("Enter valid input as Integer");
					sSwitch();
				}				
			break;
			case 0:
				Login.login();
				break;
			default:
				out.println("Enter valid input as Integer");
				log.error("Value Exit given limit");
				break;
			}
			
		} catch (Exception e) {
			log.warn(e);
			out.print("Enter valid input as Integer");
			sSwitch();
		}		
	}		
	public static void total(int i) throws ClassNotFoundException, SQLException  {		

			out.println("****************************");		
			out.println("\tTOTAL BILL AMOUNT");
			out.println("****************************\n");
		
			try {
				Connection con=getCon();	
				log.info("Total of each items");
				
				String s="select *,(price*quantity+(price*quantity*tax)/100) as Total from basket";		
				PreparedStatement ps=con.prepareStatement(s);					
				ResultSet rs=ps.executeQuery();		
				
				log.debug(ps);
				Formatter fmt=new Formatter();
				fmt.format("%5s %12s %7s %10s %8s %10s  \n\n" ,"ID","ITEMS","PRICE","QUANTITY","TAX %","TOTAL");
				while(rs.next()) {					
					
					fmt.format("%5s %12s %7s %10s %8s %10s \n" ,rs.getInt("id"),rs.getString("Items"),rs.getInt("Price"),rs.getInt("quantity"),rs.getInt("Tax"),rs.getFloat("Total"));	
				}

				out.println(fmt);
				
			} catch (Exception e) {				
				log.warn(e);
				
			} 			
			totalBill(i);
	
		}			
			public static void totalBill(int i) throws ClassNotFoundException, SQLException {		
				
				Connection con=getCon();	
				float sumTotal;
				log.info("Total Amount");
				
				String s="select sum(price*quantity+(price*quantity*tax)/100) as sum from basket";		
				PreparedStatement ps=con.prepareStatement(s);	
				log.debug(ps);
				
				ResultSet rs=ps.executeQuery();					
				
				while(rs.next()) {
					sumTotal=rs.getFloat("sum");
					
					if(i==1) {
						log.info("Paid on cash");
						out.println("\n Total Amount : "+sumTotal);
					}
						
					else if(i==2) {
						log.info("Paid on UPI and got 2% discount");
						out.println("\n Total Amount : "+(sumTotal-(sumTotal*2/100)));
						out.println("\n Saved : "+(sumTotal-((sumTotal-(sumTotal*2/100)))));
					}
						
					else if(i==3) {
						log.info("Paid on Credit Card and got 4% discount");
						out.println("\n Total Amount : "+(sumTotal-(sumTotal*4/100)));	
						out.println("\n Saved : "+(sumTotal-((sumTotal-(sumTotal*4/100)))));
					}
						
				}		
					
				}					
	
		public static void viewBasket() throws SQLException, ClassNotFoundException {
			Connection con=getCon();
			log.info("View Basket");
			
			String s="select * from basket";		
			PreparedStatement ps=con.prepareStatement(s);	
			
			ResultSet rs=ps.executeQuery();	
			log.debug(ps);
			
			Formatter fmt=new Formatter();
			fmt.format("%5s %12s %12s %7s %15s %15s %10s %8s \n\n" ,"ID","CATAGORY","ITEMS","PRICE","MFG_DATE","EXP_DATE","QUANTITY","TAX %");
			while(rs.next()) {					
				
				fmt.format("%5s %12s %12s %7s %15s %15s %10s %8s \n" ,rs.getInt("id"),rs.getString("catagory"),rs.getString("Items"),rs.getInt("Price"),rs.getString("M_date"),rs.getString("Exp_date"),rs.getInt("quantity"),rs.getInt("Tax"));	
			}
			out.println(fmt);
			
		}	
		public static Connection getCon() throws ClassNotFoundException, SQLException{
			
			String driver="com.mysql.cj.jdbc.Driver";				// --> 1
			
			String url="jdbc:mysql://localhost:3306/proj";			// --> 2
			String username="root";
			String password="DeV43";
			
			Class.forName(driver);									// --> 3		
			
			Connection con=DriverManager.getConnection(url, username, password);	// --> 4		
			
			return con;
		}
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			PropertyConfigurator.configure("log4j");			

			sSwitch();
		}

}
