package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Dconnection{
	
String tokenapipayload = "";
String apipayload = "";
String access_token = "";
URL urltoken = null;
URL urlapi = null;
HttpURLConnection connection = null;
HttpURLConnection apphconnection = null;
OutputStreamWriter writer = null;
OutputStreamWriter apiwriter = null;  
// Properties properties = new Properties ();
//FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file
public  String jconnection() throws IOException
{
	String access_token=null;
	 String tokenapipayload = "";
	  String apipayload = "";
	  URL urltoken = null;
	  HttpURLConnection connection = null;
	  BufferedReader in=null;

	  try {  

		  urltoken = new URL("http://172.16.221.147/token/");
		  //urltoken = new URL("http://125.17.73.221/token/");
		  connection = (HttpURLConnection) urltoken.openConnection();
		  connection.setDoInput(true);
		  connection.setDoOutput(true);  
		  connection.setRequestMethod("POST");
		  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		  connection.setRequestProperty("Authorization", "Basic blc3bkdNU01wWWNKWVd6OTIzQXV1RWFBTzJVYTpDWE14MGw4SU56dlFKWVo1Zjg2MUFENF9GNzhh");
	  	  String urlParameter="grant_type=password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller";
	  	  connection.setConnectTimeout(180*60*1000);
	      DataOutputStream writer=new DataOutputStream(connection.getOutputStream());
		  writer.writeBytes(urlParameter);
		  writer.flush();
		  writer.close();
		  in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      String urlString="";
	      String current;
	      while((current=in.readLine())!=null)
	      {
	      urlString +=current;
	      }
	      
	      JSONObject jsonObject = new JSONObject(urlString);  
	      access_token=jsonObject.getString("access_token");
	      
	 
	      System.out.println("*** Response code: " + connection.getResponseCode() );

		  if (connection.getResponseCode() != 200) {
		    throw new IOException(connection.getResponseMessage());
		  }
	  
	  }catch(JSONException e){	
		  e.printStackTrace();
	 }
	  catch (ConnectException ce){
			ce.printStackTrace();
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}

	  connection.disconnect();
	  connection = null;
	  urltoken = null;
	System.out.println(access_token);
	return access_token;
}
public String dconnection() throws IOException
{
try {  

	  //urltoken = new URL("http://apphonics.tcs.com/token/");
	 //urltoken = new URL("http://125.17.73.221/token/");
	 urltoken = new URL("http://172.16.221.147/token/"); //Use ip address if you observe timeout exception with apphonics.tcs.com
	  connection = (HttpURLConnection) urltoken.openConnection();
	  connection.setDoInput(true);
	  connection.setDoOutput(true);  
	  connection.setConnectTimeout(5000);
	  connection.setReadTimeout(5000);
	  connection.setRequestMethod("POST");
	  connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	  connection.setRequestProperty("Authorization", "Basic blc3bkdNU01wWWNKWVd6OTIzQXV1RWFBTzJVYTpDWE14MGw4SU56dlFKWVo1Zjg2MUFENF9GNzhh");
	  writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
    //tokenapipayload = "grant_type=password&username=JohnDoe&password=tcsapp123&scope=Account_read+Manager+Teller"; //Use JohnDoe as username for /actcr /actdl /actck API resources
	  tokenapipayload = "grant_type=password&username=Danny&password=tcsapp123&scope=Account_read+Manager+Teller"; //Use Danny as username for /deposit /withdraw /transfer API resources
	  writer.write(tokenapipayload);
	  writer.close();

}catch (ConnectException ce){
  System.out.println("*** Exception: " + ce.toString() );
}

if (connection.getResponseCode() != 200) {
  throw new IOException(connection.getResponseMessage());
}

//get JsonObject from JSONTokener from InputStream
JSONObject jsonObject = new JSONObject(new JSONTokener(connection.getInputStream()));   
access_token = jsonObject.getString("access_token");   
connection.disconnect();
connection = null;
urltoken = null;
System.out.println(access_token);
return(access_token);
}
}