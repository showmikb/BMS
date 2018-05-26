package com.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.bean.Account;
import com.bean.Customer;
import com.bean.Transaction;
import com.controller.ControllerTeller;
import com.util.Dconnection;
public class DaoTeller 
{
	HttpServletResponse response;

	public static ArrayList<Long> getAccountsForCustomer(String custid) throws IOException
	{
		String access_token=null;
		HttpURLConnection apphconnection = null;
	    BufferedReader in=null;
		URL urlapi = null;
		String apipayload = "";
		Dconnection dc=new Dconnection();
		
		//getting access token
		access_token=dc.jconnection();
		System.out.println("access_token"+access_token);
		if(access_token!=null )
		{
			
			try {  
			
				  urlapi = new URL("http://172.16.221.147/public/customers/1.0/actst");
				 // urlapi = new URL("http://125.17.73.221/public/customers/1.0/actst");
				  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
				  apphconnection.setDoInput(true);
				  apphconnection.setDoOutput(true);  
				  apphconnection.setConnectTimeout(180*60*1000);  
				  apphconnection.setRequestMethod("POST");
				  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
			      apphconnection.setRequestProperty("Content-Type", "application/json");
				  apipayload = "{\"request\": {\"resource\": \"getaccounts\", \"customer\": { \"cuid\": \""+custid+"\" }}}"; 
				  System.out.println("*** apipayload *** : " + apipayload);		  	  
				  DataOutputStream wr=new DataOutputStream(apphconnection.getOutputStream());
				  wr.writeBytes(apipayload);	
				  wr.flush();
				  wr.close();
				 in = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
				 String current;
				 String urlResponse="";
				 while((current=in.readLine())!=null)
				 {
					 urlResponse +=current;
				 }

				 JSONObject obj=new JSONObject(urlResponse);
				
				 
				 System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
				  System.out.println("URL Response :"+urlResponse);

				  //returning json object
				  String result=(String)obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000121OperationResponse").get("ws_sta_flg");
					 if(result.equals("Y"))
					 {
						 ArrayList<Long> arr=new ArrayList<Long>();
						 
						 arr.add(Long.parseLong((String)obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000121OperationResponse").get("ws_acct_id1")));
						 arr.add(Long.parseLong((String)obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000121OperationResponse").get("ws_acct_id2")));
                          return arr;
					 }
					 else
					 {
						 return null;
					 }
				  
			  }catch(JSONException e){e.printStackTrace();}
			    catch (ConnectException ce)
			  	{
				  System.out.println("*** Exception: " + ce.toString());
				} catch (MalformedURLException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
					
					e.printStackTrace();
				}
			  								
			
		}
		return null;
	}
	public String GetAccountDetail(String actid) throws IOException{
	
		  String apipayload = "";
		  URL urlapi = null;
		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter apiwriter = null;  
		 // Properties properties = new Properties ();
		  //FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file
		  //String CustID = accid;
		  
		  Dconnection dc= new Dconnection();
		  String access_token=dc.dconnection();
		  String s = "";
		//String actype =request.getParameter("actype");
		//int amount=Integer.parseInt(request.getParameter("amount"));
		//String actcrdt = request.getParameter("actcrdt");
		//String actlasttrdt=actcrdt;
		//String cuaddress = request.getParameter("cuaddress");
		//int cuage = Integer.parseInt(request.getParameter("cuage"));
		
		   try {  

			  // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			 urlapi = new URL("http://172.16.221.147/public/accounts/actdt");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			  //urlapi = new URL("http://125.17.73.221/public/accounts/actdt");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
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
		  	  //properties.load(filereader);
		  	  //CustID="100000054";
			  //CustID = properties.getProperty("CUSTID");
		  	apipayload="{\"request\": {\"resource\": \"getaccountdetail\", \"account\": { \"actid\": "+actid+" }}}";
		  	  //apipayload="{\"request\": {\"resource\": \"createaccount\", \"account\": { \"cuid\":"+ cuid+", \"actype\": "+actype+",\"actcrdt\": "+actcrdt+",\"actlasttrdt\": "+actlasttrdt+"}}}";
		  	//apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"" +  CustID+ "\", \"custname\": \"Anudeep\", \"actype\": \"S\", \"acid\": \"111011026\", \"amount\": \"500000\", \"txndate\": \"2016-06-03\" } } }";
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

		 
		  if (apphconnection.getResponseCode() == 200) {
			System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			BufferedReader rdtoken = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
			  StringBuilder sbtoken = new StringBuilder();
			  String linetoken;

			  while ((linetoken = rdtoken.readLine()) != null) {
			    sbtoken.append(linetoken);
			   
			  }
			  
			s=sbtoken.toString();
		  }
		  return s;
		
	}

	public boolean Deposite(String custid,String acid,String actype,String lasttrdate,int depositeamt)throws IOException
	{

		  String apipayload = "";
		 
		  URL urltoken = null;
		  URL urlapi = null;
		  HttpURLConnection connection = null;
		  HttpURLConnection dconnection = null;

		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter writer = null;
		  OutputStreamWriter apiwriter = null;  
	Dconnection dc=new Dconnection();
	String access_token=dc.dconnection();
		   try {  

			  
			   
			  // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			  urlapi = new URL("http://172.16.221.147/public/accounts/deposit");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			// urlapi = new URL("http://125.17.73.221/public/accounts/deposit");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
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
		  	  //properties.load(filereader);
		  	  //String CustID="100000367";
			  //CustID = properties.getProperty("CUSTID");
		  	//apipayload="{\"request\": {\"resource\": \"getaccountdetail\", \"account\": { \"actid\": "+actid+" }}}";
		  	  //apipayload="{\"request\": {\"resource\": \"createaccount\", \"account\": { \"cuid\":"+ cuid+", \"actype\": "+actype+",\"actcrdt\": "+actcrdt+",\"actlasttrdt\": "+actlasttrdt+"}}}";
		  	apipayload = "{\"request\": {\"resource\":\"moneydeposit\", \"deposit\": { \"cuid\": \"" +custid+ "\", \"actype\":\""+actype+"\", \"acid\": \""+acid+"\", \"amount\": \""+depositeamt+"\", \"txndate\": \""+lasttrdate+"\" } } }";
			
			  System.out.println("*** apipayload *** : " + apipayload);		  	  
			  apiwriter.write(apipayload);
			  apiwriter.close();  

		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString());
		}

		 
		  if (apphconnection.getResponseCode() == 200) {
			System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			BufferedReader rdtoken = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
			  StringBuilder sbtoken = new StringBuilder();
			  String linetoken;

			  while ((linetoken = rdtoken.readLine()) != null) {
			    sbtoken.append(linetoken);
			   
			  }
			 System.out.println("*** API Response *** " + sbtoken.toString());
			//response.sendRedirect("success.jsp");
			//throw new IOException(apphconnection.getResponseMessage());
				//response.sendRedirect("depositsuccess.jsp");


		  return true;
		  }

		  else if (apphconnection.getResponseCode() == 401) {
				
			  System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			  return false;
			  //System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
				//throw new IOException(apphconnection.getResponseMessage());
			 
		  }
		  else if (apphconnection.getResponseCode() == 500) {
				
			  System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			  return false;
			  //System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
				//throw new IOException(apphconnection.getResponseMessage());
			 }
		  
			  //int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
			//  System.out.println(" default error");
		  

		  apphconnection.disconnect();
		  apphconnection = null;
		  urlapi = null; 
		  return false;
	}
	public boolean WithdrawAmount(String custid,String acid,String actype,String lasttrdate,int withdrawamt) throws IOException
	{
		 String apipayload = "";
		 
		  URL urltoken = null;
		  URL urlapi = null;
		  HttpURLConnection connection = null;
		  HttpURLConnection dconnection = null;

		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter writer = null;
		  OutputStreamWriter apiwriter = null;  
	Dconnection dc=new Dconnection();
	String access_token=dc.dconnection();
		 // Properties properties = new Properties ();
		  //FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file


		   try {  

			  // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			 urlapi = new URL("http://172.16.221.147/public/accounts/withdrw");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			  //urlapi = new URL("http://125.17.73.221/public/accounts/withdrw");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
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
		  	  //properties.load(filereader);
		  	 // String CustID="100000367";
			  //CustID = properties.getProperty("CUSTID");
		  	//apipayload="{\"request\": {\"resource\": \"getaccountdetail\", \"account\": { \"actid\": "+actid+" }}}";
		  	  //apipayload="{\"request\": {\"resource\": \"createaccount\", \"account\": { \"cuid\":"+ cuid+", \"actype\": "+actype+",\"actcrdt\": "+actcrdt+",\"actlasttrdt\": "+actlasttrdt+"}}}";
		  	apipayload = "{\"request\": {\"resource\":\"moneywithdraw\", \"withdraw\": { \"cuid\": \"" +  custid+ "\", \"actype\":\""+actype+"\", \"acid\": \""+acid+"\", \"amount\": \""+withdrawamt+"\", \"txndate\": \""+lasttrdate+"\" } } }";
			
			  System.out.println("*** apipayload *** : " + apipayload);		  	  
			  apiwriter.write(apipayload);
			  apiwriter.close();  
               
		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString());
		}

		 
		  if (apphconnection.getResponseCode() == 200) {
			System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			BufferedReader rdtoken = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
			  StringBuilder sbtoken = new StringBuilder();
			  String linetoken;

			  while ((linetoken = rdtoken.readLine()) != null) {
			    sbtoken.append(linetoken);
			   
			  }
			 System.out.println("*** API Response *** " + sbtoken.toString());
			//response.sendRedirect("success.jsp");
			//throw new IOException(apphconnection.getResponseMessage());
			 JSONObject obj=new JSONObject(sbtoken.toString());
			 JSONObject j=obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000081OperationResponse");
			//response.sendRedirect("depositsuccess.jsp");
			//throw new IOException(apphconnection.getResponseMessage());
			String status=j.getString("ws_sta_msg");
				
			 if(status=="Y")
			 return true;
			 else
				 return false;

		  }

		  else if (apphconnection.getResponseCode() == 401) {
				System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				return false;
				//response.sendRedirect("failure.jsp");
				//throw new IOException(apphconnection.getResponseMessage());
			 }
		  else if (apphconnection.getResponseCode() == 500) {
				System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				return false;
				//response.sendRedirect("failure.jsp");
				//throw new IOException(apphconnection.getResponseMessage());
			 }
		  else{
			  //int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
			  //System.out.println(" default error");
		  }

		  apphconnection.disconnect();
		  apphconnection = null;
		  urlapi = null; 	
		  return false;

	}
	
	public boolean Transfer(String custid,String acid,String sactype,String tactype,String lasttrdate,int transferamt) throws JSONException, IOException
	{
		 String tokenapipayload = "";
		  String apipayload = "";
		  String access_token = "";
		  URL urltoken = null;
		  URL urlapi = null;
		  HttpURLConnection dconnection = null;

		  HttpURLConnection connection = null;
		  HttpURLConnection apphconnection = null;
		  OutputStreamWriter writer = null;
		  OutputStreamWriter apiwriter = null;  
		  Dconnection dc=new Dconnection();
			 access_token=dc.dconnection();
		 // Properties properties = new Properties ();
		  //FileReader filereader = new FileReader("G:/javaprograms/AccountOfBMS/sandboxacctapi.properties"); //To read Cust ID from file
		 // String CustID = custid;

		   try {  

			  // urlapi = new URL("http://172.16.221.147/public/accounts/deposit");
			  //urlapi = new URL("http://apphonics.tcs.com/public/customers/crtcus");
			  //urlapi = new URL("http://172.16.221.147/public/customers/crtcus");	  
			 urlapi = new URL("http://172.16.221.147/public/accounts/transfer");	 //Use ip address if you observe timeout with apphonics.tcs.com 
			 // urlapi = new URL("http://125.17.73.221/public/accounts/transfer");	 //Use ip address if you observe timeout with apphonics.tcs.com 	  
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
		  	  //properties.load(filereader);
		  	  String CustID=custid;
			  //CustID = properties.getProperty("CUSTID");
		  	//apipayload="{\"request\": {\"resource\": \"getaccountdetail\", \"account\": { \"actid\": "+actid+" }}}";
		  	  //apipayload="{\"request\": {\"resource\": \"createaccount\", \"account\": { \"cuid\":"+ cuid+", \"actype\": "+actype+",\"actcrdt\": "+actcrdt+",\"actlasttrdt\": "+actlasttrdt+"}}}";
		  	apipayload = "{\"request\": {\"resource\":\"moneytransfer\", \"transfer\": { \"cuid\": \"" +  custid+ "\", \"srcactype\":\""+sactype+"\", \"trgactype\": \""+tactype+"\", \"amount\": \""+transferamt+"\", \"txndate\": \""+lasttrdate+"\" } } }";
			
			  System.out.println("*** apipayload *** : " + apipayload);		  	  
			  apiwriter.write(apipayload);
			  apiwriter.close();  
			  
		  }catch (ConnectException ce){
		    System.out.println("*** Exception: " + ce.toString());
		}

		 
		  if (apphconnection.getResponseCode() == 200) {
			System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
			System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
			BufferedReader rdtoken = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
			  StringBuilder sbtoken = new StringBuilder();
			  String linetoken;

			  while ((linetoken = rdtoken.readLine()) != null) {
			    sbtoken.append(linetoken);
			   
			  }
			 System.out.println("*** API Response *** " + sbtoken.toString());
			 JSONObject obj=new JSONObject(sbtoken.toString());
			 JSONObject j=obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000091OperationResponse");
			//response.sendRedirect("depositsuccess.jsp");
			//throw new IOException(apphconnection.getResponseMessage());
			String status=j.getString("ws_sta_msg");
				
			 if(status=="Y")
			 return true;
			 else
				 return false;
		  }

		  else if (apphconnection.getResponseCode() == 401) {
				System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				return false;
				//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
				//throw new IOException(apphconnection.getResponseMessage());
			 }
		  else if (apphconnection.getResponseCode() == 500) {
				System.out.println("*** API Response Code = " + apphconnection.getResponseCode() );
				return false;
				//System.out.println("*** API Response Message = " + apphconnection.getResponseMessage() );
				//int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
				//throw new IOException(apphconnection.getResponseMessage());
			 }
		  else{
			  //int responsecode=apphconnection.getResponseCode();
				//response.sendRedirect("failure.jsp");
			  System.out.println(" default error");
		  }

		  apphconnection.disconnect();
		  apphconnection = null;
		  urlapi = null; 
		  return false;
	}
	public ArrayList<Transaction> getStatementBytrans(String acid,int numberOfTrns) throws IOException
	{
		
		ArrayList<Transaction> tlist=new ArrayList<>();
		
		String access_token=null;
		HttpURLConnection apphconnection = null;
	    BufferedReader in=null;
		URL urlapi = null;
		String apipayload = "";
		Dconnection dc=new Dconnection();
		//getting access token
		access_token=dc.dconnection();
		System.out.println("access_token"+access_token);
		if(access_token!=null )
		{				
			try {  
				//urlapi = new URL("http://125.17.73.221/public/accounts/1.0/laststmt");
				  urlapi = new URL("http://172.16.221.147/public/accounts/1.0/laststmt");
				  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
				  apphconnection.setDoInput(true);
				  apphconnection.setDoOutput(true);  
				  apphconnection.setConnectTimeout(180*60*1000);  
				  apphconnection.setRequestMethod("POST");
				  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
			      apphconnection.setRequestProperty("Content-Type", "application/json");
				  apipayload = "{\"request\": {\"resource\": \"lastnstatement\", \"account\": { \"actid\": \""+acid+"\",\"limit\":\""+numberOfTrns+"\" }}}"; 
				  System.out.println("*** apipayload *** : " + apipayload);		  	  
				  DataOutputStream wr=new DataOutputStream(apphconnection.getOutputStream());
				  wr.writeBytes(apipayload);
				  wr.flush();
				  wr.close();
				 in = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
				 String current;
				 String urlResponse="";
				 while((current=in.readLine())!=null)
				 {
					 urlResponse +=current;
				 }

				 JSONObject obj=new JSONObject(urlResponse);
				
				 
				 System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
				  System.out.println("URL Response :"+urlResponse);

				  //returning json object
				  String result=(String)obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000131OperationResponse").get("ws_sta_flg");
					JSONObject j=obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000131OperationResponse");
					 
				  if(result.equals("Y"))
					 {
						
						 System.out.println(j.get("ws_sta_msg"));
						for(int i=0;i<numberOfTrns;i++)
						{
							Transaction temp=new Transaction();
							JSONObject out=j.getJSONObject("ws_out0"+(i+1));
							temp.setAmount(out.getString("ws_amount"));
							temp.setOp_type(out.getString("ws_op_type"));
							temp.setTrans_date(out.getString("ws_trans_date"));
							temp.setTrans_id(out.getString("ws_trans_id"));
							tlist.add(temp);
						}
							 
						return tlist;
						 
					 }
				  return null;
				  
				  
			  }catch(JSONException e){e.printStackTrace();}
			    catch (ConnectException ce)
			  	{
				  System.out.println("*** Exception: " + ce.toString());
				} catch (MalformedURLException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
					
					e.printStackTrace();
				}
			  								
			
		}
		return tlist;
	}
	public ArrayList<Transaction> miniStatement(String accid, String sDate,
			String eDate) throws IOException {
ArrayList<Transaction> tlist=new ArrayList<>();
		
		String access_token=null;
		HttpURLConnection apphconnection = null;
	    BufferedReader in=null;
		URL urlapi = null;
		String apipayload = "";
		Dconnection dc=new Dconnection();
		//getting access token
		access_token=dc.dconnection();
		System.out.println("access_token"+access_token);
		if(access_token!=null )
		{				
			try {  
				//urlapi = new URL("http://125.17.73.221/public/accounts/1.0/laststmt");
				  urlapi = new URL("http://172.16.221.147/public/accounts/1.0/ministmt");
				  apphconnection = (HttpURLConnection) urlapi.openConnection(); 
				  apphconnection.setDoInput(true);
				  apphconnection.setDoOutput(true);  
				  apphconnection.setConnectTimeout(180*60*1000);  
				  apphconnection.setRequestMethod("POST");
				  apphconnection.setRequestProperty("Authorization", "Bearer " + access_token); 
			      apphconnection.setRequestProperty("Content-Type", "application/json");

				  apipayload = "{\"request\": {\"resource\": \"accountstatement\", \"account\": { \"actid\": \""+accid+"\",\"stdt\":\""+sDate+"\",\"endt\":\""+eDate+"\" }}}"; 
				  System.out.println("*** apipayload *** : " + apipayload);		  	  
				  DataOutputStream wr=new DataOutputStream(apphconnection.getOutputStream());
				  wr.writeBytes(apipayload);
				  wr.flush();
				  wr.close();
				 in = new BufferedReader(new InputStreamReader(apphconnection.getInputStream()));
				 String current;
				 String urlResponse="";
				 while((current=in.readLine())!=null)
				 {
					 urlResponse +=current;
				 }

				 JSONObject obj=new JSONObject(urlResponse);
				
				 
				 System.out.println("*** API Response Code = " + apphconnection.getResponseCode());
				  System.out.println("URL Response :"+urlResponse);

				  //returning json object
				  String result=(String)obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000141OperationResponse").get("ws_sta_flg");
					JSONObject j=obj.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("TF000141OperationResponse");
					 
				  if(result.equals("Y"))
					 {
						
						 System.out.println(j.get("ws_sta_msg"));
						 
						for(int i=0;i<60;i++)
						{
							
							Transaction temp=new Transaction();
							JSONObject out=null;
							if(i<9)
					    	 {
					    		 out=j.getJSONObject("ws_out0"+(i+1));
					    	 }
					    	 else
					    	 {
					    		 out=j.getJSONObject("ws_out"+(i+1));
					    	 }
							temp.setAmount(out.getString("ws_amount"));
							temp.setOp_type(out.getString("ws_op_type"));
							temp.setTrans_date(out.getString("ws_trans_date"));
							temp.setTrans_id(out.getString("ws_trans_id"));
							tlist.add(temp);
					    	 
						}
						
						return tlist;
						 
					 }
				  return null;
				  
				  
			  }catch(JSONException e){e.printStackTrace();}
			    catch (ConnectException ce)
			  	{
				  System.out.println("*** Exception: " + ce.toString());
				} catch (MalformedURLException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
					
					e.printStackTrace();
				}
			  								
			
		}
		return tlist;
	}
	
	
}

