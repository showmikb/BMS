package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connections {
	Connection con=null;
	String user = "HJA18ORAUSER2D";
	String pass = "tcshyd";
	String url="jdbc:oracle:thin:@172.25.192.82:1521:javaaodb";
	/*String user = "system";
	String pass = "password";
	String url="jdbc:oracle:thin:@localhost:1521:xe";*/

	public Connection createConnection()
	{
		System.out.println("Entering create connection");
		try
		{  
			System.out.println("\n\nConnecting ...");
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(url,user,pass);  		
			System.out.println("\n\nConnected");
			return con;
		}
		catch(Exception e)
		{ 
			System.out.println("\n\n\t\tError in connection");
			System.out.println(e);
		}  
		return null;
	}
}
