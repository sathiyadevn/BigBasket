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

public class CusOrder {

	private static Logger log =Logger.getLogger(CusOrder.class);

	public static void cSwitch() throws ClassNotFoundException, SQLException {
		//out.println("ok");
		
		out.println("\t\t\t****************************");		
		out.println("				ORDER YOUR ITEMS");
		out.println("\t\t\t****************************\n");
		
		out.println("1 - fruits");
		out.println("2 - graints");
		out.println("3 - cosmetics");
		out.println("4 - cleaning items");
		out.println("5 - desserts");
		out.println("0 - Exit");
		Scanner sc=new Scanner(in);
		out.println("Enter your choice");
		try {
			int i=sc.nextInt();
			switch(i) {
			case 1:
				String tname="fruits";			
				printStmt(tname);			
				break;
			case 2:
				String tname2="graints";
				printStmt(tname2);
				break;
			case 3:
				String tname3="cosmetics";
				printStmt(tname3);
				break;
			case 4:
				String tname4="cleaning";
				printStmt(tname4);
				break;
			case 5:
				String tname5="desserts";
				printStmt(tname5);
				break;
			case 0:
				Login.login();
				break;
			default:
				log.error("Value Exist given limit");
				out.println("Enter value inbetween (1-5)");
				break;
			}
			
		} catch (Exception e) {
			log.warn(e);
			out.println("Enter valid inut as Integer");
			cSwitch();

		}
		
	
	}

	public static void printStmt(String tname) throws ClassNotFoundException, SQLException {
				
		Connection con=getCon();		
//		Statement stmt=con.createStatement();		
		String s="select * from "+tname+"";		
		PreparedStatement ps=con.prepareStatement(s);		
//		ps.executeUpdate();								// after deletion we have to update our database
		ResultSet rs=ps.executeQuery();
		log.debug(ps);
		
		out.println(ps+"\n");		
		
		out.printf("%-4s","id");
		out.printf("%-20s","Items");
		out.printf("%4s","Price");
		out.printf("%20s","M_date");
		out.printf("%20s","Exp_date");
		out.printf("%10s","quantity");			
		out.printf("%10s","Tax");
		out.println("\n");
		
		while(rs.next()) {			
			
			out.printf("%-4d",rs.getInt("id"));
			out.printf("%-20s",rs.getString("Items"));
			out.printf("%4s",rs.getInt("Price"));
			out.printf("%20s",rs.getString("M_date"));
			out.printf("%20s",rs.getString("Exp_date"));
			out.printf("%10s",rs.getInt("quantity"));			
			out.printf("%10s",rs.getInt("Tax"));
			out.println();
			
			
		}
		out.println("\n");
		getOrder(tname);
	}
	
	
	public static void getOrder(String tname) throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(in);
		log.info("Enter product Id and Quantity");
		
		out.println("Enter your product's ID :");
		int id=sc.nextInt();
		
		out.println("Enter quantity :");
		int p_quantity=sc.nextInt();
		
		log.info("Confirm order?");
		out.println("Confirm and continue your order [y/n]:");
		
		if (sc.next().charAt(0)=='y' ) {
			log.info("Order Placed!");
			out.println("order placed into your basket");
			basket(tname,id,p_quantity);	
			getOrder(tname);		
		}	
		else
			cSwitch();
			
	}

	public static void basket(String tname, int id, int p_quantity) throws ClassNotFoundException, SQLException {
		Connection con=getCon();	
		String s="select * from "+tname+" where id=?";		
		PreparedStatement ps=con.prepareStatement(s);		
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();														
		log.debug(ps);	
		
		while(rs.next()) {
			//int p_id=(rs.getInt("id"));
			String item=(rs.getString("Items"));
			int price=(rs.getInt("Price"));
			String M_date=(rs.getString("M_date"));
			String Exp_date=rs.getString("Exp_date");
			//int quantity=(rs.getInt("quantity"));			
			int tax=(rs.getInt("Tax"));
			basketList(id,tname,item,price,p_quantity,M_date,Exp_date,tax);
		}
		
	}

	public static void basketList(int id,String tname,String item,int price,int p_quantity,String M_date,String Exp_date,int tax) throws ClassNotFoundException, SQLException {
		Connection con=getCon();
		log.info("Basket List");
		String s="insert into basket(catagory,items,price,quantity,m_date,exp_date,tax) values (?,?,?,?,?,?,?)";		
		PreparedStatement ps=con.prepareStatement(s);		
		log.debug(ps);
		
		ps.setString(1,tname);
		ps.setString(2,item);
		ps.setInt(3,price);
		ps.setInt(4,p_quantity);
		ps.setString(5,M_date);
		ps.setString(6, Exp_date);
		ps.setInt(7, tax);		
		ps.executeUpdate();	
		
		out.println("oreder into list");
		updateProducts(tname,p_quantity,id);
	}

	public static void updateProducts(String tname,int p_quantity,int id) throws ClassNotFoundException, SQLException {
		Connection con=getCon();	
		log.info("Update roducts");
		String s="update "+tname+" set quantity = (quantity-?) where id=?";		
		PreparedStatement ps=con.prepareStatement(s);	
		log.debug(ps);
		
		ps.setInt(1,p_quantity);
		ps.setInt(2,id);		
		
		ps.executeUpdate();	
		
		out.println("Product quantity updated");
		cSwitch();
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

		cSwitch();
		
	}

}
