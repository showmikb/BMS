package com.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

import javax.servlet.ServletException;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.service.Service;
import com.util.Connections;
import com.util.Dconnection;

public class Dao {
	
	Connections conn;
	PreparedStatement pst = null;
	String query="";
	
/*/////////////////////////////////////////////CREATE CUSTOMER \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
	public boolean ccust(String ssnid, String password, String cuname, String cuaddress, int cuage)throws ServletException, IOException
	{
		
		  String tokenapipayload = "";
		  String apipayload = "";
		  String access_token = "";
		  URL urltoken = null;
		  URL urlapi = null;
		  HttpURLConnection connection = null;
		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter writer = null;
		  OutputStreamWriter apiwriter = null; 
		  Properties properties = new Properties ();
		  String CustID = ssnid; 
		  //int cuage = Integer.parseInt(age);
		
		  
		  try {  

			  //urltoken = new URL("http://apphonics.tcs.com/token/");
			  urltoken = new URL("http://172.16.221.147/token/"); //Use this ip address if you observe timeout with apphonics.tcs.com (if calling from inside TCS INDIA network) 
			  //urltoken = new URL("http://125.17.73.221/token"); //Use ip this address if you observe timeout with apphonics.tcs.com (if calling from outside TCS network)
			  connection = (HttpURLConnection) urltoken.openConnection();
			  connection.setDoInput(true);
			  connection.setDoOutput(true);  
			  connection.setConnectTimeout(5000);
			  connection.setReadTimeout(10000);
			  connection.setRequestMethod("POST");
			  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			  connection.setRequestProperty("Authorization", "Basic aUtqUGNCbTJUak5JTU4yWDNESWpkdWxoMUZnYTpxTmNvaEMwRUJBMWFvdFpJV3pTdFZKUUVWdFVh");
		  	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		      tokenapipayload = "grant_type=client_credentials&scope=Account_read+Manager+Teller";
		     // password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller
			  writer.write(tokenapipayload);
			  writer.close();

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString() );
		}

		System.out.println("*** Response code: " + connection.getResponseCode() );

		  if (connection.getResponseCode() != 200) {
		    throw new IOException(connection.getResponseMessage());
		  }

		// Buffer the result into a string
		 
		 BufferedReader rdtoken = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  StringBuilder sbtoken = new StringBuilder();
		  String linetoken;

		  while ((linetoken = rdtoken.readLine()) != null) {
		    sbtoken.append(linetoken);
		  }
		  System.out.println("*** API Respo
		  nse *** " + sbtoken.toString());
		  
		  
		  
		  //get JsonObject from JSONTokener from InputStream
		  JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
		  access_token = jsonObject.getString("access_token");   
		  connection.disconnect();
		  connection = null;
		  urltoken = null;
		 
		   try {  

			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			 // urlapi = new URL("http://172.16.221.147/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			  //urlapi = new URL("http://125.17.73.221/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/actst");	  
			  //urlapi = new URL("http://172.16.221.147/public/customers/chkcus");
			  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
			  apphconnection.setDoInput(true);
			  apphconnection.setDoOutput(true);  
			  apphconnection.setConnectTimeout(5000);
			  apphconnection.setReadTimeout(10000);	  
			  apphconnection.setRequestMethod("POST");
			  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
		      apphconnection.setRequestProperty("Content-Type", "application/json");
		  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
//		      properties.load(filereader); 
			 // CustID = properties.getProperty("CUSTID");
			  apipayload = "{\"request\": {\"resource\": \"createcustomer\", \"customer\": { \"ssnid\": "+ssnid+", \"cuname\": "+cuname+", \"cuaddress\": "+cuaddress+", \"cuage\": "+cuage+" }}}"; //For /crtcus
			  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"100000060\" }}}"; //For /actst
			  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"" + CustID + "\" }}}"; //For /actst
			  //apipayload = "{\"request\": {\"resource\": \"checkcustomer\", \"customer\": { \"cuid\": \"100000301\", \"ssnid\": \"900000011\" }}}"; //For /chkcus
			  System.out.println("*** apipayload *** : " + apipayload);		  	  
			  apiwriter.write(apipayload);
			  apiwriter.close();  

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString());
		}

		 
		  // if (apphconnection.getResponseCode() != 200) {
			//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
		    //throw new IOException(apphconnection.getResponseMessage());
		 // }


		  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
//		 JSONObject obj = new JSONObject(apphconnection.getInputStream());
		// Buffer the result into a string
		  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;

		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  System.out.println("*** API Response *** " + sb.toString());
//		  String msg = obj.getString("ws_sta_msg");
//		  String msg= sb.substring(1);
		
			
		  if(sb.toString().contains("WRITE SUCCESSFUL"))
		  {
			  System.out.println("Bulls EYE : write");
			  
				System.out.println("generated password"+password);
				try
				{
					conn = new Connections();
					Connection con = conn.createConnection();
					if(con != null)
					{
						query = "insert into userstore values(?,?,?)";
						
						pst = con.prepareStatement(query);
						pst.setString(1, ssnid);
						pst.setString(3, cuname);
						pst.setString(2, password);
						
						int result = pst.executeUpdate();
						
						if(result == 1)
						{
							return true;
						}
						
					}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		  }
		 
		  rd.close();

		  apphconnection.disconnect();
		  apphconnection = null;
		  urlapi = null; 
		  
		  
		
		
		return false;
	}
	
	
/////////////////////////////////////////////CREATE CUSTOMER OVER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	
*/	
	
	
	public boolean ccust(String ssnid, String password, String cuname, String cuaddress, int cuage)throws ServletException, IOException
	{
		
		  String tokenapipayload = "";
		  String apipayload = "";
		  String access_token = "";
		  URL urltoken = null;
		  URL urlapi = null;
		  HttpURLConnection connection = null;
		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter writer = null;
		  OutputStreamWriter apiwriter = null; 
		  Properties properties = new Properties ();
		  String CustID = ssnid; 
		
		
		  
		  try {  

			  //urltoken = new URL("http://apphonics.tcs.com/token/");
			  urltoken = new URL("http://172.16.221.147/token/"); //Use this ip address if you observe timeout with apphonics.tcs.com (if calling from inside TCS INDIA network) 
			 //urltoken = new URL("http://125.17.73.221/token"); //Use ip this address if you observe timeout with apphonics.tcs.com (if calling from outside TCS network)
			  connection = (HttpURLConnection) urltoken.openConnection();
			  connection.setDoInput(true);
			  connection.setDoOutput(true);  
			  connection.setConnectTimeout(5000);
			  connection.setReadTimeout(10000);
			  connection.setRequestMethod("POST");
			  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			  connection.setRequestProperty("Authorization", "Basic aUtqUGNCbTJUak5JTU4yWDNESWpkdWxoMUZnYTpxTmNvaEMwRUJBMWFvdFpJV3pTdFZKUUVWdFVh");
		  	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		      tokenapipayload = "grant_type=client_credentials&scope=Account_read+Manager+Teller";
		     // password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller
			  writer.write(tokenapipayload);
			  writer.close();

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString() );
		}

