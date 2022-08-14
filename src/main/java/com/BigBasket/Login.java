package com.BigBasket;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Login {
	private static Logger log =Logger.getLogger(Login.class);

	public static void setNewPass(String name,int i) throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(in);
		log.info("Create new Password");
		out.println("Enter New Password");
		String new1=sc.nextLine();
		out.println("Retype New Password");
		String new2=sc.nextLine();
		if(new1.equals(new2)) {
			newPass(name,new1,i);
			
		}
		else
			setNewPass(name,i);
			
}	
	public static void newPass(String name,String newPass,int i) throws ClassNotFoundException, SQLException {
		Connection con=getCon();		
		String s="update "+name+" set pass=? where id=?";		
		PreparedStatement ps=con.prepareStatement(s);		
		log.debug(ps);		

		ps.setString(1,newPass);
		ps.setInt(2, i);

		//ResultSet rs=ps.executeQuery();	
		log.info("Your password has been successfully reset");
		
		out.println("your password has been successfully reset");
		ps.executeUpdate();								// after deletion we have to update our database
		log.info("Now Login with your new password");
		login();
		
	}
	public static void data(String name,String aName,String aPass) throws ClassNotFoundException, SQLException{
		Connection con=getCon();		
//		Statement stmt=con.createStatement();		
		String s="select * from "+name+" where name=? and pass=?";
		
		PreparedStatement ps=con.prepareStatement(s);		
		log.debug(ps);		

		ps.setString(1,aName);
		ps.setString(2,aPass);
		
//		ps.executeUpdate();								// after deletion we have to update our database
		ResultSet rs=ps.executeQuery();		
		
		if(rs.next()) {
			int i=rs.getInt("id");
//			out.println(i);				
			if(i%1==0)
				log.info("Login Successful");					
				if(name.equals("Admin"))
					Admin.aSwitch();
				else if(name.equals("Staff"))
					Staff.sSwitch();
				else if(name.equals("Customer"))
					CusOrder.cSwitch();					
		}			
		else {
			out.println("Not a valid User_name and Password \n");
			log.warn("Invalid Username and Password! Have a look at respective tables ");
			out.println("1 - Try Again");
			out.println("2 - Forgot Password");	
			out.println("0 - Exit");	
			Scanner sc=new Scanner(in);
			out.print("Enter your Choice (1-2): ");
			try {
				int in=sc.nextInt();
				switch(in) {
				case 1:
					log.warn("Make sure with your Username and Password are correct");
					login();
					break;
				case 2:				
					forgot(name);
					break;
				case 0:
					login();
					break;
				default:
					log.error("Value Exits given limit");
					out.println("Enter input within the range (1-2)");
					break;
				}
			} catch (Exception e) {
				log.warn(e);
				out.println("Enter valid Input as Integer \n");
				login();
				
			}					
		}			
	}		
//	}		
	
	public static void forgot(String name) throws SQLException, ClassNotFoundException {
		Scanner sc=new Scanner(in);	
		log.info("If you Forgot Password Enter your gmailId and phoneNumber ");
		
		out.println("Enter your Gmail");
		String mail_id=sc.nextLine();
		out.println("Enter your Phone Number");
		String phn_id=sc.nextLine();

		Connection con=getCon();	
		String s="select * from "+name+" where mail_id=? and phn_no=? ";		
		PreparedStatement ps=con.prepareStatement(s);
		
		ps.setString(1,mail_id);
		ps.setString(2,phn_id);
		ResultSet rs=ps.executeQuery();				
		log.debug(ps);		

		if(rs.next()) {
			
			int i=rs.getInt("id");
//			out.println(i);				
			if(i%1==0) {
				setNewPass(name,i);				
			}	
		}
		else {
			log.warn("Invalid mailId and phoneNumber.Have a look at respective tables");
		
			out.println("Not a valid Mail_id and PhoneNumber \n");				
			out.println("1 - Try Again");	
			out.println("0 - Exit");	

			out.println("Enter your Choice : ");
			try {
				int in=sc.nextInt();
				switch(in) {
				case 1:
					forgot(name);
					break;
				case 0:
					login();
				default:
					log.error("Value exits limits");
					out.println("Enter valid option 1 ");
					forgot(name);
				}
			} catch (Exception e) {
				log.warn(e);
				out.println("Enter valid input as Integer");
				forgot(name);
				
			}
			
		}		
	
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
		PropertyConfigurator.configure("log4j");			// Store logging info into a file

		out.println("\t\t\t****************************");		
		out.println("				BIG BASKET");
		out.println("\t\t\t****************************\n");
		
		login();
	}
	public static void login() throws ClassNotFoundException, SQLException {
//		out.println("triggered");
		out.println("****************************");		
		out.println("\tLOGIN PAGE");
		out.println("****************************\n");
//		out.println("Choose 1-3 for login as ");
		
		out.println("1 - Admin");
		out.println("2 - Staff");
		out.println("3 - Customer ");
		log.info("Have a look at respective tables for Username and Password");
		
		Scanner sc=new Scanner(in);
		
		out.print("Select your choice (1-3): ");
		try {
			int i=sc.nextInt();
			loginSwitch(i);
		} catch (Exception e) {
			log.warn(e);
			out.println("Enter valid Input as Integer \n");
			login();
		}			
		
	}
	public static void namePass(String name) throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(in);
		out.println("Enter "+name+" Name");
		String aName=sc.nextLine();
		out.println("Enter "+name+" Password");
		String aPass=sc.nextLine();
		data(name,aName,aPass);
	}
	
	public static void loginSwitch(int i) throws ClassNotFoundException, SQLException {
		//out.println("triggered");
		Scanner sc=new Scanner(in);
		try {
			switch(i) {			
			case 1:
				String name="Admin";
				namePass(name);			
				break;
			case 2:
				String name1="Staff";
				namePass(name1);	
				break;
			case 3:
				out.println("1 - Existing user (5% Extra discount)");
				out.println("2 - Create new User");
				out.println("0 - Exit");
				
				try {
					int inn=sc.nextInt();
					switch(inn) {
					case 1:
						String name2="Customer";
						namePass(name2);	
						break;
					case 2:
						String name3="Customer";
						Cus.newUser(name3);
						break;
					case 0:
						login();
						break;
					default:
						log.error("Value exits given limits");
						out.println("Enter value inbetween (1-2)");
						break;
					}
				} catch (Exception e) {
					log.warn(e);
					out.println("Enter valid input as Integer");
					login();
				}
				
			break;	
			
			default:
				log.error("Value Exist given limit");
				out.println("Enter input within the range (1-3) \n");
				login();
				break;
			}
		} catch (Exception e) {
			log.warn(e);
			out.println("Enter valid input as Integer");
			loginSwitch(i);
		}
		
	}
}












