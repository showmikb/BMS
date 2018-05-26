package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bean.Transaction;
import com.dao.DaoTeller;
import com.service.Service;
import com.util.Dconnection;

/**
 * Servlet implementation class ControllerTeller
 */
@WebServlet("/ControllerTeller")
public class ControllerTeller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerTeller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		System.out.println("Page value : "+page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 //AccountBean ab=new AccountBean();
		String page = request.getParameter("page");
		if (page.equals("getaccountstatus")) 
	       {
	   
	   		String custid =request.getParameter("custid");
	   		Service serv = new Service();
			if(serv.getAccountStatus(custid)!=null)
			{
				String ast=serv.getAccountStatus(custid);
				request.setAttribute("json", ast);				
				RequestDispatcher rd=request.getRequestDispatcher("displayaccountstatus.jsp");
				  rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("500.html");
				  rd.forward(request, response);
			}
	     
	       }
		
		
		if(page.equals("getmini"))
		{
			
			String accid=request.getParameter("accid");
			String sDate=request.getParameter("sdate");
			String eDate=request.getParameter("edate");
			Service serv=new Service();
			  ArrayList<Transaction> arr=serv.miniStatement(accid, sDate, eDate);
			  if(arr!=null)
			  {
				  RequestDispatcher rd=request.getRequestDispatcher("ministmt.jsp");
					request.setAttribute("arrList",arr);	    
					rd.forward(request, response);
			  }
			  else
			  {
				  RequestDispatcher rd=request.getRequestDispatcher("500.html"); 
					rd.forward(request, response);
			  }
			
		}
		
		if (page.equals("getaccountsforcustomer")) 
	       {
	   
	   		String custid =request.getParameter("custid");
	   		Service serv = new Service();
			if(serv.getAccountsForCustomer(custid)!=null)
			{
				ArrayList<Long> accounts=serv.getAccountsForCustomer(custid);
				if(accounts.size()==1)
				request.setAttribute("act1",accounts.get(0));
				else if(accounts.size()==2)
				{
					request.setAttribute("act1",accounts.get(0));
					request.setAttribute("act2",accounts.get(1));
				}
				RequestDispatcher rd=request.getRequestDispatcher("AccountsDisplay.jsp");
				  rd.forward(request, response);
			}
	       
			
			else
			{
				System.out.println("redirected to getaccountsforcustomer");
				 RequestDispatcher rd=request.getRequestDispatcher("500.html");
				  rd.forward(request, response);
			}
	   	
	   	}	
		else if (page.equals("getlaststmt")) 
	       {
	   
	   		String acid =request.getParameter("acid");
	   		int numberofTrns =Integer.parseInt(request.getParameter("numberofTrns"));
	   		request.setAttribute("numberofTrns",numberofTrns);
	   		Service serv = new Service();
			if(serv.getStatementBytrns(acid,numberofTrns)!=null)
			{
				ArrayList<Transaction> trlist=serv.getStatementBytrns(acid,numberofTrns);
				/*for(Transaction t:trlist)
				{
					request.setAttribute("trtype",t.getOp_type());
					request.setAttribute("trdate",t.getTrans_date());
					request.setAttribute("tramt",t.getAmount());
					request.setAttribute("trid",t.getTrans_id());
				}*/
				request.setAttribute("trlist", trlist);
				
				
			
				RequestDispatcher rd=request.getRequestDispatcher("TransactionDisplay.jsp");
				  rd.forward(request, response);
			}
	       
			
			else
			{
				System.out.println("redirected to getaccountsforcustomer");
				 RequestDispatcher rd=request.getRequestDispatcher("getAccount.jsp");
				  rd.forward(request, response);
			}
	   	
	   	}	
		else if (page.equals("AccountDetails")) 
       {
   
    	   System.out.println("in details..");
   		String actid =request.getParameter("actid");
   		Service serv = new Service();
		String responsestring=serv.GetAccountDetails(actid);
		System.out.println(responsestring);
		if(responsestring!=null)
		{ 
		JSONObject j=new JSONObject(responsestring);
			  JSONObject j1=j.getJSONObject("Envelope");
			  JSONObject j2=j1.getJSONObject("Body");
			  JSONObject j3=j2.getJSONObject("TF000111OperationResponse");
			  String acid=(String)j3.get("ws_id");
			  String actype=(String)j3.get("ws_acct_type");
			  String acbal=(String)j3.get("ws_acct_balance");
			  String lasttrdate=(String)j3.get("ws_acct_lasttr_date");
			  out.println(acid);			  
			  out.println(actype);			  
			  out.println(acbal);
			  //System.out.println(request.getParameter("a"));
			  
			  request.setAttribute("acid",acid);
			  request.setAttribute("actype",actype);
			  request.setAttribute("acbal",acbal);
			  request.setAttribute("lasttrdate",lasttrdate);
			/*  String s = (String) request.getAttribute("json");
				request.setAttribute("json",s);
				*/
			  RequestDispatcher rd=request.getRequestDispatcher("AccDetailT.jsp");
			  rd.forward(request, response);
		}
		else
			
		{
			System.out.println("redirected to getaccountsforcustomer");
			 RequestDispatcher rd=request.getRequestDispatcher("getAccount.jsp");
			  rd.forward(request, response);
		}
       }
		
		else if (page.equals("customeraccountdetails")) 
	       {
	   
	    	   System.out.println("in details..");
	   		String accid =request.getParameter("accid");
	   		Service serv1 = new Service();
			String rs=serv1.GetAccountDetails(accid);
			System.out.println(rs);
			if(rs!=null){ 
			JSONObject j=new JSONObject(rs);
				  JSONObject j1=j.getJSONObject("Envelope");
				  JSONObject j2=j1.getJSONObject("Body");
				  JSONObject j3=j2.getJSONObject("TF000111OperationResponse");
				  String acid=(String)j3.get("ws_id");
				  String actype=(String)j3.get("ws_acct_type");
				  String acbal=(String)j3.get("ws_acct_balance");
				  String lasttrdate=(String)j3.get("ws_acct_lasttr_date");
				  System.out.println(acid);			  
				  System.out.println(actype);			  
				  System.out.println(acbal);
				  //System.out.println(request.getParameter("a"));
				  
				  request.setAttribute("acid",acid);
				  request.setAttribute("actype",actype);
				  request.setAttribute("acbal",acbal);
				  request.setAttribute("lasttrdate",lasttrdate);
				/*  String s = (String) request.getAttribute("json");
					request.setAttribute("json",s);
					*/
				  RequestDispatcher rd=request.getRequestDispatcher("displayaccdetails.jsp");
				  rd.forward(request, response);
			}
		
		else
		{
			System.out.println("redirected to getaccount");
			 RequestDispatcher rd=request.getRequestDispatcher("500.html");
			  rd.forward(request, response);
		}
   	
   	}
       else if(page.equals("deposite"))
		{
			String custid=request.getParameter("custid");
			String acid = request.getParameter("acid");
			String acctype = request.getParameter("actype");
			String accbl =request.getParameter("acbal");
			String lasttrdate = (request.getParameter("lasttrdate"));
			int depositamt =Integer.parseInt(request.getParameter("depositamt"));
			System.out.println(custid+" "+acid+" "+acctype);
			Service serv = new Service();
			if(serv.Deposite(custid,acid,acctype,lasttrdate,depositamt)==true)
			{
				int x=Integer.parseInt(accbl)+depositamt;
				request.setAttribute("acid", acid);
				request.setAttribute("custid", custid);
				request.setAttribute("accbl", accbl);
				request.setAttribute("x", x);
				RequestDispatcher rd=request.getRequestDispatcher("depositsuccessful.jsp");
				rd.forward(request, response);
				//out.println("Amount deposited successful");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("depositfailure.jsp");
				rd.forward(request, response);
				//out.println("try again");
			}
		}
		else if(page.equals("withdraw"))
		{
			String custid=request.getParameter("custid");
			String acid = request.getParameter("acid");
			String acctype = request.getParameter("actype");
			String accbl =request.getParameter("acbal");
			String ltdt=(request.getParameter("lasttrdate"));
			int withdrawamt  =Integer.parseInt (request.getParameter("withdrawamt"));
			System.out.println("in withdraw...");
			Service serv = new Service();
			if(serv.Withdraw(custid,acid, acctype,ltdt, withdrawamt)==true)
			{
				int x=Integer.parseInt(accbl)-withdrawamt;
				request.setAttribute("acid", acid);
				request.setAttribute("custid", custid);
				request.setAttribute("accbl", accbl);
				request.setAttribute("x", x);
				RequestDispatcher rd=request.getRequestDispatcher("withdrawsuccessful.jsp");
				rd.forward(request, response);
				//out.println("Amount withdrawn successful");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("withdrawfailure.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("create"))
		{
			String custid = request.getParameter("custid");
			String acctype = (request.getParameter("type"));
			int amount =Integer.parseInt( request.getParameter("amount"));
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			    Date date = new Date();
			   

			Service serv = new Service();
			if(serv.create(custid,acctype,amount,dateFormat.format(date),dateFormat.format(date),365))
			{
				RequestDispatcher rd=request.getRequestDispatcher("createsuccess.jsp");
				  rd.forward(request, response);
				//out.println("Account created successfully");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("createfailure.jsp");
				  rd.forward(request, response);
			}
		}
		else if(page.equals("delete"))
		{
			String custid = request.getParameter("custid");
			String accid = request.getParameter("accid");
			String acctype = (request.getParameter("acctype"));
			Service serv = new Service();
			if(serv.Del(custid,accid,acctype))
			{
				RequestDispatcher rd=request.getRequestDispatcher("deletesuccess.jsp");
				  rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("deletefailure.jsp");
				  rd.forward(request, response);
			}
		}
		
		else if(page.equals("transfer"))
		{
		String custid=request.getParameter("custid");
			String acid = request.getParameter("acid");
			String sacctype = request.getParameter("sactype");
			String tacctype = request.getParameter("tactype");
		
			String ltdt=(request.getParameter("lasttrdate"));
			int transferamt =Integer.parseInt(request.getParameter("transferamt"));
			Service serv = new Service();
			if(serv.Transfer( custid,acid, sacctype,tacctype, ltdt, transferamt)==true)
			{
				request.setAttribute("acid", acid);
				request.setAttribute("sactype", sacctype);
				request.setAttribute("tactype", tacctype);
				request.setAttribute("custid", custid);
				request.setAttribute("transferamt",transferamt);
			
				RequestDispatcher rd=request.getRequestDispatcher("transfersuccessful.jsp");
				rd.forward(request, response);
				
				//out.println("Amount transfer successful");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("transferfailure.jsp");
				rd.forward(request, response);
				//out.println("try again");
			}
		}
	}

}