		System.out.println("*** Response code: " + connection.getResponseCode() );

		  if (connection.getResponseCode() != 200) {
		    throw new IOException(connection.getResponseMessage());
		  }

		// Buffer the result into a string
		/* 
		 BufferedReader rdtoken = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  StringBuilder sbtoken = new StringBuilder();
		  String linetoken;

		  while ((linetoken = rdtoken.readLine()) != null) {
		    sbtoken.append(linetoken);
		  }
		  System.out.println("*** API Response *** " + sbtoken.toString());
		*/  
		  
		  
		  //get JsonObject from JSONTokener from InputStream
		  JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
		  access_token = jsonObject.getString("access_token");   
		  connection.disconnect();
		  connection = null;
		  urltoken = null;
		 
		   try {  

			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			 //urlapi = new URL("http://172.16.221.147/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			  //urlapi = new URL("http://125.17.73.221/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/actst");	  
			  //urlapi = new URL("http://172.16.221.147/public/customers/chkcus");
			  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
			  apphconnection.setDoInput(true);
			  apphconnection.setDoOutput(true);  
			  apphconnection.setConnectTimeout(5000);
			  apphconnection.setReadTimeout(10000);	  
			  apphconnection.setRequestMethod("POST");
			  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
		      apphconnection.setRequestProperty("Content-Type", "application/json");
		  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
//		      properties.load(filereader); 
			 // CustID = properties.getProperty("CUSTID");
			  apipayload = "{\"request\": {\"resource\": \"createcustomer\", \"customer\": { \"ssnid\": "+ssnid+", \"cuname\": "+cuname+", \"cuaddress\": "+cuaddress+", \"cuage\": "+cuage+" }}}"; //For /crtcus
			  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"100000060\" }}}"; //For /actst
			  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"" + CustID + "\" }}}"; //For /actst
			  //apipayload = "{\"request\": {\"resource\": \"checkcustomer\", \"customer\": { \"cuid\": \"100000301\", \"ssnid\": \"900000011\" }}}"; //For /chkcus
			  System.out.println("*** apipayload *** : " + apipayload);		  	  
			  apiwriter.write(apipayload);
			  apiwriter.close();  

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString());
		}

		 
		  // if (apphconnection.getResponseCode() != 200) {
			//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
		    //throw new IOException(apphconnection.getResponseMessage());
		 // }


		  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
//		 JSONObject obj = new JSONObject(apphconnection.getInputStream());
		// Buffer the result into a string
		  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;

		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  System.out.println("*** API Response *** " + sb.toString());
//		  String msg = obj.getString("ws_sta_msg");
//		  String msg= sb.substring(1);
		
			
		  if(sb.toString().contains("WRITE SUCCESSFUL"))
		  {
			  System.out.println("Bulls EYE : write");
			 
				System.out.println("generated password"+password);
				try
				{
					conn = new Connections();
					Connection con = conn.createConnection();
					if(con != null)
					{
						query = "insert into userstore values(?,?,?,?,?)";
						
						pst = con.prepareStatement(query);
						pst.setString(1, ssnid);
						pst.setString(2, password);
						pst.setTimestamp(3,getCurrentTimeStamp());;
						pst.setString(4, "customer");
						pst.setString(5, cuname);
						
						int result = pst.executeUpdate();
						
						if(result == 1)
						{
							return true;
						}
						
					}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		  }
		 
		  rd.close();

		  apphconnection.disconnect();
		  apphconnection = null;
		  urlapi = null; 
		  
		  
		
		
		return false;
	}
	
	
	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	
/////////////////////////////////////////////CREATE CUSTOMER OVER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	
	
	
	
