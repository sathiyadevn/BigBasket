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
public class Cus {
	private static Logger log =Logger.getLogger(Cus.class);

	public static void setPass(String name3,String name,String mail_id,String phn_no) throws ClassNotFoundException, SQLException {
		log.info("Create password for new User");
		Scanner sc=new Scanner(in);
		out.println("Enter your password");
		String pass=sc.nextLine();
		out.println("Confirm your password");
		String pass1=sc.nextLine();
		
		if(pass.equals(pass1)) {
			setUser(name3,name,mail_id,phn_no,pass);
			log.info("Your password was set successfully ");
		}			
		else {
			log.warn("Password mismatched");
			out.println("password mismatched.Try Again...");
			setPass(name3,name,mail_id,phn_no);
		}
	}
	public static void newUser(String name3) throws ClassNotFoundException, SQLException {
		log.info("Create new user with credentials");
		Scanner sc=new Scanner(in);
		out.println("Enter user's name");
		String name=sc.nextLine();
		out.println("Enter user's mail_id");
		String mail_id=sc.nextLine();		
		out.println("Enter user's phone number");
		String phn_no=sc.nextLine();					
		setPass(name3,name,mail_id,phn_no);
	}
	
	public static void setUser(String name3,String name,String mail_id,String phn_no,String pass) throws ClassNotFoundException, SQLException {
		Connection con=getCon();		
		
		String s="insert into "+name3+"(name,mail_id,phn_no,pass) values(?,?,?,?)";		
		PreparedStatement ps=con.prepareStatement(s);
		log.debug(ps);
		
		ps.setString(1, name);
		ps.setString(2, mail_id);
		ps.setString(3, phn_no);
		ps.setString(4, pass);
		
		out.println("New "+name3+" created");
		ps.executeUpdate();	
		log.info("New user created Successfully");
		Login.login();
		
		
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
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j");		
	}

}