/////////////////////////////////////////////CHECK CUSTOMER STATUS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public boolean chkcust(String ssnid, String password, String cuname, String cuaddress, int cuage)throws ServletException, IOException
	{
		
		  String tokenapipayload = "";
		  String apipayload = "";
		  String access_token = "";
		  URL urltoken = null;
		  URL urlapi = null;
		  HttpURLConnection connection = null;
		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter writer = null;
		  OutputStreamWriter apiwriter = null; 
		  Properties properties = new Properties ();
		  String CustID = ssnid; 
		
		
		  
		  try {  

			  //urltoken = new URL("http://apphonics.tcs.com/token/");
			  urltoken = new URL("http://172.16.221.147/token/"); //Use this ip address if you observe timeout with apphonics.tcs.com (if calling from inside TCS INDIA network) 
			 //urltoken = new URL("http://125.17.73.221/token"); //Use ip this address if you observe timeout with apphonics.tcs.com (if calling from outside TCS network)
			  connection = (HttpURLConnection) urltoken.openConnection();
			  connection.setDoInput(true);
			  connection.setDoOutput(true);  
			  connection.setConnectTimeout(5000);
			  connection.setReadTimeout(10000);
			  connection.setRequestMethod("POST");
			  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			  connection.setRequestProperty("Authorization", "Basic aUtqUGNCbTJUak5JTU4yWDNESWpkdWxoMUZnYTpxTmNvaEMwRUJBMWFvdFpJV3pTdFZKUUVWdFVh");
		  	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		      tokenapipayload = "grant_type=client_credentials&scope=Account_read+Manager+Teller";
		     // password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller
			  writer.write(tokenapipayload);
			  writer.close();

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString() );
		}

		System.out.println("*** Response code: " + connection.getResponseCode() );

		  if (connection.getResponseCode() != 200) {
		    throw new IOException(connection.getResponseMessage());
		  }

		// Buffer the result into a string
		/* 
		 BufferedReader rdtoken = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  StringBuilder sbtoken = new StringBuilder();
		  String linetoken;

		  while ((linetoken = rdtoken.readLine()) != null) {
		    sbtoken.append(linetoken);
		  }
		  System.out.println("*** API Response *** " + sbtoken.toString());
		*/  
		  
		  
		  //get JsonObject from JSONTokener from InputStream
		  JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
		  access_token = jsonObject.getString("access_token");   
		  connection.disconnect();
		  connection = null;
		  urltoken = null;
		 
		   try {  

			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			 //urlapi = new URL("http://172.16.221.147/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			  //urlapi = new URL("http://125.17.73.221/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/actst");	  
			  urlapi = new URL("http://172.16.221.147/public/customers/chkcus");
			  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
			  apphconnection.setDoInput(true);
			  apphconnection.setDoOutput(true);  
			  apphconnection.setConnectTimeout(5000);
			  apphconnection.setReadTimeout(10000);	  
			  apphconnection.setRequestMethod("POST");
			  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
		      apphconnection.setRequestProperty("Content-Type", "application/json");
		  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
//		      properties.load(filereader); 
			  CustID = properties.getProperty("CUSTID");
			  //apipayload = "{\"request\": {\"resource\": \"createcustomer\", \"customer\": { \"ssnid\": "+ssnid+", \"cuname\": "+cuname+", \"cuaddress\": "+cuaddress+", \"cuage\": "+cuage+" }}}"; //For /crtcus
			  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"100000060\" }}}"; //For /actst
			  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"" + CustID + "\" }}}"; //For /actst
			  //apipayload = "{\"request\": {\"resource\": \"checkcustomer\", \"customer\": { \"cuid\": \"100000301\", \"ssnid\": \"900000011\" }}}"; //For /chkcus
			  System.out.println("*** apipayload *** : " + apipayload);		  	  
			  apiwriter.write(apipayload);
			  apiwriter.close();  

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString());
		}

		 
		  // if (apphconnection.getResponseCode() != 200) {
			//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
		    //throw new IOException(apphconnection.getResponseMessage());
		 // }


		  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
//		 JSONObject obj = new JSONObject(apphconnection.getInputStream());
		// Buffer the result into a string
		  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;

		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  System.out.println("*** API Response *** " + sb.toString());
//		  String msg = obj.getString("ws_sta_msg");
//		  String msg= sb.substring(1);
		
			
		  
		 
		  rd.close();

		  apphconnection.disconnect();
		  apphconnection = null;
		  urlapi = null; 
		  
		  
		
		
		return false;
	}
	
	
////////////////////////////////////////////CHECK CUSTOMER OVER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
/////////////////////////////////////////////CHECK CUSTOMER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	
	
public String chcus(String username, String password)
{
	
	
	
			try
			{
				conn = new Connections();
				Connection con = conn.createConnection();
				if(con != null)
				{
					query = "select * from userstore where ssnid=? and password=?";
					System.out.println(username+password);
					pst = con.prepareStatement(query);
					pst.setString(1, username);
					pst.setString(2, password);

					
					ResultSet result = pst.executeQuery();
					
					if(result.next())
					{
						
						return (result.getString("role"));
					}
					
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	  
	 

	  
	

	
	return null;
}


/////////////////////////////////////////////////////GET CUSTOMER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



public String search(String ssnid, String custid) throws IOException
{

	String tokenapipayload = "";
  String apipayload = "";
  String access_token = "";
  URL urltoken = null;
  URL urlapi = null;
  HttpURLConnection connection = null;
  HttpURLConnection apphconnection = null;
  OutputStreamWriter writer = null;
  OutputStreamWriter apiwriter = null; 
  Properties properties = new Properties ();
  String CustID = custid; 
  
  
  
  
  
  try {  

	  //urltoken = new URL("http://apphonics.tcs.com/token/");
	  urltoken = new URL("http://172.16.221.147/token/"); //Use this ip address if you observe timeout with apphonics.tcs.com (if calling from inside TCS INDIA network) 
	  //urltoken = new URL("http://125.17.73.221/token"); //Use ip this address if you observe timeout with apphonics.tcs.com (if calling from outside TCS network)
	  connection = (HttpURLConnection) urltoken.openConnection();
	  connection.setDoInput(true);
	  connection.setDoOutput(true);  
	  connection.setConnectTimeout(5000);
	  connection.setReadTimeout(10000);
	  connection.setRequestMethod("POST");
	  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	  connection.setRequestProperty("Authorization", "Basic aUtqUGNCbTJUak5JTU4yWDNESWpkdWxoMUZnYTpxTmNvaEMwRUJBMWFvdFpJV3pTdFZKUUVWdFVh");
  	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      tokenapipayload = "grant_type=client_credentials&scope=Account_read+Manager+Teller";
     // password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller
	  writer.write(tokenapipayload);
	  writer.close();

  }catch (ConnectException ce){
    System.out.println("*** Exception: " + ce.toString() );
}

System.out.println("*** Response code: " + connection.getResponseCode() );

  if (connection.getResponseCode() != 200) {
    throw new IOException(connection.getResponseMessage());
  }

// Buffer the result into a string
/* 
 BufferedReader rdtoken = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  StringBuilder sbtoken = new StringBuilder();
  String linetoken;

  while ((linetoken = rdtoken.readLine()) != null) {
    sbtoken.append(linetoken);
  }
  System.out.println("*** API Response *** " + sbtoken.toString());
*/  
  
  
  //get JsonObject from JSONTokener from InputStream
  JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
  access_token = jsonObject.getString("access_token");   
  connection.disconnect();
  connection = null;
  urltoken = null;
 
   try {  
	   
	   urlapi = new URL("http://172.16.221.147/public/customers/getcus");

	  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
	  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
	 // urlapi = new URL("http://172.16.221.147/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 
	  //urlapi = new URL("http://125.17.73.221/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
	  //urlapi = new URL("http://apphonics.tcs.com/public/customers/actst");	  
	  //urlapi = new URL("http://172.16.221.147/public/customers/chkcus");
	  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
	  apphconnection.setDoInput(true);
	  apphconnection.setDoOutput(true);  
	  apphconnection.setConnectTimeout(5000);
	  apphconnection.setReadTimeout(10000);	  
	  apphconnection.setRequestMethod("POST");
	  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
      apphconnection.setRequestProperty("Content-Type", "application/json");
  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
//      properties.load(filereader); 
	  CustID = properties.getProperty("CUSTID");
	  //apipayload = "{\"request\": {\"resource\": \"createcustomer\", \"customer\": { \"ssnid\": "+ssnid+", \"cuname\": "+cuname+", \"cuaddress\": "+cuaddress+", \"cuage\": "+cuage+" }}}"; //For /crtcus
	  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"100000060\" }}}"; //For /actst
	  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"" + CustID + "\" }}}"; //For /actst
	  //apipayload = "{\"request\": {\"resource\": \"checkcustomer\", \"customer\": { \"cuid\": \"100000301\", \"ssnid\": \"900000011\" }}}"; //For /chkcus
	  apipayload ="{\"request\": {\"resource\":\"getcustomerdetail\", \"customer\": { \"cuid\": \""+custid+"\" }}}";
	  System.out.println("*** apipayload *** : " + apipayload);		  	  
	  apiwriter.write(apipayload);
	  apiwriter.close();  

  }catch (ConnectException ce){
    System.out.println("*** Exception: " + ce.toString());
}

 
  // if (apphconnection.getResponseCode() != 200) {
	//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
	//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
    //throw new IOException(apphconnection.getResponseMessage());
 // }


  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
  //JSONObject obj = new JSONObject(apphconnection.getInputStream());
// Buffer the result into a string
  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
  StringBuilder sb = new StringBuilder();
  String line;

  while ((line = rd.readLine()) != null) {
    sb.append(line);
  }
  String responseString = sb.toString();
  System.out.println("*** API Response *** " + responseString);
//  String msg = obj.getString("ws_sta_msg");
//  String msg= sb.substring(1);
  
  rd.close();

  apphconnection.disconnect();
  apphconnection = null;
  urlapi = null; 
 
return responseString;
}

/////////////////////////////////////////////////////UPDATE CUSTOMER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


public boolean updatecustomer(String ssnid, String custid, String cuname, String cuaddress, int cuage) throws IOException
{

	String tokenapipayload = "";
  String apipayload = "";
  String access_token = "";
  URL urltoken = null;
  URL urlapi = null;
  HttpURLConnection connection = null;
  HttpURLConnection apphconnection = null;
  OutputStreamWriter writer = null;
  OutputStreamWriter apiwriter = null; 
  Properties properties = new Properties ();
  String CustID = ssnid; 
  
  
  
  
  
  try {  

	  //urltoken = new URL("http://apphonics.tcs.com/token/");
	  urltoken = new URL("http://172.16.221.147/token/"); //Use this ip address if you observe timeout with apphonics.tcs.com (if calling from inside TCS INDIA network) 
	  //urltoken = new URL("http://125.17.73.221/token"); //Use ip this address if you observe timeout with apphonics.tcs.com (if calling from outside TCS network)
	  connection = (HttpURLConnection) urltoken.openConnection();
	  connection.setDoInput(true);
	  connection.setDoOutput(true);  
	  connection.setConnectTimeout(5000);
	  connection.setReadTimeout(10000);
	  connection.setRequestMethod("POST");
	  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	  connection.setRequestProperty("Authorization", "Basic aUtqUGNCbTJUak5JTU4yWDNESWpkdWxoMUZnYTpxTmNvaEMwRUJBMWFvdFpJV3pTdFZKUUVWdFVh");
  	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      tokenapipayload = "grant_type=client_credentials&scope=Account_read+Manager+Teller";
     // password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller
	  writer.write(tokenapipayload);
	  writer.close();

  }catch (ConnectException ce){
    System.out.println("*** Exception: " + ce.toString() );
}

System.out.println("*** Response code: " + connection.getResponseCode() );

  if (connection.getResponseCode() != 200) {
    throw new IOException(connection.getResponseMessage());
  }

// Buffer the result into a string
/* 
 BufferedReader rdtoken = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  StringBuilder sbtoken = new StringBuilder();
  String linetoken;

  while ((linetoken = rdtoken.readLine()) != null) {
    sbtoken.append(linetoken);
  }
  System.out.println("*** API Response *** " + sbtoken.toString());
*/  
  
  
  //get JsonObject from JSONTokener from InputStream
  JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
  access_token = jsonObject.getString("access_token");   
  connection.disconnect();
  connection = null;
  urltoken = null;
 
   try {  
	   
	   urlapi = new URL("http://172.16.221.147/public/customers/updcus");

	  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
	  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
	 // urlapi = new URL("http://172.16.221.147/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 
	  //urlapi = new URL("http://125.17.73.221/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
	  //urlapi = new URL("http://apphonics.tcs.com/public/customers/actst");	  
	  //urlapi = new URL("http://172.16.221.147/public/customers/chkcus");
	  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
	  apphconnection.setDoInput(true);
	  apphconnection.setDoOutput(true);  
	  apphconnection.setConnectTimeout(5000);
	  apphconnection.setReadTimeout(10000);	  
	  apphconnection.setRequestMethod("POST");
	  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
      apphconnection.setRequestProperty("Content-Type", "application/json");
  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
//      properties.load(filereader); 
	 // CustID = properties.getProperty("CUSTID");
	  //apipayload = "{\"request\": {\"resource\": \"createcustomer\", \"customer\": { \"ssnid\": "+ssnid+", \"cuname\": "+cuname+", \"cuaddress\": "+cuaddress+", \"cuage\": "+cuage+" }}}"; //For /crtcus
	  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"100000060\" }}}"; //For /actst
	  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"" + CustID + "\" }}}"; //For /actst
	  //apipayload = "{\"request\": {\"resource\": \"checkcustomer\", \"customer\": { \"cuid\": \"100000301\", \"ssnid\": \"900000011\" }}}"; //For /chkcus
	  //apipayload ="{\"request\": {\"resource\":\"getcustomerdetail\", \"customer\": { \"cuid\": \""+ssnid+"\" }}}";
	  apipayload ="{\"request\": {\"resource\":\"updatecustomer\", \"customer\": { \"ssnid\": \""+ssnid+"\",\"cuid\": \""+custid+"\",\"cuname\": \""+cuname+"\",\"cuaddress\": \""+cuaddress+"\",\"cuage\": \""+cuage+"\"}}}";
	  System.out.println("*** apipayload *** : " + apipayload);		  	  
	  apiwriter.write(apipayload);
	  apiwriter.close();  

  }catch (ConnectException ce){
    System.out.println("*** Exception: " + ce.toString());
}

 
  // if (apphconnection.getResponseCode() != 200) {
	//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
	//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
    //throw new IOException(apphconnection.getResponseMessage());
 // }


  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
// JSONObject obj = new JSONObject(apphconnection.getInputStream());
// Buffer the result into a string
  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
  StringBuilder sb = new StringBuilder();
  String line;

  while ((line = rd.readLine()) != null) {
    sb.append(line);
  }
  System.out.println("*** API Response *** " + sb.toString());
//  String msg = obj.getString("ws_sta_msg");
//  String msg= sb.substring(1);
  
  if(sb.toString().contains("WRITE SUCCESSFUL")||sb.toString().contains("REWRITE SUCCESSFUL"))
  {
	  System.out.println("Bulls EYE : write");
	 
		
		try
		{
			conn = new Connections();
			Connection con = conn.createConnection();
			if(con != null)
			{
				/*String q = "select * from userstore where ssnid=?";
				pst = con.prepareStatement(q);
				pst.setString(1,ssnid);
				System.out.println("before");
				ResultSet rs = pst.executeQuery();
				
				if(rs.next())
				{
					*/
				String name = cuname.trim();
				String snid = ssnid.trim();
				query = "update userstore set name=? where ssnid=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, name);
				
				//ps.setTimestamp(2, getCurrentTimeStamp());
				ps.setString(2, snid);
				int x =ps.executeUpdate();
				System.out.println("query "+query);

				System.out.println("in here + "+x+" name "+name+" ssnid "+ssnid);
				if(x == 1)
					return (true);
				else
					return false;
				//}
				//else
					//return false;
				
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
  }
 
  
  rd.close();

  apphconnection.disconnect();
  apphconnection = null;
  urlapi = null; 
 
return false;
}


//////////////////////////////////////////UPDATE ENDS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


//////////////////////////////////////////DELETE STARTS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


public boolean delete(String ssnid, String custid) throws IOException
{

	String tokenapipayload = "";
  String apipayload = "";
  String access_token = "";
  URL urltoken = null;
  URL urlapi = null;
  HttpURLConnection connection = null;
  HttpURLConnection apphconnection = null;
  OutputStreamWriter writer = null;
  OutputStreamWriter apiwriter = null; 
  Properties properties = new Properties ();
  String CustID = custid; 
  
  
  
  
  
  try {  

	  //urltoken = new URL("http://apphonics.tcs.com/token/");
	  urltoken = new URL("http://172.16.221.147/token/"); //Use this ip address if you observe timeout with apphonics.tcs.com (if calling from inside TCS INDIA network) 
	  //urltoken = new URL("http://125.17.73.221/token"); //Use ip this address if you observe timeout with apphonics.tcs.com (if calling from outside TCS network)
	  connection = (HttpURLConnection) urltoken.openConnection();
	  connection.setDoInput(true);
	  connection.setDoOutput(true);  
	  connection.setConnectTimeout(5000);
	  connection.setReadTimeout(10000);
	  connection.setRequestMethod("POST");
	  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	  connection.setRequestProperty("Authorization", "Basic aUtqUGNCbTJUak5JTU4yWDNESWpkdWxoMUZnYTpxTmNvaEMwRUJBMWFvdFpJV3pTdFZKUUVWdFVh");
  	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      tokenapipayload = "grant_type=client_credentials&scope=Account_read+Manager+Teller";
     // password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller
	  writer.write(tokenapipayload);
	  writer.close();

  }catch (ConnectException ce){
    System.out.println("*** Exception: " + ce.toString() );
}

System.out.println("*** Response code: " + connection.getResponseCode() );

  if (connection.getResponseCode() != 200) {
    throw new IOException(connection.getResponseMessage());
  }

// Buffer the result into a string
/* 
 BufferedReader rdtoken = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  StringBuilder sbtoken = new StringBuilder();
  String linetoken;

  while ((linetoken = rdtoken.readLine()) != null) {
    sbtoken.append(linetoken);
  }
  System.out.println("*** API Response *** " + sbtoken.toString());
*/  
  
  
  //get JsonObject from JSONTokener from InputStream
  JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
  access_token = jsonObject.getString("access_token");   
  connection.disconnect();
  connection = null;
  urltoken = null;
 
   try {  
	   
	   urlapi = new URL("http://172.16.221.147/public/customers/delcus");

	  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
	  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
	 // urlapi = new URL("http://172.16.221.147/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 
	  //urlapi = new URL("http://125.17.73.221/public/customers/actst");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
	  //urlapi = new URL("http://apphonics.tcs.com/public/customers/actst");	  
	  //urlapi = new URL("http://172.16.221.147/public/customers/chkcus");
	  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
	  apphconnection.setDoInput(true);
	  apphconnection.setDoOutput(true);  
	  apphconnection.setConnectTimeout(5000);
	  apphconnection.setReadTimeout(10000);	  
	  apphconnection.setRequestMethod("POST");
	  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
      apphconnection.setRequestProperty("Content-Type", "application/json");
  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
//      properties.load(filereader); 
	 // CustID = properties.getProperty("CUSTID");
	  //apipayload = "{\"request\": {\"resource\": \"createcustomer\", \"customer\": { \"ssnid\": "+ssnid+", \"cuname\": "+cuname+", \"cuaddress\": "+cuaddress+", \"cuage\": "+cuage+" }}}"; //For /crtcus
	  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"100000060\" }}}"; //For /actst
	  //apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \"" + CustID + "\" }}}"; //For /actst
	  //apipayload = "{\"request\": {\"resource\": \"checkcustomer\", \"customer\": { \"cuid\": \"100000301\", \"ssnid\": \"900000011\" }}}"; //For /chkcus
	  //apipayload ="{\"request\": {\"resource\":\"getcustomerdetail\", \"customer\": { \"cuid\": \""+ssnid+"\" }}}";
	  apipayload ="{\"request\": {\"resource\":\"deletecustomer\", \"customer\": {\"cuid\": \""+custid+"\"}}}";
	  System.out.println("*** apipayload *** : " + apipayload);		  	  
	  apiwriter.write(apipayload);
	  apiwriter.close();  

  }catch (ConnectException ce){
    System.out.println("*** Exception: " + ce.toString());
}

 
  // if (apphconnection.getResponseCode() != 200) {
	//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
	//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
    //throw new IOException(apphconnection.getResponseMessage());
 // }


  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
// JSONObject obj = new JSONObject(apphconnection.getInputStream());
// Buffer the result into a string
  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
  StringBuilder sb = new StringBuilder();
  String line;

  while ((line = rd.readLine()) != null) {
    sb.append(line);
  }
  System.out.println("*** API Response *** " + sb.toString());
//  String msg = obj.getString("ws_sta_msg");
//  String msg= sb.substring(1);
  
  if(sb.toString().contains("WRITE SUCCESSFUL"))
  {
	  System.out.println("Bulls EYE : write");
	 
		
		try
		{
			conn = new Connections();
			Connection con = conn.createConnection();
			if(con != null)
			{
				String q = "select * from userstore where ssnid=?";
				pst = con.prepareStatement(q);
				pst.setString(1,ssnid);
				ResultSet rs = pst.executeQuery();
				if(rs.next())
				{
					query = "delete from userstore where ssnid=?";
					
					pst = con.prepareStatement(query);
					pst.setString(1, ssnid);
					
					int result = pst.executeUpdate();
					
					if(result == 1)
					{
						return true;
					}
	
				}
				else
					return false;
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
  }
  
  rd.close();

  apphconnection.disconnect();
  apphconnection = null;
  urlapi = null; 
  
  
  
 
return false;
}



///////////////////////////////////////////////END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
									//	ACCOUNTS DAO
////////////////////////////////////////////////////////////////////////////////////////////////////////////////


public  boolean createAccount(String custid,String acctype,int balance,String crdt,String ltdt,int dur) throws IOException 
{

 String apipayload = "";
 URL urlapi = null;
 HttpURLConnection apphconnection = null;
 OutputStreamWriter apiwriter = null;  
// Properties properties = new Properties ();
 //FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file
 String CustID = custid;
Dconnection dc=new Dconnection();  
 String access_token=dc.dconnection();

  try {  
	  // urlapi = new URL("http://125.17.73.221/public/accounts/actcr");
	  urlapi = new URL("http://172.16.221.147/public/accounts/actcr");	 //Use ip address if you observe timeout with apphonics.tcs.com //If JohnDoe as user for /actcr /actdl or /actck
	// urlapi = new URL("http://apphonics.tcs.com/public/accounts/actcr");	  //If JohnDoe as user for /actcr /actdl or /actck
	 // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");	 //Use ip address if you observe timeout with apphonics.tcs.com //If Danny as user for /deposit /withdraw or /transfer
	  //urlapi = new URL("http://apphonics.tcs.com/public/accounts/deposit");	  //If Danny as user for /deposit /withdraw or /transfer	  
	  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
	  apphconnection.setDoInput(true);
	  apphconnection.setDoOutput(true);  
	  apphconnection.setConnectTimeout(5000);
	  apphconnection.setReadTimeout(5000);	  
	  apphconnection.setRequestMethod("POST");
	  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); //Access is token is valid till
     apphconnection.setRequestProperty("Content-Type", "application/json");
 	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
    // properties.load(filereader); 
	 // CustID = properties.getProperty("CUSTID");	  
	  apipayload = "{\"request\":{\"resource\":\"createaccount\", \"account\":{ \"cuid\":\""+CustID+"\", \"actype\": \""+acctype+"\",\"actbal\":\""+balance+"\",\"actcrdt\":\""+crdt+"\",\"actlasttrdt\":\""+ltdt+"\",\"actdurtn\":\""+dur+"\" }}}"; //If JohnDoe as user for /actcr /actdl or /actck
	  //apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"100000054\", \"custname\": \"Anudeep\", \"actype\": \"S\", \"acid\": \"111011026\", \"amount\": \"1000\", \"txndate\": \"2016-04-04\" } } }"; //If Danny as user for /deposit /withdraw or /transfer
	//apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"" + CustID + "\", \"custname\": \"ARIN\", \"actype\": \"C\", \"acid\": \"111011008\", \"amount\": \"100000\", \"txndate\": \"2016-06-03\" } } }"; //If Danny as user for /deposit /withdraw or /transfer	  
	  System.out.println("*** apipayload *** : " + apipayload);		  	  
	  apiwriter.write(apipayload);
	  apiwriter.close();  

 }catch (ConnectException ce){
   System.out.println("*** Exception: " + ce.toString());
}

 System.out.println("*** API Response Code = " + apphconnection.getResponseCode());


 if (apphconnection.getResponseCode() == 200) {
		
	  System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
		System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
		// Buffer the result into a string
		  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;

		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  System.out.println("*** API Response *** " + sb.toString());
		  rd.close();
		  return true;
		//throw new IOException(apphconnection.getResponseMessage());
 }

	  else if (apphconnection.getResponseCode() == 401) {
			//System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			//int responsecode=apphconnection.getResponseCode();
			return false;
			//throw new IOException(apphconnection.getResponseMessage());
			 
	  }
	  else if (apphconnection.getResponseCode() == 500) {
		  return false;
		  //System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			//int responsecode=apphconnection.getResponseCode();
			//throw new IOException(apphconnection.getResponseMessage());
		 
	  }
	  
		  //int responsecode=apphconnection.getResponseCode();
		  //System.out.println(" default error");
		  
	  
 


 apphconnection.disconnect();
 apphconnection = null;
 urlapi = null; 
return false;

}

public boolean DelAccount(String custid,String accid,String acctype) throws IOException
{

	  String apipayload = "";
	  URL urlapi = null;
	  HttpURLConnection apphconnection = null;
	  OutputStreamWriter apiwriter = null;  
	 // Properties properties = new Properties ();
	  //FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file
	  String CustID = custid;
	  
	Dconnection dc=new Dconnection();
	String access_token=dc.dconnection();
	 
	   try {  
			 // urlapi = new URL("http://125.17.73.221/public/accounts/actdl");
		 urlapi = new URL("http://172.16.221.147/public/accounts/actdl");	 //Use ip address if you observe timeout with apphonics.tcs.com //If JohnDoe as user for /actcr /actdl or /actck
		// urlapi = new URL("http://apphonics.tcs.com/public/accounts/actcr");	  //If JohnDoe as user for /actcr /actdl or /actck
		 // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");	 //Use ip address if you observe timeout with apphonics.tcs.com //If Danny as user for /deposit /withdraw or /transfer
		  //urlapi = new URL("http://apphonics.tcs.com/public/accounts/deposit");	  //If Danny as user for /deposit /withdraw or /transfer	  
		// urlapi = new URL("https://apphonics.tcs.com/accounts/actdl");
		 apphconnection = (HttpURLConnection) urlapi.openConnection(); 
		  apphconnection.setDoInput(true);
		  apphconnection.setDoOutput(true);  
		  apphconnection.setConnectTimeout(5000);
		  apphconnection.setReadTimeout(5000);	  
		  apphconnection.setRequestMethod("POST");
		  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); //Access is token is valid till
	      apphconnection.setRequestProperty("Content-Type", "application/json");
	  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
	     // properties.load(filereader); 
		 // CustID = properties.getProperty("CUSTID");	  
		 // apipayload = "{\"request\":{\"resource\":\"createaccount\", \"account\":{ \"cuid\":\""+CustID+"\", \"actype\": \""+acctype+"\",\"actbal\":\""+balance+"\",\"actcrdt\":\""+crdt+"\",\"actlasttrdt\":\""+ltdt+"\",\"actdurtn\":\""+dur+"\" }}}"; //If JohnDoe as user for /actcr /actdl or /actck
		  //apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"100000054\", \"custname\": \"Anudeep\", \"actype\": \"S\", \"acid\": \"111011026\", \"amount\": \"1000\", \"txndate\": \"2016-04-04\" } } }"; //If Danny as user for /deposit /withdraw or /transfer
		//apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"" + CustID + "\", \"custname\": \"ARIN\", \"actype\": \"C\", \"acid\": \"111011008\", \"amount\": \"100000\", \"txndate\": \"2016-06-03\" } } }"; //If Danny as user for /deposit /withdraw or /transfer	  
	  	apipayload = "{\"request\": {\"resource\": \"deleteaccount\",\"account\": {\"cuid\":\""+CustID+"\", \"actid\": \""+accid+"\",\"actype\":\""+acctype+"\" }}}";	;
	  	  System.out.println("*** apipayload *** : " + apipayload);		  	  
		  apiwriter.write(apipayload);
		  apiwriter.close();  

	  }catch (ConnectException ce){
	    System.out.println("*** Exception: " + ce.toString());
	}

	  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
	 
	  if (apphconnection.getResponseCode() == 200) {
			
		  System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			// Buffer the result into a string
			  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
			  StringBuilder sb = new StringBuilder();
			  String line;

			  while ((line = rd.readLine()) != null) {
			    sb.append(line);
			  }
			  System.out.println("*** API Response *** " + sb.toString());
			  rd.close();
			  return true;
			//throw new IOException(apphconnection.getResponseMessage());
	  }

		  else if (apphconnection.getResponseCode() == 401) {
				System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				return false;
				//throw new IOException(apphconnection.getResponseMessage());
				 
		  }
		  else if (apphconnection.getResponseCode() == 500) {
			  return false;
			  //System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				//throw new IOException(apphconnection.getResponseMessage());
			 
		  }
		  else{
			  //int responsecode=apphconnection.getResponseCode();
			  System.out.println(" default error");
			  
		  }
	  
	 

	  apphconnection.disconnect();
	  apphconnection = null;
	  urlapi = null; 
	 return false;
	 }
public String getAccountStatus(String custid) throws IOException
{

	  String apipayload = "";
	  URL urlapi = null;
	  HttpURLConnection apphconnection = null;
	  OutputStreamWriter apiwriter = null;  
	 // Properties properties = new Properties ();
	  //FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file
	  
	  
	Dconnection dc=new Dconnection();
	String access_token=dc.jconnection();
	 
	   try {  
			 // urlapi = new URL("http://125.17.73.221/public/accounts/actdl");
		 urlapi = new URL("http://172.16.221.147/public/accounts/actck");	 //Use ip address if you observe timeout with apphonics.tcs.com //If JohnDoe as user for /actcr /actdl or /actck
		// urlapi = new URL("http://apphonics.tcs.com/public/accounts/actcr");	  //If JohnDoe as user for /actcr /actdl or /actck
		 // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");	 //Use ip address if you observe timeout with apphonics.tcs.com //If Danny as user for /deposit /withdraw or /transfer
		  //urlapi = new URL("http://apphonics.tcs.com/public/accounts/deposit");	  //If Danny as user for /deposit /withdraw or /transfer	  
		// urlapi = new URL("https://apphonics.tcs.com/accounts/actdl");
		 apphconnection = (HttpURLConnection) urlapi.openConnection(); 
		  apphconnection.setDoInput(true);
		  apphconnection.setDoOutput(true);  
		  apphconnection.setConnectTimeout(5000);
		  apphconnection.setReadTimeout(5000);	  
		  apphconnection.setRequestMethod("POST");
		  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); //Access is token is valid till
	      apphconnection.setRequestProperty("Content-Type", "application/json");
	  	  apiwriter = new OutputStreamWriter(apphconnection.getOutputStream(), "UTF-8");
	     // properties.load(filereader); 
		 // CustID = properties.getProperty("CUSTID");	  
		 // apipayload = "{\"request\":{\"resource\":\"createaccount\", \"account\":{ \"cuid\":\""+CustID+"\", \"actype\": \""+acctype+"\",\"actbal\":\""+balance+"\",\"actcrdt\":\""+crdt+"\",\"actlasttrdt\":\""+ltdt+"\",\"actdurtn\":\""+dur+"\" }}}"; //If JohnDoe as user for /actcr /actdl or /actck
		  //apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"100000054\", \"custname\": \"Anudeep\", \"actype\": \"S\", \"acid\": \"111011026\", \"amount\": \"1000\", \"txndate\": \"2016-04-04\" } } }"; //If Danny as user for /deposit /withdraw or /transfer
		//apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"" + CustID + "\", \"custname\": \"ARIN\", \"actype\": \"C\", \"acid\": \"111011008\", \"amount\": \"100000\", \"txndate\": \"2016-06-03\" } } }"; //If Danny as user for /deposit /withdraw or /transfer	  
	  	apipayload = "{\"request\": {\"resource\": \"checkaccount\",\"account\": {\"cuid\":\""+custid+"\" }}}";
	  	  System.out.println("*** apipayload *** : " + apipayload);		  	  
		  apiwriter.write(apipayload);
		  apiwriter.close();  

	  }catch (ConnectException ce){
	    System.out.println("*** Exception: " + ce.toString());
	}

	  System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
	 
	  if (apphconnection.getResponseCode() == 200) {
			
		  System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			// Buffer the result into a string
			  BufferedReader rd = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
			  StringBuilder sb = new StringBuilder();
			  String line;

			  while ((line = rd.readLine()) != null) {
			    sb.append(line);
			  }
			  System.out.println("*** API Response *** " + sb.toString());
			  rd.close();
			  return sb.toString();
			//throw new IOException(apphconnection.getResponseMessage());
	  }

		  else if (apphconnection.getResponseCode() == 401) {
				System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				return null;
				//throw new IOException(apphconnection.getResponseMessage());
				 
		  }
		  else if (apphconnection.getResponseCode() == 500) {
			  return null;
			  //System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				//throw new IOException(apphconnection.getResponseMessage());
			 
		  }
		  else{
			  //int responsecode=apphconnection.getResponseCode();
			  System.out.println(" default error");
			  
		  }
	  
	 

	  apphconnection.disconnect();
	  apphconnection = null;
	  urlapi = null; 
	 return null;
	 }
}
